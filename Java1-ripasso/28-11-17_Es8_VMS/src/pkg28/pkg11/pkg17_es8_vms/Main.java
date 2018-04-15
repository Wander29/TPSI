package pkg28.pkg11.pkg17_es8_vms;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] valori = new int[3];
        Scanner input = new Scanner(System.in);
        
        for(int a = 1; a<=3; a++){
            System.out.println("Inserisci il " + a + "° numero");
            valori[a-1] = input.nextInt();
        }
        Arrays.sort(valori);
        System.out.println(Arrays.toString(valori));
    }
}