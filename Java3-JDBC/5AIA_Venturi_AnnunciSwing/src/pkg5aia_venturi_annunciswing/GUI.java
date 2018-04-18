package pkg5aia_venturi_annunciswing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.event.*;

public class GUI extends JFrame implements ActionListener 
{
    //Variabili utilizzate
    private static final int PANELS = 4; //senza considerare il Panel del menú
    Connection connessione = null;
    
    String query;
    Statement statement;
    PreparedStatement statementPr;
    ResultSet resultSet;
    int[] cod_selected_text;
    String valori[] = new String[5];
    String[] columnNamesRel = {"Cognome", "Nome", "Ruolo"};
    String[] columnNamesAnn = {"Data", "Titolo", "Relatore"};
    int numRows, cnt, ind_row;
    int dialogBtns = JOptionPane.YES_NO_OPTION, dialogInfo = JOptionPane.INFORMATION_MESSAGE;
    
/**********************************************/
/*      DICHIARAZIONE COMPONENTI GRAFICHE     */
/**********************************************/
    //PANELs
    private JPanel pMenu = new JPanel();
    private JPanel[] p = new JPanel[PANELS];
    {
       for (int i=0; i < p.length ; i++) {
           p[i] = new JPanel();
        } 
    }
    //MENU
    private JButton btnInsRel = new JButton("Inserisci RELATORE");
    private JButton btnInsAnn = new JButton("Inserisci ANNUNCIO");    
    private JButton btnShRel = new JButton("Mostra RELATORI");
    private JButton btnShAnn = new JButton("Mostra|Cancella ANNUNCI");
    
    //Comuni
    private JButton btnBack = new JButton("BACK");
    
    //1]InsRel
    private JLabel lblNomeRel = new JLabel("NOME Relatore");
    private JLabel lblCogRel = new JLabel("COGNOME Relatore");
    private JLabel lblRuoloRel = new JLabel("Ruolo Relatore");
    private JTextField txtNomeRel = new JTextField();
    private JTextField txtCogRel = new JTextField();
    
    final DefaultComboBoxModel modelRuolo = new DefaultComboBoxModel();
    JComboBox cbRuolo = new JComboBox(modelRuolo);
    
    private JButton btnIns1 = new JButton("INSERISCI");
    private JButton btnReset1 = new JButton("C");
            
    //2]InsAnn
    private JLabel lblTit = new JLabel("Titolo Annuncio");
    private JLabel lblData = new JLabel("Data (yyyy-mm-dd)");
    private JLabel lblTipo = new JLabel("Tipo Annuncio");
    private JLabel lblFkRel = new JLabel("Relatore");
    private JLabel lblTesto = new JLabel("Testo");
    private JTextField txtTitolo = new JTextField();
    private JTextField txtData = new JTextField();
    private JTextArea txtTesto = new JTextArea();
    JScrollPane scrollPaneTesto = new JScrollPane(txtTesto); 
    
    final DefaultComboBoxModel modelTipo = new DefaultComboBoxModel();
    JComboBox cbTipo = new JComboBox(modelTipo);
    final DefaultComboBoxModel modelRel = new DefaultComboBoxModel();
    JComboBox cbRel = new JComboBox(modelRel);
    
    private JButton btnIns2 = new JButton("INSERISCI");
    private JButton btnReset2 = new JButton("C");
    
    //3]ShRel
    private JTable tableRel;
    JScrollPane scrollPaneTabRel;
    String[][] dataRel;
    
    //4]ShAnn
    private JTable tableAnn;
    JScrollPane scrollPaneTabAnn;
    String[][] dataAnn;
    JComboBox cbTipoAnn = new JComboBox(modelTipo);
    JLabel lblTipoAnn = new JLabel("Tipo Annuncio");
    JLabel lblTestoAnn = new JLabel();
    JTextArea txtTestoAnn = new JTextArea();
    JScrollPane scrollPaneTestoAnn = new JScrollPane(txtTestoAnn); 
    JButton btnEli = new JButton ("ELIMINA");
    
/**********************************************/
/*                   COSTRUTTORE              */
/**********************************************/
    public GUI (int width, int height) {
        super ("Annunci Swing - VENTURI");
        super.setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null); //layout assoluto e manuale
        
