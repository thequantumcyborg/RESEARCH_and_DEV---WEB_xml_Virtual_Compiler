/*
 * Construct and accesor methods for log in object.
 * Also, used for clearDB cloud database connection
 * note: import play.db.DB isnt used but spared for future jpa test (which provides less mysql legging)
 */
package models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import play.data.validation.*;
import java.util.*;
import play.db.DB;


/**
 *
 * @author kirubel s.
 */
public class login {
    // validator
    @Required public String onlineID;
    @Required public String passcode;
    
    //constructor, though java automatically creates one
    public login(){}
    
    /**constructor of each login attempt, helpful for multiple user access
        @param String
        @param String
        @return none
        */
    public login(String onlineID,String passcode){
        this.onlineID=onlineID;
        this.passcode=passcode;
    }
    
    /** setter
        @param String
        @return none
        */
    public void setOnlineID(String onlineID){
        this.onlineID=onlineID;
    }
    
    /** getter
        @param String
        @return String
        */
    public String getOnlineID(){
        return this.onlineID;
    }
    
    /** setter
        @param String
        @return none
        */
    public void setPassCode(String passcode){
        this.passcode=passcode;
    }
    
    /** getter
        @param String
        @return String
        */
    public String getPassCode(){
        return this.passcode;
    }
   /** recieves constructed login object from controller
    * returns true/false if designated user object exists or not
        @param String
        @param String
        @return boolean
        */
    public boolean check(String onlineID,String passcode){
     
                boolean verify=false;
                Connection conn=null;
                Statement stmt=null;
                ResultSet rs=null;
                try{
                                    
                   
                   String url = "...";
                  String user= "....;
                  String pwd="...";


                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(url,user,pwd);
                    stmt=conn.createStatement();
                    
                    String sql;
                    
                    
                     sql="SELECT username,password from heroku_c36d590dae49126.account";
                     stmt.executeQuery(sql);
                     rs = stmt.getResultSet();
                     
                    //read each resultset 
                    while(rs.next()){
                        
                        String loginID=rs.getString("username");                       
                         String pas=rs.getString("password");
                         //String loginID=Integer.toString(uID);
                           
                         if (onlineID.equals(loginID) && passcode.equals(pas)){
                             verify=true; 
                         }
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
       return verify;
    }
    
    
}
