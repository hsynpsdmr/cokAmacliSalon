/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cokamaclisalon;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author hsynpsdmr
 */
public class GirisController {
    public TextField kadi;
	public TextField ksifre;

	public void giris(ActionEvent event) throws IOException {

		if (kadi.getText().equalsIgnoreCase("asd") && ksifre.getText().equalsIgnoreCase("asd")) {
			Parent a = FXMLLoader.load(getClass().getResource("Rezervasyon.fxml"));
			Scene b = new Scene(a);
			Stage apStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			apStage.setScene(b);
			apStage.setTitle("REZERVASYONLAR");
			apStage.show();
		} else if (kadi.getText().equalsIgnoreCase("") || ksifre.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Kullanici adi veya Şifre boş geçilemez!");
		}

		else {
			JOptionPane.showMessageDialog(null, "Kullanici adi veya Şifre yanlış!");
		}
}
}
