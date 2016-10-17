/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naszewydatki;

/**
 *
 * @author Marcin
 */
public class Kategoria {
    int nrkategorii;
    String kategoria;
      private final ObslugaDB connect=new ObslugaDB();
    
    
    public Kategoria(){
        nrkategorii=0;
        kategoria="";
    }
    
    public Kategoria(int nr_kategoriiIn, String kategoriaIn){
        nrkategorii=nr_kategoriiIn;
        kategoria=kategoriaIn;
    }
    
    public void add(){
        connect.dodajKategorieDoBazy(this);
    }
    
    public void update(){
        connect.updateKategorieBaza(this);
    }
        
    

    public int getNrkategorii(){
        return nrkategorii;
    }
    
    public String getKategoria(){
        return kategoria;
    }
    
    public void SetNrKategorii(int nrKategoriiIn){
        nrkategorii=nrKategoriiIn;
    }
    
    public void SetKategoria(String kategoriaIn){
        kategoria=kategoriaIn;
    }
    
    
    @Override
    public String toString(){
        return kategoria;
    }
    
    
}
