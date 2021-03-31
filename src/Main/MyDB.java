package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author elbrh
 */
public class MyDB {
    
    
    static Connection conn ;
    
    private static MyDB instance = null ;
    
    
    private  MyDB(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/2fac","root","");
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    
    public static MyDB getInstance(){
        if ( instance == null ){
            instance  = new MyDB();
        }
        return instance;
    }
    
    public Connection getConnexion(){
        return conn ;
    }
    
    
}
