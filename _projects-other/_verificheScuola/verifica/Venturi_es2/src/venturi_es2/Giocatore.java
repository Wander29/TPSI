package venturi_es2;

public class Giocatore {
    String nome, cognome, squadra;
    int goal_fatti = 0;
    
    public Giocatore (String nome, String cognome, String squadra, int goal_fatti){
        this.nome = nome;
        this.cognome = cognome;
        this.squadra = squadra;
        this.goal_fatti = goal_fatti;
    }
    
    public int getGoalFatti(){
        return this.goal_fatti;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getCognome(){
        return this.cognome;
    }
    
    public String getSquadra(){
        return this.squadra;
    }
}
