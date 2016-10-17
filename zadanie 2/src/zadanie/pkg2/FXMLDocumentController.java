/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.pkg2;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


/**
 *
 * @author Marcin
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button button;
    
    @FXML
    private void whenOn(){
        Random los=new Random();
        
        Integer x, y;
        x=los.nextInt(400);
        y=los.nextInt(400);
        
        button.setLayoutX(x);
        button.setLayoutY(y);
        
        
    }

    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       System.exit(0);
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
