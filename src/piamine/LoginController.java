/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piamine;

import Entities.Role;
import Entities.Speciality;
import Entities.User;
import Etab_CrudSpec.AjoutScoreController;
import Services.UserService;
import Utils.JavaMailUtils;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    @FXML
    private JFXButton btneTAB;

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
    public User getID(){
        
        Connection conn = getConnection();
        String query1="SELECT * FROM User ORDER BY ID_User DESC LIMIT 1";
        User specialityss = null;
        Statement st;
        ResultSet rs;
        
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query1);
            
            while(rs.next()){
            specialityss= new User();
            specialityss.setId(rs.getInt("ID_User"));
            
                
                
            }
            
          
        }catch (SQLException ex){
        }
        return specialityss;
    }
    @FXML
    private void signup(ActionEvent event) throws SQLException, IOException,  javax.mail.MessagingException {
        if (((!radeleve.isSelected())  && (!radetudient.isSelected())) || ((tfNom.getText().isEmpty()) || (tfPrenom.getText().isEmpty()) || (tfPassword.getText().isEmpty()) || (tfAdress.getText().isEmpty())||(dateN.getValue()==null))) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty input " + tfNom.getText() + " " + tfPrenom.getText(), ButtonType.OK);
            alert.show();
        }else{
            Date date_naissance;
            String role = null;
            if (radetudient.isSelected()) {
                 role ="Etudiant";

            } else if (radeleve.isSelected()) {
                 role = "Eleve";
            }

            DatePicker tmpdate = (DatePicker) dateN;
            String date = (String) tmpdate.getValue().toString();
            date = date.substring(0, 4) + '/' + date.substring(5, 7) + '/' + date.substring(8);
            java.util.Date myDate = new java.util.Date(date);
            java.sql.Date sqldate = new java.sql.Date(myDate.getTime());
            
            User u = new User(tfNom.getText(), tfPrenom.getText(), tfPassword.getText(), role, tfEmail.getText(), tfAdress.getText(), sqldate);
            us.add(u);
            
            int id=getID().getId();
            String query ="INSERT INTO eleve (ID_User) VALUES ("+id+")";
    executeUpdate(query);
        System.out.println(query);
        
    
    

            if (role == "Etudiant") {
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
            } else if (role == "Eleve") {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Je vous souhaite la bienvenue Mr/Mme" + tfNom.getText() + " " + tfPrenom.getText(), ButtonType.OK);
                alert.show();
                JavaMailUtils.sendMail(tfEmail.getText());

                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("acceuilEleve.fxml"));
                Parent parent = Loader.load();
                AcceuilEleveController c = Loader.getController();
                 u = us.connect(tfEmail.getText(), tfPassword.getText());
                 
                c.setId_User(u.getId());
                Scene scene = new Scene(parent);
                Stage window = (Stage) n1.getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
        }
            

    }
 private void executeUpdate(String query) {
        Connection conn =getConnection();
         Statement       st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch(SQLException ex){
        }
    }
        public Connection getConnection(){
        
        String url = "jdbc:mysql://localhost:3306/2fac";
        String login="houssem";
        String pwd="1327";
        try{
           Connection conn = DriverManager.getConnection(url, login, pwd);
            return conn;
        }catch(SQLException ex){
            System.out.println("Error2: "+ ex.getMessage());
            return null;
        }
    }
    @FXML
    private void sign(MouseEvent event) {
    }

    @FXML
    private void Signin(ActionEvent event) throws SQLException, IOException {
        User u = us.connect(n1.getText(), n2.getText());
        if (u != null) {

            if (u.getRole().equals("Etudiant")) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("acceuil.fxml"));
                Parent parent = Loader.load();
                AcceuilController c = Loader.getController();

                c.setId_User(u.getId());
                Scene scene = new Scene(parent);
                Stage window = (Stage) n1.getScene().getWindow();
                window.setScene(scene);
                window.show();

            }else if (u.getRole().equals("Eleve")) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("acceuilEleve.fxml"));
                Parent parent = Loader.load();
                AcceuilEleveController c = Loader.getController();

                c.setId_User(u.getId());
                Scene scene = new Scene(parent);
                Stage window = (Stage) n1.getScene().getWindow();
                window.setScene(scene);
                window.show();
            } else if(u.getRole().equals("Admin")){
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
        }
        
    }

    @FXML
    private void signinView(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        TranslateTransition slide1 = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide1.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);
        slide1.setNode(btneTAB);
        slide1.setToX(-450);
        slide.setToX(491);
        slide.play();
        slide1.play();
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
        TranslateTransition slide1 = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide1.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);
        slide1.setNode(btneTAB);
        slide1.setToX(50);
        slide.setToX(0);
        slide.play();
        slide1.play();

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
        
        nom.setVisible(true);
        prenom.setVisible(true);
        addresse.setVisible(true);
        email.setVisible(true);
        password.setVisible(true);
        dateN.setVisible(true);

        slide.setOnFinished((e -> {

        }));
    }

    @FXML
    private void HANDLE(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/pidev/FXMLLoginetab.fxml"));
                Parent CalautoSc = (Parent)loader.load();
              
               
         Scene Calculautoscene = new Scene (CalautoSc);       
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
    }

}
