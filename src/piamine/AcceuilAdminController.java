/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piamine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev.FXMLEtablissementController;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class AcceuilAdminController implements Initializable {

    @FXML
    private AnchorPane affichage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void utilisateurs(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        affichage.getChildren().setAll(root);
    }

    @FXML
    private void clubs(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("clubs.fxml"));
        affichage.getChildren().setAll(root);
    }

    @FXML
    private void Abonnements(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("abonnements.fxml"));
        affichage.getChildren().setAll(root);
    }

    @FXML
    private void stats(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("FXChart.fxml"));
        affichage.getChildren().setAll(root);
    }

    @FXML
    private void ValiderEtab(ActionEvent event) throws IOException {
                     FXMLLoader loader=new FXMLLoader(getClass().getResource("/pidev/ConfirmEtab.fxml"));
                Parent Ajouter = (Parent)loader.load();    
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent Ajouter = (Parent)loader.load();
                Scene scene = new Scene(Ajouter);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
    
}
