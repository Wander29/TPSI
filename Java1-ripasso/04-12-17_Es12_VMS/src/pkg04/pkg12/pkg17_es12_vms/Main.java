package pkg04.pkg12.pkg17_es12_vms;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String tipo, colore;
        float velocita;
        
        System.out.println("Inserisci la marca e il modello del Motorino: ");
        tipo = input.nextLine(); //legge tutta la riga, anche quella precedente rimasta nel buffer
        System.out.println("Inserisci il colore: ");
        colore = input.next(); //legge 1 parola nella riga
        System.out.println("Inserisci la velocità max: ");
        velocita = input.nextFloat();
        
        Motorino gatto = new Motorino(colore, tipo, velocita);
        System.out.println("Aumenta la velocita' di: ");
        gatto.accelera(input.nextFloat());
        System.out.println(gatto.getVelocita());
        gatto.inserisciAntifurto();
        System.out.println("Aumenta la velocita' di: ");
        gatto.accelera(input.nextFloat());
        System.out.println(gatto.getVelocita());
    }
    
}
