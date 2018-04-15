package pkg04.pkg12.pkg17_es12_vms;

public class Motorino {
    private String colore, tipo;
    private float velocita;
    private boolean antifurto = false;
    
    public Motorino(String colore, String tipo, float velocita){
        this.colore = colore;
        this.tipo = tipo;
        this.velocita = velocita;
    }
    
    public float getVelocita(){
        return velocita;
    }
    
    public void accelera(float accelerazione) {
        if (!antifurto){
            velocita += accelerazione;
        }
    }
    
    public void inserisciAntifurto(){
        if (!antifurto){
            antifurto = true;
        }
    }
}
