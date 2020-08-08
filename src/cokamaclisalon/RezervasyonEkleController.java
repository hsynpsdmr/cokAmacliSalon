/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cokamaclisalon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import siniflar.AnaSalon;
import siniflar.Arac;
import siniflar.BuyukSalon;
import siniflar.Eglence;
import siniflar.Ekstralar;
import siniflar.KucukSalon;
import siniflar.Merasim;
import siniflar.Organizasyon;
import siniflar.Rezervasyon;
import siniflar.Salon;

public class RezervasyonEkleController {

    public TextField mAd;
    String ad;
    public TextField mSoyad;
    String soyad;
    public TextField mAdres;
    String adres;
    public TextField mTel;
    String tel;
    public DatePicker rTarih;
    String tarih;
    public TextField rSaat;
    String saat;
    public ComboBox<String> salonCB;
    Salon salon;
    public ComboBox<String> organizasyonCB;
    Organizasyon organizasyon;
    public ComboBox<String> aracCB;
    Arac arac;
    public CheckBox yemekCB;
    public CheckBox pastaCB;
    public CheckBox tatliCB;
    public CheckBox cekimCB;
    public CheckBox orkestraCB;
    public CheckBox palyacoCB;
    public CheckBox patlayicilarCB;
    public Label ucretLabel;
    private int guncellenecekIndex;
    private Boolean guncellenecekMi;
    Ekstralar ekstralar = new Ekstralar();

    @FXML
    public void initialize() throws FileNotFoundException {
        ucretLabel.setText("0 TL");
    }

    public void setGuncellenecekMi(Boolean guncellenecekMi, int index) throws FileNotFoundException {
        this.guncellenecekMi = guncellenecekMi;
        this.guncellenecekIndex = index;
        if (guncellenecekMi) {
            ArrayList<Rezervasyon> list = getRezervasyonList();
            Rezervasyon r = list.get(index);
            mAd.setText(r.getAd());
            mSoyad.setText(r.getSoyad());
            mAdres.setText(r.getAdres());
            mTel.setText(r.getTelefon());
            rTarih.setValue(r.getTarih());
            rSaat.setText(r.getSaat());
            salonCB.setValue(r.getSalon().getTur());
            aracCB.setValue(r.getArac().getTur());
            organizasyonCB.setValue(r.getOrganizasyon().getTur());
            yemekCB.setSelected(r.getEkstra().getYemek());
            pastaCB.setSelected(r.getEkstra().getPasta());
            tatliCB.setSelected(r.getEkstra().getTatli());
            cekimCB.setSelected(r.getEkstra().getCekim());
            orkestraCB.setSelected(r.getEkstra().getOrkestra());
            palyacoCB.setSelected(r.getEkstra().getPalyaco());
            patlayicilarCB.setSelected(r.getEkstra().getPatlayicilar());
        }
    }

    public void geri(ActionEvent event) throws IOException {
        Parent a = FXMLLoader.load(getClass().getResource("Rezervasyon.fxml"));
        Scene b = new Scene(a);
        Stage apStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        apStage.setScene(b);
        apStage.setTitle("REZERVASYONLAR");
        apStage.show();
    }

    public void hesapla(ActionEvent event) throws IOException {
        if (girdilerGecerliMi()) {
            ucretHesapla();
        }
    }

    public void kaydet(ActionEvent event) throws IOException {
        if (girdilerGecerliMi()) {
            if (guncellenecekMi) {
                sil(guncellenecekIndex);
            }
            String ekstralarString = ekstralar.getEkstralar();

            double ucret = ucretHesapla();

            File dosya = new File("Rezervasyonlar.txt");
            if (!dosya.exists()) {
                dosya.createNewFile();
            }

            FileWriter dYazici = new FileWriter(dosya, true);
            BufferedWriter yazici = new BufferedWriter(dYazici);
            yazici.newLine();
            yazici.append("Rezervasyon :");
            yazici.newLine();
            yazici.append("AD");
            yazici.newLine();
            yazici.append(ad);
            yazici.newLine();
            yazici.append("SOYAD");
            yazici.newLine();
            yazici.append(soyad);
            yazici.newLine();
            yazici.append("ADRES");
            yazici.newLine();
            yazici.append(adres);
            yazici.newLine();
            yazici.append("TELEFON");
            yazici.newLine();
            yazici.append(tel);
            yazici.newLine();
            yazici.append("TARİH");
            yazici.newLine();
            yazici.append(tarih);
            yazici.newLine();
            yazici.append("SAAT");
            yazici.newLine();
            yazici.append(saat);
            yazici.newLine();
            yazici.append("SALON");
            yazici.newLine();
            yazici.append(salon.toString());
            yazici.newLine();
            yazici.append("ORGANİZASYON");
            yazici.newLine();
            yazici.append(organizasyon.toString());
            yazici.newLine();
            yazici.append("ARAÇ");
            yazici.newLine();
            yazici.append(arac.getTur());
            yazici.newLine();
            yazici.append("EKSTRA");
            yazici.newLine();
            yazici.append(ekstralar.getEkstralar());
            yazici.newLine();
            yazici.append("ÜCRET");
            yazici.newLine();
            yazici.append(Double.toString(ucret));
            yazici.newLine();
            yazici.append("AKTİF Mİ");
            yazici.newLine();
            yazici.append("true");
            yazici.newLine();
            yazici.append("***");

            yazici.close();

            JOptionPane.showMessageDialog(null, "Rezervasyon Kaydedildi");
            Parent a = FXMLLoader.load(getClass().getResource("Rezervasyon.fxml"));
            Scene b = new Scene(a);
            Stage apStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            apStage.setScene(b);
            apStage.setTitle("REZERVASYONLAR");
            apStage.show();

        }
    }

