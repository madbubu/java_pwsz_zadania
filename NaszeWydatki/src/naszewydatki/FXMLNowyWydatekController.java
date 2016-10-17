/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naszewydatki;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Marcin
 */
public class FXMLNowyWydatekController implements Initializable {

    @FXML
    private ComboBox comboKategorie;
    @FXML
    private DatePicker poleData;
    @FXML
    private TextField poleOpis, poleKwota;
    @FXML
    private ToggleGroup Typ;
    @FXML
    private Stage dialogStage;
    @FXML
    private RadioButton typWydatek, typPrzychod;
    @FXML
    private Label labelId;
    
    private final ObslugaDB connect=new ObslugaDB();
   
    public Wydatek wydatek;
    
    
    
    @FXML
    private void saveButtonClicked(){
      
       
        String stringData=(poleData.getValue()).format(DateTimeFormatter.ISO_DATE);
        double kwota=kwotaDouble(poleKwota.getText());
        
        String wybraneCombo=comboKategorie.getSelectionModel().getSelectedItem().toString();

      
        int indeks=Integer.parseInt(labelId.getText());
        
        //wydatek=new Wydatek(0,stringData,kwota,poleOpis.getText(),wybraneCombo, 1);
        
        wydatek.setIndeks(indeks);
        wydatek.setData(stringData);
        wydatek.setKwota(kwota);
        wydatek.setOpis(poleOpis.getText());
        wydatek.setKategoria(wybraneCombo);
        wydatek.setNrBudzetu(1);
       
        dialogStage.close();

    }
    
    public void setDialogStage(Stage dialogStage){
        this.dialogStage=dialogStage;
    }
    

    public void setWydatek(Wydatek wydatek){
        this.wydatek=wydatek;
        labelId.setText(Integer.toString(this.wydatek.getIndeks()));
        poleOpis.setText(this.wydatek.getOpis());
        poleData.setValue(LocalDate.parse(this.wydatek.getDatawydatek()));
        String kwotaString=Double.toString(this.wydatek.getKwota());
        poleKwota.setText(kwotaString);
        comboKategorie.setValue(this.wydatek.getKategoria());
        
        if (this.wydatek.getKwota()>0){
             typPrzychod.setSelected(true);
        }else{
            typWydatek.setSelected(true);
        }
           
       
        

    }
    
    
    
    private Double kwotaDouble(String kwota){
        
        
        
        double kwota_temp= Double.valueOf(kwota);
        
        
        if (typWydatek.selectedProperty().getValue()){
          if (kwota_temp>0){
              kwota_temp=kwota_temp*-1.0;
          }  
        }
        
        return kwota_temp;
    }
    
    
    
    
    
    private void fillCombo(){
        ObservableList<Kategoria> kategorie = connect.getCategories();
       comboKategorie.setItems(kategorie); 
    }
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCombo();
    }    
    
}
