/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Role;
import Entities.User;
import ServiceInterface.ServiceInterface;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skand
 */
public class UserService implements ServiceInterface<User>{

    private Connection con;
    private Statement ste;
    private Statement ste1;

    public UserService() {
        con = DataBase.getInstance().getConnection();
    }
    @Override
    public boolean add(User t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert;
        requeteInsert = "INSERT INTO `2fac`.`user` (`Nom`,`Prenom`,`Email`,`Password`,`Role`,`addresse`,`date_naissance`) VALUES (?,?,?,?,?,?,?)";
          
        PreparedStatement PS = con.prepareStatement(requeteInsert);
        PS.setString(1, t.getNom());
        PS.setString(2, t.getPrenom());
        PS.setString(3, t.getEmail());
       
        /*String date= (String) t.getDate_naissance().toString();
        date = date.substring(0,4)+'/'+date.substring(5,7)+'/'+date.substring(8);            
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        
        PS.setDate(5, sqlDate);*/
        
        PS.setString(4, t.getPassword());
        PS.setString(5,t.getRole().toString());
        PS.setString(6,t.getAddresse());
        PS.setDate(7,t.getDate_naissance());

        PS.executeUpdate();
        
        //JavaMailUtil.sendMail(t.getEmail());
        return true;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        ste=con.createStatement();
        ste1=con.createStatement();
        String quere="delete from eleve where id_User="+id;
        ste1.executeUpdate(quere);
        String query="delete from user where id_User="+id;
        ste.executeUpdate(query);
        return true; 
    }
    
    @Override
    public boolean delete1(int id) throws SQLException {
        ste=con.createStatement();
        ste1=con.createStatement();
        String quere="delete from rating where ID_USER="+id;
        ste1.executeUpdate(quere);
        String query="delete from user where id_User="+id;
        ste.executeUpdate(query);
        return true; 
    }

    @Override
    public boolean update(User t) throws SQLException {
         ste = con.createStatement();
        String requeteInsert;
        requeteInsert = "update `2fac`.`user` set `Nom`=?,`Prenom`=?,`Email`=?,`Password`=?,`Role`=?  where id=?";
          
        PreparedStatement PS = con.prepareStatement(requeteInsert);
        
        PS.setString(1, t.getNom());
        PS.setString(2, t.getPrenom());
        PS.setString(3, t.getEmail());
       
        /*String date= (String) t.getDate_naissance().toString();
        date = date.substring(0,4)+'/'+date.substring(5,7)+'/'+date.substring(8);            
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        
        PS.setDate(4, sqlDate);*/
        
        PS.setString(5, t.getPassword());
        PS.setString(6,t.getRole().toString());
        PS.setInt(7, t.getId());

        PS.executeUpdate();

        return true;
    }

    @Override
    public List<User> readAll() throws SQLException {
        List<User> list= new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from user");
        while (rs.next()) {     
            User u=new User(rs.getInt("id_User"), rs.getString("nom"),  rs.getString("prenom"),  rs.getString("password"),  rs.getString("role"),  rs.getString("email"),  rs.getString("addresse"),rs.getDate("date_naissance"));
            list.add(u);
        }
        return list; 
    }
    
    public User connect(String email,String password) throws SQLException {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from user where email='"+email+"' and password='"+password+"'");
        while (rs.next()) {     
            User u=new User(rs.getInt("id_User"), rs.getString("nom"),  rs.getString("prenom"),  rs.getString("password"), rs.getString("role"),  rs.getString("email"),  rs.getString("addresse"),rs.getDate("date_naissance"));
            return u;
        }
        return null; 
    }
    
    public User getUserById(int id) throws SQLException {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from user where id_User="+id);
        while (rs.next()) {     
            User u=new User(rs.getInt("id_User"), rs.getString("nom"),  rs.getString("prenom"),  rs.getString("password"), rs.getString("role"),  rs.getString("email"),  rs.getString("addresse"),rs.getDate("date_naissance"));
            return u;
        }
        return null; 
    }
    
    
    public boolean updateData(User t) throws SQLException {
         ste = con.createStatement();
        String requeteInsert;
        requeteInsert = "update `2fac`.`user` set `Nom`=?,`Prenom`=?,`Email`=? where id_User=?";
          
        PreparedStatement PS = con.prepareStatement(requeteInsert);
        
        PS.setString(1, t.getNom());
        PS.setString(2, t.getPrenom());
        PS.setString(3, t.getEmail());
        
        PS.setInt(4, t.getId());

        PS.executeUpdate();

        return true;
    }
    
    public boolean updatePassword(User t) throws SQLException {
         ste = con.createStatement();
        String requeteInsert;
        requeteInsert = "update `2fac`.`user` set `password`=? where id_User=?";
          
        PreparedStatement PS = con.prepareStatement(requeteInsert);
        
        PS.setString(1, t.getPassword());
        
        PS.setInt(2, t.getId());

        PS.executeUpdate();

        return true;
    }
    
    public List<User> search(String str) throws SQLException {
        List<User> list= new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from user where nom like '"+str+"%' or prenom like '"+str+"%'");
        while (rs.next()) {     
            User u=new User(rs.getInt("id_User"), rs.getString("nom"),  rs.getString("prenom"),  rs.getString("password"),  rs.getString("role"),  rs.getString("email"),  rs.getString("addresse"),rs.getDate("date_naissance"));
            list.add(u);
        }
        return list; 
    }
    
}
