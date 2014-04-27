/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import play.data.validation.Required;

/**
 *
 * @author seifu001
 */
public class Submit {
    @Required public String email;
    @Required public String umcid;
    @Required public String first;
    @Required public String last;
   @Required public String itemID;
    @Required public String forDate;
    
    public Submit (){}
    
    public Submit (String email, String umcid, String first, String last, String itemID,String forDate)  {
        this.email=email;
        this.umcid=umcid;
        this.first=first;
        this.last=last;
        this.itemID=itemID;
        this.forDate=forDate;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setUmcid(String umcid){
        this.umcid=umcid;
    }
    public void setFirst(String first){
        this.first=first;
    }
    public void setLast(String last){
        this.last=last;
    }
    public void setItemID(String item_id){
        this.itemID=item_id;
    }
    public void setForDate(String date){
        this.forDate=date;
    }
     
    public String getEmail(){
        return this.email;
    }
    public String getUmcid(){
        return this.umcid;
    }
    public String getFirst(){
        return this.first;
    }
    public String getLast(){
        return this.last;
    }
    public String getItemID(){
        return this.itemID;
    }
    public String getForDate(){
        return this.forDate;
    }
    
    
    public static boolean complete(String email, String umcid, String first, String last, String itemID,String forDate)throws Exception{
              Submit client = new Submit(email, umcid, first, last, itemID,forDate);
              
                boolean status=false;
                Connection conn=null;
                Statement stmt=null;
                ResultSet rs=null;
                 PreparedStatement pst = null;
                try{
                    
                  
                  String url = "...";
                  String user= "...";
                  String pwd="...";

                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(url,user,pwd);
                   
                   
                    pst = conn.prepareStatement( "INSERT INTO heroku_c36d590dae49126.reserve VALUES('"+client.getUmcid()+"', '"+client.getFirst()+"', '"+client.getLast()+"', '"+client.getEmail()+"', '"+client.getItemID()+"', '"+client.getForDate()+"')");
                    pst.executeUpdate(); 
                    
                                   
                     status=true;
                     }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }
                     return status;
    }
    
     public static ArrayList reservationList(){
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
                    
                    sql="SELECT * from heroku_c36d590dae49126.reserve";
                     stmt.executeQuery(sql);
                     rs = stmt.getResultSet();
            
                 
                 
                    //read each resultset 
                    while(rs.next()){
                         
                        String umcid=rs.getString("umc_id");
                        String fname=rs.getString("first_name");
                        String lname=rs.getString("last_namel");
                        String email=rs.getString("email");
                        String resitem=rs.getString("item_id");
                        String fordate=rs.getString("fordate");
                        
                       
                      activity.add(new Submit(email,umcid,fname,lname,resitem,fordate));  
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
     
     public static boolean update(String info, String qty) throws Exception {
      //   String formatInfo= info.toUpperCase();
         boolean status =false;
         Connection conn=null;
                Statement stmt=null;
                //ResultSet rs=null;
                try{
                      String url = "jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/heroku_c36d590dae49126?reconnect=true";
                          String user= "b6cf1f485e63de";
                          String pwd="b2147dbe";

                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(url,user,pwd);
                    
                    
                    String updateTableSQL = "UPDATE heroku_c36d590dae49126.fall"
				+ " SET quantity = quantity-'"+qty+"'"
				+ " WHERE itemid = '"+info+"'";
                                
                               
                    stmt = conn.createStatement();
 
			System.out.println(updateTableSQL);
 
			// execute update SQL stetement
			stmt.execute(updateTableSQL);
                       status=true;
                  //  rs.close();
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
         
         return status;
         
     }
 
     public static boolean returnedItem(String info, String qty){
         boolean done=false;
         Connection conn=null;
                Statement stmt=null;
                //ResultSet rs=null;
                try{
                      String url = "jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/heroku_c36d590dae49126?reconnect=true";
                          String user= "b6cf1f485e63de";
                          String pwd="b2147dbe";

                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(url,user,pwd);
                     
                    
                    String updateTableSQL = "UPDATE heroku_c36d590dae49126.fall"
				+ " SET quantity = quantity+'"+qty+"'"
				+ " WHERE itemid = '"+info+"'";
                                
                               
                    stmt = conn.createStatement();
 
			System.out.println(updateTableSQL);
 
			// execute update SQL stetement
			stmt.execute(updateTableSQL);
                     
                done=true;
                  //  rs.close();
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
         return done;
     }
    
      public static boolean deletedItem(String umcid){
         boolean done=false;
         Connection conn=null;
                Statement stmt=null;
                //ResultSet rs=null;
                try{
                      String url = "jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/heroku_c36d590dae49126?reconnect=true";
                          String user= "b6cf1f485e63de";
                          String pwd="b2147dbe";

                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(url,user,pwd);
                    
                    stmt = conn.createStatement();
                     String sql = "DELETE FROM heroku_c36d590dae49126.reserve " +
                        "WHERE umc_id= '"+umcid+"'";
                        stmt.executeUpdate(sql);
                    
                           done=true;
                  //  rs.close();
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
         
         return done;
     }
     
}
