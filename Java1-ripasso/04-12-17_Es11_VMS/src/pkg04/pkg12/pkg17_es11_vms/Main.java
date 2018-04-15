package pkg04.pkg12.pkg17_es11_vms;

import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String nome, cognome;
        int eta;
        
        System.out.println("****** Prima persona ******");
        System.out.println("Inserisci il nome: ");
        nome = input.next();
        System.out.println("Inserisci il cognome: ");
        cognome = input.next();
        System.out.println("Inserisci l'eta': ");
        eta = input.nextInt();
    
        Persona p1 = new Persona(nome, cognome, eta);
    
        nome = "";
        cognome = "";
        eta = 0;
        
        System.out.println("****** Seconda persona ******");
        System.out.println("Inserisci il nome: ");
        nome = input.next();
        System.out.println("Inserisci il cognome: ");
        cognome = input.next();
        System.out.println("Inserisci l'eta': ");
        eta = input.nextInt();
    
        Persona p2 = new Persona(nome, cognome, eta);
        
        if (p1.getEta() > p2.getEta()){
            System.out.println(p2.getNome() + " " + p2.getCognome() + " è il piu giovane");
        } else {
            System.out.println(p1.getNome() + " " + p1.getCognome() + " è il piu giovane");
        }
    }
}
