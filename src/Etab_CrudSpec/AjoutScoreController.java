/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Etab_CrudSpec;

import Entities.AlertBox;
import Entities.Data;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.xml.bind.DatatypeConverter;

/**
 * FXML Controller class
 *
 * @author iHoussem
 */
public class AjoutScoreController implements Initializable {

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
    private Button btConf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void handleButtonConfAjout(ActionEvent event) throws IOException {
         if (event.getSource() == btConf) {
            
             init();
             if (tfBacEco1.getText() != "0") {
                 insertBacEco();
             }
             if (tfBacInfo1.getText() != "0") {
                 insertBacInfo();
             }
             if (tfBacLettre1.getText()!= "0") {
              insertBacLet();   
             }
             if (tfBacMath1.getText() != "0") {
                 insertBacMath();
             }
             if (tfBacSc1.getText()!= "0") {
                 insertBacSc();
             }
             if (tfBacSport1.getText() != "0") {
                insertBacSport(); 
             }
             if (tfBacTech1.getText() != "0") {
                 insertBacTech();
             }
            
            AlertBox.display("Done", " succée");
            Parent CalautoSc= FXMLLoader.load(getClass().getResource("MainCrudEtabPane.fxml"));
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
            
           }}
         public void init (){
             Speciality sp=getID();
             int ID_SPEC=sp.getID_SPEC();
             String queryinitialise="INSERT INTO scoreapprox VALUES ("+0+","+0+","+0+","+0+","+0+","+0+","+0+","+ID_SPEC+")";
        executeUpdate(queryinitialise);
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
         private void insertBacEco(){
             int ID_Etab=getID().getID_Etb();
    int ID_SPEC=getID().getID_SPEC();
         
        String query ="INSERT INTO bacecoscore VALUES ("+ID_Etab+",'"+Float.parseFloat(tfBacEco1.getText())
                +"','"+Float.parseFloat(tfBacEco2.getText())+"','"
                +Float.parseFloat(tfBacEco3.getText())+"','"+
                Float.parseFloat(tfBacEco4.getText())+"','"+Float.parseFloat(tfBacEco5.getText())+"',"+ID_SPEC+")";
            executeUpdate(query);
             

    Data f[]={
          new Data(1,Float.parseFloat(tfBacEco1.getText())),new Data(2,Float.parseFloat(tfBacEco2.getText()))
                ,new Data(3,Float.parseFloat(tfBacEco3.getText())),new Data(4,Float.parseFloat(tfBacEco4.getText())),
                new Data(5,Float.parseFloat(tfBacEco5.getText()))
        };
         double scoreapprox= Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreECO=("+ scoreapprox +") WHERE ID_SPC="+ID_SPEC+"";
        executeUpdate(queryscore);
    
    
    }
    private void insertBacInfo(){
        int ID_Etab=getID().getID_Etb();
    int ID_SPEC=getID().getID_SPEC();
         
        String query ="INSERT INTO bacinfoscore VALUES ("+ID_Etab+","+Float.parseFloat(tfBacInfo1.getText())+","+
                Float.parseFloat(tfBacInfo2.getText())+","
                +Float.parseFloat(tfBacInfo3.getText())+","+Float.parseFloat(tfBacInfo4.getText())
                +","+Float.parseFloat(tfBacInfo5.getText())+","+ID_SPEC+")";
    executeUpdate(query);

    Data f[]={
          new Data(1,Float.parseFloat(tfBacInfo1.getText())),new Data(2,Float.parseFloat(tfBacInfo2.getText()))
                ,new Data(3,Float.parseFloat(tfBacInfo3.getText())),new Data(4,Float.parseFloat(tfBacInfo4.getText())),
                new Data(5,Float.parseFloat(tfBacInfo5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreINFO=("+ scoreapprox +") WHERE ID_SPC="+ID_SPEC+"";
        executeUpdate(queryscore);
    
    }
    private void insertBacLet(){
        int ID_Etab=getID().getID_Etb();
    int ID_SPEC=getID().getID_SPEC();
        
        String query ="INSERT INTO bacletscore VALUES ("+ID_Etab+","+ Float.parseFloat(tfBacLettre1.getText())
                +","+Float.parseFloat(tfBacLettre2.getText())+","
                +Float.parseFloat(tfBacLettre3.getText())+","+
                Float.parseFloat(tfBacLettre4.getText())+","+Float.parseFloat(tfBacLettre5.getText())+","+ID_SPEC+")";
    executeUpdate(query);
        Data f[]={
          new Data(1,Float.parseFloat(tfBacLettre1.getText())),new Data(2,Float.parseFloat(tfBacLettre2.getText()))
                ,new Data(3,Float.parseFloat(tfBacLettre3.getText())),new Data(4,Float.parseFloat(tfBacLettre4.getText())),
                new Data(5,Float.parseFloat(tfBacLettre5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreLET=("+ scoreapprox +") WHERE ID_SPC="+ID_SPEC+"";
        executeUpdate(queryscore);

    
    }
    private void insertBacMath(){
        int ID_Etab=getID().getID_Etb();
    int ID_SPEC=getID().getID_SPEC();
        
        String query ="INSERT INTO bacmathscore VALUES ("+ID_Etab+","+ Float.parseFloat(tfBacMath1.getText())
                +","+Float.parseFloat(tfBacMath2.getText())+","
                +Float.parseFloat(tfBacMath3.getText())+","+Float.parseFloat(tfBacMath4.getText())
                +","+Float.parseFloat(tfBacMath5.getText())+","+ID_SPEC+")";
    executeUpdate(query);
        Data f[]={
          new Data(1,Float.parseFloat(tfBacMath1.getText())),new Data(2,Float.parseFloat(tfBacMath2.getText()))
                ,new Data(3,Float.parseFloat(tfBacMath3.getText())),new Data(4,Float.parseFloat(tfBacMath4.getText())),
                new Data(5,Float.parseFloat(tfBacMath5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreMATH=("+ scoreapprox +") WHERE ID_SPC="+ID_SPEC+"";
        executeUpdate(queryscore);

    
    }
    private void insertBacSc(){
        int ID_Etab=getID().getID_Etb();
    int ID_SPEC=getID().getID_SPEC();
         
        String query ="INSERT INTO bacscscore VALUES ("+ID_Etab+","+Float.parseFloat(tfBacSc1.getText())
                +","+Float.parseFloat(tfBacSc2.getText())+","
                +Float.parseFloat(tfBacSc3.getText())+","+
                Float.parseFloat(tfBacSc4.getText())+","+Float.parseFloat(tfBacSc5.getText())+","+ID_SPEC+")";
    executeUpdate(query);
        Data f[]={
          new Data(1,Float.parseFloat(tfBacSc1.getText())),new Data(2,Float.parseFloat(tfBacSc2.getText()))
                ,new Data(3,Float.parseFloat(tfBacSc3.getText())),new Data(4,Float.parseFloat(tfBacSc4.getText())),
                new Data(5,Float.parseFloat(tfBacSc5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreSc=("+ scoreapprox +") WHERE ID_SPC="+ID_SPEC+"";
        executeUpdate(queryscore);

    }
    private void insertBacSport(){
        int ID_Etab=getID().getID_Etb();
    int ID_SPEC=getID().getID_SPEC();
         
        String query ="INSERT INTO bacsportscore VALUES ("+ID_Etab+","+Float.parseFloat(tfBacSport1.getText())+","+Float.parseFloat(tfBacSport2.getText())+","
                +Float.parseFloat(tfBacSport3.getText())+","+Float.parseFloat(tfBacSport4.getText())+","+Float.parseFloat(tfBacSport5.getText())+","+ID_SPEC+")";
    executeUpdate(query);

        Data f[]={
          new Data(1,Float.parseFloat(tfBacSport1.getText())),new Data(2,Float.parseFloat(tfBacSport2.getText()))
                ,new Data(3,Float.parseFloat(tfBacSport3.getText())),new Data(4,Float.parseFloat(tfBacSport4.getText())),
                new Data(5,Float.parseFloat(tfBacSport5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreSPORT=("+ scoreapprox +") WHERE ID_SPC="+ID_SPEC+"";
        executeUpdate(queryscore);

    
    }
    private void insertBacTech(){
        int ID_Etab=getID().getID_Etb();
    int ID_SPEC=getID().getID_SPEC();
        String query ="INSERT INTO bactechscore VALUES ("+ID_Etab+","+Float.parseFloat(tfBacTech1.getText())+","+Float.parseFloat(tfBacTech2.getText())+","
                +Float.parseFloat(tfBacTech3.getText())+","+Float.parseFloat(tfBacTech4.getText())+","+Float.parseFloat(tfBacTech5.getText())+","+ID_SPEC+")";
    executeUpdate(query);
    
        Data f[]={
          new Data(1,Float.parseFloat(tfBacTech1.getText())),new Data(2,Float.parseFloat(tfBacTech2.getText()))
                ,new Data(3,Float.parseFloat(tfBacTech3.getText())),new Data(4,Float.parseFloat(tfBacTech4.getText())),
                new Data(5,Float.parseFloat(tfBacTech5.getText())),
        };
        double scoreapprox=Data.interpolate(f, 6, 5);
        String queryscore="UPDATE scoreapprox SET ScoreTECH=("+ scoreapprox +") WHERE ID_SPC="+ID_SPEC+"";
        executeUpdate(queryscore);

    
    }
    
    
    
    
    
    
    public Speciality getID(){
        
        Connection conn = getConnection();
        String query = "SELECT * FROM speciality ORDER BY ID_SPEC DESC LIMIT 1";
        Speciality specialityss= null;
        Statement st;
        ResultSet rs;
        
        try{
            st= conn.createStatement();
            
            rs= st.executeQuery(query);
            
            while(rs.next()){
            specialityss= new Speciality(rs.getInt("ID_Etb"),rs.getInt("ID_SPEC"));
            
                
                
            }
            
          
        }catch (SQLException ex){
        }
        return specialityss;
    }
    
}
