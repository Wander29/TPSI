package pkg28.pkg11.pkg17_es6_vms;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int val1 = 0;
        
        System.out.println("Inserisci il primo numero: ");
        val1 = input.nextInt();
        System.out.println("Inserisci il secondo numero: ");
        
        if(val1 == input.nextInt()){
            System.out.println("UGUALI");
        }else {
            System.out.println("DIVERSI");
        }
    }
    
}
