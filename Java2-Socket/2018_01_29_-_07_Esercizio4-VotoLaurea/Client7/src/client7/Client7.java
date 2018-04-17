package client7;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Client7 extends JFrame implements ActionListener{
    
    //Dichiarazione Componenti Grafici
    private JButton btnInizia = new JButton("START");
    private JButton btnTermina = new JButton("TERMINA");
    private JButton btnInvia = new JButton("INVIA");
    private JButton btnRestart = new JButton("RESTART >");
    private JButton btnExit = new JButton("ESCI");
    private JLabel lblTitolo = new JLabel("Voto Laurea");
    private JLabel lblEsame = new JLabel("ESAME");
    private JLabel lblVoto = new JLabel("Voto");
    private JLabel lblCrediti = new JLabel("Crediti");
    private JLabel lblPartenza = new JLabel("Voto Partenza");
    private JLabel lblRisultato = new JLabel("");
    private JLabel lblErrVoto = new JLabel("ERRORE, voto da 18 a 30");
    private JLabel lblErrCrediti = new JLabel("ERRORE, crediti da 3 a 15");
    private JTextField txtVoto = new JTextField(15);
    private JTextField txtCrediti = new JTextField(15);
    
    //Variabili utilizzate
    private int cnt_esame = 1, varInt;
    String var;
    boolean votoOk, creditiOk;
    
    //Costruttore
    public Client7(){
        super("Voto Laurea - Venturi");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        doCampi(false); //editable = false
        setLayout(null); //layout assoluto e manuale
        
        add(btnInizia); //aggiunta di tutti gli elementi al Frame
        add(lblEsame);
        add(lblVoto);
        add(txtVoto);
        add(lblErrVoto);
        add(lblCrediti);
        add(txtCrediti);
        add(lblErrCrediti);
        add(btnInvia);
        add(btnTermina);
        add(lblPartenza);
        add(lblRisultato);
        add(btnRestart);
        add(btnExit);
        
    /**************************************************************************/
    /*              Configurazioni componenti                                 */
    /**************************************************************************/
        btnInvia.setEnabled(false);
        lblErrVoto.setVisible(false);
        lblErrCrediti.setVisible(false);
        btnRestart.setVisible(false);
        btnExit.setVisible(false);
        lblRisultato.setVisible(false);
        
        doBtnTermina(false, Color.BLACK);
        lblRisultato.setForeground(Color.red);
        btnInizia.setForeground(Color.red);
        lblErrVoto.setForeground(Color.red);
        lblErrVoto.setFont(new Font("Consolas", 0, 13));
        lblErrCrediti.setForeground(Color.red);
        lblErrCrediti.setFont(new Font("Consolas", 0, 13));
        btnExit.setFont(new Font("Century Gothic", 0, 14));
        btnRestart.setFont(new Font("Century Gothic", 1, 15));
        btnRestart.setBackground(new Color(102, 255, 102));
        btnExit.setBackground(new Color(255, 51, 51));
        
        btnTermina.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRestart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
    /**************************************************************************/
    /*              Set Grandezza componenti                                  */
    /**************************************************************************/
        btnInizia.setBounds(200,35,100,25); //x, y , largh e altezza
        
        lblEsame.setBounds(150,100,100,25); //valori = punto inizio del componente, non il centro
        
        lblVoto.setBounds(150,160,100,25); //... sono divisi per riga
        txtVoto.setBounds(220,160,100,25);
        lblErrVoto.setBounds(325,160,300,25);
        
        lblCrediti.setBounds(150,200,100,25);
        txtCrediti.setBounds(220,200,100,25);
        lblErrCrediti.setBounds(325,200,300,25);
        
        btnInvia.setBounds(150,270,90,25);
        btnTermina.setBounds(260,270,90,25);
        
        lblPartenza.setBounds(150,350,100,25);
        lblRisultato.setBounds(260,350,60,25);
        btnRestart.setBounds(380,350,120,25);
        
        btnExit.setBounds(420,400,80,25);
        
    /**************************************************************************/
    /*              GESTORI EVENTI                                            */
    /**************************************************************************/
        txtVoto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVotoFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtVotoFocusGained(evt);
                //if(btnTermina.isEnabled()){ doBtnTermina(false, Color.BLACK); }
            }
        });
        
        txtCrediti.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCreditiFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCreditiFocusGained(evt);
                //if(btnTermina.isEnabled()){ doBtnTermina(false, Color.BLACK); }
            }
        });
        
        btnInvia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnInviaActionPerformed(evt);
            }
        });
        
        btnInizia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnIniziaActionPerformed(evt);
            }
        });
        
        btnTermina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnTerminaActionPerformed(evt);
            }
        });
        
        btnRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRestartActionPerformed(evt);
            }
        });
        
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
    }  
    
    /**************************************************************************/
    /*                      EVENTI                                            */
    /**************************************************************************/
    private void txtVotoFocusLost(java.awt.event.FocusEvent evt) {
        try{
            varInt = Integer.parseInt(txtVoto.getText());
            if(varInt > 30 || varInt < 18){
                lblErrVoto.setVisible(true);
                txtVoto.setForeground(Color.red);
                votoOk = false;
            }else{ 
                votoOk = true; 
                txtVoto.setForeground(Color.green);
            }
        }catch(Exception e){
            e.getMessage();
            System.out.println("Errore nell'inserimento del Voto");
        }
    } 
    
    private void txtCreditiFocusLost(java.awt.event.FocusEvent evt) {                                    
        try{
            varInt = Integer.parseInt(txtCrediti.getText());
            if(varInt > 15 || varInt < 3){
                lblErrCrediti.setVisible(true);
                txtCrediti.setForeground(Color.red);
                creditiOk = false;
            }else{ 
                creditiOk = true; 
                txtCrediti.setForeground(Color.green);
            }
        }catch(Exception e){
            e.getMessage();
            System.out.println("Errore nell'inserimento dei Crediti");
        }
    } 
    
    private void txtVotoFocusGained(java.awt.event.FocusEvent evt) {                                    
        txtVoto.setText("");
        if(txtVoto.getForeground() != Color.BLACK){ txtVoto.setForeground(Color.BLACK); }
        if(lblErrVoto.isVisible()){ lblErrVoto.setVisible(false); }
        //if(!btnInvia.isEnabled()){ btnInvia.setEnabled(true); }
    }
    
    private void txtCreditiFocusGained(java.awt.event.FocusEvent evt) {                                    
        txtCrediti.setText("");
        if(txtCrediti.getForeground() != Color.BLACK){ txtCrediti.setForeground(Color.BLACK); }
        if(lblErrCrediti.isVisible()){ lblErrCrediti.setVisible(false); }
        //if(!btnInvia.isEnabled()){ btnInvia.setEnabled(true); }
    }
    
    private void btnInviaActionPerformed(ActionEvent evt){
        if(votoOk && creditiOk){
            try{
                if(btnInvia.getForeground() == Color.RED){ btnInvia.setForeground(Color.BLACK); }
                var = txtVoto.getText();
                System.out.println("Voto: " + var);
                outVersoServer.writeBytes(var + "\n");
                var = "";
                var = txtCrediti.getText();
                System.out.println("Crediti: " + var); 
                outVersoServer.writeBytes(var + "\n");
                svuotaCampi();
                creditiOk = false;
                votoOk = false;
                cnt_esame++;
                if(cnt_esame == 2){ 
                    doBtnTermina(true, Color.RED);
                }
                setEsameLabel(cnt_esame);
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Errore durante la connessione con il Server");
            }
        }else{
            btnInvia.setForeground(Color.RED);
            if(!lblErrCrediti.isVisible() && !creditiOk){ lblErrCrediti.setVisible(true); }
            if(!lblErrVoto.isVisible() && !votoOk){ lblErrVoto.setVisible(true); }
        }
    }
    
    private void btnTerminaActionPerformed(ActionEvent evt){
        try{
            svuotaCampi();
            doCampi(false);
            setEsameLabel(0);
            outVersoServer.writeBytes("termina" + "\n");
            lblRisultato.setVisible(true);
            lblRisultato.setText(inDalServer.readLine() + "/110");
            btnInvia.setEnabled(false);
            doBtnTermina(false, Color.BLACK);
            btnRestart.setVisible(true);
            btnExit.setVisible(true);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'elaborazione del Server");
            System.exit(1);
        }
    }
    
    private void btnIniziaActionPerformed(ActionEvent evt){
        connetti();
        txtVoto.setVisible(true);
        txtCrediti.setVisible(true);
        doCampi(true);
        setEsameLabel(1);
        btnInizia.setEnabled(false);
        btnInvia.setEnabled(true);
    }
    
    private void btnRestartActionPerformed(ActionEvent evt){
        try{
            outVersoServer.writeBytes("restart" + "\n");   
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante il Restart");
            System.exit(1);
        }
        btnRestart.setVisible(false);
        lblRisultato.setText("");
        lblRisultato.setVisible(false);
        initVar();
    }
    
    private void btnExitActionPerformed(ActionEvent evt){
        try{
            outVersoServer.writeBytes("esci" + "\n");   
            unSocket.close();
            System.exit(1);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'uscita dal Programma");
            System.exit(1);
        }
    }
    
    /**************************************************************************/
    /*              CONNESSIONE con il SERVER                                 */
    /**************************************************************************/
    Socket unSocket;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    
    private void connetti(){
        try{
            System.out.println("->Client in esecuzione");
            unSocket = new Socket(InetAddress.getLocalHost(), 6789);
            inDalServer = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
            outVersoServer = new DataOutputStream(unSocket.getOutputStream());
        }catch(UnknownHostException e){
            System.err.println("Host sconosciuto");
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione al Server");
            System.exit(1);
        }
    }
    
    /**************************************************************************/
    /*                  FUNZIONI APPOGGIO                                     */
    /**************************************************************************/
    private void setEsameLabel(int var){
        if(var != 0){
            lblEsame.setText("ESAME n°" + var);
        }else{ lblEsame.setText("ESAME"); }     
    }
    
    private void svuotaCampi(){
        txtVoto.setText("");
        txtCrediti.setText("");
    }
    
    private void doCampi(boolean var){
        txtVoto.setEditable(var);
        txtCrediti.setEditable(var);
    }
    
    private void doBtnTermina(boolean bool, Color colore){
        btnTermina.setEnabled(bool); 
        btnTermina.setBorder(new javax.swing.border.LineBorder(colore, 1, false));
    }
    
    private void initVar(){
        doCampi(true);
        svuotaCampi();
        btnInvia.setEnabled(true);
        cnt_esame = 1;
        varInt = 0;
        var = "";
        votoOk = false;
        creditiOk = false;
        setEsameLabel(1);
    }
    
    public static void main(String[] args) {
        Client7 cliente = new Client7();
        cliente.setSize(550,500);
        cliente.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}