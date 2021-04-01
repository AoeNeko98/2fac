/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

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
import javafx.stage.Stage;
import pidev.FXMLEtablissementController;

/**
 * FXML Controller class
 *
 * @author kais
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button BtnConsultation;
    @FXML
    private Label label;
    @FXML
    private Button BtnCommentaire;
    @FXML
    public Label tfid;
    @FXML
    public Label tftype;
    
   
    
    @FXML
    public void changesScreenConsulerPubs(ActionEvent event) throws IOException
    {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterPublication.fxml"));
                Parent CalautoSc = (Parent)loader.load();
        ConsulterPublicationController doc=loader.getController();
                doc.etabname="e";
                doc.tfidd=tfidd;
                doc.set("e",tfidd,nom);
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
    }
   
    
     @FXML
    public void changesScreenCommentaire(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("commentaire.fxml"));
                Parent CalautoSc = (Parent)loader.load();
        CommentaireController doc=loader.getController();
                doc.etabname="e";
                doc.tfidd=tfidd;
                doc.set("e",tfidd,nom);
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
    }
    
    public String etabname;
    public String nom;
    public int tfidd;
    public void set(String Y,int id,String nom){
        etabname=(Y);
        tfidd=id;
        this.nom=nom;
        System.out.println(etabname);
        System.out.println(tfidd);
        
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
    }  

   

    @FXML
    private void retour(ActionEvent event) throws IOException, SQLException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/pidev/FXMLEtablissement.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLEtablissementController doc=loader.getController();
                doc.set(nom,tfidd);
                doc.Afficher(tfidd);
                    
                
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

   

    
}
