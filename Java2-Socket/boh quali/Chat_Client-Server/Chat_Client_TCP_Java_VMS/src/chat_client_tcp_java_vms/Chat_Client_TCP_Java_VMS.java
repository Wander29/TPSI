package chat_client_tcp_java_vms;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chat_Client_TCP_Java_VMS {

    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    String nick;
    String finito;
    Scanner input = new Scanner(System.in);
        
    public Socket connetti() {
        System.out.println("######## CLIENT partito in esecuzione ########");
        try{
            //per l'input da tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            
            // creo un socket
            miosocket = new Socket(nomeServer, portaServer);
            //
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader (miosocket.getInputStream()));
        }catch(UnknownHostException e){
            System.err.println("!!!! Host Sconosciuto");
        }catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("!!!! Errore durante la connessione!");
            System.exit(1);
        }
        return miosocket;
    }
    
    public void comunica() {
        try{
                System.out.println("Nickname:");
                nick = input.nextLine();
                do{
                    System.out.println("--> Invia:");
                    stringaUtente = nick + tastiera.readLine();
                    outVersoServer.writeBytes(nick + ": " + stringaUtente + '\n');
                    
                        //leggo la risposta dal server
                    stringaRicevutaDalServer = inDalServer.readLine();
                    if(stringaRicevutaDalServer != ""){
                        System.out.println("#Ricevuto-->" + stringaRicevutaDalServer);
                    }
                    //chiudo la connessione
                }while(stringaUtente.toLowerCase() != "exit");
                
                System.out.println("termina elaborazione e chiude connessione");
                System.exit(1); 
        }catch(Exception e){   
        }
    }
    
    public static void main(String[] args) {
        Chat_Client_TCP_Java_VMS cliente = new Chat_Client_TCP_Java_VMS();
        cliente.connetti();
        cliente.comunica();
    }
    
}
