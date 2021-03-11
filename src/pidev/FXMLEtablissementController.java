/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Toumi
 */
public class FXMLEtablissementController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button btnConsulter;
    @FXML
    private Button btnAjouterCours;
    @FXML
    private Button btnlogout;
    @FXML
    private Button btnmodif;
    @FXML
    private Text etabname;
    private int tfidd;
    @FXML
    private Button btnClubg;
    @FXML
    private Button btnClubC;
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
    } 
    public void set(String set,int id){
        etabname.setText(set);
        tfidd=id;
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
            
        }else if (event.getSource()==btnmodif){
            try{
              FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLModifetab.fxml"));
                    Parent Ajouter = (Parent)loader.load();
                    FXMLModifetabController doc=loader.getController();
                    doc.Afficher(tfidd);
                    doc.set(tfidd,etabname.getText());
                  
                     
                     Scene scene = new Scene(Ajouter);
        
                     Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                     window.setScene(scene);
                        window.show();
           }catch(IOException ex){
               System.out.println("erorr");
           }
        }
    }

    @FXML
    private void GestClub(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLAjouterClub.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLAjouterClubController doc=loader.getController();
                doc.set(tfidd,etabname.getText());
                doc.ShowClubs(tfidd);
                               
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
      
    }

    @FXML
    private void Consultclub(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLConsulterClub.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLConsulterClubController doc=loader.getController();
                doc.set(tfidd,etabname.getText());
                
                               
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
    
    
    
}
