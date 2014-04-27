/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.ArrayList;
import play.data.validation.Required;
import java.io.*;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 *
 * @author seifu001
 */
public class Reservation {
    @Required public String itemID;
    @Required public String itemName;
    @Required public String itemDesc;
    @Required public String itemQty;
    
    public Reservation(String itemID,String itemDesc, String itemQty){
          this.itemID=itemID; 
          this.itemDesc=itemDesc;
         this.itemQty=itemQty;
    }
    public Reservation(String itemID,String itemName, String itemDesc, String itemQty){
        this.itemID=itemID;
        this.itemName=itemName;
        this.itemDesc=itemDesc;
        this.itemQty=itemQty;
    }
    
    public void setItemID (String itemID){
        this.itemID=itemID;
    }
    public String getItemID(){
        return this.itemID;
    }
    public String getItemName(){
        return this.itemName;
    }
    public String getItemDesc(){
        return this.itemDesc;
    }
    public String getItemQty(){
        return this.itemQty;
    }
    
    
    public static ArrayList productList () throws Exception {
        ArrayList product = new ArrayList();
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();
    Element results = doc.createElement("Results");
    doc.appendChild(results);

      Connection conn=null;
                Statement stmt=null;
                ResultSet rs=null;
                try{
                    
                  
                  String url = "...";
                  String user= "..";
                  String pwd="...";

                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(url,user,pwd);
                    stmt=conn.createStatement();
                    
                    String sql;
                    sql="SELECT * from heroku_c36d590dae49126.fall";
                     stmt.executeQuery(sql);
                     rs = stmt.getResultSet();

    ResultSetMetaData rsmd = rs.getMetaData();
    int colCount = rsmd.getColumnCount();

    while (rs.next()) {
      Element row = doc.createElement("Row");
      results.appendChild(row);
      for (int i = 1; i <= colCount; i++) {
        String columnName = rsmd.getColumnName(i);
        Object value = rs.getObject(i);
        Element node = doc.createElement(columnName);
        node.appendChild(doc.createTextNode(value.toString()));
        row.appendChild(node);
      }
    }
    DOMSource domSource = new DOMSource(doc);
   
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
    transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
    StringWriter sw = new StringWriter();
    StreamResult sr = new StreamResult(sw);
    transformer.transform(domSource, sr);

     XPathFactory xfactory = javax.xml.xpath.XPathFactory.newInstance();
    XPath xPath = xfactory.newXPath();
 
      // System.out.println("Xpath Test manipulation");


XPathExpression xPathExpression_1_1 = xPath.compile("//Results//Row[1]//description");
String desc1= xPathExpression_1_1.evaluate(doc,XPathConstants.STRING).toString();

XPathExpression xPathExpression_1_2 = xPath.compile("//Results//Row[1]//quantity");
String qty1= xPathExpression_1_2.evaluate(doc,XPathConstants.STRING).toString();

XPathExpression xPathExpression_1_3 = xPath.compile("//Results//Row[1]//itemid");
String item1= xPathExpression_1_3.evaluate(doc,XPathConstants.STRING).toString();

XPathExpression xPathExpression_2_1 = xPath.compile("//Results//Row[2]//description");
String desc2= xPathExpression_2_1.evaluate(doc,XPathConstants.STRING).toString();

XPathExpression xPathExpression_2_2 = xPath.compile("//Results//Row[2]//quantity");
String qty2= xPathExpression_2_2.evaluate(doc,XPathConstants.STRING).toString();

XPathExpression xPathExpression_2_3 = xPath.compile("//Results//Row[2]//itemid");
String item2= xPathExpression_2_3.evaluate(doc,XPathConstants.STRING).toString();

XPathExpression xPathExpression3_1 = xPath.compile("//Results//Row[3]//description");
String desc3= xPathExpression3_1.evaluate(doc,XPathConstants.STRING).toString();

XPathExpression xPathExpression_3_2 = xPath.compile("//Results//Row[3]//quantity");
String qty3= xPathExpression_3_2.evaluate(doc,XPathConstants.STRING).toString();

XPathExpression xPathExpression_3_3 = xPath.compile("//Results//Row[3]//itemid");
String item3= xPathExpression_3_3.evaluate(doc,XPathConstants.STRING).toString();

Reservation products1 = new Reservation(item1,desc1,qty1);
product.add(products1);


Reservation products2 = new Reservation(item2,desc2,qty2);
product.add(products2);


Reservation products3 = new Reservation(item3,desc3,qty3);
product.add(products3);
//consol test
//System.out.println("Product desc is ---> " + desc);
    
    //consol test
    //System.out.println(sw.toString());
    }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           
    return product;
    }
    
    public static String itemList ()throws Exception{
        String items="";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element results = doc.createElement("Results");
            doc.appendChild(results);

              Connection conn=null;
                        Statement stmt=null;
                        ResultSet rs=null;
                        try{


                          String url = "jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/heroku_c36d590dae49126?reconnect=true";
                          String user= "b6cf1f485e63de";
                          String pwd="b2147dbe";

                            Class.forName("com.mysql.jdbc.Driver");
                            conn = DriverManager.getConnection(url,user,pwd);
                            stmt=conn.createStatement();

                            String sql;
                            sql="SELECT * from heroku_c36d590dae49126.fall";
                             stmt.executeQuery(sql);
                             rs = stmt.getResultSet();

            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();

            while (rs.next()) {
              Element row = doc.createElement("Row");
              results.appendChild(row);
              for (int i = 1; i <= colCount; i++) {
                String columnName = rsmd.getColumnName(i);
                Object value = rs.getObject(i);
                Element node = doc.createElement(columnName);
                node.appendChild(doc.createTextNode(value.toString()));
                row.appendChild(node);
              }
            }
            DOMSource domSource = new DOMSource(doc);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
             
            items=sw.toString();
           
              // System.out.println("Xpath Test manipulation");

        //consol test
        //System.out.println("Product desc is ---> " + desc);

            //consol test
            //System.out.println(sw.toString());
            }catch(SQLException se2){
                      }// nothing we can do
                      try{
                         if(conn!=null)
                            conn.close();
                      }catch(SQLException se){
                         se.printStackTrace();
                      }//end finally try

        return items;
    }
    
   public static ArrayList inventoryList(){
       ArrayList activity = new ArrayList();
              
                Connection conn=null;
                Statement stmt=null;
                ResultSet rs=null;
                try{
                      String url = "jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/heroku_c36d590dae49126?reconnect=true";
                          String user= "b6cf1f485e63de";
                          String pwd="b2147dbe";

                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(url,user,pwd);
                    stmt=conn.createStatement();
                    
                    String sql;
                    
                    sql="SELECT * from heroku_c36d590dae49126.fall";
                     stmt.executeQuery(sql);
                     rs = stmt.getResultSet();
            
                 
                 
                    //read each resultset 
                    while(rs.next()){
                         
                        String itemid=rs.getString("itemid");
                        String name=rs.getString("name");
                        String desc=rs.getString("description");
                        String qty=rs.getString("quantity");
                        
                       
                      activity.add(new Reservation(itemid,name,desc,qty));  
                      }
               
                   
                    
                    rs.close();
                    stmt.close();
                    conn.close();
                }catch(SQLException se){
                    se.printStackTrace();

                }catch(Exception e){
                    e.printStackTrace();
                }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }//end try 
              
             
      return activity; 
   }
}

