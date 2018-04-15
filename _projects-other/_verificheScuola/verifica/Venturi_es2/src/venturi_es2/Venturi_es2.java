package venturi_es2;

import java.util.*;

public class Venturi_es2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    //variabili da passare agli oggetti
        String nome, cognome, squadra;
        int goal_fatti;
        
        System.out.println("***** Classifica CANNONIERI_esercizioGiocatori *****");
    //1° Giocatore
        System.out.println("__1° Giocatore__");
        System.out.println("#NOME: ");
        nome = input.next();
        System.out.println("#COGNOME: ");
        cognome = input.next();
        System.out.println("#SQUADRA: ");
        squadra = input.next();
        System.out.println("#GOAL_FATTI: ");
        goal_fatti = input.nextInt();
        System.out.println("=====================");
        Giocatore primo = new Giocatore(nome, cognome, squadra, goal_fatti);
        
    //2° Giocatore
        System.out.println("__2° Giocatore__");
        System.out.println("#NOME: ");
        nome = input.next();
        System.out.println("#COGNOME: ");
        cognome = input.next();
        System.out.println("#SQUADRA: ");
        squadra = input.next();
        System.out.println("#GOAL_FATTI: ");
        goal_fatti = input.nextInt();
        System.out.println("=====================\n");
        Giocatore secondo = new Giocatore(nome, cognome, squadra, goal_fatti);
        
    //3° Giocatore
        System.out.println("__3° Giocatore__");
        System.out.println("#NOME: ");
        nome = input.next();
        System.out.println("#COGNOME: ");
        cognome = input.next();
        System.out.println("#SQUADRA: ");
        squadra = input.next();
        System.out.println("#GOAL_FATTI: ");
        goal_fatti = input.nextInt();
        System.out.println("=====================\n");
        Giocatore terzo = new Giocatore(nome, cognome, squadra, goal_fatti);
        
    //Classifica cannonieri
        System.out.println("_____ CLASSIFICA CANNONIERI _____");
        if(primo.getGoalFatti()> secondo.getGoalFatti()){ //1>2 si
            if(primo.getGoalFatti() > terzo.getGoalFatti()){ //1>3 si
                System.out.println("1°_ " + primo.getNome() + " " + primo.getCognome() + " _Goal:" + primo.getGoalFatti() + " SQUADRA: " + primo.getSquadra());
                if(secondo.getGoalFatti() > terzo.getGoalFatti()){ //2>3 si allora 1 2 3
                    System.out.println("2°_ " + secondo.getNome() + " " + secondo.getCognome() + " _Goal:" + secondo.getGoalFatti() + " SQUADRA: " + secondo.getSquadra());
                    System.out.println("3°_ " + terzo.getNome() + " " + terzo.getCognome() + " _Goal:" + terzo.getGoalFatti() + " SQUADRA: " + terzo.getSquadra());
                }else{  //2>3 no dopo 1 allora 1 3 2
                    System.out.println("2°_ " + terzo.getNome() + " " + terzo.getCognome() + " _Goal:" + terzo.getGoalFatti() + " SQUADRA: " + terzo.getSquadra());
                    System.out.println("3°_ " + secondo.getNome() + " " + secondo.getCognome() + " _Goal:" + secondo.getGoalFatti() + " SQUADRA: " + secondo.getSquadra());}
            }else{ //1>3 no ma 1>2 allora 3 1 2
                System.out.println("1°_ " + terzo.getNome() + " " + terzo.getCognome() + " _Goal:" + terzo.getGoalFatti() + " SQUADRA: " + terzo.getSquadra());
                System.out.println("2°_ " + primo.getNome() + " " + primo.getCognome() + " _Goal:" + primo.getGoalFatti() + " SQUADRA: " + primo.getSquadra());
                System.out.println("3°_ " + secondo.getNome() + " " + secondo.getCognome() + " _Goal:" + secondo.getGoalFatti() + " SQUADRA: " + secondo.getSquadra());
            }
        }else{ // 1>2 no allora se 2>3
            if(secondo.getGoalFatti() > terzo.getGoalFatti()){ //2>3 si allora sicuramente 2...
                System.out.println("1°_ " + secondo.getNome() + " " + secondo.getCognome() + " _Goal:" + secondo.getGoalFatti() + " SQUADRA: " + secondo.getSquadra());
                if(terzo.getGoalFatti() > primo.getGoalFatti()){ //3>1 allora 2 3 1
                    System.out.println("2°_ " + terzo.getNome() + " " + terzo.getCognome() + " _Goal:" + terzo.getGoalFatti() + " SQUADRA: " + terzo.getSquadra());
                    System.out.println("3°_ " + primo.getNome() + " " + primo.getCognome() + " _Goal:" + primo.getGoalFatti() + " SQUADRA: " + primo.getSquadra());
                }else{ //3>1 no allora 2 1 3
                    System.out.println("2°_ " + primo.getNome() + " " + primo.getCognome() + " _Goal:" + primo.getGoalFatti() + " SQUADRA: " + primo.getSquadra());
                    System.out.println("3°_ " + terzo.getNome() + " " + terzo.getCognome() + " _Goal:" + terzo.getGoalFatti() + " SQUADRA: " + terzo.getSquadra());
                }
            }else{ //2>3 no allora 3 2 1 (1 è sicuramente minore del secondo per via dell'else iniziale
                System.out.println("1°_ " + terzo.getNome() + " " + terzo.getCognome() + " _Goal:" + terzo.getGoalFatti() + " SQUADRA: " + terzo.getSquadra());
                System.out.println("2°_ " + secondo.getNome() + " " + secondo.getCognome() + " _Goal:" + secondo.getGoalFatti() + " SQUADRA: " + secondo.getSquadra());
                System.out.println("3°_ " + primo.getNome() + " " + primo.getCognome() + " _Goal:" + primo.getGoalFatti() + " SQUADRA: " + primo.getSquadra());
            }
        }
        
    }
}