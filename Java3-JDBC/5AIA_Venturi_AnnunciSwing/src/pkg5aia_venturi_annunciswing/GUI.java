package pkg5aia_venturi_annunciswing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class GUI extends JFrame implements ActionListener 
{
    //Variabili utilizzate
    private static final int PANELS = 5;
    Connection connessione = null;
    String query, query2, selected_text;
    Statement statement, statement2;
    PreparedStatement statementPr;
    ResultSet resultSet;
    int[] cod_selected_text = new int[2];
    String valori[] = new String[3];
    
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
    
    //3]ShRel
    private JTable tableRel = new JTable();
    
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
        
        btnIns1.setBackground(Color.GREEN);
        btnIns1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
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
        
        btnIns2.setBackground(Color.GREEN);
        btnIns2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
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
        tableRel.setFont(null);
        
        tableRel.setBounds(30,30,600,400); 
        
        
    //Aggiunta componenti nei Panel
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
        
        //3]ShRel
        p[2].add(tableRel);
        
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
        
        
        btnIns1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnIns1ActionPerformed(evt);
            }
        });
        
        setVisible(true); //rende visibile il Frame
    }
/**********************************************/
/*                   EVENTI                   */
/**********************************************/
    private void btnBackActionPerformed(ActionEvent evt){
        setContentPane(pMenu);
        revalidate();
    }
    
    private void btnInsRelActionPerformed(ActionEvent evt){
        setContentPane(p[0]);
        getContentPane().add(btnBack);
        getContentPane().add(lblResult);
        
        query = "SELECT ruolo.Ruolo FROM ruolo ORDER BY Ruolo";
        try {
            statement = connessione.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                modelRuolo.addElement(resultSet.getString(1));
            }
        } catch (Exception e) {  }
        
        revalidate();
    }
    
    private void btnIns1ActionPerformed(ActionEvent evt){
        //Controllo pienezza Campi
        if (txtNomeRel.getText().equals("") || txtCogRel.getText().equals(""))
        {
            inserted(false);
        } else {
            cod_selected_text = svuotaArr(cod_selected_text);
            selected_text = (String)cbRuolo.getItemAt(cbRuolo.getSelectedIndex());

            query = "SELECT CodRuolo FROM ruolo WHERE Ruolo = '" + selected_text + "'";
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
                } else {
                    inserted(false);
                }
            } catch (Exception e) { 
                inserted(false);
            }
        }
    }
    
    private void btnInsAnnActionPerformed(ActionEvent evt){
        setContentPane(p[1]);
        getContentPane().add(btnBack);
        getContentPane().add(lblResult);
        
        query = "SELECT Tipo FROM tipo_annuncio ORDER BY Tipo";
        try {
            statement = connessione.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                modelTipo.addElement(resultSet.getString(1));
            }
        } catch (Exception e) {  }
        
        query = "SELECT cognome, nome FROM relatore ORDER BY cognome";
        try {
            statement = connessione.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                modelRel.addElement(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        } catch (Exception e) {  }
        
        revalidate();
    }
    
    private void btnIns2ActionPerformed(ActionEvent evt){
        valori = svuotaArr(valori);
        valori[0] = capitalize1(txtTitolo.getText());
        valori[1] = txtData.getText();
        valori[2] = capitalize1(txtTesto.getText());
        if (valori[0].equals("") || valori[1].equals("") || valori[2].equals("") )
        {
            inserted(false);
        } else {
            cod_selected_text = svuotaArr(cod_selected_text);
            
            selected_text = (String)cbTipo.getItemAt(cbTipo.getSelectedIndex());
            query = "SELECT CodTipoA FROM tipo_annuncioo WHERE Tipo = '" + selected_text + "'";
            try {
                statement = connessione.createStatement();
                resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    cod_selected_text[0] = Integer.parseInt(resultSet.getString(1));
                }
            } catch (Exception e) {  }
            
            selected_text = (String)cbRel.getItemAt(cbRel.getSelectedIndex());
            query = "SELECT CodRelatore FROM relatore WHERE "
                    + "CONCAT(relatore.cognome, ' ', relatore.nome) = '" + selected_text + "'";
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
                } else {
                    inserted(false);
                }
            } catch (Exception e) { 
                inserted(false);
            }
        }
    }
    
    private void btnShRelActionPerformed(ActionEvent evt){
        setContentPane(p[2]);
        getContentPane().add(btnBack);
        
        tableRel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
                {"ciao", null, null, null},
                {null, null, null, null},
                {null, null, null, "sisi"},
                {null, null, null, null}
                    
            },
            new String [] {
                "CodRelatore", "Nome", "Cognome", "Ruolo"
            }
        ));
        revalidate();
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
    
    private int[] svuotaArr (int vet[]) {
        for (int i = 0; i< vet.length; i++) {
            vet[i] = 0;
        }
        return vet;
    }
    
    private String[] svuotaArr (String vet[]) {
        for (int i = 0; i< vet.length; i++) {
            vet[i] = "";
        }
        return vet;
    }
    
    public String capitalize1(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
