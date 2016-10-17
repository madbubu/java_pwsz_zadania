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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Marcin
 */
public class FXMLKategorieController implements Initializable {

    @FXML
    private TableView tableTable;
    
    private final ObslugaDB connect=new ObslugaDB();
    
    
    
    @FXML
    private void addButtonClicked(ActionEvent event){
        //Wydatek wydatek=showAddWindow(new Wydatek(), event);
        Kategoria kategoria=showAddWindow(new Kategoria(), event);
        //System.out.println(kategoria.getKategoria());
        if(kategoria.getKategoria()!=""){
             kategoria.add();
        }
        fillTable();

    }
    
    
    @FXML
    private void editButtonClicked(ActionEvent event){
        Kategoria wybranaKategoria=(Kategoria) tableTable.getSelectionModel().getSelectedItem();
        
        if(wybranaKategoria!=null){
            showAddWindow(wybranaKategoria, event);
            wybranaKategoria.update();
            fillTable();
        }
        
    }
    
    
    
    public Kategoria showAddWindow(Kategoria kategoria, ActionEvent event){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLNowaKategoria.fxml"));
            Parent root = (Parent) loader.load();
             Stage dialogStage = new Stage();
             dialogStage.setTitle("Kategoria");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            
            
            
            FXMLNowaKategoriaController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setKategoria(kategoria);

            dialogStage.showAndWait();
          
            
           return controller.kategoria;
        }catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
    }
    }
    
 
    
    private void fillTable(){
        ObservableList<TableColumn> kolumny = FXCollections.observableArrayList(tableTable.getColumns());
        kolumny.get(0).setCellValueFactory(new PropertyValueFactory<Kategoria, Integer>("nrkategorii"));
        kolumny.get(1).setCellValueFactory(new PropertyValueFactory<Kategoria, String>("kategoria"));
        
         ObservableList<Kategoria> all2 = connect.getCategories();
         tableTable.setItems(all2);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTable();
    }    
    
}
