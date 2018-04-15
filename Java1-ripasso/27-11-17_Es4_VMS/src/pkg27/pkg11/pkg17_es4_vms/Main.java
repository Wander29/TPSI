package pkg27.pkg11.pkg17_es4_vms;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int valore = 0, somma = 0;
        for(int i=1; i<=4; i++){
            System.out.println("Inserisci il " + i + "° numero");
            valore = input.nextInt();
            somma += valore;
        }
        System.out.println("SOMMA: " + somma);
    }
    
}
