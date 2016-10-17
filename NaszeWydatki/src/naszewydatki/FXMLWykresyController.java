/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naszewydatki;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Marcin
 */
public class FXMLWykresyController implements Initializable {
      private final ObslugaDB connect=new ObslugaDB();  
    
      @FXML 
      private BarChart chartBar;
      @FXML
      private RadioButton radioKategorie,radioMiesiac;
      @FXML
      private Pane paneKategorie, paneMiesiac;
      @FXML
      private ToggleGroup Grupa;
      @FXML
      private ComboBox comboKategorie, comboRok1, comboRok2, comboMiesiac;
      @FXML
      private PieChart wykresPie;
      
      
      @FXML
      private void radioButtonChange(ActionEvent event){
       
          if(radioKategorie.isSelected()){
              paneKategorie.setDisable(false);
              paneMiesiac.setDisable(true);
          }else if (radioMiesiac.isSelected()){
              paneKategorie.setDisable(true);
              paneMiesiac.setDisable(false);
          }
          
          
       
          radioKategorie.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
              @Override
               public void handle(ActionEvent actionEvent) {
                   paneKategorie.setDisable(false);
                   paneMiesiac.setDisable(true); 
                }
              
          });
          
            radioMiesiac.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
              @Override
               public void handle(ActionEvent actionEvent) {
                   paneKategorie.setDisable(true);
                   paneMiesiac.setDisable(false); 
                }
              
          });
          
          
          
          
          /*
          
         radioMiesiac.selectedProperty().addListener(new ChangeListener<Boolean>(){
         @Override
         public void changed (ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected){
             if(isNowSelected){
                   paneKategorie.setDisable(true);
                     paneMiesiac.setDisable(false);
             }
         }
     }

     );   

     
     radioKategorie.selectedProperty().addListener(new ChangeListener<Boolean>(){
         @Override
         public void changed (ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected){
             if(isNowSelected){
                   paneKategorie.setDisable(false);
                   paneMiesiac.setDisable(true);
             }
         }
     }

     );
         
       
      */
          
    }
      
      
      
      
      
      @FXML
      private void buttonGenerateChart(ActionEvent event){
          
          if(radioKategorie.isSelected()){
              String rok=comboRok1.getSelectionModel().getSelectedItem().toString();
              String kategoria=comboKategorie.getSelectionModel().getSelectedItem().toString();
              chartBar.setVisible(true);
              wykresPie.setVisible(false);
              
              barChartCategories(rok,kategoria);
          }else if(radioMiesiac.isSelected()){
              String rok=comboRok2.getSelectionModel().getSelectedItem().toString();
              String miesiac=comboMiesiac.getSelectionModel().getSelectedItem().toString();
               chartBar.setVisible(false);
              wykresPie.setVisible(true);
              
              roundChartCategories(rok, miesiac);
          }
          
      }
      
      
      
      
      
      
      
      
      private void roundChartCategories(String rok, String miesiac ){
           ArrayList<String[]> result=connect.getValuesForMonth(rok, miesiac);
           
            ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
           
           wykresPie.getData().clear();
           
           for (int i=0; i<result.size();i++){
              String[] currentList=result.get(i);
                for(int j=0; j<currentList.length;j=j+2){
                  String singleObject = currentList[j];
                  Double sumaMiesiac=Double.parseDouble(currentList[j+1]);
                  System.out.println(singleObject);
                  pieChartData.add(new PieChart.Data(singleObject, sumaMiesiac));
                }
              
          }
           wykresPie.getData().addAll(pieChartData);
          wykresPie.setTitle("Bilans dla "+miesiac +"." +rok);
           
           
           
      }
      
      
      
      
      
      
      
      private void barChartCategories(String rok, String kategoria){
          ArrayList<String[]> result=connect.getValuesForCategories(rok, kategoria);
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
          chartBar.setTitle(kategoria + " w " + rok);
          
      }
      
      
      
      private void fillKategorie(){
        ObservableList<Kategoria> kategorie = connect.getCategories();
        comboKategorie.setItems(kategorie); 
      }
      
      private void fillRok(){
          ObservableList<String> ls=connect.getValuesYears();
          comboRok1.setItems(ls);
          comboRok2.setItems(ls);
      }
    
      private void fillMiesiac(){
          comboMiesiac.getItems().addAll(
                  "1",
                  "2",
                  "3",
                  "4",
                  "5",
                  "6",
                  "7",
                  "8",
                  "9",
                  "10",
                  "11",
                  "12"
          );
      }
      
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillKategorie();
        fillRok();
        fillMiesiac();
    }    
    
}