    /**********************************************/
    /*          CONNESSIONE DB                    */
    /**********************************************/
        String DRIVER = "com.mysql.jdbc.Driver";
        try {
            Class.forName(DRIVER);
            System.out.println("Driver TROVATO");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver NON trovato");
            System.exit(1);
        }

        String url_db = "jdbc:mysql://localhost:3306/5aia_venturi_annunciswing";
        System.out.println("Connessione al db: " + url_db);

        try {
            connessione = DriverManager.getConnection(url_db, "root", "");
            System.out.println("Connessione avvenuta");

        } catch (Exception e) {
            System.out.println("Errore durante la connessione: " + e);
        }
        
    /**********************************************/
    /*          CONFIGURAZIONE PANELs             */
    /**********************************************/
        {
           for (int i=0; i < p.length ; i++) {
               p[i].setLayout(null);
               p[i].setBounds(0, 0, width, height);
               p[i].setFont(new Font("Arial", 0, 22));
            } 
        }
        pMenu.setLayout(null);
        pMenu.setFont(new Font("Century Gothic", 0, 22));
        pMenu.setBounds(0, 0, width, height);
        pMenu.setBorder(BorderFactory.createTitledBorder("Annunci Swing"));
        p[0].setBorder(BorderFactory.createTitledBorder("Inserisci RELATORE"));
        p[1].setBorder(BorderFactory.createTitledBorder("Inserisci ANNUNCIO"));
        p[2].setBorder(BorderFactory.createTitledBorder("Mostra RELATORI"));
        p[3].setBorder(BorderFactory.createTitledBorder("Mostra | Cancella ANNUNCI"));
        
    /**********************************************/
    /*          CONFIGURAZIONE COMPONENTI         */
    /**********************************************/
        //MENU
        btnInsRel.setFont(null);
        btnInsAnn.setFont(null);
        btnShRel.setFont(null);
        btnShAnn.setFont(null);
        
        btnInsRel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnInsAnn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnShRel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnShAnn.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        btnInsRel.setBounds(175,100,350,40); //x, y , largh e altezza
        btnInsAnn.setBounds(175,230,350,40);
        btnShRel.setBounds(175,360,350,40);
        btnShAnn.setBounds(175,490,350,40);
        
        //Comuni
        btnBack.setFont(null);
        
        btnBack.setBackground(Color.red);
        
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnBack.setBounds(20,560,100,50);
        
        //1] InsRel
        lblNomeRel.setFont(null);
        lblCogRel.setFont(null);
        lblRuoloRel.setFont(null);
        txtNomeRel.setFont(null);
        txtCogRel.setFont(null);
        cbRuolo.setFont(null);
        btnIns1.setFont(null);
        btnReset1.setFont(null);
        
        btnIns1.setBackground(Color.GREEN);
        btnIns1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReset1.setBackground(Color.RED);
        btnReset1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnReset1.setToolTipText("RESET dei campi");
        
        btnReset1.setBounds(600,10,50,50);        
        lblNomeRel.setBounds(50,40,250,30);
        lblCogRel.setBounds(50,140,250,30);
        lblRuoloRel.setBounds(50,240,250,30);
        txtNomeRel.setBounds(330,40,200,30);
        txtCogRel.setBounds(330,140,200,30);
        cbRuolo.setBounds(330,240,200,30);
        btnIns1.setBounds(420,560,200,50);
        
        //2] InsAnn
        lblTit.setFont(null);
        lblData.setFont(null);
        lblTipo.setFont(null);
        lblFkRel.setFont(null);
        lblTesto.setFont(null);
        txtTitolo.setFont(null);
        txtData.setFont(null);
        cbTipo.setFont(null);
        cbRel.setFont(null);
        btnIns2.setFont(null);
        btnReset2.setFont(null);
        
        btnIns2.setBackground(Color.GREEN);
        btnIns2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReset2.setBackground(Color.RED);
        btnReset2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnReset2.setToolTipText("RESET dei campi");
        
        btnReset2.setBounds(600,10,50,50);  
                
        lblTit.setBounds(50,40,200,30); //x, y, largh, alt
        lblData.setBounds(50,100,200,30);
        lblTipo.setBounds(50,160,200,30);
        lblFkRel.setBounds(50,220,200,30);
        lblTesto.setBounds(50,280,200,20);
        txtTitolo.setBounds(330,40,200,30);
        txtData.setBounds(330,100,200,30);
        cbTipo.setBounds(330,160,200,30);
        cbRel.setBounds(330,220,200,30);
        //txtTesto.setBounds(160,280,460,240); non necessario
        scrollPaneTesto.setBounds(20,305,420,240);
        btnIns2.setBounds(420,560,200,50);
        
