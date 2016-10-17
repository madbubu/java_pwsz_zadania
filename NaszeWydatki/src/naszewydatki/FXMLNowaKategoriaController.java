/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naszewydatki;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marcin
 */
public class FXMLNowaKategoriaController implements Initializable {

   
    @FXML
    private Stage dialogStage;
    @FXML
    private Label labelId;
    @FXML
    private TextField poleNazwa;
    
    
    public Kategoria kategoria;
    
   
    
    
    
    @FXML 
    private void saveButtonClicked(){
        
        int indeks=Integer.parseInt(labelId.getText());
        
        
        
        kategoria.SetNrKategorii(indeks);
        kategoria.SetKategoria(poleNazwa.getText());
        dialogStage.close();
    }
   
    public void setDialogStage(Stage dialogStage){
        this.dialogStage=dialogStage;
    }
    
    
    public void setKategoria(Kategoria kategoria){
        this.kategoria=kategoria;
        String labelIdtekst=Integer.toString(this.kategoria.nrkategorii);
        labelId.setText(labelIdtekst);
        poleNazwa.setText(this.kategoria.getKategoria());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
