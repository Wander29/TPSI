package server;
import java.io.*;
import java.net.*;
import java.lang.*;

public class Server {
    Socket client;
    String stringaRicevuta;
    String stringaInvertita;
    ServerSocket server_socket;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    char[] appo;
    
    public void attendi(){
        try{
            System.out.println("->Server Partito");
            server_socket = new ServerSocket(6789);
            client = server_socket.accept();
            server_socket.close(); //connessione per UN solo client
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream (client.getOutputStream());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore nell'avvio del Server");
            System.exit(1);
        }
    }
    
    public void comunica(){
        try{
            stringaRicevuta = inDalClient.readLine();
            System.out.println("Client: " + stringaRicevuta);
            
            int len = stringaRicevuta.length();
            appo = new char[len];
            for(int i = 0; i < len; i++){
                appo[i] = stringaRicevuta.charAt(len - i -1); //comincia a contare da 
            }
            
            stringaInvertita = new String(appo);
            System.out.println(stringaInvertita);
            outVersoClient.writeBytes( stringaInvertita + '\n');
            
            System.out.println("->Termine elaborazione Server");
            client.close();
        }catch(Exception e){
            System.out.println("Errore durante la comunicazione con il Client");
        }
    }
    
    public static void main(String[] args) {
        Server servente = new Server();
        servente.attendi();
        servente.comunica();
    }
}
