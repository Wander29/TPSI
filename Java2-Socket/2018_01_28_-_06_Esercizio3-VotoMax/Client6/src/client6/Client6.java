package client6;
import java.io.*;
import java.net.*;

public class Client6 {
    Socket unSocket;
    BufferedReader inDalServer;
    DataOutputStream outVersoServer;
    BufferedReader tastiera;
    String stringa;
    int cont = 0;
    int votoMax;
    
    public void connetti(){
        try{
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("->Client partito");
            
            do{
                System.out.println("Per iniziare la comunicazione con il Server inserisci  'inizio'");
                stringa = tastiera.readLine();
            }while(!stringa.toLowerCase().equals("inizio"));
            
            unSocket = new Socket(InetAddress.getLocalHost(), 6789);
            inDalServer = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
            outVersoServer = new DataOutputStream(unSocket.getOutputStream());
        }catch(UnknownHostException e){
            System.err.println("Host sconosciuto");
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione con il Server");
            System.exit(1);
        }
    }
    public void comunica(){
        try{
            do{
                System.out.println("#Inserisci il " + ++cont + "°voto:   ['fine' per terminare]");
                stringa = tastiera.readLine();
                outVersoServer.writeBytes(stringa + "\n");
            }while(!stringa.toLowerCase().equals("fine"));
            
            /*System.out.println("#Inserisci il 1°voto:   ['fine' per terminare]");
                stringa = tastiera.readLine();
                outVersoServer.writeBytes(stringa + "\n");
            System.out.println("#Inserisci il 2°voto:   ['fine' per terminare]");
                stringa = tastiera.readLine();
                outVersoServer.writeBytes(stringa + "\n");*/
            
            System.out.println("\nIn attesa del Server...{}");
            votoMax = Integer.parseInt(inDalServer.readLine());
            System.out.println("\nIl Voto massimo è: " + votoMax);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il Server");
            System.exit(1);
        }
    }
    
    public static void main(String[] args){
        Client6 cliente = new Client6();
        cliente.connetti();
        cliente.comunica();
    }
}
