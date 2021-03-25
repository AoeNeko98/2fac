/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2fac_houssemriahi;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Entities.Speciality;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import static DataBaseConn.Update.executeUpdate;
import Entities.AlertBox;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jdk.nashorn.internal.objects.NativeRegExp;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author iHoussem
 */
public class MainController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane insererAnchorBacTic;
    @FXML
    private TextField tfAngTic;
    @FXML
    private TextField tfFrTic;
    @FXML
    private TextField tfMathTic;
    @FXML
    private TextField tfPhyTic;
    @FXML
    private TextField tfTecTic;
    @FXML
    private TextField tfMoyTic;
    @FXML
    private AnchorPane insererAnchorBacSport;
    @FXML
    private TextField tfAngSport;
    @FXML
    private TextField tfFrSport;
    @FXML
    private TextField tfEduPhSport;
    @FXML
    private TextField tfSVTSport;
    @FXML
    private TextField tfSpPrSport;
    @FXML
    private TextField tfSpTheoSport;
    @FXML
    private TextField tfPhysiqueSport;
    @FXML
    private TextField tfPhiloSport;
    @FXML
    private TextField tfMoySport;
    @FXML
    private AnchorPane inserAnchorBacSe;
    @FXML
    private TextField tfAngSe;
    @FXML
    private TextField tfFrSe;
    @FXML
    private TextField tfMathSe;
    @FXML
    private TextField tfPhySe;
    @FXML
    private TextField tfSVTSe;
    @FXML
    private TextField tfMoySe;
    @FXML
    private AnchorPane insererAnchorBacMath;
    @FXML
    private TextField tfAngMath;
    @FXML
    private TextField tfFrMath;
    @FXML
    private TextField tfMathMath;
    @FXML
    private TextField tfPhyMath;
    @FXML
    private TextField tfSVTMath;
    @FXML
    private TextField tfMoyMath;
    @FXML
    private AnchorPane insererAnchorBacLet;
    @FXML
    private TextField tfArabLet;
    @FXML
    private TextField tfAnglaisLet;
    @FXML
    private TextField tfFrLet;
    @FXML
    private TextField tfHisGeoLet;
    @FXML
    private TextField tfPhyLet;
    @FXML
    private TextField tfMoyLet;
    @FXML
    private AnchorPane insererAnchorBacEco;
    @FXML
    private TextField tfAnglaisEco;
    @FXML
    private TextField tfMathEco;
    @FXML
    private TextField tfHisGeoEco;
    @FXML
    private TextField tfEcoEco;
    @FXML
    private TextField tfFrEco;
    @FXML
    private TextField tfMoyEco;
    @FXML
    private TextField tfGeoEco;
   
    @FXML
    private AnchorPane insererAnchorBacInfo;
    @FXML
    private TextField tfAlgoInfo;
    @FXML
    private TextField tfAngInfo;
    @FXML
    private TextField tfDBInfo;
    @FXML
    private TextField tfFrInfo;
    @FXML
    private TextField tfMathInfo;
    @FXML
    private TextField tfPhInfo;
    @FXML
    private TextField tfTicInfo;
    @FXML
    private TextField tfMoyInfo;
    @FXML
    private AnchorPane topbar;
    @FXML
    private AnchorPane consulterAnchor;
    @FXML
    private TableView<Speciality> tableview;
    @FXML
    private TableColumn<Speciality, String> colEtab;
    @FXML
    private TableColumn<Speciality, String> colName;
    @FXML
    private TableColumn<Speciality, String> colDiscrip;
    @FXML
    private TableColumn<Speciality, Float> colScore;
    @FXML
    private TextField filterField;
    @FXML
    private Label lbscore;
    @FXML
    private AnchorPane saisirAnchor;
    @FXML
    private AnchorPane BacTypeAnchor;
    @FXML
    private  ImageView returns;
    @FXML
    private AnchorPane User;
    @FXML
    private ImageView saisir;
    @FXML
    private ImageView inserer;
    @FXML
    private ImageView consulter;
    @FXML
    private Button btsuivant;
    @FXML
    private SplitMenuButton menuBac;
    @FXML
    private MenuItem menuEco;
    @FXML
    private MenuItem MenuInfo;
    @FXML
    private MenuItem MenuLet;
    @FXML
    private MenuItem MenuMath;
    @FXML
    private MenuItem MenuSe;
    @FXML
    private MenuItem MenuSport;
    @FXML
    private MenuItem MenuTech;
    @FXML
    private TextField TFSCORE;
    @FXML
    private Button btConfirScoreSaisie;
    @FXML
    private Label sum;
    @FXML
    private Button btConf;
    @FXML
    private Button btValider;
    @FXML
    private Label lbbactype;
    @FXML
    private Label lbtype;
    @FXML
    private AnchorPane labelAnchor;
    @FXML
    private Button btMap;
    @FXML
    private Button btRecherche;
    @FXML
    private Label lbEtab;
    @FXML
    private Label lbSpec;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btsuivant) {
            BacTypeAnchor.setVisible(false);
            saisirAnchor.setVisible(true);
             showSpeciality();
        
        float score ;
        
            score = score().getScore();
        
        lbscore.setText(String.valueOf(score));
            }
        
    }
    
    public Speciality score() {
     Connection conn = getConnection();
     String query="SELECT score from eleve where id_User=4";
       Speciality specialitys= null;
        Statement st;
        ResultSet rs;
        
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query);
            
            if(rs.next()){
            specialitys= new Speciality();
            specialitys.setScore(rs.getFloat("score"));
            
            }
            
          
        }catch (SQLException ex){
        }
        return specialitys;
    }
    public static Connection getConnection1(){
        
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
    public Speciality getbactype1() {
     Connection conn = getConnection1();
     String query="SELECT bac_type from eleve where id_User=4";
       Speciality specialitys= null;
        Statement st;
        ResultSet rs;
        
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query);
            
            if(rs.next()){
            specialitys= new Speciality();
            specialitys.setBac_Type(rs.getString("Bac_type"));
            
            }
            
          
        }catch (SQLException ex){
        }
        return specialitys;
    }
    public Speciality getbactype() {
     Connection conn = getConnection();
     String query="SELECT bac_type from eleve where id_User=4";
       Speciality specialitys= null;
        Statement st;
        ResultSet rs;
        
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query);
            
            if(rs.next()){
            specialitys= new Speciality();
            specialitys.setBac_Type(rs.getString("Bac_type"));
            
            }
            
          
        }catch (SQLException ex){
        }
        return specialitys;
    }
  
    public static void executeUpdate(String query) {
        java.sql.Connection conn =getConnection();
         Statement       st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public static void executeUpdate1(String query) {
        java.sql.Connection conn =getConnection();
         Statement       st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public static Connection getConnection(){
        
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           
    } 
    @FXML
    private void bactypeEco() {
        String query ="UPdate eleve SET Bac_Type='"+menuEco.getText() +"' WHERE id_User=4";
    executeUpdate(query);
    String bactype=getbactype().getBac_Type();
    lbbactype.setText(bactype);
    }
    @FXML
    private void bactypeInfo() {
    String query ="UPdate eleve SET Bac_Type='"+MenuInfo.getText()+"' WHERE id_User=4";
    executeUpdate(query);
    String bactype=getbactype().getBac_Type();
    lbbactype.setText(bactype);
    }
    @FXML
    private void bactypeLet() {
        String query ="UPdate eleve SET Bac_Type='"+MenuLet.getText() +"' WHERE id_User=4";
    executeUpdate(query);
    String bactype=getbactype().getBac_Type();
    lbbactype.setText(bactype);
    }
    @FXML
    private void bactypeMath() {
        String query ="UPdate eleve SET Bac_Type='"+MenuMath.getText() +"' WHERE id_User=4";
    executeUpdate(query);
    String bactype=getbactype().getBac_Type();
    lbbactype.setText(bactype);
    }
    @FXML
    private void bactypeSe() {
        String query ="UPdate eleve SET Bac_Type='"+MenuSe.getText() +"' WHERE id_User=4";
    executeUpdate(query);
    String bactype=getbactype().getBac_Type();
    lbbactype.setText(bactype);
    }
    @FXML
    private void bactypeSport() {
        String query ="UPdate eleve SET Bac_Type='"+MenuSport.getText() +"' WHERE id_User=4";
    executeUpdate(query);
    String bactype=getbactype().getBac_Type();
    lbbactype.setText(bactype);
    }
    @FXML
    private void bactypeTech() {
        String query ="UPdate eleve SET Bac_Type='"+MenuTech.getText() +"' WHERE id_User=4";
    executeUpdate(query);
    String bactype=getbactype().getBac_Type();
    lbbactype.setText(bactype);
    }
    @FXML
    private void SaisirScore(ActionEvent event) throws IOException {
        String query="UPdate eleve SET score="+Float.parseFloat(TFSCORE.getText()) +" WHERE id_User=4";
    executeUpdate(query);
    AlertBox.display("Done", "votre score est enregistée avec succée");
        consulterAnchor.setVisible(true);
    }
    
 

    public ObservableList<Speciality> getSpecialitysList() {
        
        String bactype=getbactype1().getBac_Type();
        ObservableList<Speciality> specialityslist = FXCollections.observableArrayList();
        lbtype.setText(bactype);
        Connection conn = getConnection1();
        String query = "SELECT DISTINCT Nom_Etab,Nom,discription,score"+bactype+" FROM finally Where Score"+bactype+" > 50 ORDER BY Score"+bactype+" ASC";
        
        Statement st;
        
        ResultSet rs;
        
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query);
            
            Speciality specialitys;
            while(rs.next() ){
                
                specialitys = new Speciality();
                specialitys.setNom_Etab(rs.getString("Nom_Etab"));
                specialitys.setNom(rs.getString("Nom"));
                specialitys.setDiscription(rs.getString("Discription"));
                specialitys.setScore(rs.getFloat("Score"+bactype));
                specialityslist.add(specialitys);
            }
          
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return specialityslist;
    }
    public void showSpeciality() {
    ObservableList<Speciality> list = getSpecialitysList();
    String bactype=getbactype().getBac_Type();
     colEtab.setCellValueFactory(new PropertyValueFactory<>("Nom_Etab"));       
        colName.setCellValueFactory(new PropertyValueFactory<>("Nom"));        
        colDiscrip.setCellValueFactory(new PropertyValueFactory<>("Discription"));        
        colScore.setCellValueFactory(new PropertyValueFactory<>("Score"));
        
        tableview.setItems(list);
      tableview.setRowFactory(tv -> new TableRow<Speciality>() {
    @Override
    protected void updateItem(Speciality item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || item.getScore()== 0)
            setStyle("");
        else 
            if (item.getScore()> score().getScore()+15)
                setStyle("-fx-background-color: Red;");
            else if (item.getScore()< score().getScore()-15)
                setStyle("-fx-background-color: Green;");
            else
                setStyle("-fx-background-color: Orange;");
        
    }
});
      
    FilteredList<Speciality> filteredData = new FilteredList<>(list, b -> true);
		
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Speciality -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Speciality.getNom_Etab().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Speciality.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Speciality> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableview.setItems(sortedData);
}

    @FXML
    private void handleImageViewAction(javafx.scene.input.MouseEvent event) throws SQLException {
      
        if(event.getTarget()== inserer){
            String bactype=getbactype().getBac_Type();
            if( null != bactype)switch (bactype) {
                case "ECO":
                    
                    insererAnchorBacEco.setVisible(true);
                    labelAnchor.toFront();
                    btConf.toFront();
                    btValider.toFront();
                    saisirAnchor.setVisible(false);
                    consulterAnchor.setVisible(false);
                    inserAnchorBacSe.setVisible(false);
                    insererAnchorBacInfo.setVisible(false);
                    insererAnchorBacLet.setVisible(false);
                    insererAnchorBacMath.setVisible(false);
                    insererAnchorBacSport.setVisible(false);
                    insererAnchorBacTic.setVisible(false);
                    break;
                case "INFO":
                    insererAnchorBacInfo.setVisible(true);
                    labelAnchor.toFront();
                    btConf.toFront();
                    btValider.toFront();
                    inserAnchorBacSe.setVisible(false);
                    insererAnchorBacEco.setVisible(false);
                    saisirAnchor.setVisible(false);
                    consulterAnchor.setVisible(false);
                    insererAnchorBacLet.setVisible(false);
                    insererAnchorBacMath.setVisible(false);
                    insererAnchorBacSport.setVisible(false);
                    insererAnchorBacTic.setVisible(false);
                    break;
                case "LET":
                    insererAnchorBacLet.setVisible(true);
                    labelAnchor.toFront();
                    btConf.toFront();
                    btValider.toFront();
                    inserAnchorBacSe.setVisible(false);
                    insererAnchorBacEco.setVisible(false);
                    insererAnchorBacInfo.setVisible(false);
                    saisirAnchor.setVisible(false);
                    consulterAnchor.setVisible(false);
                    insererAnchorBacMath.setVisible(false);
                    insererAnchorBacSport.setVisible(false);
                    insererAnchorBacTic.setVisible(false);
                    break;
                case "MATH":
                    insererAnchorBacMath.setVisible(true);
                    labelAnchor.toFront();
                    btConf.toFront();
                    btValider.toFront();
                    saisirAnchor.setVisible(false);
                    consulterAnchor.setVisible(false);
                    inserAnchorBacSe.setVisible(false);
                    insererAnchorBacEco.setVisible(false);
                    insererAnchorBacInfo.setVisible(false);
                    insererAnchorBacLet.setVisible(false);
                    insererAnchorBacSport.setVisible(false);
                    insererAnchorBacTic.setVisible(false);
                    break;
                case "SE":
                    inserAnchorBacSe.setVisible(true);
                    labelAnchor.toFront();
                    btConf.toFront();
                    btValider.toFront();
                    saisirAnchor.setVisible(false);
                    consulterAnchor.setVisible(false);
                    insererAnchorBacEco.setVisible(false);
                    insererAnchorBacInfo.setVisible(false);
                    insererAnchorBacLet.setVisible(false);
                    insererAnchorBacMath.setVisible(false);
                    insererAnchorBacSport.setVisible(false);
                    insererAnchorBacTic.setVisible(false);
                    break;
                case "SPORT":
                    insererAnchorBacSport.setVisible(true);
                    labelAnchor.toFront();
                    btConf.toFront();
                    btValider.toFront();
                    saisirAnchor.setVisible(false);
                    consulterAnchor.setVisible(false);
                    inserAnchorBacSe.setVisible(false);
                    insererAnchorBacEco.setVisible(false);
                    insererAnchorBacInfo.setVisible(false);
                    insererAnchorBacLet.setVisible(false);
                    insererAnchorBacMath.setVisible(false);
                    insererAnchorBacTic.setVisible(false);
                    break;
                case "TECH":
                    insererAnchorBacTic.setVisible(true);
                    labelAnchor.toFront();
                    btConf.toFront();
                    btValider.toFront();
                    consulterAnchor.setVisible(false);
                    inserAnchorBacSe.setVisible(false);
                    insererAnchorBacEco.setVisible(false);
                    insererAnchorBacInfo.setVisible(false);
                    insererAnchorBacLet.setVisible(false);
                    insererAnchorBacMath.setVisible(false);
                    insererAnchorBacSport.setVisible(false);
                    break;
                default:
                    break;
            }
        }else if(event.getTarget()== saisir){
            saisirAnchor.setVisible(true);
            inserAnchorBacSe.setVisible(false);
            insererAnchorBacEco.setVisible(false);
            insererAnchorBacInfo.setVisible(false);
            insererAnchorBacLet.setVisible(false);
            insererAnchorBacMath.setVisible(false);
            insererAnchorBacSport.setVisible(false);
            insererAnchorBacTic.setVisible(false);
            consulterAnchor.setVisible(false);
            labelAnchor.toBack();
                    btConf.toBack();
                    btValider.toBack();
            
        }else if(event.getTarget()== consulter){
            float score=score().getScore();
            if (score >= 50) {
                consulterAnchor.setVisible(true);
                saisirAnchor.setVisible(false);
            inserAnchorBacSe.setVisible(false);
            insererAnchorBacEco.setVisible(false);
            insererAnchorBacInfo.setVisible(false);
            insererAnchorBacLet.setVisible(false);
            insererAnchorBacMath.setVisible(false);
            insererAnchorBacSport.setVisible(false);
            insererAnchorBacTic.setVisible(false);
            labelAnchor.toBack();
                    btConf.toBack();
                    btValider.toBack();
            }else{
                saisirAnchor.setVisible(true);
                inserAnchorBacSe.setVisible(false);
            insererAnchorBacEco.setVisible(false);
            insererAnchorBacInfo.setVisible(false);
            insererAnchorBacLet.setVisible(false);
            insererAnchorBacMath.setVisible(false);
            insererAnchorBacSport.setVisible(false);
            insererAnchorBacTic.setVisible(false);
            consulterAnchor.setVisible(false);
            labelAnchor.toBack();
                    btConf.toBack();
                    btValider.toBack();
            }
        }else if(event.getTarget() == returns){
            if (saisirAnchor.isVisible() == true){
                BacTypeAnchor.setVisible(true);
                 saisirAnchor.setVisible(false);
                inserAnchorBacSe.setVisible(false);
            insererAnchorBacEco.setVisible(false);
            insererAnchorBacInfo.setVisible(false);
            insererAnchorBacLet.setVisible(false);
            insererAnchorBacMath.setVisible(false);
            insererAnchorBacSport.setVisible(false);
            insererAnchorBacTic.setVisible(false);
            consulterAnchor.setVisible(false);
            labelAnchor.toBack();
                    btConf.toBack();
                    btValider.toBack();
                
                
            }else{
                saisirAnchor.setVisible(true);
                inserAnchorBacSe.setVisible(false);
            insererAnchorBacEco.setVisible(false);
            insererAnchorBacInfo.setVisible(false);
            insererAnchorBacLet.setVisible(false);
            insererAnchorBacMath.setVisible(false);
            insererAnchorBacSport.setVisible(false);
            insererAnchorBacTic.setVisible(false);
            consulterAnchor.setVisible(false);
            labelAnchor.toBack();
                    btConf.toBack();
                    btValider.toBack();
            }
        }
    }

    @FXML
    private void CScore(javafx.scene.input.MouseEvent event) throws SQLException {
        String bactype=getbactype().getBac_Type();
        if( null != bactype)switch (bactype) {
            case "ECO":{
                float MG=Float.parseFloat(tfMoyEco.getText());
                float M=Float.parseFloat(tfMathEco.getText());
                float ECO=Float.parseFloat(tfEcoEco.getText());
                float GE=Float.parseFloat(tfGeoEco.getText());
                float HG=Float.parseFloat(tfHisGeoEco.getText());
                float F=Float.parseFloat(tfFrEco.getText());
                float ANG=Float.parseFloat(tfAnglaisEco.getText());
                double FG=4*MG+0.5*M+1.5*ECO+1.5*GE+0.5*HG+F+ANG;
                BigDecimal bd = new BigDecimal(FG).setScale(2, RoundingMode.HALF_UP);
                double newInput = bd.doubleValue();
                sum.setText(String.valueOf(newInput));
                    break;
                }
            case "INFO":{
                float MG=Float.parseFloat(tfMoyInfo.getText());
                float M=Float.parseFloat(tfMathInfo.getText());
                float SP=Float.parseFloat(tfPhInfo.getText());
                float ALGO=Float.parseFloat(tfAlgoInfo.getText());
                float TIC=Float.parseFloat(tfTicInfo.getText());
                float BD=Float.parseFloat(tfDBInfo.getText());
                float F=Float.parseFloat(tfFrInfo.getText());
                float ANG=Float.parseFloat(tfAngInfo.getText());
                double FG=4*MG+1.5*M+0.5*SP+1.5*ALGO+F+ANG+0.25*(TIC+BD) ;
                sum.setText("Score: "+FG);
                    break;
                }
            case "LET":{
                float MG=Float.parseFloat(tfMoyLet.getText());
                float AR=Float.parseFloat(tfArabLet.getText());
                float PHY=Float.parseFloat(tfPhyLet.getText());
                float HG=Float.parseFloat(tfHisGeoLet.getText());
                float F=Float.parseFloat(tfFrLet.getText());
                float ANG=Float.parseFloat(tfAnglaisLet.getText());
                double FG=4*MG+1.5*AR+1.5*PHY+HG+F+ANG   ;
                sum.setText("Score: "+FG);
                    break;
                }
            case "MATH":{
                float MG=Float.parseFloat(tfMoyMath.getText());
                float M=Float.parseFloat(tfMathMath.getText());
                float SP=Float.parseFloat(tfPhyMath.getText());
                float SVT=Float.parseFloat(tfSVTMath.getText());
                float F=Float.parseFloat(tfFrMath.getText());
                float ANG=Float.parseFloat(tfAngMath.getText());
                double FG=4*MG+2*M+1.5*SP+0.5*SVT+F+ANG ;
                sum.setText("Score: "+FG);
                    break;
                }
            case "SE":{
                float MG=Float.parseFloat(tfMoySe.getText());
                float M=Float.parseFloat(tfMathSe.getText());
                float SP=Float.parseFloat(tfPhySe.getText());
                float SVT=Float.parseFloat(tfSVTSe.getText());
                float F=Float.parseFloat(tfFrSe.getText());
                float ANG=Float.parseFloat(tfAngSe.getText());
                double FG=4*MG+M+1.5*SP+1.5*SVT+F+ANG ;
                sum.setText("Score: "+FG);
                    break;
                }
            case "SPORT":{
                float MG=Float.parseFloat(tfMoySport.getText());
                float SPEPR=Float.parseFloat(tfSpPrSport.getText());
                float SPETR=Float.parseFloat(tfSpTheoSport.getText());
                float SVT=Float.parseFloat(tfSVTSport.getText());
                float EP=Float.parseFloat(tfEduPhSport.getText());
                float PHY=Float.parseFloat(tfPhysiqueSport.getText());
                float F=Float.parseFloat(tfFrSport.getText());
                float ANG=Float.parseFloat(tfAngSport.getText());
                double FG=(4*MG+1.5*SVT+SPEPR+0.5*EP+0.5*SPETR+0.5*PHY+F+ANG);
                sum.setText("Score: "+FG);
                    break;
                }
            case "TECH":{
                float MG=Float.parseFloat(tfMoyTic.getText());
                float M=Float.parseFloat(tfMathTic.getText());
                float SP=Float.parseFloat(tfPhyTic.getText());
                float TEC=Float.parseFloat(tfTecTic.getText());
                float F=Float.parseFloat(tfFrTic.getText());
                float ANG=Float.parseFloat(tfAngTic.getText());
                double FG=4*MG+1.5*M+1*SP+1.5*TEC+F+ANG ;
                sum.setText("Score: "+FG);
                    break;
                }
            default:
                break;
        }
        
    }

    @FXML
    private void updateScore(javafx.scene.input.MouseEvent event) {
        String query ="UPdate eleve SET score="+ Double.parseDouble(sum.getText())+" WHERE id_User=2";
    executeUpdate1(query);
        AlertBox.display("Done", "votre score est enregistée avec succée");
        
        consulterAnchor.setVisible(true);
    }

    @FXML
    private void showmap(ActionEvent event) throws IOException {
        if (event.getSource() == btMap) {
            try {
                
    Desktop.getDesktop().browse(new URL("http://localhost:7882/test1/"+lbEtab.getText()+".html").toURI());
} catch (IOException e) {
    e.printStackTrace();
} catch (URISyntaxException e) {
    e.printStackTrace();
}
            
        }}
    

    @FXML
    private void handleButtonAction2(javafx.scene.input.MouseEvent event) {
        Speciality speciality= tableview.getSelectionModel().getSelectedItem();
       lbEtab.setText(String.valueOf(speciality.getNom_Etab()));
       lbSpec.setText(String.valueOf(speciality.getNom()));
      
        
        
    }

    @FXML
    private void rechercher(ActionEvent event) {
        if (event.getSource() == btRecherche) {
            try {
               char ch='+';
       String lb;
       lb=lbSpec.getText();
       lb=lb.replace(' ', ch);
    Desktop.getDesktop().browse(new URL("https:www.google.tn/search?q="+lb).toURI());
} catch (IOException e) {
    e.printStackTrace();
} catch (URISyntaxException e) {
    e.printStackTrace();
}
            
        }
    }
    
}
