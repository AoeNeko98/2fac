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
public class FXMLInscrireEtabController implements Initializable {

    @FXML
    private TextField tfNom_etab;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextArea tfDiscription;
    @FXML
    private Button btncancel;
    @FXML
    private Button btninscrire;
    @FXML
    private PasswordField tfpass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Onclick(ActionEvent event) throws IOException {
        if(event.getSource()==btninscrire){
            Ajouter();
            AlertBox.display("Bienvenue", "Merci d'attendre la validation d'un Admin");
            Parent Ajouter = FXMLLoader.load(getClass().getResource("FXMLLoginetab.fxml"));
           
        
        Scene scene = new Scene(Ajouter);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        }else if(event.getSource()==btncancel){
            Parent Ajouter = FXMLLoader.load(getClass().getResource("FXMLLoginetab.fxml"));
           
        
        Scene scene = new Scene(Ajouter);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
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
     
     private void executeQuery(String query) {
       Connection conn = getConnetion();
        Statement st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("heelp:!!!!");
            
        }
    }
     
     public void Ajouter() {
          String query = "INSERT INTO etablissement (Nom,Adress,Discription,password) VALUES('"+tfNom_etab.getText()+"','"+tfAddress.getText()+"','"+tfDiscription.getText()+"','"+tfpass.getText()+"')";
          executeQuery(query);
       
         
         
         
     }
     
     
    
    
    
}
