package pkg27.pkg11.pkg17_es3_vms;

import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Inserisci il primo valore");
        
        int val1 = input.nextInt();
        
        System.out.println("Inserisci il secondo valore");
        
        int val2 = input.nextInt();
        
        int somma = val1+val2;
        int prodotto = val1*val2;
        
        System.out.println("SOMMA: " + somma);
        
        System.out.println("PRODOTTO: " + prodotto);
    }
    
}
