package server5;
import java.io.*;
import java.net.*;

public class Server5 {
    String stringaInput;
    int vocali, cons, spazi;
    DataOutputStream outVersoClient;
    BufferedReader inDalClient;
    ServerSocket server_socket;
    Socket client;
    int[] appo = {97, 101, 105, 111, 117}; //ASCII conversione delle vocali
    int var = 0;
    boolean isVocale;
    
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
            stringaInput = inDalClient.readLine();
            stringaInput = stringaInput.toLowerCase();
            
            if(stringaInput.equals("exit")){
                //chiudo la connessione
                System.out.println("-> Chiusura Server");
                client.close();
            }else{
                for(int i = 0; i < stringaInput.length(); i++)
                {
                    var = (int)stringaInput.charAt(i);

                    if( var >=97 &&  var <= 122) //dalla A a Z minuscole in ASCII
                    { 
                        for(int a = 0; a < appo.length; a++)
                        {
                            if(var == appo[a]){
                                vocali++;
                                isVocale = true;
                                break;
                            }else{ isVocale = false; }
                        }
                        if (!isVocale){ cons++; }   
                    }else{  
                        if(var == 32){ spazi++; }else{
                            //aggiusto la stringa, vocali accentate ecc..
                            /* Ho fatto tutta sta fatica.. e niente, usa l'Unicode per i caratteri particolari :/
                            if((var >= 135 && var <= 140) //à
                                || (var >=142 && var <= 149 )//i, e
                                || (var >=151 && var<= 159))//o,u
                                { vocali++; }else{
                                    if(var==150 || var==141 || var==190 ||
                                        var==191 || var==207 || var==216 || 
                                        var==217)
                                        { cons++; }
                                    } */
                            }   
                        }
                }
                
                outVersoClient.write(vocali);
                outVersoClient.write(cons);
                outVersoClient.write(spazi);

                System.out.println("->Chiusura Server");
                client.close();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il Client");
        }
    }
    
    public static void main(String[] args) {
        Server5 servente = new Server5();
        servente.attendi();
        servente.comunica();
    }
}
