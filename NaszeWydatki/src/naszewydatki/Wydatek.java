/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naszewydatki;

import java.time.LocalDate;

/**
 *
 * @author Marcin
 */
public class Wydatek {
    private int indeks;
    private String datawydatek;
    private double kwota;
    private String opis;
    private String kategoria;
    private int nrBudzetu;
     private final ObslugaDB connect=new ObslugaDB();
    
    public Wydatek(){
        indeks=0;
        datawydatek=LocalDate.now().toString();
        kwota=0.0;
        opis="";
        kategoria="";
        nrBudzetu=0;
    }
    
  
    public Wydatek(int indeksIn, String dataIn, double kwotaIn, String opisIn,String kategoriaIn, int nrBudzetuIn){
        indeks=indeksIn;
        datawydatek=dataIn;
        kwota=kwotaIn;
        opis=opisIn;
        kategoria=kategoriaIn;
        nrBudzetu=nrBudzetuIn;    
    }
    
    public void add(){
        connect.dodajDoBazy(this);

    }
    
    public void update(){
         System.out.println(getIndeks()+";"+getDatawydatek()+";"+getKwota()+";"+getOpis()+";"+getKategoria()+";"+getnrBudzetu());
        connect.updateBazy(this);
    }
    
    
    public void delete(){
        connect.usunZBazy(this);
    }
    
    
    public double getKwota(){
        return kwota;
    }
    
    public String getOpis(){
        return opis;
    }
    
    public int getIndeks(){
        return indeks;
    }
    
    public String getDatawydatek(){
        return datawydatek;
    }
    
     public String getKategoria(){
        return kategoria;
    }
     
     public int getnrBudzetu(){
         return nrBudzetu;
     }
     
     
     public void setKwota(double kwotaIn){
         kwota=kwotaIn;
     }
     public void setOpis(String opisIn){
         opis=opisIn;
     }
     public void setKategoria(String kategoriaIn){
         kategoria=kategoriaIn;
     }
     
     public void setIndeks(int indeksIn){
         indeks=indeksIn;
     }
     
     public void setData(String dataIn){
         datawydatek=dataIn;
     }
     
     public void setNrBudzetu(int nrBudzetuIn){
         nrBudzetu=nrBudzetuIn;
     }
    
    
}
