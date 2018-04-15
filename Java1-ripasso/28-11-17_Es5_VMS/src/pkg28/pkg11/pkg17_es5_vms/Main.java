package pkg28.pkg11.pkg17_es5_vms;

import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

    public static void main(String[] args) {
        final double pigreco = Math.PI;
        float raggio = 0;
        
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il RAGGIO del cerchio: ");
        raggio = input.nextFloat();
        
        System.out.println("AREA cerchio: " + Math.round(Math.pow(raggio, 2) * pigreco));
    }
    
}
