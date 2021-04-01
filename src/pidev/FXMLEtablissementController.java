/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import DB.DataBase;
import Entités.Etablissement;
import Etab_CrudSpec.AjoutScoreController;
import Etab_CrudSpec.MainCrudEtabPaneController;
import Forum.FXMLDocumentController;
import Utils.AlertBox;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import piamine.ClubController;
import services.ServiceEtablissement;

/**
 *
 * @author Toumi
 */
public class FXMLEtablissementController implements Initializable {
    
    private Button btnConsulter;
    @FXML
    private Button btnAjouterCours;
    @FXML
    private Button btnlogout;
    @FXML
    private Text etabname;
    public static int tfidd;
    @FXML
    private Button btnClubg;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextArea tfDiscription;
    @FXML
    private TextField tfNum;
    @FXML
    private Button btnsupprime;
    @FXML
    private Button Btnmodif;
    @FXML
    private TextField tfpassnew;
    @FXML
    private Button btnpass;
    
    ServiceEtablissement sp=new ServiceEtablissement();
    @FXML
    private Button btnForum;
    @FXML
    private Button btnspec;
    @FXML
    private Button btnbiblio;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
    } 
    public void set(String set,int id){
        etabname.setText(set);
        tfidd=id;
    }
    public void set(String set,int id,String e){
        etabname.setText(set);
        tfidd=id;
        String etat=e;
    }
    
   

    @FXML
    private void Onclick(ActionEvent event) throws IOException, SQLException {
        if(event.getSource()==btnAjouterCours){
            try{
                FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLAjouter.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLAjouterController doc=loader.getController();
                doc.set(tfidd,etabname.getText());
                doc.ShowCours(tfidd);
                
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }catch(IOException ex){
                System.out.println("erorr");
            }
        }else if (event.getSource()==btnConsulter){
            try{
               FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLConsulterAdmin.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLConsulterAdminController doc=loader.getController();
                doc.set(tfidd,etabname.getText(),1);
                               
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
           }catch(IOException ex){
               System.out.println("erorr");
           }
            
            
        }else if (event.getSource()==btnlogout){
            try{
               Parent Login = FXMLLoader.load(getClass().getResource("FXMLLoginetab.fxml"));
           
        
        Scene scene = new Scene(Login);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
           }catch(IOException ex){
               System.out.println("erorr");
           }
            
        }else if(event.getSource()==Btnmodif){
            Etablissement e=new Etablissement(tfidd, tfNom.getText(), tfAddress.getText(), tfDiscription.getText(),Integer.parseInt(tfNum.getText()));
            sp.Update(e);
            AlertBox.display("Done", "Etablissement ");
               }
         else if(event.getSource()==btnsupprime){
              Etablissement e=new Etablissement(tfidd);
             AlertBox.display("Bye bye", "Vous avez supprimer votre Compte");
             sp.Supprime(e);
             FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLLoginetab.fxml"));
                    Parent Ajouter = (Parent)loader.load();
                     Scene scene = new Scene(Ajouter);
                     
                     Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                     window.setScene(scene);
                        window.show();
            
        }else if(event.getSource()==btnpass){
            Etablissement e=new Etablissement(tfidd,tfpassnew.getText());
            sp.UpdatePass(e);
            AlertBox.display("Congratz", "Mot de passe ");
        }
    } 
    public void Afficher(int id) throws SQLException{
        Connection conn =DataBase.getInstance().getConn();
        String query= "SELECT * FROM etablissement where ID_Etab="+id;
        Statement st;
        ResultSet rs;
        
         st = conn.createStatement();
         rs = st.executeQuery(query);
         
         while(rs.next()){
             tfNom.setText(rs.getString("Nom"));
             tfAddress.setText(rs.getString("Adress"));
             tfDiscription.setText((rs.getString("Discription")));
             tfNum.setText((rs.getString("Num")));
                   
         }
        
    }

    @FXML
    private void GestClub(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/piamine/Club.fxml"));
                Parent Ajouter = (Parent)loader.load();
                ClubController doc=loader.getController();
                doc.set(etabname.getText(), tfidd);
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    private void admin(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ConfirmEtab.fxml"));
                Parent Ajouter = (Parent)loader.load();
                ConfirmEtabController doc=loader.getController();
                doc.sett(etabname.getText(),tfidd);
                
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
        
    }

    @FXML
    private void Forumhold(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/Forum/FXMLDocument.fxml"));
                Parent CalautoSc = (Parent)loader.load();
                FXMLDocumentController doc=loader.getController();
                doc.etabname="e";
                doc.tfidd=tfidd;
                doc.set("e",tfidd,etabname.getText());
                
              
                
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
    }

    @FXML
    private void holdspec(ActionEvent event) throws IOException {
        
            
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Etab_CrudSpec/MainCrudEtabPane.fxml"));
                Parent CalautoSc = (Parent)loader.load();
                MainCrudEtabPaneController doc=loader.getController();
               
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
    }

    @FXML
    private void HoldBiblio(ActionEvent event) {
    }
  

    
}
