package server7;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server7 {

    ServerSocket server_socket;
    Socket client;
    DataOutputStream outVersoClient;
    BufferedReader inDalClient;
    String appo, risposta;
    ArrayList<Integer> voti = new ArrayList();
    ArrayList<Integer> crediti = new ArrayList();
    int temp_cr = 0, crs = 0;
    float ris = 0;
    
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
        Server7 servente = new Server7();
        servente.attendi();
        servente.comunica();
    }
}
