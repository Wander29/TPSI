package pkg04.pkg12.pkg17_es11_vms;

import java.lang.*;

public class Persona {
    private String nome, cognome;
    private int eta = 0;
    
    public Persona(String nome, String cognome, int eta){
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }
    
    public void setCognome(String cognome){
        this.cognome = cognome;
    }
    
    public String getCognome(){
        return cognome;
    }
    
    public void setEta(int eta){
        this.eta = eta;
    }
    
    public int getEta(){
        return eta;
    }
}
