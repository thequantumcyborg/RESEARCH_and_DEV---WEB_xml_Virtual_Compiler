package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import java.io.*;
import java.util.*;
import  play.libs.*;
import models.*;




import java.io.File;
import java.util.Date;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void prototyoe() {
        render("@Application.prototype");
    }
    public static void fall() throws Exception  {
            
      List items = Reservation.productList();
            
      render("@Application.fall",items);
    }
    public static void winter() {
        render("@Application.winter");
    }
    public static void summer() {
        render("@Application.summer");
    }
    public static void magazine() {
        render("@Application.magazine");
    }
    
            
    public static void submit(String email, String umcid, String first, String last,String itemID, String time) throws Exception {
      Boolean reload = true;
         
               
      reload =Submit.complete(email,umcid,first,last,itemID,time);
     
            if (reload)  {
                  index();   
            }
    }
    
   
    public static void signIn(String onlineID, String passcode) throws Exception{
       
        boolean verify=false;
        //creaat a login object sent to a constructor in model 
        login existing = new login(onlineID,passcode);
       // render("@Application.admin");
        //send to model(login.java) and connect to database
        verify=existing.check(existing.getOnlineID(),existing.getPassCode());
            
            //This boolean check allows/disallows routing to next page 
            if(verify){
             
             List reslist= Submit.reservationList();
             List  activity = Reservation.inventoryList();
            String xmlItems = Reservation.itemList();
           
                            String fullxml="<?xml version=\"1.0\" encoding=\"UTF-8\"?> <!DOCTYPE Results [\n" +
                            "\n" +
                            "<!ELEMENT Results ( Row+ ) >\n" +
                            "\n" +
                            "<!ELEMENT Row ( itemid, name, description, quantity ) >\n" +
                            "\n" +
                            "<!ELEMENT description ( #PCDATA ) >\n" +
                            "\n" +
                            "<!ELEMENT itemid ( #PCDATA ) >\n" +
                            "\n" +
                            "<!ELEMENT name ( #PCDATA ) >\n" +
                            "\n" +
                            "<!ELEMENT quantity ( #PCDATA ) > ]>"+ xmlItems;
                           
 
             List generateReport =DOMValidator.validateDTD(fullxml);
             render("@Application.admin", activity,reslist,generateReport,fullxml);
            
            }
            else 
                index();
       
         }
    
    public static void checkout(String itemID, String qty)throws Exception{
        
                
        boolean status=Submit.update(itemID,qty);
        List reslist;
        if(status ){
            reslist= Submit.reservationList();
             List  activity = Reservation.inventoryList();
            String xmlItems = Reservation.itemList();
           
                            String fullxml="<?xml version=\"1.0\" encoding=\"UTF-8\"?> <!DOCTYPE Results [\n" +
                            "\n" +
                            "<!ELEMENT Results ( Row+ ) >\n" +
                            "\n" +
                            "<!ELEMENT Row ( itemid, name, description, quantity ) >\n" +
                            "\n" +
                            "<!ELEMENT description ( #PCDATA ) >\n" +
                            "\n" +
                            "<!ELEMENT itemid ( #PCDATA ) >\n" +
                            "\n" +
                            "<!ELEMENT name ( #PCDATA ) >\n" +
                            "\n" +
                            "<!ELEMENT quantity ( #PCDATA ) > ]>"+ xmlItems;
                           
 
             List generateReport =DOMValidator.validateDTD(fullxml);
             render("@Application.admin", activity,reslist,generateReport,fullxml);}
        else
            index();
    }
    
    public static void returnedItem(String  itemID, String qty)throws Exception{
           boolean done = false;
           List reslist;
           String xmlItems="";
            done=Submit.returnedItem(itemID,qty);
           if (done){
            reslist= Submit.reservationList();
             List  activity = Reservation.inventoryList();
             xmlItems = Reservation.itemList();
           
                            String fullxml="<?xml version=\"1.0\" encoding=\"UTF-8\"?> <!DOCTYPE Results [\n" +
                            "\n" +
                            "<!ELEMENT Results ( Row+ ) >\n" +
                            "\n" +
                            "<!ELEMENT Row ( itemid, name, description, quantity ) >\n" +
                            "\n" +
                            "<!ELEMENT description ( #PCDATA ) >\n" +
                            "\n" +
                            "<!ELEMENT itemid ( #PCDATA ) >\n" +
                            "\n" +
                            "<!ELEMENT name ( #PCDATA ) >\n" +
                            "\n" +
                            "<!ELEMENT quantity ( #PCDATA ) > ]>"+ xmlItems;
                           
 
             List generateReport =DOMValidator.validateDTD(fullxml);
             render("@Application.admin", activity,reslist,generateReport,fullxml);
           }else
               index();
           
    
    }
            
           
    
    public static void deletedItem(String umcid)throws Exception{
           boolean done = false;
           List reslist;
           done=Submit.deletedItem(umcid);
           String xmlItems="";
           if (done){
            reslist= Submit.reservationList();
             List  activity = Reservation.inventoryList();
             xmlItems = Reservation.itemList();
           
                            String fullxml="<?xml version=\"1.0\" encoding=\"UTF-8\"?> <!DOCTYPE Results [\n" +
                            "\n" +
                            "<!ELEMENT Results ( Row+ ) >\n" +
                            "\n" +
                            "<!ELEMENT Row ( itemid, name, description, quantity ) >\n" +
                            "\n" +
                            "<!ELEMENT description ( #PCDATA ) >\n" +
                            "\n" +
                            "<!ELEMENT itemid ( #PCDATA ) >\n" +
                            "\n" +
                            "<!ELEMENT name ( #PCDATA ) >\n" +
                            "\n" +
                            "<!ELEMENT quantity ( #PCDATA ) > ]>"+ xmlItems;
                           
 
             List generateReport =DOMValidator.validateDTD(fullxml);
             render("@Application.admin", activity,reslist,generateReport,fullxml);
           }else
               index();
           
           
    }
  
}