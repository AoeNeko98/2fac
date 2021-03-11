/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DB.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Entités.Cours;
import Entités.Rate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Toumi
 */
public class ServiceCours {
     Connection conn;
      public ServiceCours() {
         conn = DataBase.getInstance().getConn();
        
    }
      
      public void AjouterEtab(Cours c) throws SQLException{ 
        
        Statement st;
        String query2="SELECT ID_SPEC FROM speciality WHERE Nom='"+c.getNom_spec()+"'";
        st = conn.createStatement();
        ResultSet rs2=st.executeQuery(query2);
        while(rs2.next()){
            int id_spec=rs2.getInt("ID_SPEC");
         String query = "INSERT INTO Cours (ID_Etab,Nom,Discription,ID_SPEC) VALUES("+c.getId_etab()+",'"+c.getCoursname()+"','"+c.getDiscription()+"',"+id_spec+")";
         Statement st2=conn.createStatement();
         st2.executeUpdate(query);
        
    }
        

    }
      
      public void Supprimer(Cours c) throws SQLException {
          String query="DELETE from Cours where ID_Cours="+c.getID_Cours();
          String query1="DELETE from rating where ID_Cours="+c.getID_Cours();
        Statement st;
        st=conn.createStatement();
          st.executeUpdate(query1);
        st.executeUpdate(query);
        
      }
      
    public void modifier(Cours c) throws SQLException{
        Statement st;
        String query2="SELECT ID_SPEC FROM speciality WHERE Nom='"+c.getNom_spec()+"'";
        st = conn.createStatement();
        ResultSet rs2=st.executeQuery(query2);
        while(rs2.next()){
            int id_spec=rs2.getInt("ID_SPEC");
         String query = "UPDATE Cours SET Nom='"+c.getCoursname()+"',Discription='"+c.getDiscription()+"',ID_SPEC="+id_spec+" WHERE ID_Cours="+c.getID_Cours();
         Statement st1=conn.createStatement();
        st1.executeUpdate(query);
       
    }
    }
    public ObservableList<Cours> getCoursList(int id){
        ObservableList<Cours> CoursList = FXCollections.observableArrayList();
        String query= "SELECT * FROM Cours where ID_Etab="+id;

        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Cours cour;
            while(rs.next()){
                 int id_spec=rs.getInt("ID_SPEC");
                 String query1="SELECT Nom FROM speciality WHERE ID_SPEC="+id_spec;
                 
                int id_cours=rs.getInt("ID_Cours");
                String Nom_cours=rs.getString("Nom");
                String Discription=rs.getString("Discription");
                Statement st1;
                 st1=conn.createStatement();
                ResultSet rs1=st1.executeQuery(query1);
                while(rs1.next()){
                   
                    cour=new Cours(id_cours, Discription,rs1.getString("Nom"), Nom_cours);
                    System.out.println(cour.toString());
                    
                CoursList.add(cour);
                }
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
        return CoursList;
        }
    
    public void Ajoutrate(Rate c) throws SQLException{
        Statement st=conn.createStatement();
        if(verif(c.getId_USER(),c.getId_Cours())){
        String query="Insert into rating (ID_Cours,ID_USER,Rate) values ("+c.getId_Cours()+","+c.getId_USER()+","+c.getRating()+")";
        st.executeUpdate(query);
        }else{
             String query="UPDATE rating SET Rate="+c.getRating()+" WHERE ID_Cours="+c.getId_Cours()+" AND ID_USER="+c.getId_USER();
        st.executeUpdate(query);
        }
    }
        
    public boolean verif(int id_user,int id_Cours) throws SQLException{
           Statement st=conn.createStatement();
           String query="SELECT * from rating where ID_Cours="+id_Cours+" AND ID_USER="+id_user;
           ResultSet rs;
           rs=st.executeQuery(query);
           while(rs.next()){
               return false;
           }
            
        return true;
}
      
    
    
}
