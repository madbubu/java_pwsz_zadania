/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naszewydatki;


import java.sql.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Marcin
 */
public class ObslugaDB {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public ObslugaDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wydatki","root","");
            st=con.createStatement();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void getData(){
        try{
            String query="select * from listawydatki ";
            rs=st.executeQuery(query);
            System.out.println("Records from database");
            while(rs.next()){
                int id_wydatek=rs.getInt("id_wydatek");
                Date data=rs.getDate("data");
                double kwota=rs.getDouble("kwota");
                String opis=rs.getString("opis");
                int nr_kategorii=rs.getInt("nr_kategorii");
                int nr_budzetu=rs.getInt("nr_budzetu");
                System.out.println(id_wydatek+" "+data+" "+kwota+" "+opis+" "+nr_kategorii+" "+nr_budzetu);
                
            }
            
        }catch(Exception ex){
            System.out.println(ex);
        }
        
    }  
    
    public ObservableList<Wydatek> getAll2(){
        ObservableList<Wydatek> wydatekList=FXCollections.<Wydatek>observableArrayList();
         try{
            String query="SELECT id_wydatek, data, kwota, opis, kategoria, nr_budzetu\n" +
                            "FROM listawydatki\n" +
                            "JOIN kategorie\n" +
                            "ON listawydatki.nr_kategorii=kategorie.nr_kategorii ";
            rs=st.executeQuery(query); 
            while(rs.next()){
                int nrIndeks=rs.getInt("id_wydatek");
                Date data=rs.getDate("data");
                String datawydatek=data.toString();
                double kwota=rs.getDouble("kwota");
                String opis=rs.getString("opis");
                String kategoria=rs.getString("kategoria");
                int nr_budzetu=rs.getInt("nr_budzetu");
             Wydatek wydatek=new Wydatek(nrIndeks,datawydatek,kwota,opis,kategoria,nr_budzetu );
             wydatekList.add(wydatek);
             
            
            
             
            }
         }catch(Exception ex){
            System.out.println(ex);
    }   
     return wydatekList;   
    }
    
     public ObservableList<Kategoria> getCategories(){
         ObservableList<Kategoria> kategoriaList=FXCollections.<Kategoria>observableArrayList();
        try{
            String query="select * from kategorie";
            rs=st.executeQuery(query); 
            while(rs.next()){
                int nrKategoria=rs.getInt("nr_kategorii");
                String kategoria_nazwa=rs.getString("kategoria");
                
                Kategoria kategoria=new Kategoria(nrKategoria, kategoria_nazwa);
                kategoriaList.add(kategoria);

            }
            
            
        } catch(Exception ex){
          System.out.println(ex);
 
     }
        return kategoriaList;
     }
     
     
     
     
     public void dodajDoBazy(Wydatek wydatek){
         
         try{
         int nrKategoriiBaza=nrKategoriiWBazie(wydatek.getKategoria());
         String query="INSERT INTO `listawydatki` (`id_wydatek`, `data`, `kwota`, `opis`, `nr_kategorii`, `nr_budzetu`) VALUES (NULL, '" + wydatek.getDatawydatek() +"', '"+wydatek.getKwota()+"', '"+wydatek.getOpis()+"', '"+nrKategoriiBaza+"', '"+wydatek.getnrBudzetu()+"')";
        System.out.println(query);
         st.executeUpdate(query);
         }catch(Exception ex){
          System.out.println(ex);
     }
        
     }
     
     
     
     public void updateBazy(Wydatek wydatek){
         try{
             int nrKategoriiBaza=nrKategoriiWBazie(wydatek.getKategoria());
             String query="UPDATE `listawydatki` SET `data` = '"+wydatek.getDatawydatek()+"',`kwota` = '"+wydatek.getKwota() +"',`opis` = '"+wydatek.getOpis()+"',`nr_kategorii` = '"+nrKategoriiBaza+"' WHERE `listawydatki`.`id_wydatek` = "+wydatek.getIndeks();
              System.out.println(query);
             st.executeUpdate(query);
            
         }catch(Exception ex){
          System.out.println(ex);
         }
        
         
         
     }
     
     
     public void usunZBazy(Wydatek wydatek){
           try{
         String query="DELETE FROM `listawydatki` WHERE `listawydatki`.`id_wydatek` = "+wydatek.getIndeks();
          st.executeUpdate(query);
        }catch(Exception ex){
          System.out.println(ex);
         }
     
     }
     
