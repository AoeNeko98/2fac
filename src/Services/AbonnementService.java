/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Abonnement;
import Entities.Domaine;
import Entities.club;
import ServiceInterface.ServiceInterface;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AbonnementService implements ServiceInterface<Abonnement> {

    private Connection con;
    private Statement ste;

    public AbonnementService() {
        con = DataBase.getInstance().getConnection();
    }
    
    @Override
    public boolean add(Abonnement t) throws SQLException {
         ste = con.createStatement();
        String requeteInsert;
        requeteInsert = "INSERT INTO `amine`.`abonnement` (`id_user`,`id_club`,`date`) VALUES (?,?,?)";
          
        PreparedStatement PS = con.prepareStatement(requeteInsert);
        PS.setInt(1, t.getId_user());
        PS.setInt(2, t.getId_club());
        PS.setDate(3, t.getDate());


        PS.executeUpdate();
        return true;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        ste=con.createStatement();
        String query="delete from abonnement where id="+id;
        ste.execute(query);
        return true; 
    }

    @Override
    public boolean update(Abonnement t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert;
        requeteInsert = "Update `amine`.`abonnement` set `id_club`=?,`date`=? where `id`=?";
          
        PreparedStatement PS = con.prepareStatement(requeteInsert);
        PS.setInt(1, t.getId_club());
        PS.setDate(2, t.getDate());
        PS.setInt(3, t.getId());


        PS.executeUpdate();
        return true;
    }

    @Override
    public List<Abonnement> readAll() throws SQLException {
        List<Abonnement> list= new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select abonnement.*,user.prenom,club.nom from abonnement inner join club on abonnement.id_club=club.id inner join user on abonnement.id_user=user.id");
        while (rs.next()) {     
            Abonnement a=new Abonnement(rs.getInt("id"), rs.getInt("id_user"),  rs.getInt("id_club"),  rs.getDate("date"),rs.getString("nom"),rs.getString("prenom"));
            list.add(a);
        }
        return list; 
    }
    
    public List<Abonnement> readAllByUserId(int id) throws SQLException {
        List<Abonnement> list= new ArrayList<>();
        ste=con.createStatement();
        System.out.println(id);
        ResultSet rs=ste.executeQuery("select abonnement.*,user.prenom,club.nom from abonnement inner join club on abonnement.id_club=club.id inner join user on abonnement.id_user=user.id where abonnement.id_user='"+id+"'");
        while (rs.next()) {     
            Abonnement a=new Abonnement(rs.getInt("id"), rs.getInt("id_user"),  rs.getInt("id_club"),  rs.getDate("date"),rs.getString("nom"),rs.getString("prenom"));
            list.add(a);
        }
        return list; 
    }
    
    
    public int CalculPlacesDispo(int id) throws SQLException {
        ste=con.createStatement();
        int total=0;
        ResultSet rs=ste.executeQuery("select abonnement.*,user.prenom,club.nom from abonnement inner join club on abonnement.id_club=club.id inner join user on abonnement.id_user=user.id where abonnement.id_club='"+id+"'");
        while (rs.next()) {     
            total++;
        }
        return total; 
    }
    
    public boolean Verification(int id_user,int id_club) throws SQLException {
        ste=con.createStatement();
        int total=0;
        ResultSet rs=ste.executeQuery("select abonnement.*,user.prenom,club.nom from abonnement inner join club on abonnement.id_club=club.id inner join user on abonnement.id_user=user.id where abonnement.id_user='"+id_user+"' and abonnement.id_club='"+id_club+"'");
        while (rs.next()) {     
            total++;
        }
       if(total==0){
           return false;
       }
       else{
           return true;
       }
    }
    
    public List<Abonnement> readAllByIdClub(int id_club) throws SQLException {
        List<Abonnement> list= new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select abonnement.*,user.prenom,club.nom from abonnement inner join club on abonnement.id_club=club.id inner join user on abonnement.id_user=user.id where id_club='"+id_club+"'");
        while (rs.next()) {     
            Abonnement a=new Abonnement(rs.getInt("id"), rs.getInt("id_user"),  rs.getInt("id_club"),  rs.getDate("date"),rs.getString("nom"),rs.getString("prenom"));
            list.add(a);
        }
        return list; 
    }
}
