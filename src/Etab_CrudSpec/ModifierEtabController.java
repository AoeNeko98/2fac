/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Etab_CrudSpec;

import Entities.AlertBox;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static pidev.FXMLEtablissementController.tfidd;

/**
 * FXML Controller class
 *
 * @author iHoussem
 */
public class ModifierEtabController implements Initializable {

    @FXML
    private Label lbNomEtabModif;
    @FXML
    private Label lbIDspeModif;
    @FXML
    private TextField tfnomModif;
    @FXML
    private TextField tfDiscModif;
    @FXML
    private TableView<Speciality> tvSpecModif;
    @FXML
    private TableColumn<Speciality, String> colNomModif;
    @FXML
    private TableColumn<Speciality, String> colDiscModif;
    @FXML
    private TableColumn<Speciality, Float> colEcoModif;
    @FXML
    private TableColumn<Speciality, Float> colInfoModif;
    @FXML
    private TableColumn<Speciality, Float> colLetModif;
    @FXML
    private TableColumn<Speciality, Float> colMathModif;
    @FXML
    private TableColumn<Speciality, Float> colScModif;
    @FXML
    private TableColumn<Speciality, Float> colSpModif;
    @FXML
    private TableColumn<Speciality, Float> colTechModif;
    @FXML
    private Button btUpdateScore;
    @FXML
    private Button btDone;
    @FXML
    private Button btSuppr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showSpeciality();
    }    

    @FXML
    private void handleButtonAction2(MouseEvent event) {
              Speciality speciality= tvSpecModif.getSelectionModel().getSelectedItem();
       lbIDspeModif.setText(String.valueOf(speciality.getID_SPEC()));
       tfDiscModif.setText(String.valueOf(speciality.getDiscription()));
       tfnomModif.setText(String.valueOf(speciality.getNom()));
    }

    @FXML
    private void handleButtonUpdatescore(ActionEvent event) throws IOException {
        if (event.getSource()== btUpdateScore) {
            
        
        Parent CalautoSc= FXMLLoader.load(getClass().getResource("AjoutScore.fxml"));
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();}
    }

    @FXML
    private void handleButtonDoneModif(ActionEvent event) {
          if (event.getSource() == btDone) {
            
        
           modifButton();
            AlertBox.display("Done", " succée");
            showSpeciality();
            
            
    }
    }

    @FXML
    private void handleButtonSupprim(ActionEvent event) {
         if (event.getSource()==btSuppr){
            deleteButton();
            AlertBox.display("Done", " succée");
            showSpeciality();
        }
        
    }
      private void modifButton() {
        String query ="UPdate speciality SET Nom='"+tfnomModif.getText()+"' ,discription='"+ tfDiscModif.getText()+"' WHERE ID_SPEC="+Integer.parseInt(lbIDspeModif.getText());
    executeUpdate(query);
    showSpeciality();
    
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
        public void showSpeciality(){
        
        ObservableList<Speciality> list = getSpecialitysList();
        
        
        colNomModif.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colDiscModif.setCellValueFactory(new PropertyValueFactory<>("Discription"));
        colEcoModif.setCellValueFactory(new PropertyValueFactory<>("ScoreECO"));
        colInfoModif.setCellValueFactory(new PropertyValueFactory<>("ScoreINFO"));
        colLetModif.setCellValueFactory(new PropertyValueFactory<>("ScoreLET"));
        colMathModif.setCellValueFactory(new PropertyValueFactory<>("ScoreMATH"));
        colScModif.setCellValueFactory(new PropertyValueFactory<>("ScoreSc"));
        colSpModif.setCellValueFactory(new PropertyValueFactory<>("ScoreSPORT"));
        colTechModif.setCellValueFactory(new PropertyValueFactory<>("ScoreTECH"));
        tvSpecModif.setItems(list);
        
        
    }
        public ObservableList<Speciality> getSpecialitysList(){
        ObservableList<Speciality> specialityslist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT ID_SPEC,Nom,Discription,ScoreEco,ScoreINFO,ScoreLET,ScoreMATH,ScoreSc,ScoreSPORT,ScoreTECH FROM finalys WHERE ID_ETB="+tfidd;
        
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
     private void deleteButton() {
    String query ="DELETE FROM speciality WHERE ID_SPEC= "+Integer.parseInt(lbIDspeModif.getText())+"";
    executeUpdate(query);
    showSpeciality();}
}
