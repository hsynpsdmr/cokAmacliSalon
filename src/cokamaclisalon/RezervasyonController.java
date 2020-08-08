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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import siniflar.AnaSalon;
import siniflar.Arac;
import siniflar.BuyukSalon;
import siniflar.Eglence;
import siniflar.KucukSalon;
import siniflar.Merasim;
import siniflar.Rezervasyon;

/**
 *
 * @author hsynpsdmr
 */
public class RezervasyonController {

    public TableView<Rezervasyon> table;

    @FXML
    public void initialize() throws FileNotFoundException {
        table.setEditable(true);
        rezervasyonListele();
    }

    public void ekle(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RezervasyonEkle.fxml"));
        Scene b = new Scene(loader.load());
        Stage apStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        apStage.setScene(b);
        apStage.setTitle("Rezervasyon Ekle");
        RezervasyonEkleController controller = loader.<RezervasyonEkleController>getController();
        controller.setGuncellenecekMi(false, 0);
        apStage.show();
    }

    public void guncelle(ActionEvent event) throws IOException {
        Rezervasyon rezervasyon = table.getSelectionModel().getSelectedItem();
        ArrayList<Rezervasyon> list = getRezervasyonList();
        if (rezervasyon == null) {
            JOptionPane.showMessageDialog(null, "Lütfen güncellenecek satırı seçiniz");
        } else {
            int index = indexBul(rezervasyon.getTarih(), rezervasyon.getSaat());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RezervasyonEkle.fxml"));
            Scene b = new Scene(loader.load());
            Stage apStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            apStage.setScene(b);
            apStage.setTitle("Rezervasyon Ekle");
            RezervasyonEkleController controller = loader.<RezervasyonEkleController>getController();
            controller.setGuncellenecekMi(true, index);
            apStage.show();
        }
    }

    public void sil(ActionEvent event) throws IOException {
        Rezervasyon rezervasyon = table.getSelectionModel().getSelectedItem();
        ArrayList<Rezervasyon> list = getRezervasyonList();
        if (rezervasyon != null) {
            int index = indexBul(rezervasyon.getTarih(), rezervasyon.getSaat());
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
                yazici.append(r.getOrganizasyon().toString());
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

            rezervasyonListele();
        }

    }

    private void rezervasyonListele() throws FileNotFoundException {
        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("ad"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("soyad"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("adres"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("telefon"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("tarih"));
        table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("saat"));
        table.getColumns().get(6).setCellValueFactory(new PropertyValueFactory("salon"));
        table.getColumns().get(7).setCellValueFactory(new PropertyValueFactory("organizasyon"));
        table.getColumns().get(8).setCellValueFactory(new PropertyValueFactory("arac"));
        table.getColumns().get(9).setCellValueFactory(new PropertyValueFactory("ekstra"));
        table.getColumns().get(10).setCellValueFactory(new PropertyValueFactory("ucret"));

        ArrayList<Rezervasyon> list = getRezervasyonList();
        ObservableList data = FXCollections.observableList(list);
        table.setItems(data);
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
                            }
                            else {
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

    private int indexBul(LocalDate tarih, String saat) throws FileNotFoundException {
        ArrayList<Rezervasyon> list = getRezervasyonList();
        for (Rezervasyon rezervasyon : list) {
            if (rezervasyon.getTarih().equals(tarih) && rezervasyon.getSaat().equalsIgnoreCase(saat)) {
                return list.indexOf(rezervasyon);
            }
        }
        return -1;
    }
}
