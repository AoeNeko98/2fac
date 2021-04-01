/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import Main.MyDB;

import entity.User;
import iservice.IUser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Aoe Neko 98
 */
public class UserService implements IUser{
     
    Connection conn ;
     

    public UserService() {
         this.conn = MyDB.getInstance().getConnexion();

    
}

    @Override
    public ArrayList<User> getAllUsers() {
        
        ArrayList<User> ls = new ArrayList<>();
        ResultSet rs ;
        User user ;
        String sql ="SELECT * FROM user ;";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while(rs.next()){
                user = new User();
                
                 user.setNom(rs.getString("nom"));
                
<<<<<<< HEAD
                 user.setId(rs.getInt("id_User"));
=======
                 user.setId(rs.getInt("id"));
>>>>>>> bbe37c878253ac63f653aff063c98b5a402e9028
                 ls.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return ls ;
       
    }
}
