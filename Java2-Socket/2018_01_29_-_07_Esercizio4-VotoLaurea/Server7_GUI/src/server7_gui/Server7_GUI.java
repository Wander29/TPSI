package server7_gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Server7_GUI extends JFrame implements ActionListener{

    ServerSocket server_socket;
    Socket client;
    DataOutputStream outVersoClient;
    BufferedReader inDalClient;
    String appo, risposta;
    ArrayList<Integer> voti = new ArrayList();
    ArrayList<Integer> crediti = new ArrayList();
    int temp_cr = 0, crs = 0;
    float ris = 0;
    
    //Componenti Grafici
    private JButton btnChiudi = new JButton("ESCI");
    
    public Server7_GUI(){
        super("Server - Venturi");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        setLayout(null);
        add(btnChiudi);
        
        btnChiudi.setBounds(110,45,75,35);
        btnChiudi.setBackground(Color.RED);
        btnChiudi.setFont(new Font("Century Gothic", 1, 15));
        btnChiudi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnChiudi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnChiudiActionPerformed(evt);
            }
        });
    }
    
    private void btnChiudiActionPerformed(ActionEvent evt){
        chiudi();
    }
    
    public void attendi(){
        try{
            System.out.println("->Server in esecuzione");
            server_socket = new ServerSocket(6789);
            client = server_socket.accept();
            server_socket.close();
            
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'avvio del Server");
            System.exit(1);
        }
    }
    
    public void comunica(){
        do{
            try{
                initVarServer();
                for(;;){
                    //Legge la variabile e la immagazzina in una variabile, svuotando il buffer
                    appo = inDalClient.readLine();
                    System.out.println(appo);
                    if(appo.toLowerCase().equals("esci")){ chiudi(); break; }
                    else{ //se si preme ESCI chiude il Server e blocca tutto
                        if(appo.toLowerCase().equals("termina")){
                            break; }
                    } //Se non viene premuto termina si salvano i dati
                    voti.add(Integer.parseInt(appo));
                    appo = inDalClient.readLine();
                    System.out.println(appo);
                    crediti.add(Integer.parseInt(appo));
                }
                for(int i = 0; i < voti.size(); i++){ //calcolo del risultato, usciti dal ciclo
                    crs = crediti.get(i);
                    ris += (voti.get(i) * crs);
                    temp_cr += crs;
                }
                ris = ((ris/temp_cr)*110)/30;
                outVersoClient.writeBytes(Integer.toString(Math.round(ris)) + "\n");
                risposta = inDalClient.readLine();
            }catch(Exception e){
                risposta = "error";
                System.out.println(e.getMessage());
                System.out.println("Errore durante la comunicazione con il Client");
                System.exit(1);
            }
        }while(risposta.toLowerCase().equals("restart"));
        chiudi();
    }
    
    public void chiudi(){
        initVarServer();
        try{
            System.out.println("->Server in chiusura");
            client.close();
            System.exit(1);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la chiusura del Server");
            System.exit(1);
        }
    }
    
    private void initVarServer(){
        risposta = "";  appo = "";  crs = 0;    temp_cr = 0;    ris = 0;
        voti.clear();
        crediti.clear();
    }
    
    public static void main(String[] args) {
        Server7_GUI servente = new Server7_GUI();
        servente.setSize(300,150);
        servente.setVisible(true);
        servente.attendi();
        servente.comunica();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
