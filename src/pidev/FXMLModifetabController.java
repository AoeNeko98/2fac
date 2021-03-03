/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Toumi
 */
public class FXMLModifetabController implements Initializable {

    @FXML
    private Button btncancel;
    @FXML
    private Button Btnmodif;
    @FXML
    private Button btnsupprime;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextArea tfDiscription;
    @FXML
    private PasswordField tfpassnew;
    @FXML
    private Button btnpass;
    private int id;
    private String Nom;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }   
    
    public void set(int idd,String Nom){
        id=idd;
        this.Nom=Nom;
    }

    @FXML
    private void Onclick(ActionEvent event) throws IOException {
        if(event.getSource()==Btnmodif){
            Update();
            AlertBox.display("Done", "Etablissement ");
            
        }else if (event.getSource()==btncancel){
            try{
              FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLEtablissement.fxml"));
                    Parent Ajouter = (Parent)loader.load();
                    
                    
                     
                     Scene scene = new Scene(Ajouter);
                     FXMLEtablissementController etab=loader.getController();
                     etab.set(tfNom.getText(), id);
        
                     Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                     window.setScene(scene);
                        window.show();
           }catch(IOException ex){
               System.out.println("erorr");
           }
         }else if (event.getSource()==btnsupprime){
             AlertBox.display("Bye bye", "Vous avez supprimer votre Compte");
             Supprime();
             FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLLoginetab.fxml"));
                    Parent Ajouter = (Parent)loader.load();
                     Scene scene = new Scene(Ajouter);
                     
                     Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                     window.setScene(scene);
                        window.show();
            
        }else if(event.getSource()==btnpass){
            UpdatePass();
            AlertBox.display("Congratz", "Mot de passe ");
        }
    }
    public Connection getConnetion(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/2fac","root","");
            return conn;
        }catch(Exception ex){
            System.out.println("error");
            return null;
        }
    }
 
    
    public void Afficher(int id) throws SQLException{
        Connection conn = getConnetion();
        String query= "SELECT * FROM etablissement where ID_Etab="+id;
        Statement st;
        ResultSet rs;
        
         st = conn.createStatement();
         rs = st.executeQuery(query);
         
         while(rs.next()){
             tfNom.setText(rs.getString("Nom"));
             tfAddress.setText(rs.getString("Adress"));
             tfDiscription.setText((rs.getString("Discription")));
             
             
         }
        
    }
    
    private void executeQuery(String query) {
       Connection conn = getConnetion();
        Statement st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("heelb:!!!!");
            
        }
    }
    
    public void Update(){
        String query="UPDATE etablissement SET Nom='"+tfNom.getText()+"',Adress='"+tfAddress.getText()+"',Discription='"+tfDiscription.getText()+"' WHERE ID_Etab="+id;
        executeQuery(query);
    }
    public void Supprime(){
        String query="DELETE FROM etablissement WHERE ID_Etab="+id;
        executeQuery(query);
    }
    public void UpdatePass(){
        String query="UPDATE etablissement SET password='"+tfpassnew.getText()+"' WHERE ID_Etab="+id;
        executeQuery(query);
    }
    
}
