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
public class Eglence extends Organizasyon implements OrganizasyonInterface {
     public Eglence(String tur){
        setFiyat(this.getFiyat());
        setTur(tur);
    }
    
    
    @Override
    public double getFiyat(){
        return 1000;
    }
     @Override
    public String toString() {
        return getTur();
    }
}
