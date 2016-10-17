/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Marcin
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label labelWynikGracza,labelKartyGracza,labelWynikKomp,labelKartyKomp,labelWynikGry;
    
    @FXML
    private Button buttonDobierzKarte,buttonNowaGra,buttonZatrzymajGre;
    
    Gra karta=new Gra();
    
    
    @FXML
    private void buttonNowaGraClicked(){
        
        buttonDobierzKarte.setDisable(false);
        buttonZatrzymajGre.setDisable(false);
        labelWynikGry.setText("");
        
        String wynik;
        karta.nowaGra();
        wynik=String.format("%d",karta.punktyGracz());
        labelWynikGracza.setText(wynik);
        labelKartyGracza.setText(karta.kartyGracza());
        labelWynikKomp.setText("_");
        labelKartyKomp.setText(karta.kartyKompUkryte());
    }

    
    @FXML
    private void buttonDobierzKarteClicked(){
        karta.dobierzKarte();
        String wynik;
        wynik=String.format("%d",karta.punktyGracz());
        labelWynikGracza.setText(wynik);
        labelKartyGracza.setText(karta.kartyGracza());
        
        if (karta.sprawdzWynikGracz()){
            koniecGry();
        }
            
    }
    
    @FXML
    private void buttonZatrzymajGreClicked(){
        karta.dobierzKarteKomp();
        
        koniecGry();
    }
    
    
    private void koniecGry(){
        buttonDobierzKarte.setDisable(true);
        buttonZatrzymajGre.setDisable(true);
        
        String wynik2;
        wynik2=String.format("%d",karta.punktyKomp());
        labelWynikKomp.setText(wynik2);
        labelKartyKomp.setText(karta.kartyKomp());
        
        
        labelWynikGry.setText(karta.wynikGry());
 
    }
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
