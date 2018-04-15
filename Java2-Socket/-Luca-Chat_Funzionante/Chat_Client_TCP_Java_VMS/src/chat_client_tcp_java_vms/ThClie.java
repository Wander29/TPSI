/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_client_tcp_java_vms;


import java.io.*;
import java.io.IOException;
import java.net.Socket;

public class ThClie extends Thread{
    private Socket s;
	private DataInputStream in;
	private boolean end;
	private String message,name;
        BufferedReader inDalServer;
        String stringaRicevutaDalServer;
	
	public ThClie(Socket s){
		this.s=s;
		this.name=name;
	}
	
	public ThClie(){
		
	}
	
	public void run(){
		try{
			inDalServer = new BufferedReader(new InputStreamReader (s.getInputStream()));
                        while(!end){
                            stringaRicevutaDalServer = inDalServer.readLine(); 
                            if(stringaRicevutaDalServer != ""){
                                System.out.print("" + stringaRicevutaDalServer + '\n' + ">  ");
                            }
                        }
                        
		}catch(IOException io){
			System.out.println("Error client thread");
			io.printStackTrace();
			end=true;
		}
	}
}
