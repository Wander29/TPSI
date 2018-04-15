package pkg28.pkg11.pkg17_es9_vms;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       int val1 = 0, val2 = 0;
       int num_valori = 3;
       
       System.out.println("Insierisci valore:");
       val1 = input.nextInt();
       
       for(int i=0; i < num_valori-1; i++){
           System.out.println("Insierisci valore:");
           val2 = input.nextInt();
           
           if( val1 < val2 ){
               val1 = val2;
           }
       }
       for(int o = 0; o < val1; o++){
           System.out.println("Ciao!");
    }
    }
}

