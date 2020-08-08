/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siniflar;

import java.time.LocalDate;

/**
 *
 * @author hsynpsdmr
 */
public class Rezervasyon {

    private String ad;
    private String soyad;
    private String adres;
    private String telefon;
    private LocalDate tarih;
    private String saat;
    private Salon salon;
    private Organizasyon organizasyon;
    private Arac arac;
    private Ekstralar ekstra  = new Ekstralar();
    private double ucret;
    Boolean aktifMi;

    public Boolean getAktifMi() {
        return aktifMi;
    }

    public void setAktifMi(Boolean aktifMi) {
        this.aktifMi = aktifMi;
    }

    public double getUcret() {
        return ucret;
    }

    public void setUcret(double ucret) {
        this.ucret = ucret;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public LocalDate getTarih() {
        return tarih;
    }

    public void setTarih(LocalDate tarih) {
        this.tarih = tarih;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }


    public Organizasyon getOrganizasyon() {
        return organizasyon;
    }

    public void setOrganizasyon(Organizasyon organizasyon) {
        this.organizasyon = organizasyon;
    }

    public Arac getArac() {
        return arac;
    }

    public void setArac(Arac arac) {
        this.arac = arac;
    }

    public Ekstralar getEkstra() {
        return ekstra;
    }

    public void setEkstra(Ekstralar ekstra) {
        this.ekstra = ekstra;
    }

    public void setEkstra(String ekstra) {
    this.ekstra.setYemek(ekstra.contains("Yemek"));
    this.ekstra.setPasta(ekstra.contains("Pasta"));
    this.ekstra.setTatli(ekstra.contains("Tatlı"));
    this.ekstra.setCekim(ekstra.contains("Çekim"));
    this.ekstra.setOrkestra(ekstra.contains("Orkestra"));
    this.ekstra.setPalyaco(ekstra.contains("Palyaço"));
    this.ekstra.setPatlayicilar(ekstra.contains("Patlayıcılar"));
    }

}
