package pkg04.pkg12.pkg17_es10_vms;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il lato del quadrato:");
        Quadrato figura = new Quadrato(input.nextDouble());
        System.out.println("Il Perimetro è: " + figura.getPerimetro());
        System.out.println("L'Area è: " + figura.getArea());
    }
    
}
