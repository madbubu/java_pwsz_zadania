/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.pkg3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Marcin
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TableView tableTable;
    
    private final ContactsRepository repository=new ContactsRepository();
    
    private  void fillTable(){
        ObservableList<TableColumn> kolumny = FXCollections.observableArrayList(tableTable.getColumns());
        
        kolumny.get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        kolumny.get(1).setCellValueFactory(new PropertyValueFactory<>("surname"));
        kolumny.get(2).setCellValueFactory(new PropertyValueFactory<>("phone"));
    
        ObservableList<Contact> contacts=repository.getAll();
        tableTable.setItems(contacts);
    
    }
    
    @FXML
    private void addButtonClicked(ActionEvent event){
        Contact contact=showAddEditWindow(new Contact("","",""), event);
        ObservableList contacts=tableTable.getItems();
        contacts.add(contact);
        repository.save(contacts);
 
    }
    
    
    @FXML
    private void editButtonClicked(ActionEvent event){
        Contact selectedContact=(Contact) tableTable.getSelectionModel().getSelectedItem();
        
        if(selectedContact!=null){
            showAddEditWindow(selectedContact, event);
            ObservableList contacts=tableTable.getItems();
            repository.save(contacts);
            fillTable();
        }
        
    }
    
    
    @FXML 
    private void deleteButtoClicked(ActionEvent event){
        int selectedContact=tableTable.getSelectionModel().getSelectedIndex();
        ObservableList contacts=tableTable.getItems();
        contacts.remove(selectedContact);
        repository.save(contacts);
        fillTable();
    }
    
    
    
    public Contact showAddEditWindow(Contact contact, ActionEvent event){
      try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addEditWindow.fxml"));
        
        Parent root = (Parent) loader.load();
        Stage dialogStage = new Stage();
        
        dialogStage.setTitle("Contact data");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        dialogStage.setScene(new Scene(root));
          
        addEditWindowController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setContact(contact);
             
        dialogStage.showAndWait();
            
        return controller.contact;    
      }catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
      }
    }
    
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTable();
    }    
    
}
