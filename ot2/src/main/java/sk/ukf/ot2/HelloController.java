package sk.ukf.ot2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class HelloController {
    @FXML
    private TextField text;

    @FXML
    private Label vysledok;

    @FXML
   private void initialize(){
        vysledok.setText("---");
    }
    @FXML
    private void premen(ActionEvent actionEvent){

            String a = text.getText();
            int poc = 1;

            Button button = (Button) actionEvent.getSource();
            String operator = button.getText();

            switch (operator) {
                case "Velke":
                    a = a.toUpperCase();
                    break;
                case "Male":
                    a = a.toLowerCase();
                    break;
                case "Pocet znakov":
                    a = String.valueOf(a.length());
                    break;
                case "Prve velke pismeno kazdeho slova":
                   StringBuilder sb = new StringBuilder();
                   boolean dalsieVelke = true;
                   for(int i = 0; i < a.length(); i++){
                       char c = a.charAt(i);
                       if(c == ' '){
                           dalsieVelke = true;
                           sb.append(c);
                       }else if (dalsieVelke){
                           sb.append(Character.toUpperCase(c));
                           dalsieVelke = false;
                       } else {
                           sb.append(c);
                       }
                   }
                   a= sb.toString();
                   break;
                case "Pocet slov":
                    for (int i = 0; i < a.length(); i++) {
                        if (a.charAt(i) == ' ') poc++;
                    }
                    a = String.valueOf(poc);
                    break;
                case "Obrat text":
                    a = new StringBuilder(a).reverse().toString();
                    break;
                case "Vymaz":
                    vysledok.setText("---");
                    break;



            }
            vysledok.setText(a);


        }

    }

