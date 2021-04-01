/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piamine;

import Entities.User;
import Forum.CommentaireController;
import Services.UserService;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pkg2fac_houssemriahi.MainController;


/**
 * FXML Controller class
 *
 * @author skand
 */
public class AcceuilEleveController implements Initializable {

    

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
    private void param(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("Parametre.fxml"));
        Parent parent = Loader.load();
        AnchorPane root = (AnchorPane) parent;
        ParametreController c = Loader.getController();
        c.setId_User(id_user);
        affichage.getChildren().setAll(root);
    }
UserService us = new UserService();
    User u = new User();
    public static int id_user = 0;
    public static String  etat="u"; 
    @FXML
    private void score(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/pkg2fac_houssemriahi/Main.fxml"));
                Parent Ajouter = (Parent)loader.load();
                 MainController c = loader.getController();
                 
                Scene scene = new Scene(Ajouter);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    public void setId_User(int id) throws SQLException {
        id_user = id;
        u = us.getUserById(id);

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
