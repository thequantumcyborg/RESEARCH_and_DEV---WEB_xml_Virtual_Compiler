/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.*;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author seifu001
 */
public class DOMValidator {
    public String warning ;
    public String error;
    public String fatal;
    public String publicID;
    public String systemID;
    public String lineNumber;
    public String columnNumber;
    public String message;
    public static DOMValidator repo;
     static DOMValidator status;
     
   
    
    
    public DOMValidator(){}
    public DOMValidator(String warning, String error,String fatal, String publicID, String systemID, String lineNumber, String columnNumber,String message){
        this.warning=warning;
        this.error=error;
        this.fatal=fatal;        
        this.publicID=publicID;
        this.systemID=systemID;
        this.lineNumber=lineNumber;
        this.columnNumber=columnNumber;
        this.message= message;
        
   
   }
    //setter
    public void setWarning(String warning){
        this.warning=warning;
    }
    public void setError(String error){
        this.error=error;
    }
    public void setFatal(String fatal){
        this.fatal=fatal;
    }
    public void setPublicID(String publicID){
        this.publicID=publicID;
    }
    public void setSystemID(String systemID){
        this.systemID=systemID;
    }
     public void setLineNumber(String lineNumber){
        this.lineNumber=lineNumber;
    }
     public void setColumnNumber(String columnNumber){
        this.columnNumber=columnNumber;
    }
    public void setMessage(String message){
        this.message=message;
    }
    
    //getter
     public String getWarning(){
        return this.warning;
    }
    public String getError(){
        return this.error;
    }
    public String getFatal(){
        return this.fatal;
    }
    public String getPublicID(){
        return this.publicID;
    }
    public String getSystemID(){
        return this.systemID;
    }
     public String getLineNumber(){
        return this.lineNumber;
    }
     public String getColumnNumber(){
        return this.columnNumber;
    }
    public String getMessage(){
        return this.message;
    }
    
    public static ArrayList validateDTD(String xml){
      
       status = new DOMValidator ();
        ArrayList reportCopy = new ArrayList(); 
        try {
         //File x = new File(args[0]);
         /* String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
" <!DOCTYPE Results [<!ELEMENT Results ( Row ) >\n" +
"\n" +
"<!ELEMENT Row ( itemid, name, description, quantity ) >\n" +
"\n" +
"<!ELEMENT description ( #PCDATA ) >\n" +
"\n" +
"<!ELEMENT itemid ( #PCDATA ) >\n" +
"\n" +
"<!ELEMENT name ( #PCDATA ) >\n" +
"\n" +
"<!ELEMENT quantity ( #PCDATA ) > ]>\n"+ 
 "<Row><itemid>UMC-001</itemid><name>Canoe</name><description>Built from tough thermoformed polyethylene that's designed to handle Maine's most rugged ponds and lakes. Paddles efficiently so you can use it as a recreational boat for the whole family.</description><quantity>10</quantity></Row></Results>";
*/
                  DocumentBuilderFactory f
            = DocumentBuilderFactory.newInstance();
         f.setValidating(true); // Default is false
         DocumentBuilder b = f.newDocumentBuilder();
         // ErrorHandler h = new DefaultHandler();
         ErrorHandler h = new MyErrorHandler();
         b.setErrorHandler(h);
         Document d = b.parse(new InputSource(new java.io.StringReader(xml)));
          
      } catch (ParserConfigurationException e) {
         System.out.println(e.toString());      
      } catch (SAXException e) {
         System.out.println(e.toString());      
      } catch (IOException e) {
         System.out.println(e.toString());      
      }
 
       repo = new DOMValidator (status.getWarning(),status.getError(),status.getFatal(),status.getPublicID(),status.getSystemID(),status.getPublicID(),status.getColumnNumber(),status.getMessage());
        reportCopy.add(repo);
        
         return reportCopy;
   }
   public static class MyErrorHandler implements ErrorHandler {
      
      public void warning(SAXParseException e) throws SAXException {
         //System.out.println("Warning: "); 
         status.setWarning("Warning :");
         printInfo(e);
        
      }
      public void error(SAXParseException e) throws SAXException {
         //System.out.println("Error: "); 
         status.setError("Error :");
         printInfo(e);
       
      }
      public void fatalError(SAXParseException e) 
         throws SAXException {
         //System.out.println("Fattal error: "); 
         status.setFatal("Fatal Error :");
         printInfo(e);
        
      }
      public void printInfo(SAXParseException e) {
         
         status.setPublicID("   Public ID: "+e.getPublicId());
         status.setSystemID("   System ID: "+e.getSystemId());
         status.setPublicID("   Line number: "+e.getLineNumber());
         status.setColumnNumber("   Column number: "+e.getColumnNumber());
         status.setMessage("   Message: "+e.getMessage());
         //localhost test format
         //System.out.println("   Public ID: "+e.getPublicId());
         //System.out.println("   System ID: "+e.getSystemId());
         //System.out.println("   Line number: "+e.getLineNumber());
         //System.out.println("   Column number: "+e.getColumnNumber());
         //System.out.println("   Message: "+e.getMessage());
       
      }
    
   }
}
    

