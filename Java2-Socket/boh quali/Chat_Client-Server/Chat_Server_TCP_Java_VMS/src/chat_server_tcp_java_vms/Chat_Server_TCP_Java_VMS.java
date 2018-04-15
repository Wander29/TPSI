package chat_server_tcp_java_vms;

import java.io.*;
import java.net.*;
import java.util.*;


public class Chat_Server_TCP_Java_VMS {

    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    String nick;
    Scanner input = new Scanner(System.in);
    String invio;
    
    public Socket attendi(){
        try{
        System.out.println("######## SERVER partito in esecuzione ########");
        server = new ServerSocket(6789);       
        client = server.accept();
        server.close();
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
        return client;
    }

    public void comunica(){
        try{
            System.out.println("Nickname:");
            nick = input.nextLine();
            do{
                stringaRicevuta = inDalClient.readLine();
                System.out.print("#Ricevuto--> " + stringaRicevuta);
                if(inDalClient.lines().count()>0){
                    int c = (int)inDalClient.lines().count();
                    for(int i=0; i<c; i++){
                        System.out.print("#Ricevuto--> " + inDalClient.lines());
                    }
                }
                System.out.println("--> Invia:");
                invio = input.nextLine();
                outVersoClient.writeBytes(nick + ": " + invio + "\n"); 
            }while(stringaRicevuta.toLowerCase() != "exit");
            client.close();             
        }catch(Exception e){ 
        }
    }
    
    public static void main(String[] args) {
        Chat_Server_TCP_Java_VMS servente = new Chat_Server_TCP_Java_VMS();
        servente.attendi();
        servente.comunica();
    }
}
