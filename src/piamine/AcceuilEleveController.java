/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piamine;

import Entities.User;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class AcceuilEleveController implements Initializable {

    UserService us = new UserService();
    User u = new User();
    int id_user = 0;

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

    @FXML
    private void score(ActionEvent event) {
    }

    public void setId_User(int id) throws SQLException {
        id_user = id;
        u = us.getUserById(id);

    }
}
