/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private VBox vbox1;
    @FXML
    private VBox vbox2;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfDate;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfStatus;
    @FXML
    private TableView<User> tvuser;
    @FXML
    private TableColumn<User, Integer> colid;
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
    private TableColumn<User, String> colStatus;
    @FXML
    private Button bntinsert;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showuser();
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
            if (event.getSource()== bntinsert){
            insertRecord();
            showuser();
        }else if (event.getSource()== btnModifier){
            
        }else if (event.getSource()== btnSupprimer){
           
        }
    }
    
     public Connection getConnetion(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/2fac","root","");
            return conn;
        }catch(Exception ex){
            System.out.println("error");
            return null;
        }
    }
    
     public ObservableList<User> getUserList(){
        ObservableList<User> userlist = FXCollections.observableArrayList();
        Connection conn = getConnetion();
        String query= "SELECT * FROM user";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            User user;
            while(rs.next()){
                user = new User(rs.getInt("ID_User"), rs.getString("Nom"), rs.getString("Prenom"),rs.getString("Email"),rs.getString("Date"),rs.getString("Password"),rs.getString("Status"));
                userlist.add(user);
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
        return userlist;
}
         public void showuser(){
        ObservableList<User> list = getUserList();
        
        colid.setCellValueFactory(new PropertyValueFactory<User, Integer>("ID_User"));
        colNom.setCellValueFactory(new PropertyValueFactory<User, String>("Nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        colDate.setCellValueFactory(new PropertyValueFactory<User, String>("Date"));
        colPassword.setCellValueFactory(new PropertyValueFactory<User, String>("Password"));
        colStatus.setCellValueFactory(new PropertyValueFactory<User, String>("Status"));
        
        tvuser.setItems(list);
    }
         private void insertRecord(){ 
       // String query = "INSERT INTO user VALUES("+tfid.getText()+",'"+tfNom.getText()+"','"+tfStatus.getText()+"',"+tfid.getText()+","+tfid.getText()+")";
 String query = "INSERT INTO user VALUES("+tfid.getText()+",'"+tfNom.getText()+"','"+tfPrenom.getText()+"','"+tfEmail.getText()+"','"+tfDate.getText()+"','"+tfPassword.getText()+"','"+tfStatus.getText()+"')";
 //String query = "INSERT INTO user VALUES("+tfNom.getText()+"','"+tfPrenom.getText()+"','"+tfEmail.getText()+"','"+tfDate.getText()+"','"+tfPassword.getText()+"','"+tfStatus.getText()+"')";
        executeQuery(query);
        showuser();
    }

    private void executeQuery(String query) {
Connection conn = getConnetion();
        Statement st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
            
        }    }
}