     public void dodajKategorieDoBazy(Kategoria kategoria){
         try{
             String query="INSERT INTO `kategorie` (`nr_kategorii`, `kategoria`) VALUES (NULL, '" + kategoria.getKategoria() +"')" ;
              st.executeUpdate(query);
         }catch(Exception ex){
          System.out.println(ex);
         }
     }
     
     
     public void updateKategorieBaza(Kategoria kategoria){
         try{
             
             String query="UPDATE `kategorie` SET `kategoria` = '"+kategoria.getKategoria()+"' WHERE `kategorie`.`nr_kategorii` = "+kategoria.getNrkategorii();
             st.executeUpdate(query);
         }catch(Exception ex){
          System.out.println(ex);
         }
     }
     
     
     private int nrKategoriiWBazie(String kategoria){
         int nrKategoria = 0; 
         try{
              String query="SELECT nr_kategorii FROM `kategorie` WHERE kategoria='"+kategoria+"'";
              rs=st.executeQuery(query); 
              while(rs.next()){
                 nrKategoria=rs.getInt("nr_kategorii");
              }
              
              
          }catch(Exception ex){
          System.out.println(ex);
     }
        
          
         return nrKategoria;
     }
     
     
     public ArrayList<String[]> getValuesForCategories(String rok, String kategoria){
        ArrayList<String[]> result = new ArrayList<String[]>();
         try{
         String query="SELECT MONTH(data), sum(kwota) FROM listawydatki JOIN kategorie ON listawydatki.nr_kategorii=kategorie.nr_kategorii and kategorie.kategoria='"+ kategoria + "' and YEAR(listawydatki.data)='" + rok + "' GROUP BY MONTH(data)";
         rs=st.executeQuery(query); 
         int columnCount = rs.getMetaData().getColumnCount();
         while(rs.next()){
             String[] row = new String[columnCount];
             for (int i=0; i <columnCount ; i++)
            {
                row[i] = rs.getString(i + 1);
            }
            result.add(row); 
         }
        }catch(Exception ex){
          System.out.println(ex);
 
     }

         return result;
     }
     
      public ArrayList<String[]> getValuesForMain(String rok){
        ArrayList<String[]> result = new ArrayList<String[]>();
         try{
         String query="SELECT MONTH(data), sum(kwota) FROM listawydatki JOIN kategorie ON listawydatki.nr_kategorii=kategorie.nr_kategorii and YEAR(listawydatki.data)='" + rok + "' GROUP BY MONTH(data)";
         rs=st.executeQuery(query); 
         int columnCount = rs.getMetaData().getColumnCount();
         while(rs.next()){
             String[] row = new String[columnCount];
             for (int i=0; i <columnCount ; i++)
            {
                row[i] = rs.getString(i + 1);
            }
            result.add(row); 
         }
        }catch(Exception ex){
          System.out.println(ex);
 
     }

         return result;
     }
     
     
     
     
     
     
     
     public ArrayList<String[]> getValuesForMonth(String rok, String miesiac){
         ArrayList<String[]> result = new ArrayList<String[]>();
         try{
             String query="SELECT kategorie.kategoria, sum(listawydatki.kwota)\n" +
                        "FROM listawydatki \n" +
                        "JOIN kategorie ON listawydatki.nr_kategorii=kategorie.nr_kategorii and YEAR(listawydatki.data)="+rok+" and MONTH(listawydatki.data)="+miesiac+" \n" +
                    "GROUP BY kategorie.kategoria";
             
             rs=st.executeQuery(query); 
             int columnCount = rs.getMetaData().getColumnCount();
             while(rs.next()){
             String[] row = new String[columnCount];
             for (int i=0; i <columnCount ; i++)
            {
                row[i] = rs.getString(i + 1);
            }
            result.add(row); 
         } 
         }catch(Exception ex){
          System.out.println(ex);
 
     }
         return result;
     }
     
     
     
     
    
     
     public ObservableList<String> getValuesYears(){
         ObservableList<String> result=FXCollections.<String>observableArrayList();
         try{
             String query="SELECT YEAR(data) as rok FROM listawydatki group by YEAR(data)";
             rs=st.executeQuery(query); 
             while(rs.next()){
                 result.add(rs.getString("rok"));
             }
  
         }catch(Exception ex){
          System.out.println(ex);
 
     }
         return result;
         
     }

}
