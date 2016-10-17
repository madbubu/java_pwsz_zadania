/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Marcin
 */
public class Gra {
    
    private Random los=new Random();
    private ArrayList<Integer> punktyGracz;
    private ArrayList<Integer> kartyGracz;
    private ArrayList<Integer> punktyKomp;
    private ArrayList<Integer> kartyKomp;

    public void nowaGra(){
        punktyGracz=new ArrayList<>();
        kartyGracz=new ArrayList<>();
        punktyKomp=new ArrayList<>();
        kartyKomp=new ArrayList<>();
        int karta;
        for (int i=1; i<3;i++){
            karta=pobierzKarte();
            punktyGracz.add(karta);
            kartyGracz.add(karta);
        }
        for (int i=1; i<3;i++){
            karta=pobierzKarte();
            punktyKomp.add(karta);
            kartyKomp.add(karta);
        }
    }
    
    
    public void dobierzKarte(){
        int karta;
      
        do{
              karta=pobierzKarte();
        }while (sprawdzKarte(karta));
        punktyGracz.add(karta);
        kartyGracz.add(karta);
       
        
    }
    
    
    
    public boolean sprawdzWynikGracz(){
        return punktyGracz()>21;
    }
    
     public boolean sprawdzWynikKomp(){
        return punktyKomp()>21;
    }

    
    
    
    
    
    private boolean sprawdzKarte(int karta){
        int ile_wylosowanych=0;
        for(int i=0; i<punktyGracz.size();i++){
            if(punktyGracz.get(i)==karta){
                ile_wylosowanych++;
            }
        }
        for(int i=0; i<punktyKomp.size();i++){
            if(punktyKomp.get(i)==karta){
                ile_wylosowanych++;
            }
        }
        
        if (karta==10){
         return ile_wylosowanych == 12;   
        }else{
            return ile_wylosowanych == 4;
        }
    }
    
    
    public void dobierzKarteKomp(){
        int karta;
        while(punktyKomp()<17){
            
      
        do{
              karta=pobierzKarte();
        }while (sprawdzKarte(karta));
        punktyKomp.add(karta);
        kartyKomp.add(karta);
        }
        
    }
    
    public String wynikGry(){
        
        if(punktyKomp()==punktyGracz()){
            return "Remis";
        }
        if(sprawdzWynikGracz()){
            if(sprawdzWynikKomp()){
                return "Remis";
            }else{
                return "Wygrana komputer";
            }
        }    
        if(sprawdzWynikKomp()){
            if(!sprawdzWynikGracz()){
                return "Wygrana gracza";
            }
        }
        if(punktyGracz()>punktyKomp()){
            return "Wygrana gracza";
        }else{
            return "Wygrana komputer";
        }
        
        
    }
    
    
    
    
    private int pobierzKarte(){
       int result= los.nextInt(10)+2;
       return result;
    }
    
  
    public int punktyGracz(){
        int punkty=0;
        int dodana;
        Collections.sort(punktyGracz);
        for (int i=0; i<punktyGracz.size();i++){
            
            if(punkty>10 && punktyGracz.get(i)==11){
                dodana=1;
            }else{
                dodana=punktyGracz.get(i);
            }

            punkty=punkty+dodana;
        }
        return punkty;
    }
    
    public String kartyGracza(){
        String lista="";
        for (int i=0; i<kartyGracz.size();i++){
            lista=lista + String.format("%d ; ", kartyGracz.get(i));
        
        }
        return lista;
    }
    
    
    public int punktyKomp(){
        int punkty=0;
        
        int dodana;
        Collections.sort(punktyKomp);
        
         for (int i=0; i<punktyKomp.size();i++){
        if(punkty>10 && punktyKomp.get(i)==11){
                dodana=1;
            }else{
                dodana=punktyKomp.get(i);
            }
            punkty=punkty+dodana;
        }
        return punkty;
    }
    
    
    public String kartyKompUkryte(){
        String lista="_;";
        for (int i=1; i<punktyKomp.size();i++){
            lista=lista + String.format("%d ; ", punktyKomp.get(i));
        
        }
        return lista;
    }
    
     public String kartyKomp(){
         String lista="";
        for (int i=0; i<kartyKomp.size();i++){
            lista=lista + String.format("%d ; ", kartyKomp.get(i));
        
        }
        return lista;
    }
    
    
    
    
    
    
}
