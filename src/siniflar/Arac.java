/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siniflar;

/**
 *
 * @author Seyf
 */
public class Arac {
    String tur;
    double fiyat;

    public Arac(){
 
    }
    
    public Arac(String tur){
        setTur(tur);
    }
    
    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public double getFiyat() {
       if(tur.equalsIgnoreCase("Limuzin")){
           return 1000;
       }
       if(tur.equalsIgnoreCase("Klasik")){
           return 500;
       }
       if(tur.equalsIgnoreCase("Toplu Taşıma")){
           return 100;
       }
       return 0;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    @Override
    public String toString() {
        return this.getTur();
    }
}
