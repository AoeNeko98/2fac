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
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private TableView<User> tvuser;
    @FXML
    private TableColumn<User, String> colNom;
    @FXML
    private TableColumn<User, String> colPrenom;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, Date> colDate;
    @FXML
    private TableColumn<User, String> colPassword;
    @FXML
    private TableColumn<User, String> tcrole;
    @FXML
    private TableColumn<User, Integer> colidu;
    private Button bntinsert;
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
     
          showuser();
        
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        
    }

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
private void executeUpdate(String query) {
        Connection conn =getConnections();
         Statement       st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch(SQLException ex){
        }
    }
        public Connection getConnections(){
        
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
public ObservableList<User> getUserList(){
    ObservableList<User> specialityslist = FXCollections.observableArrayList();
        Connection conn = getConnections();
        String query = "SELECT * FROM user";
            
        Statement st;
        
        ResultSet rs;
        System.out.println("ahla1");
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query);
            
            User specialitys;
            System.out.println("ahla");
            while(rs.next()){
                System.out.println("ahla2");
                specialitys = new User();
                specialitys.setId(rs.getInt("ID_User"));
                
                System.out.println(specialitys.getId());
                specialitys.setNom(rs.getString("Nom"));
                specialitys.setPrenom(rs.getString("Prenom"));
                specialitys.setEmail(rs.getString("Email"));
                specialitys.setDate_naissance(rs.getDate("Date_naissance"));
                specialitys.setPassword(rs.getString("password"));
                specialitys.setRole(rs.getString("role"));
                specialitys.setAddresse(rs.getString("Addresse"));
                System.out.println(specialitys.toString());
                specialityslist.add(specialitys);
                System.out.println(specialitys);
            }
          
        }catch (SQLException ex){
            System.out.println(ex);
        }
        return specialityslist;
}
    
    public void showuser()  {
//        List<User> liste= new ArrayList<User>();
//        liste=us.readAll();
        ObservableList<User> list = getUserList();
//        for (User aux : liste)
        

        colidu.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        System.out.println(list);
        tvuser.setItems(list);
        
    }

//    private void sea(ActionEvent event) throws SQLException {
//        System.out.println("test");
//        UserList.clear();
//        List<User> liste= new ArrayList<User>();
//        liste=us.search(s.getText());
//        
//        for (User aux : liste)
//        {            
//            UserList.add(new User(aux.getId(),aux.getNom(),aux.getPrenom(),aux.getPassword(),aux.getRole(),aux.getEmail(),aux.getAddresse(),aux.getDate_naissance()));
//            tvuser.setItems(UserList);    
//        }
//
//        colidu.setCellValueFactory(new PropertyValueFactory<>("id"));
//        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
//        tcrole.setCellValueFactory(new PropertyValueFactory<>("role"));
//
//        tvuser.setItems(UserList);
//    }

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