    private ArrayList<Rezervasyon> getRezervasyonList() throws FileNotFoundException {
        ArrayList<Rezervasyon> list = new ArrayList<>();
        File file = new File("Rezervasyonlar.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        String oku;

        try {
            while ((oku = br.readLine()) != null) {
                if (oku.equalsIgnoreCase("Rezervasyon :")) {
                    Rezervasyon rezervasyon = new Rezervasyon();
                    while (oku != null && !oku.equalsIgnoreCase("***")) {
                        if (oku.equalsIgnoreCase("AD")) {
                            oku = br.readLine();
                            rezervasyon.setAd(oku);
                        }
                        if (oku.equalsIgnoreCase("SOYAD")) {
                            oku = br.readLine();
                            rezervasyon.setSoyad(oku);

                        }
                        if (oku.equalsIgnoreCase("ADRES")) {
                            oku = br.readLine();
                            rezervasyon.setAdres(oku);
                        }
                        if (oku.equalsIgnoreCase("TELEFON")) {
                            oku = br.readLine();
                            rezervasyon.setTelefon(oku);
                        }
                        if (oku.equalsIgnoreCase("TARİH")) {
                            oku = br.readLine();
                            rezervasyon.setTarih(LocalDate.parse(oku));
                        }
                        if (oku.equalsIgnoreCase("SAAT")) {
                            oku = br.readLine();
                            rezervasyon.setSaat(oku);
                        }
                        if (oku.equalsIgnoreCase("SALON")) {
                            oku = br.readLine();
                            if (oku.equalsIgnoreCase("Ana Salon")) {
                                rezervasyon.setSalon(new AnaSalon());
                            }
                            if (oku.equalsIgnoreCase("Büyük Salon")) {
                                rezervasyon.setSalon(new BuyukSalon());
                            }
                            if (oku.equalsIgnoreCase("Küçük Salon")) {
                                rezervasyon.setSalon(new KucukSalon());
                            }
                        }
                        if (oku.equalsIgnoreCase("ORGANİZASYON")) {
                            oku = br.readLine();
                            if (oku.equalsIgnoreCase("Düğün")
                                    || oku.equalsIgnoreCase("Nişan")
                                    || oku.equalsIgnoreCase("Kına")
                                    || oku.equalsIgnoreCase("Sünnet")) {
                                rezervasyon.setOrganizasyon(new Merasim(oku));
                            } else {
                                rezervasyon.setOrganizasyon(new Eglence(oku));
                            }
                        }
                        if (oku.equalsIgnoreCase("ARAÇ")) {
                            oku = br.readLine();
                            rezervasyon.setArac(new Arac(oku));
                        }
                        if (oku.equalsIgnoreCase("EKSTRA")) {
                            oku = br.readLine();
                            rezervasyon.setEkstra(oku);
                        }
                        if (oku.equalsIgnoreCase("ÜCRET")) {
                            oku = br.readLine();
                            rezervasyon.setUcret(Double.parseDouble(oku));
                        }

                        if (oku.equalsIgnoreCase("AKTİF Mİ")) {
                            oku = br.readLine();
                            rezervasyon.setAktifMi(oku.equalsIgnoreCase("true"));
                        }

                        oku = br.readLine();
                    }
                    if (rezervasyon != null && rezervasyon.getAktifMi()) {
                        list.add(rezervasyon);
                    }
                }
            }

        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Hata !");

        }
        return list;
    }

    private Boolean girdilerGecerliMi() throws FileNotFoundException {
        ad = mAd.getText();
        soyad = mSoyad.getText();
        adres = mAdres.getText();
        tel = mTel.getText();
        saat = rSaat.getText();
        String salonString = salonCB.getSelectionModel().getSelectedItem();
        String organizasyonString = organizasyonCB.getSelectionModel().getSelectedItem();
        String aracString = aracCB.getSelectionModel().getSelectedItem();

        if (ad.equalsIgnoreCase("") || soyad.equalsIgnoreCase("") || adres.equalsIgnoreCase("") || tel.equalsIgnoreCase("") || saat.equalsIgnoreCase("") || rTarih.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz");
            return false;
        } else {
            if (salonString == null || organizasyonString == null || aracString == null) {
                JOptionPane.showMessageDialog(null, "Lütfen salon, organizasyon ve araç seçimi yapınız");
                return false;
            }
        }
        
         salon = new Salon();
        if (salonString.equalsIgnoreCase("Ana Salon")) {
            salon = new AnaSalon();
        }
        if (salonString.equalsIgnoreCase("Büyük Salon")) {
            salon = new BuyukSalon();
        }
        if (salonString.equalsIgnoreCase("Küçük Salon")) {
            salon = new KucukSalon();
        }
        
        
        tarih = rTarih.getValue().toString();
        if (indexBul(rTarih.getValue(), saat, salon) != -1 && !guncellenecekMi) {
            JOptionPane.showMessageDialog(null, "Bu salonda bu tarih ve saatte bir rezervasyon zaten mevcut");
            return false;
        }
        
        organizasyon = new Organizasyon();
        if (organizasyonString.equalsIgnoreCase("Düğün")
                || organizasyonString.equalsIgnoreCase("Nişan")
                || organizasyonString.equalsIgnoreCase("Kına")
                || organizasyonString.equalsIgnoreCase("Sünnet")) {

            organizasyon = new Merasim(organizasyonString);
        } else {
            organizasyon = new Eglence(organizasyonString);
        }

       
        ekstralar.setYemek(yemekCB.isSelected());
        ekstralar.setPasta(pastaCB.isSelected());
        ekstralar.setTatli(tatliCB.isSelected());
        ekstralar.setCekim(cekimCB.isSelected());
        ekstralar.setOrkestra(orkestraCB.isSelected());
        ekstralar.setPalyaco(palyacoCB.isSelected());
        ekstralar.setPatlayicilar(patlayicilarCB.isSelected());
        arac = new Arac(aracString);
        return true;
    }

    private double ucretHesapla() {
        double ucret = salon.getFiyat() + arac.getFiyat() + ekstralar.getFiyat() + organizasyon.getFiyat();
        ucretLabel.setText(Double.toString(ucret) + " TL");
        return ucret;
    }

    private void sil(int index) throws IOException {
        ArrayList<Rezervasyon> list = getRezervasyonList();
        list.get(index).setAktifMi(false);

        File dosya = new File("Rezervasyonlar.txt");
        if (!dosya.exists()) {
            dosya.createNewFile();
        }

        FileWriter bosDYazici = new FileWriter(dosya);
        BufferedWriter bosYazici = new BufferedWriter(bosDYazici);
        bosYazici.close();

        FileWriter dYazici = new FileWriter(dosya, true);
        BufferedWriter yazici = new BufferedWriter(dYazici);
        yazici.append("***");
        for (Rezervasyon r : list) {
            yazici.newLine();
            yazici.append("Rezervasyon :");
            yazici.newLine();
            yazici.append("AD");
            yazici.newLine();
            yazici.append(r.getAd());
            yazici.newLine();
            yazici.append("SOYAD");
            yazici.newLine();
            yazici.append(r.getSoyad());
            yazici.newLine();
            yazici.append("ADRES");
            yazici.newLine();
            yazici.append(r.getAdres());
            yazici.newLine();
            yazici.append("TELEFON");
            yazici.newLine();
            yazici.append(r.getTelefon());
            yazici.newLine();
            yazici.append("TARİH");
            yazici.newLine();
            yazici.append(r.getTarih().toString());
            yazici.newLine();
            yazici.append("SAAT");
            yazici.newLine();
            yazici.append(r.getSaat());
            yazici.newLine();
            yazici.append("SALON");
            yazici.newLine();
            yazici.append(r.getSalon().toString());
            yazici.newLine();
            yazici.append("ORGANİZASYON");
            yazici.newLine();
            yazici.append(r.getOrganizasyon().getTur());
            yazici.newLine();
            yazici.append("ARAÇ");
            yazici.newLine();
            yazici.append(r.getArac().getTur());
            yazici.newLine();
            yazici.append("EKSTRA");
            yazici.newLine();
            yazici.append(r.getEkstra().getEkstralar());
            yazici.newLine();
            yazici.append("ÜCRET");
            yazici.newLine();
            yazici.append(Double.toString(r.getUcret()));
            yazici.newLine();
            yazici.append("AKTİF Mİ");
            yazici.newLine();
            if (r.getAktifMi()) {
                yazici.append("true");
            } else {
                yazici.append("false");
            }
            yazici.newLine();
            yazici.append("***");
        }
        yazici.close();
    }

    private int indexBul(LocalDate tarih, String saat) throws FileNotFoundException {
        ArrayList<Rezervasyon> list = getRezervasyonList();
        for (Rezervasyon rezervasyon : list) {
            if (rezervasyon.getTarih().equals(tarih) && rezervasyon.getSaat().equalsIgnoreCase(saat)) {
                return list.indexOf(rezervasyon);
            }
        }
        return -1;
    }

    private int indexBul(LocalDate tarih, String saat, Salon salon) throws FileNotFoundException {
        ArrayList<Rezervasyon> list = getRezervasyonList();
        for (Rezervasyon rezervasyon : list) {
            if (rezervasyon.getTarih().equals(tarih) && rezervasyon.getSaat().equalsIgnoreCase(saat)
                    && rezervasyon.getSalon().toString().equalsIgnoreCase(salon.toString())) {
                return list.indexOf(rezervasyon);
            }
        }
        return -1;
    }
}
