package server6;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server6 {
    ServerSocket server_socket;
    Socket client;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    ArrayList<Integer> voti = new ArrayList<>();
    String appo;
    int votoMax = 0;
    
    public void attendi(){
        try{
            System.out.println("->Server partito");
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
        try{
            for(;;)
            {
                appo = inDalClient.readLine();
                if(appo.toLowerCase().equals("fine")){
                    break;
                }
                //System.out.println(Integer.parseInt(appo));
                voti.add(Integer.parseInt(appo));
                /* Oppure, senza salvare i voti
                if(Integer.parseInt(appo)>votoMax){
                    votoMax = Integer.parseInt(appo);
                }
                */
            }
            for(int i = 0; i < voti.size(); i++){
                if(voti.get(i)>votoMax){
                    votoMax = voti.get(i);
                }
            } 
            outVersoClient.writeBytes(Integer.toString(votoMax) + "\n"); //writeInt ha problemi
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore nella comunicazione con il Client");
            System.exit(1);
        }
    }
    
    public static void main(String[] args) {
        Server6 servente = new Server6();
        servente.attendi();
        servente.comunica();
    }
}
