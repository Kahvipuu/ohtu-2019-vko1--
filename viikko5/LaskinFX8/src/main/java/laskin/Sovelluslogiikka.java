package laskin;

import java.util.HashMap;

public class Sovelluslogiikka {
 
    private int tulos;
    private HashMap<String, Komento> komennot;
    
    public Sovelluslogiikka(){
        komennot = new HashMap<>();
        komennot.put("summa", new Summa());
        komennot.put("erotus", new Erotus());
    }
    
    public void plus(int luku) {
        tulos = komennot.get("summa").suorita(tulos, luku);
    }
     
    public void miinus(int luku) {
        tulos = komennot.get("erotus").suorita(tulos, luku);
    }
 
    public void nollaa() {
        tulos = 0;
    }
 
    public int tulos() {
        return tulos;
    }
}