/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.pkg3;

import java.io.BufferedWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Marcin
 */
public class ContactsRepository {
    
    public ObservableList<Contact> getAll() {
        ObservableList<Contact> contacts=FXCollections.<Contact>observableArrayList();
            try{
                File file=new File("phonelist.txt");
                Scanner scan=new Scanner(file);
                
                while(scan.hasNextLine()){
                    String contactLine=scan.nextLine();
                    String data[]=contactLine.split(";");
                    
                    Contact contact=new Contact(data[0], data[1], data[2]);

                    contacts.add(contact);
                }
                
                scan.close();

            }catch (FileNotFoundException ex) {
            Logger.getLogger(ContactsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    return contacts;

}
    
    public void add(Contact contact){
        try{
            FileWriter fW=new FileWriter("phonelist.txt",true);
            BufferedWriter bW=new BufferedWriter(fW);
            PrintWriter print=new PrintWriter(bW);
            
            print.println(contact.getName()+";"+contact.getSurname()+";"+contact.getPhone());
            print.flush();
        }catch (IOException ex) {
            Logger.getLogger(ContactsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void save(ObservableList contacts){
        try{
            PrintWriter printWr=new PrintWriter("phonelist.txt");
            int n=0;
            while(n<contacts.size()){
                Contact contact=((Contact)contacts.get(n));
                printWr.println(contact.getName()+";"+contact.getSurname()+";"+contact.getPhone());
                n++;
            }
            printWr.close();
        }
        catch(IOException ex) {
            Logger.getLogger(ContactsRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        }
        
        
    }
    
    
    

