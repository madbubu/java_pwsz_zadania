/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naszewydatki;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Marcin
 */
public class FXMLDocumentController implements Initializable {
    
    
    private final ObslugaDB connect=new ObslugaDB();  
    
    @FXML
    private Button buttonNowa;
    @FXML
    private BarChart chartBar;
   

    
    @FXML
    private void stworzObiekt(ActionEvent event){
        Wydatek wydatek=new Wydatek(1,"string",14.5,"string","string", 1);
        System.out.println(wydatek.getOpis().toString());
    }
    
    
    
    @FXML
    private void addButtonClicked(ActionEvent event){

        Wydatek wydatek=showAddWindow(new Wydatek(), event);
       System.out.println(wydatek.getIndeks()+";"+wydatek.getDatawydatek()+";"+wydatek.getKwota()+";"+wydatek.getOpis()+";"+wydatek.getKategoria()+";"+wydatek.getnrBudzetu());
      
       if(wydatek.getKwota()!=0){
            wydatek.add();
       }

        //wyswietlenie(wydatek);
        
    }
   
    private void wyswietlenie(Wydatek wydatekIn){
        Wydatek wydatek=wydatekIn;
        System.out.println(wydatek.getOpis());
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
    
    
    @FXML 
    public void showDetailsWindow(ActionEvent event){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLSzczegoly.fxml"));
            Parent root = (Parent) loader.load();
             Stage dialogStage = new Stage();
             dialogStage.setTitle("Szczegoly");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();
            
            
        }catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
    @FXML 
    public void showCategoriesWindow(ActionEvent event){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLKategorie.fxml"));
            Parent root = (Parent) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Kategorie");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();
            
        }catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML 
    public void showChartsWindow(ActionEvent event){
         try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLWykresy.fxml"));
            Parent root = (Parent) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Analizuj");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();
            
        }catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void ustawDane(){
        String rok="2016";

        ArrayList<String[]> result=connect.getValuesForMain(rok);
          XYChart.Series series1 = new XYChart.Series();
          
          chartBar.getData().clear();
          
          for (int i=0; i<result.size();i++){
              String[] currentList=result.get(i);
                for(int j=0; j<currentList.length;j=j+2){
                  String singleObject = currentList[j];
                  Double sumaMiesiac=Double.parseDouble(currentList[j+1]);
                  System.out.println(singleObject);
                  series1.getData().add(new XYChart.Data(singleObject, sumaMiesiac));
                }
              
          }
          
          chartBar.getData().addAll(series1);
          chartBar.setLegendVisible(false);
          chartBar.setTitle("Saldo w podziale na miesiace w " + rok);
        
        
        
        
        
        
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ustawDane();
    }    
    
}
