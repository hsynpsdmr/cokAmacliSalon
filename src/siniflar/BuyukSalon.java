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
public class BuyukSalon extends Salon implements SalonInterface{   
     public BuyukSalon(){
        setFiyat(this.getFiyat());
        setTur(this.getTur());
        setKapasite(this.getKapasite());
    }
    
    @Override
    public String getTur(){
        return "Büyük Salon";
    }
    
    @Override
    public int getKapasite(){
        return 2000;
    }
    
    @Override
    public double getFiyat(){
        return 3500;
    }
     @Override
    public String toString() {
        return this.getTur();
    }
}
