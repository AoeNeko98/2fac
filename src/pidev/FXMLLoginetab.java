/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entités.Etablissement;
import Utils.AlertBox;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
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
import pidev.FXMLEtablissementController;
import services.ServiceEtablissement;

/**
 * FXML Controller class
 *
 * @author Toumi
 */
public class FXMLLoginetab implements Initializable {

    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Button btnlogin;
    @FXML
    private Button btninscrire;
    @FXML
    private Button btninscrire2;
    public int id;
    public String name;
    @FXML
    private TextField tfNom;
    @FXML
    private Button btncancel;
    @FXML
    private TextField tfNom_etab;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextArea tfDiscription;
    @FXML
    private TextField tfNum;
    @FXML
    private PasswordField tfpass;
    @FXML
    private Button getpass;
    ServiceEtablissement sp=new ServiceEtablissement();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Onclick(ActionEvent event) throws IOException, SQLException {
        if(event.getSource()==btninscrire){
            Parent etab = FXMLLoader.load(getClass().getResource("FXMLInscrireEtab.fxml"));
            
            
        Scene scene = new Scene(etab);
        
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        }else if (event.getSource()==btnlogin){
            if (Veriflogin(tfLogin.getText(),tfpassword.getText())==true){
                if (Valide(tfLogin.getText())==true){
                    
                     FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLEtablissement.fxml"));
                    Parent Ajouter = (Parent)loader.load();
                    FXMLEtablissementController doc=loader.getController();
                    doc.set(tfLogin.getText(),getid(tfLogin.getText()));
                    doc.Afficher(getid(tfLogin.getText()));
                    
                     
                     Scene scene = new Scene(Ajouter);
        
                     Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                     window.setScene(scene);
                        window.show();
                }else   {
                    AlertBox.display("Probleme", "Compte non active");
                }
                
                        
                
            }else {
                AlertBox.display("Probleme", "Invalid login or password");
        }
            
        }else if(event.getSource()==btninscrire2){
            Etablissement e=new Etablissement(tfNom_etab.getText(), tfAddress.getText(), tfDiscription.getText(), tfpass.getText(), Integer.parseInt(tfNum.getText()));
            sp.Ajouter(e);
            AlertBox.display("Bienvenue", "Merci d'attendre la validation d'un Admin");
            Parent Ajouter = FXMLLoader.load(getClass().getResource("FXMLLoginetab.fxml"));
           
            System.out.println("done");
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
     
     private boolean Veriflogin(String login,String pass) throws SQLException{
         
         Connection conn = getConnetion();
        String query= "SELECT * FROM etablissement";
        Statement st;
        ResultSet rs;        
         st = conn.createStatement();
         rs = st.executeQuery(query);
         while(rs.next()){
             if (rs.getString("Nom").toLowerCase().equals(login.toLowerCase()) && rs.getString("password").equals(pass)){
                 
                 return true;
             }
         }
         
         return false;
     }
     
    private boolean Valide(String Nom) throws SQLException{
        Connection conn = getConnetion();
        String query= "SELECT * FROM etablissement WHERE Nom='"+Nom+"'";
        Statement st;
        ResultSet rs;
        
         st = conn.createStatement();
         rs = st.executeQuery(query);
         while(rs.next()){
             if (rs.getBoolean("Etat")==true){
                 
                 return true;
             }return false;}
     
     return false;
    
}
    private int getid(String login) throws SQLException{
        Connection conn = getConnetion();
        String query= "SELECT * FROM etablissement WHERE Nom='"+login+"'";
        Statement st;
        ResultSet rs;
        
         st = conn.createStatement();
         rs = st.executeQuery(query);
         while(rs.next()){
             return rs.getInt("ID_Etab");
         }
         return 0;
    }
    @FXML
    private void Lostpasswordhold(ActionEvent event) throws  IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLLostpass.fxml"));
                    Parent Ajouter = (Parent)loader.load();
                  
                   
                     
                     Scene scene = new Scene(Ajouter);
        
                     Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                     window.setScene(scene);
                        window.show();
        
    }
    @FXML
    private void Onhold(ActionEvent event) throws SQLException, IOException {
        if(event.getSource()==getpass){
                    SendSMS();
        AlertBox.display("Done", "Votre mot de passe est Envoyé");
    FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLLoginetab.fxml"));
                    Parent Ajouter = (Parent)loader.load();
                     Scene scene = new Scene(Ajouter);      
                     Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                     window.setScene(scene);
                            window.show();  
        }else if (event.getSource()==btncancel){
                FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLLoginetab.fxml"));
                    Parent Ajouter = (Parent)loader.load();
                     Scene scene = new Scene(Ajouter);      
                     Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                     window.setScene(scene);
                            window.show(); 
        }
        
    }
    
    public void SendSMS() throws SQLException{
        Connection conn = getConnetion();
        String query= "SELECT * FROM etablissement where Nom='"+tfNom.getText()+"'";
        Statement st;
        ResultSet rs;
        
         st = conn.createStatement();
         rs = st.executeQuery(query);
         while(rs.next()){

        Twilio.init("ACe1490497eaf1f7648005b7e398d9a671","ea2e45d154c4d8a1a0812d565e4992a6");
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+216"+rs.getInt("Num")),
                new com.twilio.type.PhoneNumber("+1 203-408-6653"),
                "Salut, "+rs.getString("Nom")+" Votre mot de passe est '"+rs.getString("password")+"'")
            .create();

        System.out.println(message.getSid());
    }
                 
                 }
        
}