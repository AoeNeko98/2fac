/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DB.DataBase;
import Entités.Cours;
import Entités.Etablissement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Toumi
 */
public class ServiceEtablissement implements IService.IServiceEtablissement{
      Connection conn;
      public ServiceEtablissement() {
         conn = DataBase.getInstance().getConn();
        
    }
      
      @Override
       public void Ajouter(Etablissement e) throws SQLException {
          String query = "INSERT INTO etablissement (Nom,Adress,Discription,password,Num) VALUES('"+e.getNom()+"','"+e.getAddresse()+"','"+e.getDiscription()+"','"+e.getPass()+"',"+e.getNum()+")";
            Statement st=conn.createStatement();
            st.executeUpdate(query);
}
      @Override
       public void Update(Etablissement e) throws SQLException{
        String query="UPDATE etablissement SET Nom='"+e.getNom()+"',Adress='"+e.getAddresse()+"',Discription='"+e.getDiscription()+"',Num="+e.getNum()+" WHERE ID_Etab="+e.getId();
         Statement st=conn.createStatement();
            st.executeUpdate(query);
    }
       
      @Override
      public void Supprime(Etablissement e) throws SQLException{
          ServiceCours spc=new ServiceCours();
          String query="Select ID_Cours FROM cours WHERE ID_Etab="+e.getId();
          Statement st=conn.createStatement();
          ResultSet rs=st.executeQuery(query);
          while(rs.next()){
              Cours c=new Cours(rs.getInt("ID_Cours"));
              spc.Supprimer(c);
              
          }

          String query1="DELETE FROM speciality WHERE ID_Etb="+e.getId();
          String query2="DELETE FROM etablissement WHERE ID_Etab="+e.getId();

            st.executeUpdate(query1);
            st.executeUpdate(query2);

      }
      @Override
      public void UpdatePass(Etablissement e) throws SQLException{
        String query="UPDATE etablissement SET password='"+e.getPass()+"' WHERE ID_Etab="+e.getId();
        Statement st=conn.createStatement();
            st.executeUpdate(query);
}
      
      public ObservableList<Etablissement> getCoursList(){
        ObservableList<Etablissement> EtabList = FXCollections.observableArrayList();
        String query= "SELECT * FROM etablissement ";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Cours cour;
            while(rs.next()){
                    Etablissement e=new Etablissement(rs.getInt("ID_Etab"),rs.getString("Nom"),rs.getString("Adress"),rs.getString("Discription"),rs.getInt("Num"),rs.getInt("Etat"));
    
                    
                EtabList.add(e);
                
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
        return EtabList;
        }
      public void valide(Etablissement e) throws SQLException{
          
          String query="UPDATE etablissement SET Etat="+1+" WHERE ID_Etab="+e.getId();
         Statement st=conn.createStatement();
            st.executeUpdate(query); 
          
      }
      public void invalide(Etablissement e) throws SQLException{
          
          String query="UPDATE etablissement SET Etat="+0+" WHERE ID_Etab="+e.getId();
         Statement st=conn.createStatement();
            st.executeUpdate(query); 
          
      }
}
