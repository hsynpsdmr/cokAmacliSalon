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
public class KucukSalon extends Salon implements SalonInterface{   
    public KucukSalon(){
        setFiyat(this.getFiyat());
        setTur(this.getTur());
        setKapasite(this.getKapasite());
    }
    
    @Override
    public String getTur(){
        return "Küçük Salon";
    }
    
    @Override
    public int getKapasite(){
        return 50;
    }
    
    @Override
    public double getFiyat(){
        return 1000;
    }

    @Override
    public String toString() {
        return this.getTur();
    }
    
}
