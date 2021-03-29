/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piamine;

import Entities.Role;
import Entities.User;
import Services.UserService;
import Utils.JavaMailUtils;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class LoginController implements Initializable {

    UserService us = new UserService();

    @FXML
    private AnchorPane layersignup;
    @FXML
    private AnchorPane layer1;
    @FXML
    private Label a2;
    @FXML
    private Label a1;
    @FXML
    private Label b2;
    @FXML
    private TextField u1;
    @FXML
    private TextField u2;
    @FXML
    private TextField u3;
    @FXML
    private JFXButton btnsignup;
    @FXML
    private Label b1;
    @FXML
    private JFXButton btnsignin;

    @FXML
    private Label n3;

    @FXML
    private AnchorPane layer2;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private JFXButton signin;
    @FXML
    private JFXButton signup;
    @FXML
    private Label s1;
    @FXML
    private Label s2;
    @FXML
    private Label s3;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfAdress;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private RadioButton radeleve;
    @FXML
    private ToggleGroup mychange;
    @FXML
    private RadioButton radetudient;
    @FXML
    private RadioButton radetablissement;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label addresse;
    @FXML
    private Label email;
    @FXML
    private Label password;
    @FXML
    private TextField n1;
    @FXML
    private PasswordField n2;
    @FXML
    private DatePicker dateN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
        btnsignin.setVisible(false);
        n1.setVisible(false);
        n2.setVisible(false);
        n3.setVisible(false);
        u1.setVisible(false);
        u2.setVisible(false);
        u3.setVisible(false);
    }

    @FXML
    private void btnsignup(MouseEvent event) {
    }

    @FXML
    private void signup(ActionEvent event) throws SQLException, IOException, MessagingException {
        Date date_naissance;
        Role role;
        if (radetudient.isSelected()) {
            role = Role.Etudiant;

        } else if (radeleve.isSelected()) {
            role = Role.Eleve;
        } else {
            role = Role.Etablissement;
        }

        DatePicker tmpdate = (DatePicker) dateN;
        String date = (String) tmpdate.getValue().toString();
        date = date.substring(0, 4) + '/' + date.substring(5, 7) + '/' + date.substring(8);
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqldate = new java.sql.Date(myDate.getTime());

        User u = new User(tfNom.getText(), tfPrenom.getText(), tfPassword.getText(), role, tfEmail.getText(), tfAdress.getText(), sqldate);
        us.add(u);

        if (role == Role.Etudiant) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Je vous souhaite la bienvenue Mr/Mme" + tfNom.getText() + " " + tfPrenom.getText(), ButtonType.OK);
            alert.show();
            JavaMailUtils.sendMail(tfEmail.getText());
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("acceuil.fxml"));
            Parent parent = Loader.load();
            AcceuilController c = Loader.getController();
            u = us.connect(tfEmail.getText(), tfPassword.getText());

            c.setId_User(u.getId());
            Scene scene = new Scene(parent);
            Stage window = (Stage) n1.getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else if (role == Role.Etablissement) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Je vous souhaite la bienvenue Mr/Mme" + tfNom.getText() + " " + tfPrenom.getText(), ButtonType.OK);
            alert.show();
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("acceuilEtablissement.fxml"));
            Parent parent = Loader.load();
            AcceuilEtablissementController c = Loader.getController();
            u = us.connect(tfEmail.getText(), tfPassword.getText());

            c.setId_User(u.getId());
            Scene scene = new Scene(parent);
            Stage window = (Stage) n1.getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else if (role == Role.Eleve) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Je vous souhaite la bienvenue Mr/Mme" + tfNom.getText() + " " + tfPrenom.getText(), ButtonType.OK);
            alert.show();
            JavaMailUtils.sendMail(tfEmail.getText());

            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("acceuilEleve.fxml"));
            Parent parent = Loader.load();
            AcceuilEleveController c = Loader.getController();

            c.setId_User(u.getId());
            Scene scene = new Scene(parent);
            Stage window = (Stage) n1.getScene().getWindow();
            window.setScene(scene);
            window.show();
        }else if (tfEmail.getText().isEmpty() && tfPassword.getText().isEmpty()&& tfNom.getText().isEmpty()&&tfPrenom.getText().isEmpty()&&tfAdress.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Empty input " + tfNom.getText() + " " + tfPrenom.getText(), ButtonType.OK);
                    alert.show();
                }

    }

    @FXML
    private void sign(MouseEvent event) {
    }

    @FXML
    private void Signin(ActionEvent event) throws SQLException, IOException, MessagingException {
        User u = us.connect(n1.getText(), n2.getText());
        if (u != null) {
             
            if (u.getRole() == Role.Etudiant) {
                JavaMailUtils.sendMail(tfEmail.getText());
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("acceuil.fxml"));
                Parent parent = Loader.load();
                AcceuilController c = Loader.getController();

                c.setId_User(u.getId());
                Scene scene = new Scene(parent);
                Stage window = (Stage) n1.getScene().getWindow();
                window.setScene(scene);
                window.show();

            } else if (u.getRole() == Role.Etablissement) {
                System.out.println("Etablissement!");
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("acceuilEtablissement.fxml"));
                Parent parent = Loader.load();
                AcceuilEtablissementController c = Loader.getController();

                c.setId_User(u.getId());
                Scene scene = new Scene(parent);
                Stage window = (Stage) n1.getScene().getWindow();
                window.setScene(scene);
                window.show();
            } else if (u.getRole() == Role.Eleve) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("acceuilEleve.fxml"));
                Parent parent = Loader.load();
                AcceuilEleveController c = Loader.getController();
                c.setId_User(u.getId());
                Scene scene = new Scene(parent);
                Stage window = (Stage) n1.getScene().getWindow();
                window.setScene(scene);
                window.show();
            } else {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("acceuilAdmin.fxml"));
                Parent parent = Loader.load();

                Scene scene = new Scene(parent);
                Stage window = (Stage) n1.getScene().getWindow();
                window.setScene(scene);
                window.show();
            }

        } else if (tfEmail.getText().isEmpty() && tfPassword.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Empty input " + tfNom.getText() + " " + tfPrenom.getText(), ButtonType.OK);
                    alert.show();
                } {
            System.out.println("Erreur!");
        }
    }

    @FXML
    private void signinView(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(491);
        slide.play();

        layer1.setTranslateX(-309);
        btnsignin.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(true);

        s1.setVisible(true);
        s2.setVisible(true);
        s3.setVisible(true);
        signup.setVisible(true);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        signin.setVisible(false);
        a1.setVisible(false);
        a2.setVisible(false);
        btnsignup.setVisible(false);
        n1.setVisible(true);
        n2.setVisible(true);
        n3.setVisible(true);
        u1.setVisible(false);
        u2.setVisible(false);
        u3.setVisible(false);

        tfNom.setVisible(false);
        tfPrenom.setVisible(false);
        tfAdress.setVisible(false);
        tfEmail.setVisible(false);
        tfPassword.setVisible(false);
        radeleve.setVisible(false);
        radetudient.setVisible(false);
        radetablissement.setVisible(false);
        nom.setVisible(false);
        prenom.setVisible(false);
        addresse.setVisible(false);
        email.setVisible(false);
        password.setVisible(false);
        dateN.setVisible(false);

        slide.setOnFinished((e -> {

        }));
    }

    @FXML
    private void signupView(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(0);
        slide.play();

        layer1.setTranslateX(0);
        btnsignin.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);

        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        signin.setVisible(true);
        a1.setVisible(true);
        a2.setVisible(true);
        btnsignup.setVisible(true);
        n1.setVisible(false);
        n2.setVisible(false);
        n3.setVisible(false);
        u1.setVisible(false);
        u2.setVisible(false);
        u3.setVisible(false);

        tfNom.setVisible(true);
        tfPrenom.setVisible(true);
        tfAdress.setVisible(true);
        tfEmail.setVisible(true);
        tfPassword.setVisible(true);
        radeleve.setVisible(true);
        radetudient.setVisible(true);
        radetablissement.setVisible(true);
        nom.setVisible(true);
        prenom.setVisible(true);
        addresse.setVisible(true);
        email.setVisible(true);
        password.setVisible(true);
        dateN.setVisible(true);

        slide.setOnFinished((e -> {

        }));
    }

}
