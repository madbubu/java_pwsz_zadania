/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Marcin
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private ToggleGroup Input_scale,Output_scale;
    @FXML private TextField input_val;
    @FXML private Label output_val,alertLabel;
    
    @FXML 
    private void convert(ActionEvent event){
     try {   
     int selectedIn, selectedOut;     
        
     Double inValue;
     String inString=input_val.getText().trim();
     
     
     
   String regular_exp="[-+]?\\d*\\.?\\d+";
   
   if(inString.matches(regular_exp)){
       alertLabel.setVisible(false);
   }else{
       alertLabel.setVisible(true);
   }
   
     
     inValue=Double.parseDouble(inString);
     
     
     
     
     RadioButton radioIn=(RadioButton) Input_scale.getSelectedToggle();
     RadioButton radioOut=(RadioButton) Output_scale.getSelectedToggle();
     
     
     selectedIn=1;
     selectedOut=1;
     
     switch (radioIn.getText()){
         case "Celcius":
             selectedIn=1;
             break;
         case "Fahrenheit":
             selectedIn=2;
             break;
         case "Kelvin":
             selectedIn=3;
             break; 
     }
     
         switch (radioOut.getText()){
         case "Celcius":
             selectedOut=1;
             break;
         case "Fahrenheit":
             selectedOut=2;
             break;
         case "Kelvin":
             selectedOut=3;
             break; 
     }
     
         double outValue=Converter.convertTemperature(inValue, selectedIn, selectedOut);
         
         output_val.setText(Double.toString(outValue));
         //output_val.setText("tekst");
     
     }
      catch(NumberFormatException e) {
          
        }
        
    }
    
    
    
    
    

    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
