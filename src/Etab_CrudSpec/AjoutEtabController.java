/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Etab_CrudSpec;

import Entities.Speciality;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author iHoussem
 */
public class AjoutEtabController implements Initializable {

    @FXML
    private Label lbNomEtabAjout;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfDisc;
    @FXML
    private TableView<Speciality> tvSpecAjout;
    @FXML
    private TableColumn<Speciality, String> colNomAjout;
    @FXML
    private TableColumn<Speciality, String> colDiscAjout;
    @FXML
    private TableColumn<Speciality, Float> colEcoAjout;
    @FXML
    private TableColumn<Speciality, Float> colInfoAjout;
    @FXML
    private TableColumn<Speciality, Float> colLetAjout;
    @FXML
    private TableColumn<Speciality, Float> colMathAjout;
    @FXML
    private TableColumn<Speciality, Float> colScAjout;
    @FXML
    private TableColumn<Speciality, Float> colSpAjout;
    @FXML
    private TableColumn<Speciality, Float> colTechAjout;
    @FXML
    private Button btsuivant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showSpeciality();
      
    }    
    
    @FXML
    private void handleButtonSuivantAjout(ActionEvent event) throws IOException {
          
          if (event.getSource() == btsuivant) {
            
        
        insertSpeciality();
         Parent CalautoSc= FXMLLoader.load(getClass().getResource("AjoutScore.fxml"));
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();}
    
    }
    private void insertSpeciality(){
        String query ="INSERT INTO speciality (ID_Etb,Nom,Discription) VALUES (2,'"+tfnom.getText()+"','"+ tfDisc.getText()+"')";
    executeUpdate(query);
    
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
         public ObservableList<Speciality> getSpecialitysList(){
        ObservableList<Speciality> specialityslist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT ID_SPEC,Nom,Discription,ScoreEco,ScoreINFO,ScoreLET,ScoreMATH,ScoreSc,ScoreSPORT,ScoreTECH FROM finally WHERE ID_ETB=1";
        
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
       
        
        colNomAjout.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colDiscAjout.setCellValueFactory(new PropertyValueFactory<>("Discription"));
        colEcoAjout.setCellValueFactory(new PropertyValueFactory<>("ScoreECO"));
        colInfoAjout.setCellValueFactory(new PropertyValueFactory<>("ScoreINFO"));
        colLetAjout.setCellValueFactory(new PropertyValueFactory<>("ScoreLET"));
        colMathAjout.setCellValueFactory(new PropertyValueFactory<>("ScoreMATH"));
        colScAjout.setCellValueFactory(new PropertyValueFactory<>("ScoreSc"));
        colSpAjout.setCellValueFactory(new PropertyValueFactory<>("ScoreSPORT"));
        colTechAjout.setCellValueFactory(new PropertyValueFactory<>("ScoreTECH"));
        tvSpecAjout.setItems(list);
        
      
        
        
    }
    
    
}
