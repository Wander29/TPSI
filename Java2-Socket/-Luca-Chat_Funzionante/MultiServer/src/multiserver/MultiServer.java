package multiserver;

import java.net.*; 
import java.io.*; 
import java.util.*;
class ServerThread extends Thread { 
  ServerSocket server      = null;
  Socket client            = null;
  Socket dest = null;
  String stringaRicevuta   = null;
  String stringaModificata = null;
  BufferedReader   inDalClient; 
  DataOutputStream outVersoClient;
  int desti;
  
  public ServerThread (Socket socket, Socket dest){ 
    this.client = socket;
    this.dest = dest;
  } 
   
  public void run(){ 
  try{
    comunica();  
  }catch (Exception e){ 
    e.printStackTrace(System.out);  } 
  } 
  
  public void comunica ()throws Exception{
    inDalClient      = new BufferedReader(new InputStreamReader (client.getInputStream()));
    outVersoClient   = new DataOutputStream(dest.getOutputStream());
    for (;;){ 
      stringaRicevuta = inDalClient.readLine();
      if (stringaRicevuta == null || stringaRicevuta.equals("FINE")){ 
        outVersoClient.writeBytes(stringaRicevuta+" (=>server in chiusura...)" + '\n'); 
        System.out.println("Echo sul server in chiusura  :" + stringaRicevuta); 
        break;
      }
      else{
        outVersoClient.writeBytes(stringaRicevuta + '\n'); 
        System.out.println("6 Echo sul server :" + stringaRicevuta); 
      }
    } 
    outVersoClient.close(); 
    inDalClient.close(); 
    System.out.println("9 Chiusura socket" + client); 
    client.close(); 
  } 
} 
  
public class MultiServer{ 
  public void start(){ 
    try{
      ServerSocket serverSocket = new ServerSocket(6789); 
      Socket socket = null;
      Socket socketdest = null;
      Socket socket1 = null;
      Socket socketdest1 = null;
      for (int i=0; i<2; i++) 
      { 
        System.out.println("1 Server in attesa ... ");
        if(i == 0){
            socket = serverSocket.accept();
            socketdest = socket;
            System.out.println("3 Server socket  " + socket);
        }else{
            socket1 = serverSocket.accept();
            socketdest1 = socket1;
            System.out.println("3 Server socket  " + socket1);
        } 
      }
      ServerThread serverThread = new ServerThread(socket, socketdest1); 
      serverThread.start();
      ServerThread serverThread2 = new ServerThread(socket1, socketdest); 
      serverThread2.start();
    }
    catch (Exception e){
      System.out.println(e.getMessage());
      System.out.println("Errore durante l'istanza del server !");
      System.exit(1);
    }
  } 

  public static void main (String[] args){ 
     MultiServer tcpServer = new MultiServer(); 
     tcpServer.start(); 
   } 
}

