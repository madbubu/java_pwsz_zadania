/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.pkg3;

/**
 *
 * @author Marcin
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addEditWindowController implements Initializable {
    
    @FXML 
    private TextField textName, textSurname, textPhone;
    
    private Stage dialogStage;
   
    public Contact contact;
    
    
    @FXML
    private void saveButtonClicked(){
        
        contact.setName(textName.getText());
        contact.setSurname(textSurname.getText());
        contact.setPhone(textPhone.getText());
        
        dialogStage.close();
        
    }
    
    
    
    
    
    
    
    
    public void setDialogStage(Stage dialogStage){
        this.dialogStage=dialogStage;
    }
    
    public void setContact(Contact contact){
        this.contact=contact;
        
        textName.setText(this.contact.getName());
        textSurname.setText(this.contact.getSurname());
        textPhone.setText(this.contact.getPhone());
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    
}
