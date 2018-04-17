package client5;
import java.io.*;
import java.net.*;

public class Client5 {
    String stringaInput;
    int voc, cons, spazi;
    Socket unSocket;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    BufferedReader tastiera;
    
    public void connetti(){
        try{
            System.out.println("->Client partito");
            unSocket = new Socket(InetAddress.getLocalHost(), 6789);
            
            inDalServer = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
            outVersoServer = new DataOutputStream(unSocket.getOutputStream());
        }catch(UnknownHostException e){
            System.err.println("Host sconosciuto");
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il Server");
            System.exit(1);
        }
    }
    
    public void comunica(){
        try{
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("->Inserisci la stringa da inviare   ['exit' per uscire]");
            stringaInput = tastiera.readLine();
            outVersoServer.writeBytes(stringaInput + '\n');
            
            if(stringaInput.toLowerCase().equals("exit")){
                //chiudo la connessione, saltando l'else
                }else{
                    voc = inDalServer.read();
                    cons = inDalServer.read();
                    spazi = inDalServer.read();

                    if(voc > 0){ System.out.println("----> Vocali: " + voc); }
                    if(cons > 0){ System.out.println("----> Consonanti: " + cons); }
                    if(spazi > 0){ System.out.println("----> Spazi: " + spazi); }
                }
            System.out.println("\n-> Termine elaborazione client");
            unSocket.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il Server");
        }
    }
    public static void main(String[] args) {
        Client5 cliente = new Client5();
        cliente.connetti();
        cliente.comunica();
    }
    
}
