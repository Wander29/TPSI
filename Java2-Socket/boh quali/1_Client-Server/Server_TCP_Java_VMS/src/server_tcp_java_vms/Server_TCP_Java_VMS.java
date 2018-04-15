package server_tcp_java_vms;

import java.io.*;
import java.net.*;
import java.util.*;


public class Server_TCP_Java_VMS {

    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    String vocali = "aeiou";
    
    public Socket attendi(){
        try{
        System.out.println("1 SERVER partito in esecuzione");
        
        server = new ServerSocket(6789);
        
        client = server.accept();
        
        server.close();
        
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
        
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errosre durante l'istanza del server!");
            System.exit(1);
        }
        return client;
    }

    public void comunica(){
        try{
            System.out.println("3 benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo ...");
            stringaRicevuta = inDalClient.readLine();
            System.out.println("6 ricevuta:" + stringaRicevuta);
            
            
            stringaRicevuta.toLowerCase();
            int count=0;
            for(int i=0; i<stringaRicevuta.length(); i++){
                char appo;
                appo = stringaRicevuta.charAt(i);
                for(int j=0; j<vocali.length(); j++){
                    if(appo == vocali.charAt(j)){
                        count ++;
                    }
                }
                    
            }
            
            //stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("7 invio la stringa modificata al client");
            outVersoClient.writeBytes(String.valueOf(count) /*stringaModificata*/+'\n');
            
            System.out.println("9 SERVER: fine elaborazione");
            client.close();
        }catch(Exception e){
            
        }
    }
    
    public static void main(String[] args) {
        Server_TCP_Java_VMS servente = new Server_TCP_Java_VMS();
        servente.attendi();
        servente.comunica();
    }
    
}
