package client;
import java.io.*;
import java.net.*;

public class Client {
    String stringaInput;
    Socket unSocket;
    BufferedReader tastiera;
    String stringaServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    String nomeServer = "localhost";
    int portaServer = 6789;
    
    public void connetti(){
        System.out.println("-> Client in esecuzione");
        try{
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            unSocket = new Socket(nomeServer, portaServer);
            
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
    
    public void comunica(){
        try{
            System.out.println("-> Inserisci Stringa da inviare al Server");
            stringaInput = tastiera.readLine();
            outVersoServer.writeBytes(stringaInput + '\n');
            
            stringaServer = inDalServer.readLine();
            System.out.println("Server: " + stringaServer);
            
            System.out.println("->Chiusura Connessione");
            unSocket.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il Server");
            System.exit(1);
        }
    }
    
    public static void main(String[] args) {
        Client cliente = new Client();
        cliente.connetti();
        cliente.comunica();
    }
    
}
