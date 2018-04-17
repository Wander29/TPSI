package pkg5aia_venturi_annunciswing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.plaf.FontUIResource;

public class GUI extends JFrame implements ActionListener 
{
    //Variabili utilizzate
    private static final int PANELS = 5;
    
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
    private JComboBox cbRuolo = new JComboBox();
    private JButton btnIns1 = new JButton("INSERISCI");
    
    //2]InsAnn
    
    private JButton btnIns2 = new JButton("INSERISCI");
    
/**********************************************/
/*                   COSTRUTTORE              */
/**********************************************/
    public GUI (int width, int height) {
        
        super ("Annunci Swing - VENTURI");
        super.setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null); //layout assoluto e manuale
        
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
        
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnBack.setBounds(20,560,100,50);
        lblResult.setBounds(200,560,150,50);
        
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
                
        lblNomeRel.setBounds(50,40,200,30);
        lblCogRel.setBounds(50,140,200,30);
        lblRuoloRel.setBounds(50,240,200,30);
        txtNomeRel.setBounds(330,40,200,30);
        txtCogRel.setBounds(330,140,200,30);
        cbRuolo.setBounds(330,240,200,30);
        btnIns1.setBounds(420,560,200,50);
        
        //2] InsAnn
        
        
        
    //Aggiunta componenti nei Panel
        //MENU
        pMenu.add(btnInsRel);  
        pMenu.add(btnInsAnn);
        pMenu.add(btnShRel);
        pMenu.add(btnShAnn);
        pMenu.add(btnDelAnn);
        
        //NOTA, i componenti posso stare solo in un Panel, quelli comuni vanno aggiunti al content pane
        
        //1] InsRel
        p[1].add(lblNomeRel);
        p[1].add(lblCogRel);
        p[1].add(lblRuoloRel);
        p[1].add(txtNomeRel);
        p[1].add(txtCogRel);
        p[1].add(cbRuolo);
        p[1].add(btnIns1);
        
        //2] InsAnn
        p[2].add(btnIns2);
        
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
        setContentPane(p[1]);
        getContentPane().add(btnBack);
        getContentPane().add(lblResult);
        
        //prendo i ruoli ogni volta che clicco sul bottone inserisci
        cbRuolo.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        revalidate();
    }
    
    private void btnInsAnnActionPerformed(ActionEvent evt){
        setContentPane(p[2]);
        getContentPane().add(btnBack);
        getContentPane().add(lblResult);

        revalidate();
    }
    
    public static void main(String[] args) {
        GUI annunci_GUI = new GUI(700, 700);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
