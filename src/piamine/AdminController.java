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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class AdminController implements Initializable {

    UserService us=new UserService();
    
    ObservableList<User> UserList = FXCollections.observableArrayList();

    
    @FXML
    private VBox vbox1;
    @FXML
    private Label colid;
    @FXML
    private Label colnom;
    @FXML
    private Label colprenom;
    @FXML
    private Label colemail;
    @FXML
    private Label coldate;
    @FXML
    private Label colpassword;
    @FXML
    private Label colrole;
    @FXML
    private VBox vbox2;
    @FXML
    private TextField tfidu;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private DatePicker tfDate;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfrole;
    @FXML
    private TableView<User> tvuser;
    @FXML
    private TableColumn<User, String> colNom;
    @FXML
    private TableColumn<User, String> colPrenom;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colDate;
    @FXML
    private TableColumn<User, String> colPassword;
    @FXML
    private TableColumn<User, String> tcrole;
    @FXML
    private TableColumn<User, Integer> colidu;
    private Button bntinsert;
    @FXML
    private Button bntRetour;
    @FXML
    private Button bntstat;
    
    ObservableList<User> dataList;
    @FXML
    private TextField s;
    @FXML
    private Label colid1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showuser();
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == bntinsert) {
            //insertRecord();
            showuser();
        } else if (event.getSource() == bntstat) {
            Parent Ajouter = FXMLLoader.load(getClass().getResource("FXChart.fxml"));
            Scene scene = new Scene(Ajouter);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }  
    }


    
    public void showuser() throws SQLException {
        List<User> liste= new ArrayList<User>();
        liste=us.readAll();
        
        for (User aux : liste)
        {            
            UserList.add(new User(aux.getId(),aux.getNom(),aux.getPrenom(),aux.getPassword(),aux.getRole(),aux.getEmail(),aux.getAddresse(),aux.getDate_naissance()));
            tvuser.setItems(UserList);    
        }

        colidu.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcrole.setCellValueFactory(new PropertyValueFactory<>("role"));

        tvuser.setItems(UserList);
        
    }

    private void sea(ActionEvent event) throws SQLException {
        System.out.println("test");
        UserList.clear();
        List<User> liste= new ArrayList<User>();
        liste=us.search(s.getText());
        
        for (User aux : liste)
        {            
            UserList.add(new User(aux.getId(),aux.getNom(),aux.getPrenom(),aux.getPassword(),aux.getRole(),aux.getEmail(),aux.getAddresse(),aux.getDate_naissance()));
            tvuser.setItems(UserList);    
        }

        colidu.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcrole.setCellValueFactory(new PropertyValueFactory<>("role"));

        tvuser.setItems(UserList);
    }

    @FXML
    private void search(KeyEvent event) throws SQLException {
        System.out.println("test");
        UserList.clear();
        List<User> liste= new ArrayList<User>();
        liste=us.search(s.getText());
        
        for (User aux : liste)
        {            
            UserList.add(new User(aux.getId(),aux.getNom(),aux.getPrenom(),aux.getPassword(),aux.getRole(),aux.getEmail(),aux.getAddresse(),aux.getDate_naissance()));
            tvuser.setItems(UserList);    
        }

        colidu.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcrole.setCellValueFactory(new PropertyValueFactory<>("role"));

        tvuser.setItems(UserList);
    }
    
}
