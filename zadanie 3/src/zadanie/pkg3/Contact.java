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
public class Contact {
    
    public String name;
    public String surname;
    public String phone;
    
    public Contact(String name, String surname, String phone){
        this.name=name;
        this.surname=surname;
        this.phone=phone;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getSurname(){
        return this.surname;
    }
    
    public String getPhone(){
        return this.phone;
    }
    
    public void setName(String name){
        this.name=name;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    
    public void setPhone(String phone){
        this.phone=phone;
    }
    
    
}
