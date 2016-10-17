/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naszewydatki;

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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marcin
 */
public class FXMLSzczegolyController implements Initializable {
    @FXML
    private TableView tableTable;
    
    private final ObslugaDB connect=new ObslugaDB();
    
    //FXMLDocumentController controllerDocument;
    
    @FXML
    private void addButtonClicked(ActionEvent event){
        Wydatek wydatek=showAddWindow(new Wydatek(), event);
        if(wydatek.getKwota()!=0){
            wydatek.add();  
        }
        fillTable();
 
    }
    
    
    @FXML 
    private void editButtonClicked(ActionEvent event){
        Wydatek wybranyWydatek=(Wydatek) tableTable.getSelectionModel().getSelectedItem();
        
        if(wybranyWydatek!=null){
        showAddWindow(wybranyWydatek, event);
        wybranyWydatek.update();
        
        fillTable();
    }
        
    }
    
    
    @FXML
    private void deleteButtonClicked(ActionEvent event){
        Wydatek wybranyWydatek=(Wydatek) tableTable.getSelectionModel().getSelectedItem();
        if(wybranyWydatek!=null){
            wybranyWydatek.delete();
            fillTable();
        }
    }
    
    
    
    
    
     public Wydatek showAddWindow(Wydatek wydatek, ActionEvent event){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLNowyWydatek.fxml"));
            Parent root = (Parent) loader.load();
             Stage dialogStage = new Stage();
             dialogStage.setTitle("Wydatki/Przychody");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            
            
            
            FXMLNowyWydatekController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setWydatek(wydatek);
             
            
            dialogStage.showAndWait();
          
            
           return controller.wydatek;
        }catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
    }
    }
    
    
    
    
    
    
    
    
    
    
    
    private void fillTable(){
        ObservableList<TableColumn> kolumny = FXCollections.observableArrayList(tableTable.getColumns());
        kolumny.get(0).setCellValueFactory(new PropertyValueFactory<>("indeks"));
        kolumny.get(1).setCellValueFactory(new PropertyValueFactory<Wydatek, String>("datawydatek"));
        kolumny.get(2).setCellValueFactory(new PropertyValueFactory<>("kwota"));
        kolumny.get(3).setCellValueFactory(new PropertyValueFactory<>("opis"));
        kolumny.get(4).setCellValueFactory(new PropertyValueFactory<>("kategoria"));
       
       
      // System.out.println(kolumny.size());
        
        ObservableList<Wydatek> all2 = connect.getAll2();
      
        tableTable.setItems(all2);
    }
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTable();
    }    
    
}
