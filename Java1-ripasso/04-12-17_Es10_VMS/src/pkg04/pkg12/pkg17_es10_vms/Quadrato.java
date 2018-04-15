package pkg04.pkg12.pkg17_es10_vms;

public class Quadrato {
    
    /*attributi*/
    private double lato = 0;
    
    /*costruttore*/
    public Quadrato(double lato){
        this.lato = lato; 
    }
    
    /*metodi della classe*/
    public double getPerimetro(){
        return lato*4;
    }
    
    public double getArea(){
        return lato*lato;
    }
}
