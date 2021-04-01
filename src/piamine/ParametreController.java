/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piamine;

import Entities.User;
import Services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class ParametreController implements Initializable {

    UserService us = new UserService();

    @FXML
    private Button p4;
    @FXML
    private AnchorPane ap;
    @FXML
    private Label fnom;
    @FXML
    private Label fprenom;
    @FXML
    private Label ffemail;
    @FXML
    private TextField tfnom;
    @FXML
    private Button btninfo;
    @FXML
    private TextField tfprenom;
    @FXML
    private Label tfpw1;
    @FXML
    private Label tfpw2;
    @FXML
    private TextField tfemail;
    @FXML
    private Button btnpw;
    @FXML
    private PasswordField tfoldpw1;
    @FXML
    private PasswordField tfnewpw;
    @FXML
    private Button fdelete;

    @FXML
    private Label id_user;
    User u = new User();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void setId_User(int id) {
        id_user.setText(String.valueOf(id));
        id_user.setVisible(false);
        try {
            u = us.getUserById(id);
        } catch (SQLException ex) {
            Logger.getLogger(ParametreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfemail.setText(u.getEmail());
        tfnom.setText(u.getNom());
        tfprenom.setText(u.getPrenom());
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void change1(ActionEvent event) throws SQLException {
        u.setEmail(tfemail.getText());
        u.setNom(tfnom.getText());
        u.setPrenom(tfprenom.getText());
        us.updateData(u);
        Notifications notificationBuilder = Notifications.create()
                .title("Alert").text("Information modifié !").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.CENTER_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("clicked ON ");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void change2(ActionEvent event) throws SQLException {
        if (tfoldpw1.getText().equals(u.getPassword())) {
            if (tfnewpw.getText() != null) {
                u.setPassword(tfnewpw.getText());
                us.updatePassword(u);
                Notifications notificationBuilder = Notifications.create()
                        .title("Alert").text("mot de passe modifié!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                        .position(Pos.CENTER_LEFT)
                        .onAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                System.out.println("clicked ON ");
                            }
                        });
                notificationBuilder.darkStyle();
                notificationBuilder.show();
            }

        } else {
            Notifications notificationBuilder = Notifications.create()
                    .title("Alert").text("mot de passe érroné !").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.CENTER_LEFT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("clicked ON ");
                        }
                    });
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        }

    }

    @FXML
    private void change(ActionEvent event) throws SQLException {
        UserService i=new UserService();
        try {
             i.delete(Integer.parseInt(id_user.getText()));
        } catch (Exception e) {
        }
        try {
            i.delete1(Integer.parseInt(id_user.getText()));
        } catch (Exception e) {
        }
       
    }

}
