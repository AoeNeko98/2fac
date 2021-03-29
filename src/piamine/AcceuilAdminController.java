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
import javafx.scene.layout.AnchorPane;

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
    
}
