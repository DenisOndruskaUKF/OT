package sk.ukf.hw1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class HelloController {
    @FXML
    private Label vysledok;

    @FXML
    private TextField cislo;

    @FXML
    private ComboBox<String> combo;



    @FXML
    public void initialize() {
        combo.getItems().addAll("mm", "cm", "m", "km");
    }

    @FXML
    protected void preved() {
        try{
            double a = Double.parseDouble(cislo.getText());
            String selected = combo.getValue();
            if (selected == null){
                vysledok.setText("chyba: nie je vybrana jednotka");
                return;
            }
        switch (selected){
            case"mm": vysledok.setText("Milimetre = "+(a)+"\n Centimetre = "+(a/10)+"\n Metre = "+(a/1000)+"\n Kilometre = "+(a/1000000));break;
            case "cm": vysledok.setText("Milimetre = "+(a*10)+"\n Centimetre = "+(a)+"\n Metre = "+(a/100)+"\n Kilometre = "+(a/100000));break;
            case"m": vysledok.setText("Milimetre = "+(a*1000)+"\n Centimetre = "+(a*100)+"\n Metre = "+(a)+"\n Kilometre = "+(a/1000));break;
            case"km": vysledok.setText("Milimetre = "+(a*1000000)+"\n Centimetre = "+(a*100000)+"\n Metre = "+(a*1000)+"\n Kilometre = "+(a));break;

        }

        }
        catch (NumberFormatException e){
            vysledok.setText("Zadaj cislo v spravnom formate");
        }

    }
}
