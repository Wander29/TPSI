package pkg28.pkg11.pkg17_es7_vms;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       int val1 = 0, val2 = 0;
       System.out.println("Insierisci valore:");
       val1 = input.nextInt();
       
       for(int i=0; i<2; i++){
           System.out.println("Insierisci valore:");
           val2 = input.nextInt();
           
           if( val1 < val2 ){
               val1 = val2;
           }
       }
       System.out.println("Valore più grande: " + val1);
    }
}
