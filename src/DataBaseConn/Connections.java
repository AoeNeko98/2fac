/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseConn;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author iHoussem
 */
public class Connections {
    public static Connection getConnection(){
        
        String url = "jdbc:mysql://localhost:3306/2fac";
        String login="houssem";
        String pwd="1327";
        try{
           Connection conn = DriverManager.getConnection(url, login, pwd);
            return conn;
        }catch(SQLException ex){
            System.out.println("Error2: "+ ex.getMessage());
            return null;
        }
    }
}