        //3]ShRel
        
        //4]ShAnn
        lblTipoAnn.setFont(null);
        cbTipoAnn.setFont(null);
        lblTestoAnn.setFont(new Font("Arial", 0, 20));
        btnEli.setFont(null);
        
        btnEli.setForeground(Color.red);
        btnEli.setBackground(Color.WHITE);
        
        btnEli.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        txtTestoAnn.setEditable(false);
        
        lblTestoAnn.setVisible(false);
        scrollPaneTestoAnn.setVisible(false);
        btnEli.setVisible(false);
        
        lblTipoAnn.setBounds(50,20,250,30);
        cbTipoAnn.setBounds(280,20,300,30);
        lblTestoAnn.setBounds(20,315,600,20);
        scrollPaneTestoAnn.setBounds(20,340,420,200);
        btnEli.setBounds(300,560,200,50);   //x,y,largh,alt        
        
    /**********************************************/
    /*      AGGIUNTA COMPONENTI NEI PANEL         */
    /**********************************************/
        //MENU
        pMenu.add(btnInsRel);  
        pMenu.add(btnInsAnn);
        pMenu.add(btnShRel);
        pMenu.add(btnShAnn);
        
        //NOTA, i componenti possono stare solo in un Panel, quelli comuni vanno aggiunti al content pane
        
        //1] InsRel
        p[0].add(lblNomeRel);
        p[0].add(lblCogRel);
        p[0].add(lblRuoloRel);
        p[0].add(txtNomeRel);
        p[0].add(txtCogRel);
        p[0].add(cbRuolo);
        p[0].add(btnIns1);
        p[0].add(btnReset1);
        
        //2] InsAnn
        p[1].add(lblTit);
        p[1].add(lblData);
        p[1].add(lblTipo);
        p[1].add(lblFkRel);
        p[1].add(lblTesto);
        p[1].add(txtTitolo);
        p[1].add(txtData);
        p[1].add(cbTipo);
        p[1].add(cbRel);
        p[1].add(scrollPaneTesto);
        p[1].add(btnIns2);
        p[1].add(btnReset2);
        
        //3]ShRel
        
        //4]ShAnn
        p[3].add(lblTipoAnn);
        p[3].add(cbTipoAnn);
        p[3].add(lblTestoAnn);
        p[3].add(scrollPaneTestoAnn);
        p[3].add(btnEli);
        
        setContentPane(pMenu); //pannello che viene utilizzato
        
    /**********************************************/
    /*              GESTORI EVENTI                */
    /**********************************************/
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
    
        btnInsRel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnInsRelActionPerformed(evt);
            }
        });
        
        btnInsAnn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnInsAnnActionPerformed(evt);
            }
        });
        
        btnShRel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnShRelActionPerformed(evt);
            }
        });
        
        btnShAnn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnShAnnActionPerformed(evt);
                if (cnt != 0)
                    cbTipoAnnActionPerformed(evt);
            }
        });
        
        btnIns1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnIns1ActionPerformed(evt);
            }
        });
        
        btnIns2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnIns2ActionPerformed(evt);
            }
        });
        
        btnReset1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnReset1ActionPerformed(evt);
            }
        });
        
        btnReset2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnReset2ActionPerformed(evt);
            }
        });
        
        cbTipoAnn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cbTipoAnnActionPerformed(evt);
            }
        });
        
        btnEli.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnEliActionPerformed(evt);
            }
        });
        
        setVisible(true); //rende visibile il Frame
    }
