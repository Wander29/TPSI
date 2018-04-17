package pkg5aia_venturi_annunciswing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.swing.table.*;

public class GUI extends JFrame implements ActionListener 
{
    //Variabili utilizzate
    private static final int PANELS = 5;
    Connection connessione = null;
    
    String query;
    Statement statement;
    PreparedStatement statementPr;
    ResultSet resultSet;
    int[] cod_selected_text = new int[2];
    String valori[] = new String[5];
    String[] columnNamesRel = {"Cognome", "Nome", "Ruolo"};
    String[] columnNamesAnn = {"Data", "Titolo", "Relatore"};
    int numRows, cnt;
    
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
    private JButton btnShAnn = new JButton("Mostra ANNUNCI");
    private JButton btnDelAnn = new JButton("Cancella ANNUNCIO");
    
    //Comuni
    private JButton btnBack = new JButton("BACK");
    private JLabel lblResult = new JLabel("");
    
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
    
    final DefaultComboBoxModel modelTipo = new DefaultComboBoxModel();
    JComboBox cbTipo = new JComboBox(modelTipo);
    final DefaultComboBoxModel modelRel = new DefaultComboBoxModel();
    JComboBox cbRel = new JComboBox(modelRel);
    
    private JButton btnIns2 = new JButton("INSERISCI");
    private JButton btnReset2 = new JButton("C");
    
    //3]ShRel
    private JTable tableRel;
    JScrollPane scrollPaneTabRel;
    
    //4]ShAnn
    private JTable tableAnn;
    JScrollPane scrollPaneTabAnn;
    JComboBox cbTipoAnn = new JComboBox(modelTipo);
    JLabel lblTipoAnn = new JLabel("Tipo Annuncio");

    //5]DelAnn
    
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
        p[3].setBorder(BorderFactory.createTitledBorder("Mostra ANNUNCI"));
        p[4].setBorder(BorderFactory.createTitledBorder("Cancella ANNUNCIO"));
        
    /**********************************************/
    /*          CONFIGURAZIONE COMPONENTI         */
    /**********************************************/
        //MENU
        btnInsRel.setFont(null);
        btnInsAnn.setFont(null);
        btnShRel.setFont(null);
        btnShAnn.setFont(null);
        btnDelAnn.setFont(null);
        
        btnInsRel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnInsAnn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnShRel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnShAnn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelAnn.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        btnInsRel.setBounds(175,60,350,40); //x, y , largh e altezza
        btnInsAnn.setBounds(175,180,350,40);
        btnShRel.setBounds(175,300,350,40);
        btnShAnn.setBounds(175,420,350,40);
        btnDelAnn.setBounds(175,540,350,40);
        
        //Comuni
        btnBack.setFont(null);
        lblResult.setFont(null);
        
        btnBack.setBackground(Color.red);
        
        lblResult.setVisible(false);
        
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnBack.setBounds(20,560,100,50);
        lblResult.setBounds(220,560,200,50);
        
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
        txtTesto.setFont(null);
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
        lblTesto.setBounds(50,280,200,30);
        txtTitolo.setBounds(330,40,200,30);
        txtData.setBounds(330,100,200,30);
        cbTipo.setBounds(330,160,200,30);
        cbRel.setBounds(330,220,200,30);
        txtTesto.setBounds(160,280,460,240);
        btnIns2.setBounds(420,560,200,50);
        
        //3]ShRel
        
        //4]ShAnn
        lblTipoAnn.setFont(null);
        cbTipoAnn.setFont(null);
        
        lblTipoAnn.setBounds(50,20,200,30);
        cbTipoAnn.setBounds(280,20,300,30);
        
        //5]DelAnn
        
        
    /**********************************************/
    /*      AGGIUNTA COMPONENTI NEI PANEL         */
    /**********************************************/
        //MENU
        pMenu.add(btnInsRel);  
        pMenu.add(btnInsAnn);
        pMenu.add(btnShRel);
        pMenu.add(btnShAnn);
        pMenu.add(btnDelAnn);
        
        //NOTA, i componenti posso stare solo in un Panel, quelli comuni vanno aggiunti al content pane
        
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
        p[1].add(txtTesto);
        p[1].add(btnIns2);
        p[1].add(btnReset2);
        
        //3]ShRel
        
        //4]ShAnn
        p[3].add(lblTipoAnn);
        p[3].add(cbTipoAnn);
        
        //5]DelAnn
        
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
        
        btnDelAnn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDelAnnActionPerformed(evt);
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
        getContentPane().add(lblResult);
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
            cod_selected_text = svuotaArr(cod_selected_text);
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
        getContentPane().add(lblResult);
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
            cod_selected_text = svuotaArr(cod_selected_text);
            
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
        
        String[][] dataRel = new String[numRows][3];
        
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
        valori = svuotaArr(valori);
        if (cbTipoAnn.getItemAt(cbTipoAnn.getSelectedIndex()) != null)
            valori[0] = (String)cbTipoAnn.getItemAt(cbTipoAnn.getSelectedIndex());
        
        if (!valori[0].equals("")) {
            if (cnt != 0) {
                p[3].remove(scrollPaneTabAnn);
                cnt = 0;
            }
            numRows = 0;
            
            cod_selected_text = svuotaArr(cod_selected_text);

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

            String[][] dataAnn = new String[numRows][3];
            cnt = 0;

            query = "SELECT a.Data, a.Titolo, re.Cognome, re.Nome " +
                "FROM relatore as re, annuncio as a " +
                "WHERE a.FkRelatore = re.CodRelatore AND a.FkTipoA = " + cod_selected_text[0] + 
                " ORDER BY a.Data";
            try {
                statement = connessione.createStatement();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    dataAnn[cnt][0] = (resultSet.getString(1));
                    dataAnn[cnt][1] = (resultSet.getString(2));
                    dataAnn[cnt][2] = (resultSet.getString(3) + " " + resultSet.getString(4));
                    cnt++;
                }
            } catch (Exception e) {  }

            if (cnt != 0) {
                tableAnn = new JTable(dataAnn, columnNamesAnn);
                tableAnn.setAutoCreateRowSorter(true); //Permette il sort dall'header
                scrollPaneTabAnn = new JScrollPane(tableAnn); //metto la tabella nello scrollpane, mi gestisce la visual
                //tableRel.setFillsViewportHeight(true);//la tabella riempie l'altezza
                scrollPaneTabAnn.setBounds(20,70,640,240);
                p[3].add(scrollPaneTabAnn);
            }
        } else {
            if (cnt != 0) {
                p[3].remove(scrollPaneTabAnn);
                scrollPaneTabAnn = null;
                tableAnn = null;
                cnt = 0;
            }
        }
        repaint();
    }
    
    private void btnDelAnnActionPerformed(ActionEvent evt){
        setContentPane(pMenu);
        cleanVar();
        revalidate();
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
        lblResult.setVisible(true);
        if (flag){
            lblResult.setForeground(Color.blue);
            lblResult.setText("Record INSERITO");
        } else {
            lblResult.setForeground(Color.RED);
            lblResult.setText("ERRORE");
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
        cod_selected_text = svuotaArr(cod_selected_text);
        valori = svuotaArr(valori);
        statement = null;
        statementPr = null;
        resultSet = null;
        numRows = cnt = 0;
        
        if (lblResult.isVisible()) {
            lblResult.setVisible(false);
        }
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
