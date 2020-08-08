/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siniflar;

/**
 *
 * @author hsynpsdmr
 */
public class Ekstralar {

    private Boolean yemek;
    private Boolean pasta;
    private Boolean tatli;
    private Boolean cekim;
    private Boolean orkestra;
    private Boolean palyaco;
    private Boolean patlayicilar;

    public Boolean getYemek() {
        return yemek;
    }

    public void setYemek(Boolean yemek) {
        this.yemek = yemek;
    }

    public Boolean getPasta() {
        return pasta;
    }

    public void setPasta(Boolean pasta) {
        this.pasta = pasta;
    }

    public Boolean getTatli() {
        return tatli;
    }

    public void setTatli(Boolean tatli) {
        this.tatli = tatli;
    }

    public Boolean getCekim() {
        return cekim;
    }

    public void setCekim(Boolean cekim) {
        this.cekim = cekim;
    }

    public Boolean getOrkestra() {
        return orkestra;
    }

    public void setOrkestra(Boolean orkestra) {
        this.orkestra = orkestra;
    }

    public Boolean getPalyaco() {
        return palyaco;
    }

    public void setPalyaco(Boolean palyaco) {
        this.palyaco = palyaco;
    }

    public Boolean getPatlayicilar() {
        return patlayicilar;
    }

    public void setPatlayicilar(Boolean patlayicilar) {
        this.patlayicilar = patlayicilar;
    }

    public String getEkstralar() {

        String ekstralar = "";
        if (yemek) {
            ekstralar += "-Yemek ";
        }
        if (pasta) {
            ekstralar += "-Pasta  ";
        }
        if (tatli) {
            ekstralar += "-Tatlı  ";
        }
        if (cekim) {
            ekstralar += "-Çekim  ";
        }
        if (orkestra) {
            ekstralar += "-Orkestra  ";
        }
        if (palyaco) {
            ekstralar += "-Palyaço  ";
        }
        if (patlayicilar) {
            ekstralar += "-Patlayıcılar  ";
        }

        if(ekstralar.equalsIgnoreCase("")){
            ekstralar = "Ekstra Yok";
        }
        return ekstralar;
    }
    
    public double getFiyat() {

        double fiyat = 0;
        if (yemek) {
            fiyat += 500;
        }
        if (pasta) {
            fiyat += 200;
        }
        if (tatli) {
            fiyat += 150;
        }
        if (cekim) {
            fiyat += 150;
        }
        if (orkestra) {
            fiyat += 200;
        }
        if (palyaco) {
            fiyat += 100;
        }
        if (patlayicilar) {
            fiyat += 50;
        }

        return fiyat;
    }

    @Override
    public String toString() {
        return this.getEkstralar();
    }
}