/**********************************************/
/*                   EVENTI                   */
/**********************************************/
    private void btnBackActionPerformed(ActionEvent evt){
        setContentPane(pMenu);
        cleanVar();
        revalidate();
    }
    
    private void btnInsRelActionPerformed(ActionEvent evt){
        modelRuolo.addElement("");
        query = "SELECT ruolo.Ruolo FROM ruolo ORDER BY Ruolo";
        try {
            statement = connessione.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                modelRuolo.addElement(resultSet.getString(1));
            }
        } catch (Exception e) {  }
        
        setContentPane(p[0]);
        getContentPane().add(btnBack);
        revalidate();
    }
    
    private void btnIns1ActionPerformed(ActionEvent evt){
        valori = svuotaArr(valori);
        valori[0] = capitalize1(txtNomeRel.getText());
        valori[1] = capitalize1(txtCogRel.getText());
        valori[2] = (String)cbRuolo.getItemAt(cbRuolo.getSelectedIndex());
        //Controllo pienezza Campi
        if (valori[0].equals("") || valori[1].equals("") || valori[2].equals(""))
        {
            inserted(false);
        } else {
            cod_selected_text = new int[1];
            query = "SELECT CodRuolo FROM ruolo WHERE Ruolo = '" + valori[2] + "'";
            try {
                statement = connessione.createStatement();
                resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    cod_selected_text[0] = Integer.parseInt(resultSet.getString(1));
                }
            } catch (Exception e) {  }

            query = "INSERT INTO relatore (nome, cognome, FkRuolo) VALUES (?, ?, ?)";
            try {
                statementPr = connessione.prepareStatement(query);
                statementPr.setString(1, txtNomeRel.getText());
                statementPr.setString(2, txtCogRel.getText());
                statementPr.setInt(3, cod_selected_text[0]);
                
                int rowsInserted = statementPr.executeUpdate();
                if (rowsInserted > 0) {
                    inserted(true);
                    svuota1();
                } else {
                    inserted(false);
                }
            } catch (Exception e) { 
                inserted(false);
            }
        }
    }
    
    private void btnInsAnnActionPerformed(ActionEvent evt){
        getModelTipoAnn();
        
        modelRel.addElement("");
        query = "SELECT cognome, nome FROM relatore ORDER BY cognome";
        try {
            statement = connessione.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                modelRel.addElement(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        } catch (Exception e) {  }
        
        setContentPane(p[1]);
        getContentPane().add(btnBack);
        revalidate();
    }
    
    private void btnIns2ActionPerformed(ActionEvent evt){
        valori = svuotaArr(valori);
        valori[0] = capitalize1(txtTitolo.getText());
        valori[1] = txtData.getText();
        valori[2] = capitalize1(txtTesto.getText());
        valori[3] = (String)cbTipo.getItemAt(cbTipo.getSelectedIndex());
        valori[4] = (String)cbRel.getItemAt(cbRel.getSelectedIndex());
        if (valori[0].equals("") || valori[1].equals("") || valori[2].equals("") || valori[3].equals("") || valori[4].equals("") )
        {
            inserted(false);
        } else {
            cod_selected_text = new int[2];
            
            query = "SELECT CodTipoA FROM tipo_annuncio WHERE Tipo = '" + valori[3] + "'";
            try {
                statement = connessione.createStatement();
                resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    cod_selected_text[0] = Integer.parseInt(resultSet.getString(1));
                }
            } catch (Exception e) {  }
            
            query = "SELECT CodRelatore FROM relatore WHERE "
                    + "CONCAT(relatore.cognome, ' ', relatore.nome) = '" + valori[4] + "'";
            try {
                statement = connessione.createStatement();
                resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    cod_selected_text[1] = Integer.parseInt(resultSet.getString(1));
                }
            } catch (Exception e) {  }

            query = "INSERT INTO annuncio (titolo, data, testo, FkTipoA, FkRelatore)"
                    + " VALUES (?, ?, ?, ?, ?)";
            try {
                statementPr = connessione.prepareStatement(query);
                statementPr.setString(1, valori[0]);
                statementPr.setString(2, valori[1]);
                statementPr.setString(3, valori[2]);
                statementPr.setInt(4, cod_selected_text[0]);
                statementPr.setInt(5, cod_selected_text[1]);

                int rowsInserted = statementPr.executeUpdate();
                if (rowsInserted > 0) {
                    inserted(true);
                    svuota2();
                } else {
                    inserted(false);
                }
            } catch (Exception e) { 
                inserted(false);
            }
        }
    }
    
    private void btnShRelActionPerformed(ActionEvent evt){
        query = "SELECT COUNT(*) FROM relatore";
        try {
            statement = connessione.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                numRows = Integer.parseInt(resultSet.getString(1));
            }
        } catch (Exception e) {  }
        
        dataRel = new String[numRows][3];
        
        query = "SELECT r.Cognome, r.Nome, ruolo.Ruolo FROM relatore as r, ruolo"
                + " WHERE r.FkRuolo = ruolo.CodRuolo ORDER BY r.Cognome";
        try {
            statement = connessione.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                dataRel[cnt][0] = (resultSet.getString(1));
                dataRel[cnt][1] = (resultSet.getString(2));
                dataRel[cnt][2] = (resultSet.getString(3));
                cnt++;
            }
        } catch (Exception e) {  }
        
        tableRel = new JTable(dataRel, columnNamesRel);
        tableRel.setAutoCreateRowSorter(true); //Permette il sort dall'header
        scrollPaneTabRel = new JScrollPane(tableRel); //metto la tabella nello scrollpane, mi gestisce la visual
        //tableRel.setFillsViewportHeight(true);//la tabella riempie l'altezza
        scrollPaneTabRel.setBounds(20,20,640,520);
        p[2].add(scrollPaneTabRel);
        
        setContentPane(p[2]);
        getContentPane().add(btnBack);
        revalidate();
    }
    
    private void btnShAnnActionPerformed(ActionEvent evt){
        getModelTipoAnn();
        cbTipoAnn.setSelectedIndex(0);
        
        setContentPane(p[3]);
        getContentPane().add(btnBack);
        revalidate();
    }
    
    private void cbTipoAnnActionPerformed(ActionEvent evt){
        scrollPaneTestoAnn.setVisible(false);
        btnEli.setVisible(false);
        valori = svuotaArr(valori);
        if (cbTipoAnn.getItemAt(cbTipoAnn.getSelectedIndex()) != null)
            valori[0] = (String)cbTipoAnn.getItemAt(cbTipoAnn.getSelectedIndex());
        
        if (!valori[0].equals("")) {
            if (cnt != 0) {
                p[3].remove(scrollPaneTabAnn);
                cnt = 0;
            }
            numRows = 0;
            
            cod_selected_text = new int[1];

            query = "SELECT CodTipoA FROM tipo_annuncio WHERE Tipo = '" + valori[0] + "'";
            try {
                statement = connessione.createStatement();
                resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    cod_selected_text[0] = Integer.parseInt(resultSet.getString(1));
                }
            } catch (Exception e) {  }

            query = "SELECT COUNT(*) FROM annuncio WHERE annuncio.FkTipoA = " + cod_selected_text[0];
            try {
                statement = connessione.createStatement();
                resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    numRows = Integer.parseInt(resultSet.getString(1));
                }
            } catch (Exception e) {  }

            dataAnn = new String[numRows][3];
            cnt = 0;

            query = "SELECT a.Data, a.Titolo, re.Cognome, re.Nome, a.CodAnnuncio " +
                "FROM relatore as re, annuncio as a " +
                "WHERE a.FkRelatore = re.CodRelatore AND a.FkTipoA = " + cod_selected_text[0] + 
                " ORDER BY a.Data";
            cod_selected_text = new int[numRows];
            try {
                statement = connessione.createStatement();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    dataAnn[cnt][0] = (resultSet.getString(1));
                    dataAnn[cnt][1] = (resultSet.getString(2));
                    dataAnn[cnt][2] = (resultSet.getString(3) + " " + resultSet.getString(4));
                    cod_selected_text[cnt] = Integer.parseInt(resultSet.getString(5));
                    cnt++;
                }
            } catch (Exception e) {  }

            if (cnt != 0) {
                tableAnn = new JTable(dataAnn, columnNamesAnn);
                tableAnn.setAutoCreateRowSorter(true); //Permette il sort dall'header
                scrollPaneTabAnn = new JScrollPane(tableAnn);
                scrollPaneTabAnn.setBounds(20,70,640,240);
                p[3].add(scrollPaneTabAnn);
                
                lblTestoAnn.setText("Seleziona un articolo per ELIMINARLO o per visualizzarlo");
                if (!lblTestoAnn.isVisible())
                    lblTestoAnn.setVisible(true);
                p[3].repaint();
                
                tableAnn.setSelectionMode(SINGLE_SELECTION);
                
                //Listener sul click di una riga o sulla scelta tramite frecce
                tableAnn.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {
                    ind_row = tableAnn.getSelectedRow();
                    if (ind_row != -1) {
                        query = "SELECT annuncio.Testo FROM annuncio WHERE annuncio.CodAnnuncio = " + cod_selected_text[ind_row];
                        try {
                            statement = connessione.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                txtTestoAnn.setText(resultSet.getString(1));
                            }
                        } catch (Exception e) {  }
                        lblTestoAnn.setText("Testo");
                        scrollPaneTestoAnn.setVisible(true);
                        btnEli.setVisible(true);
                    }      
                }
            });
            } else { //se la tabella risultante é vuota
                lblTestoAnn.setVisible(false);
            }
        } else {
            lblTestoAnn.setVisible(false);
            scrollPaneTestoAnn.setVisible(false);
            if (cnt != 0) {
                p[3].remove(scrollPaneTabAnn);
                scrollPaneTabAnn = null;
                tableAnn = null;
                cnt = 0;
            }
        }
        repaint();
    }
    
    private void btnEliActionPerformed(ActionEvent evt){
        valori[0] = "Eliminare l'annuncio '" + (String)tableAnn.getValueAt(tableAnn.getSelectedRow(), 1) + "' ?";//Messaggio messageBox
        valori[1] = "ELIMINAZIONE annuncio";//Titolo messageBox
        if(dialogBox(valori[0], valori[1]) == 0) {
            //Se SI
            query = "DELETE annuncio.* FROM annuncio WHERE annuncio.CodAnnuncio = ?";
            try {
                statementPr = connessione.prepareStatement(query);
                statementPr.setInt(1, cod_selected_text[ind_row]);

                int rowsUpdated = statementPr.executeUpdate();
                if (rowsUpdated > 0) {
                    infoBox("Eliminazione effettuata con successo", "ELIMINAZIONE COMPLETATA");
                    cbTipoAnnActionPerformed(evt);
                } else {
                    infoBox("Eliminazione non riuscita", "ELIMINAZIONE FALLITA");
                }
            } catch (Exception e) {  }
        } else {
            //se NO
        }
    }
    
    private void btnReset1ActionPerformed(ActionEvent evt){
        svuota1();
    }
    
    private void btnReset2ActionPerformed(ActionEvent evt){
        svuota2();
    }
   
    public static void main(String[] args) {
        GUI annunci_GUI = new GUI(700, 700);
    }
    
    private void inserted(boolean flag) {
        if (flag){
            infoBox("Record INSERITO correttamente", "INSERIMENTO COMPLETATO");
        } else {
            infoBox("ERRORE: Record NON INSERITO", "INSERIMENTO FALLITO");
        }         
    }
    
    private void getModelTipoAnn() {
        modelTipo.addElement("");
        query = "SELECT Tipo FROM tipo_annuncio ORDER BY Tipo";
        try {
            statement = connessione.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                modelTipo.addElement(resultSet.getString(1));
            }
        } catch (Exception e) {  }
    }
    
    private int[] svuotaArr (int vet[]) {
        for (int i = 0; i< vet.length; i++) {
            vet[i] = 0;
        }
        return vet;
    }
    
    private String[] svuotaArr (String vet[]) {
        for (int i = 0; i < vet.length; i++) {
            vet[i] = "";
        }
        return vet;
    }
    
    private String[][] svuotaArr (String vet[][], int nCols, int nRows) {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                vet[i][j] = "";
            }
        }
        return vet;
    }
    
    public String capitalize1(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }
    
    private void cleanVar() {
        modelRel.removeAllElements();
        modelRuolo.removeAllElements();
        modelTipo.removeAllElements();
        query = "";
        valori = svuotaArr(valori);
        statement = null;
        statementPr = null;
        resultSet = null;
        numRows = cnt = ind_row = 0;
        cod_selected_text = null;
        dataAnn = dataRel = null;
    }
    
    private void svuota1() {
        txtNomeRel.setText("");
        txtCogRel.setText("");
        cbRuolo.setSelectedIndex(0);
        revalidate();
    }
    
    private void svuota2() {
        txtTitolo.setText("");
        txtData.setText("");
        txtTesto.setText("");
        cbTipo.setSelectedIndex(0);
        cbRel.setSelectedIndex(0);
        revalidate();
    }
    
    public void infoBox(String msg, String title)
    {
        JOptionPane.showMessageDialog(this, msg, title, dialogInfo);
    }
    
    public int dialogBox(String msg, String title)
    {
        return JOptionPane.showConfirmDialog(this, msg, title, dialogBtns);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
