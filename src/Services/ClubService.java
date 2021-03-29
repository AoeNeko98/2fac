/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Abonnement;
import Entities.Domaine;
import Entities.Role;
import Entities.User;
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

/**
 *
 * @author skand
 */
public class ClubService implements ServiceInterface<club>{
    private Connection con;
    private Statement ste;

    public ClubService() {
        con = DataBase.getInstance().getConnection();
    }
    

    @Override
    public boolean add(club t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert;
        requeteInsert = "INSERT INTO `amine`.`club` (`nom`,`description`,`domaine`,`places`) VALUES (?,?,?,?)";
          
        PreparedStatement PS = con.prepareStatement(requeteInsert);
        PS.setString(1, t.getNom());
        PS.setString(2, t.getDescription());
        PS.setString(3, String.valueOf(t.getDomaine()));
        PS.setInt(4, t.getPlaces());


        PS.executeUpdate();
        return true;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        AbonnementService as=new AbonnementService();
        List<Abonnement> l=new ArrayList<>();
        l=as.readAllByIdClub(id);
        for(Abonnement a:l){
            as.delete(a.getId());
        }
        ste=con.createStatement();
        String query="delete from club where id="+id;
        ste.execute(query);
        return true; 
    }

    @Override
    public boolean update(club t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert;
        requeteInsert = "Update `amine`.`club` set `nom`=?,`description`=?,`domaine`=?,`places`=? where `id`=?";
          
        PreparedStatement PS = con.prepareStatement(requeteInsert);
        PS.setString(1, t.getNom());
        PS.setString(2, t.getDescription());
        PS.setString(3, String.valueOf(t.getDomaine()));
        PS.setInt(4, t.getPlaces());
        PS.setInt(5, t.getId());


        PS.executeUpdate();
        return true;
    }

    @Override
    public List<club> readAll() throws SQLException {
        List<club> list= new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from club");
        AbonnementService as=new AbonnementService();
        while (rs.next()) {     
            club c=new club(rs.getInt("id"), rs.getString("nom"),  rs.getString("description"),  Domaine.valueOf(rs.getString("domaine")),rs.getInt("places"));
            c.setPlaceDispo(rs.getInt("places") - as.CalculPlacesDispo(rs.getInt("id")));
            list.add(c);
        }
        return list; 
    }
    
    
    
}
