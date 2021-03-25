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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.xml.bind.DatatypeConverter;

/**
 * FXML Controller class
 *
 * @author iHoussem
 */
public class MainCrudEtabPaneController implements Initializable {

    @FXML
    private AnchorPane AjoutAnchor;
    @FXML
    private Label lbNomEtabAjout;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfDisc;
    @FXML
    private Button btsuivant;
    @FXML
    private AnchorPane ModifAnchor;
    @FXML
    private Label lbNomEtabModif;
    @FXML
    private TextField tfnomModif;
    @FXML
    private TextField tfDiscModif;
    @FXML
    private Button btUpdateScore;
    @FXML
    private Label lbIDspeModif;
    @FXML
    private Button btDone;
    @FXML
    private Button btSuppr;
    
    @FXML
    private TextField tfBacEco1;
    @FXML
    private TextField tfBacEco2;
    @FXML
    private TextField tfBacEco3;
    @FXML
    private TextField tfBacEco4;
    @FXML
    private TextField tfBacEco5;
    @FXML
    private TextField tfBacInfo1;
    @FXML
    private TextField tfBacInfo2;
    @FXML
    private TextField tfBacInfo3;
    @FXML
    private TextField tfBacInfo4;
    @FXML
    private TextField tfBacInfo5;
    @FXML
    private TextField tfBacLettre1;
    @FXML
    private TextField tfBacLettre2;
    @FXML
    private TextField tfBacLettre3;
    @FXML
    private TextField tfBacLettre4;
    @FXML
    private TextField tfBacLettre5;
    @FXML
    private TextField tfBacMath1;
    @FXML
    private TextField tfBacMath2;
    @FXML
    private TextField tfBacMath3;
    @FXML
    private TextField tfBacMath4;
    @FXML
    private TextField tfBacMath5;
    @FXML
    private TextField tfBacSc1;
    @FXML
    private TextField tfBacSc2;
    @FXML
    private TextField tfBacSc3;
    @FXML
    private TextField tfBacSc4;
    @FXML
    private TextField tfBacSc5;
    @FXML
    private TextField tfBacSport1;
    @FXML
    private TextField tfBacSport2;
    @FXML
    private TextField tfBacSport3;
    @FXML
    private TextField tfBacSport4;
    @FXML
    private TextField tfBacSport5;
    @FXML
    private TextField tfBacTech1;
    @FXML
    private TextField tfBacTech2;
    @FXML
    private TextField tfBacTech3;
    @FXML
    private TextField tfBacTech4;
    @FXML
    private TextField tfBacTech5;
    @FXML
    private AnchorPane topbarAnchor;
    @FXML
    private ImageView returns;
    @FXML
    private ImageView inserer;
    @FXML
    private ImageView consulter;
    @FXML
    private AnchorPane ConsulterAnchor;
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
    private TableView<Speciality> tvSpecAjout;
    @FXML
    private TableView<Speciality> tvSpecModif;
    @FXML
    private ImageView modifier;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Label lbIDSpecAjoutScore;
    @FXML
    private Button btConf;
    @FXML
    private AnchorPane ajoutscoreAnchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showSpeciality(); // TODO
    }    

    

    @FXML
    private void handleImageViewAction(MouseEvent event) {
        if (event.getTarget()== inserer) {
            
           ConsulterAnchor.setVisible(false);
           ModifAnchor.setVisible(false);
           ajoutscoreAnchor.setVisible(false);
           AjoutAnchor.setVisible(true);
           showSpeciality();
           
           
           
            
        }else if (event.getTarget()== modifier) {
            
            ConsulterAnchor.setVisible(false);
           AjoutAnchor.setVisible(false);
           ajoutscoreAnchor.setVisible(false);
           ModifAnchor.setVisible(true);
           showSpeciality();
          
            
        }else if (event.getTarget()== returns) {
            
           ModifAnchor.setVisible(false);
           AjoutAnchor.setVisible(false);
           ajoutscoreAnchor.setVisible(false);
           ConsulterAnchor.setVisible(true);
           showSpeciality();
           
            
        }else if (event.getTarget()== consulter) {
            
           ModifAnchor.setVisible(false);
           AjoutAnchor.setVisible(false);
          ajoutscoreAnchor.setVisible(false);
           ConsulterAnchor.setVisible(true);
           showSpeciality();
           
            
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

    @FXML
    private void handleButtonAction2(MouseEvent event) {
        
            
        
         Speciality speciality= tvSpecModif.getSelectionModel().getSelectedItem();
       lbIDspeModif.setText(String.valueOf(speciality.getID_SPEC()));
       tfDiscModif.setText(String.valueOf(speciality.getDiscription()));
       tfnomModif.setText(String.valueOf(speciality.getNom()));
       
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
    private void modifButton() {
        String query ="UPdate speciality SET Nom='"+tfnomModif.getText()+"' ,discription='"+ tfDiscModif.getText()+"' WHERE ID_SPEC="+Integer.parseInt(lbIDspeModif.getText());
    executeUpdate(query);
    showSpeciality();
    
    }

    private void deleteButton() {
    String query ="DELETE FROM speciality WHERE ID_SPEC= "+Integer.parseInt(lbIDspeModif.getText())+"";
    executeUpdate(query);
    showSpeciality();}
    private void insertSpeciality(){
        String query ="INSERT INTO speciality (ID_Etb,Nom,Discription) VALUES (1,'"+tfnom.getText()+"','"+ tfDisc.getText()+"')";
    executeUpdate(query);
    
    }
    private void insertBacEco(){
         
        String query ="INSERT INTO bacecoscore VALUES ("+ID_Etab+","+ DatatypeConverter.parseDecimal(tfBacEco1.getText())
                +","+DatatypeConverter.parseDecimal(tfBacEco2.getText())+","
                +DatatypeConverter.parseDecimal(tfBacEco3.getText())+","+
                DatatypeConverter.parseDecimal(tfBacEco4.getText())+","+DatatypeConverter.parseDecimal(tfBacEco5.getText())+","+ID_SPEC+")";
            executeUpdate(query);
            String queryinitialise="INSERT INTO scoreapprox VALUES ("+0+","+0+","+0+","+0+","+0+","+0+","+0+","+ID_SPEC+")";
        executeUpdate(queryinitialise);

    Data f[]={
          new Data(1,Float.parseFloat(tfBacEco1.getText())),new Data(2,Float.parseFloat(tfBacEco2.getText()))
                ,new Data(3,Float.parseFloat(tfBacEco3.getText())),new Data(4,Float.parseFloat(tfBacEco4.getText())),
                new Data(5,Float.parseFloat(tfBacEco5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreECO="+scoreapprox+" WHERE ID_SPEC="+ID_SPEC;
        executeUpdate(queryscore);
    
    
    }
    private void insertBacInfo(){
         
        String query ="INSERT INTO bacinfoscore VALUES ("+ID_Etab+","+DatatypeConverter.parseDecimal(tfBacInfo1.getText())+","+
                DatatypeConverter.parseDecimal(tfBacInfo2.getText())+","
                +DatatypeConverter.parseDecimal(tfBacInfo3.getText())+","+DatatypeConverter.parseDecimal(tfBacInfo4.getText())
                +","+DatatypeConverter.parseDecimal(tfBacInfo5.getText())+","+ID_SPEC+")";
    executeUpdate(query);
    String queryinitialise="INSERT INTO scoreapprox VALUES ("+0+","+0+","+0+","+0+","+0+","+0+","+0+","+ID_SPEC+")";
        executeUpdate(queryinitialise);

    Data f[]={
          new Data(1,Float.parseFloat(tfBacInfo1.getText())),new Data(2,Float.parseFloat(tfBacInfo2.getText()))
                ,new Data(3,Float.parseFloat(tfBacInfo3.getText())),new Data(4,Float.parseFloat(tfBacInfo4.getText())),
                new Data(5,Float.parseFloat(tfBacInfo5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreINFO="+scoreapprox+" WHERE ID_SPEC="+ID_SPEC;
        executeUpdate(queryscore);
    
    }
    private void insertBacLet(){
        
        String query ="INSERT INTO bacletscore VALUES ("+ID_Etab+","+ DatatypeConverter.parseDecimal(tfBacLettre1.getText())
                +","+DatatypeConverter.parseDecimal(tfBacLettre2.getText())+","
                +DatatypeConverter.parseDecimal(tfBacLettre3.getText())+","+
                DatatypeConverter.parseDecimal(tfBacLettre4.getText())+","+DatatypeConverter.parseDecimal(tfBacLettre5.getText())+","+ID_SPEC+")";
    executeUpdate(query);
    String queryinitialise="INSERT INTO scoreapprox VALUES ("+0+","+0+","+0+","+0+","+0+","+0+","+0+","+ID_SPEC+")";
        executeUpdate(queryinitialise);
        Data f[]={
          new Data(1,Float.parseFloat(tfBacLettre1.getText())),new Data(2,Float.parseFloat(tfBacLettre2.getText()))
                ,new Data(3,Float.parseFloat(tfBacLettre3.getText())),new Data(4,Float.parseFloat(tfBacLettre4.getText())),
                new Data(5,Float.parseFloat(tfBacLettre5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreLET="+scoreapprox+" WHERE ID_SPEC="+ID_SPEC;
        executeUpdate(queryscore);

    
    }
    private void insertBacMath(){
        
        String query ="INSERT INTO bacmathscore VALUES ("+ID_Etab+","+ DatatypeConverter.parseDecimal(tfBacMath1.getText())
                +","+DatatypeConverter.parseDecimal(tfBacMath2.getText())+","
                +DatatypeConverter.parseDecimal(tfBacMath3.getText())+","+DatatypeConverter.parseDecimal(tfBacMath4.getText())
                +","+DatatypeConverter.parseDecimal(tfBacMath5.getText())+","+ID_SPEC+")";
    executeUpdate(query);
    String queryinitialise="INSERT INTO scoreapprox VALUES ("+0+","+0+","+0+","+0+","+0+","+0+","+0+","+ID_SPEC+")";
        executeUpdate(queryinitialise);
        Data f[]={
          new Data(1,Float.parseFloat(tfBacMath1.getText())),new Data(2,Float.parseFloat(tfBacMath2.getText()))
                ,new Data(3,Float.parseFloat(tfBacMath3.getText())),new Data(4,Float.parseFloat(tfBacMath4.getText())),
                new Data(5,Float.parseFloat(tfBacMath5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreMATH="+scoreapprox+" WHERE ID_SPEC="+ID_SPEC;
        executeUpdate(queryscore);

    
    }
    private void insertBacSc(){
         
        String query ="INSERT INTO bacscscore VALUES ("+ID_Etab+","+ DatatypeConverter.parseDecimal(tfBacSc1.getText())
                +","+DatatypeConverter.parseDecimal(tfBacSc2.getText())+","
                +DatatypeConverter.parseDecimal(tfBacSc3.getText())+","+
                DatatypeConverter.parseDecimal(tfBacSc4.getText())+","+DatatypeConverter.parseDecimal(tfBacSc5.getText())+","+ID_SPEC+")";
    executeUpdate(query);
    String queryinitialise="INSERT INTO scoreapprox VALUES ("+0+","+0+","+0+","+0+","+0+","+0+","+0+","+ID_SPEC+")";
        executeUpdate(queryinitialise);
        Data f[]={
          new Data(1,Float.parseFloat(tfBacSc1.getText())),new Data(2,Float.parseFloat(tfBacSc2.getText()))
                ,new Data(3,Float.parseFloat(tfBacSc3.getText())),new Data(4,Float.parseFloat(tfBacSc4.getText())),
                new Data(5,Float.parseFloat(tfBacSc5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreSc="+scoreapprox+" WHERE ID_SPEC="+ID_SPEC;
        executeUpdate(queryscore);

    }
    private void insertBacSport(){
         
        String query ="INSERT INTO bacsportscore VALUES ("+ID_Etab+","+DatatypeConverter.parseDecimal(tfBacSport1.getText())+","+DatatypeConverter.parseDecimal(tfBacSport2.getText())+","
                +DatatypeConverter.parseDecimal(tfBacSport3.getText())+","+DatatypeConverter.parseDecimal(tfBacSport4.getText())+","+DatatypeConverter.parseDecimal(tfBacSport5.getText())+","+ID_SPEC+")";
    executeUpdate(query);
    String queryinitialise="INSERT INTO scoreapprox VALUES ("+0+","+0+","+0+","+0+","+0+","+0+","+0+","+ID_SPEC+")";
        executeUpdate(queryinitialise);
        Data f[]={
          new Data(1,Float.parseFloat(tfBacSport1.getText())),new Data(2,Float.parseFloat(tfBacSport2.getText()))
                ,new Data(3,Float.parseFloat(tfBacSport3.getText())),new Data(4,Float.parseFloat(tfBacSport4.getText())),
                new Data(5,Float.parseFloat(tfBacSport5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreSPORT="+scoreapprox+" WHERE ID_SPEC="+ID_SPEC;
        executeUpdate(queryscore);

    
    }
    private void insertBacTech(){
        
        String query ="INSERT INTO bactechscore VALUES ("+ID_Etab+","+ DatatypeConverter.parseDecimal(tfBacTech1.getText())+","+DatatypeConverter.parseDecimal(tfBacTech2.getText())+","
                +DatatypeConverter.parseDecimal(tfBacTech3.getText())+","+DatatypeConverter.parseDecimal(tfBacTech4.getText())+","+DatatypeConverter.parseDecimal(tfBacTech5.getText())+","+ID_SPEC+")";
    executeUpdate(query);
    String queryinitialise="INSERT INTO scoreapprox VALUES ("+0+","+0+","+0+","+0+","+0+","+0+","+0+","+ID_SPEC+")";
        executeUpdate(queryinitialise);
        Data f[]={
          new Data(1,Float.parseFloat(tfBacTech1.getText())),new Data(2,Float.parseFloat(tfBacTech2.getText()))
                ,new Data(3,Float.parseFloat(tfBacTech3.getText())),new Data(4,Float.parseFloat(tfBacTech4.getText())),
                new Data(5,Float.parseFloat(tfBacTech5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreTECH="+scoreapprox+" WHERE ID_SPEC="+ID_SPEC;
        executeUpdate(queryscore);

    
    }


    

    

    

  


    

    private void handleButtonAction6(ActionEvent event) {
       
       }

    @FXML
    private void handleButtonConfAjout(ActionEvent event) {
         if (event.getSource() == btConf) {
            
        
        insertBacEco();
            insertBacInfo();
            insertBacLet();
            insertBacMath();
            insertBacSc();
            insertBacSport();
            insertBacTech();
            AlertBox.display("Done", " succée");
            ajoutscoreAnchor.setVisible(false);
            showSpeciality();
           ConsulterAnchor.setVisible(true);}
    }

    @FXML
    private void handleButtonUpdatescore(ActionEvent event) {
        if (event.getSource() == btUpdateScore) {
            lbIDSpecAjoutScore.setText(lbIDspeModif.getText());
            ConsulterAnchor.setVisible(false);
            AjoutAnchor.setVisible(false);
            ModifAnchor.setVisible(false);
           ajoutscoreAnchor.setVisible(true);
           ajoutscoreAnchor.toFront();
    }
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

    @FXML
    private void handleButtonSuivantAjout(ActionEvent event) {
          if (event.getSource() == btsuivant) {
            
        
        insertSpeciality();
        AjoutAnchor.setVisible(false);
        ajoutscoreAnchor.setVisible(true);}
    }
     public Speciality getID(){
        
        Connection conn = getConnection();
        String query = "SELECT * FROM speciality WHERE id_SPEC=(SELECT max(id_SPEC) FROM SPECIALITY)";
        Speciality specialitys= null;
        Statement st;
        ResultSet rs;
        
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query);
            
            if(rs.next()){
            specialitys= new Speciality();
            specialitys.setID_Etab(rs.getInt("ID_Etab"));
            specialitys.setID_SPEC(rs.getInt("ID_SPEC"));
            }
            
          
        }catch (SQLException ex){
        }
        return specialitys;
    }
    
    int ID_Etab=getID().getID_Etab();
    int ID_SPEC=getID().getID_SPEC();
}
    

