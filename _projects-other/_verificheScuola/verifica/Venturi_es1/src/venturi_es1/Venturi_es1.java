package venturi_es1;

import java.util.*;

public class Venturi_es1 {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num_addendi = 0;
        float var = 0, somma = 0;
        boolean risposta = false;
        
        System.out.println("***** SOMMA di N Numeri *****"); 
        
        System.out.print("Quanti addendi vuoi sommare? ");
        num_addendi = input.nextInt();
        
        for(int i = 1; i <= num_addendi; i++){
            System.out.print("\nInserisci il " + i + "° addendo: ");
            var = input.nextFloat();
            somma += var;
        }
        System.out.println("=============================");
        while(!risposta){
            System.out.print("\nIndovina la somma: ");
            if(input.nextFloat() == somma){
                risposta = true;
                System.out.println("\nESATTO! Complimenti, hai sfidato la sorte e hai vinto, wow");
            }else{
                risposta = false;
                System.out.println("Sbagliato.. ritenta");
            }
        }
        
    }
    
}
