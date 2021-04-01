/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import DB.DataBase;
import Entités.Club;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Entités.Cours;

/**
 *
 * @author Toumi
 */
public class ServiceClub {
    
    Connection conn;
      public ServiceClub() {
         conn = DataBase.getInstance().getConn();
        
    }
      
     public void Ajouter(Club c) throws SQLException{
    Statement st = conn.createStatement();
          String req =" insert into club (Nom,Discription,ID_Etab) values ('"+c.getNom()+"','"+c.getDescription() +"',"+c.getId_etab()+")"; 
    st.executeUpdate(req);
    
}
     
     public void Modifier(Club c) throws SQLException {
         Statement st = conn.createStatement();
         String req="UPDATE club Set Nom='"+c.getNom()+"',Discription='"+c.getDescription()+"' where ID_Club="+c.getId();
         st.executeUpdate(req);
                
     }
     
     public void Supprimer(Club c) throws SQLException{
        Statement st = conn.createStatement();
         String req="Delete from club where ID_Club="+c.getId();
         st.executeUpdate(req);
     }
     
    
           public ObservableList<Club> getCoursList(int id){
         Connection conn;
         conn = DataBase.getInstance().getConn();
        ObservableList<Club> ClubList = FXCollections.observableArrayList();
        String query= "SELECT * FROM club Where ID_Etab="+id;
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Club club;
            while(rs.next()){
                 int id_club=rs.getInt("ID_Club");
                int id_etab=rs.getInt("ID_Etab");
                String query1="SELECT Nom,ID_Etab FROM etablissement WHERE ID_Etab="+id_etab;
                String Nom_club=rs.getString("Nom");
                String Discription=rs.getString("Discription");
                Statement st1;
                 st1=conn.createStatement();
                 ResultSet rs1=st1.executeQuery(query1);
                while(rs1.next()){
                   
                    club=new Club(id_club, Nom_club, Discription,rs1.getString("Nom"));
                    
                ClubList.add(club);
                }
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
        return ClubList;
        }

            public ObservableList<Club> getCoursLists(){
         Connection conn;
         conn = DataBase.getInstance().getConn();
        ObservableList<Club> ClubList = FXCollections.observableArrayList();
        String query= "SELECT * FROM club ";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Club club;
            while(rs.next()){
                 int id_club=rs.getInt("ID_Club");
                int id_etab=rs.getInt("ID_Etab");
                String query1="SELECT Nom,ID_Etab FROM etablissement WHERE ID_Etab="+id_etab;
                String Nom_club=rs.getString("Nom");
                String Discription=rs.getString("Discription");
                Statement st1;
                 st1=conn.createStatement();
                 ResultSet rs1=st1.executeQuery(query1);
                while(rs1.next()){
                   
                    club=new Club(id_club, Nom_club, Discription,rs1.getString("Nom"));
                    
                ClubList.add(club);
                }
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
        return ClubList;
        }
    
    
}
