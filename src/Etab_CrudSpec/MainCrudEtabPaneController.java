/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Etab_CrudSpec;

import Entities.AlertBox;
import Entities.Data;
import Entities.Speciality;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.xml.bind.DatatypeConverter;

/**
 * FXML Controller class
 *
 * @author iHoussem
 */
public class MainCrudEtabPaneController implements Initializable {
    @FXML
    private TableView<Speciality> tvSpec;
    @FXML
    private TableColumn<Speciality, String> colNom;
    @FXML
    private TableColumn<Speciality, String> colDisc;
    @FXML
    private TableColumn<Speciality, Float> colEco;
    @FXML
    private TableColumn<Speciality, Float> colInfo;
    @FXML
    private TableColumn<Speciality, Float> colLet;
    @FXML
    private TableColumn<Speciality, Float> colMath;
    @FXML
    private TableColumn<Speciality, Float> colSc;
    @FXML
    private TableColumn<Speciality, Float> colSp;
    @FXML
    private TableColumn<Speciality, Float> colTech;
   
    @FXML
    private Label lbNomEtabConsulter;
    @FXML
    private Button BtModif;
    @FXML
    private Button BtAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showSpeciality(); // TODO
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
    
    
    public ObservableList<Speciality> getSpecialitysList(){
        ObservableList<Speciality> specialityslist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT ID_SPEC,Nom,Discription,ScoreEco,ScoreINFO,ScoreLET,ScoreMATH,ScoreSc,ScoreSPORT,ScoreTECH FROM finally WHERE ID_ETB=2";
        
        Statement st;
        
        ResultSet rs;
        
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query);
            
            Speciality specialitys;
            while(rs.next() ){
                specialitys = new Speciality(rs.getString("Nom"),rs.getString("Discription"),rs.getFloat("ScoreECO"),rs.getFloat("ScoreINFO")
                        ,rs.getFloat("ScoreLET"),rs.getFloat("ScoreMATH"),rs.getFloat("ScoreSc"),rs.getFloat("ScoreSPORT"),rs.getFloat("ScoreTECH"));
                specialitys.setID_SPEC(rs.getInt("ID_SPEC"));
                specialityslist.add(specialitys);
                
            }
          
        }catch (SQLException ex){
        }
        return specialityslist;
    }
    public void showSpeciality(){
        
        ObservableList<Speciality> list = getSpecialitysList();
        colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colDisc.setCellValueFactory(new PropertyValueFactory<>("Discription"));
        colEco.setCellValueFactory(new PropertyValueFactory<>("ScoreECO"));
        colInfo.setCellValueFactory(new PropertyValueFactory<>("ScoreINFO"));
        colLet.setCellValueFactory(new PropertyValueFactory<>("ScoreLET"));
        colMath.setCellValueFactory(new PropertyValueFactory<>("ScoreMATH"));
        colSc.setCellValueFactory(new PropertyValueFactory<>("ScoreSc"));
        colSp.setCellValueFactory(new PropertyValueFactory<>("ScoreSPORT"));
        colTech.setCellValueFactory(new PropertyValueFactory<>("ScoreTECH"));
        tvSpec.setItems(list);
        
      
        
        
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
  
     public Speciality getID(){
        
        Connection conn = getConnection();
        String query = "SELECT * FROM speciality ORDER BY ID_SPEC DESC LIMIT 1";
        Speciality specialitys= null;
        Statement st;
        ResultSet rs;
        
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query);
            
            if(rs.next()){
            specialitys= new Speciality();
            specialitys.setID_Etb(rs.getInt("ID_Etb"));
            specialitys.setID_SPEC(rs.getInt("ID_SPEC"));
            }
            
          
        }catch (SQLException ex){
        }
        return specialitys;
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        if (event.getSource()== BtModif) {
            
        
        Parent CalautoSc= FXMLLoader.load(getClass().getResource("ModifierEtab.fxml"));
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();}
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        if (event.getSource()== BtAjout) {
        Parent CalautoSc= FXMLLoader.load(getClass().getResource("AjoutEtab.fxml"));
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();}
    }
    
  
}
    

