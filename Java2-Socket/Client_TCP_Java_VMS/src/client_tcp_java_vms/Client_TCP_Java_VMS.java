package client_tcp_java_vms;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client_TCP_Java_VMS {

    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    //Scanner input = new Scanner(System.in);
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
        
    public Socket connetti() {
        System.out.println("2 CLIENT partito in esecuzione ... ");
        try{
            //per l'input da tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            // creo un socket
            miosocket = new Socket(nomeServer, portaServer);
            //
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader (miosocket.getInputStream()));
        }catch(UnknownHostException e){
            System.err.println("Host Sconosciutissimo");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
        return miosocket;
    }
    
    public void comunica() {
        try{
            System.out.println("4 ... inserisci la stringa da trasmettere al server:" + '\n');
            stringaUtente = tastiera.readLine();
            //la spedisco al server
            System.out.println("5 ... invio la stringa al server e attendo");
            outVersoServer.writeBytes( stringaUtente+'\n' );
            //leggo la risposta dal server
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("8 ... risposta dal server" + '\n' + stringaRicevutaDalServer);
            //chiudo la connesasione
            System.out.println("9 ... termina elaborazione e chiude connessione");
            System.exit(1);
        }catch(Exception e){
            
        }
        
    }
    
    public static void main(String[] args) {
        Client_TCP_Java_VMS cliente = new Client_TCP_Java_VMS();
        cliente.connetti();
        cliente.comunica();
    }
    
}
