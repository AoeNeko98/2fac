/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.Forum;

import com.sun.org.apache.bcel.internal.generic.PopInstruction;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kais
 */
public class AjouterpubController implements Initializable {

    @FXML
    private Button BtnCreerP;
    @FXML
    private Button BtnAnnulerCP;
    @FXML
    private TextField TfNomPub;
    @FXML
    private TextField TfContenuPub;
    @FXML
    private Label LabAjoutNom;
    @FXML
    private Label LabajoutContenu;
    @FXML
    private Button BtnUpload;
    @FXML
    private Label tfupload;
    private String name;
    @FXML
    public void changesScreenreturn(ActionEvent event) throws IOException
    {
        Parent CalautoSc= FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
    }
        @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {
       
        if (event.getSource()==BtnCreerP){
            updatepub();
            ajoutimg();
            AlertBox.display("Pop-up", "ajout fait avec sucsee");
            Parent CalautoSc= FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
            
        }
        
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public Connection getConnection(){
        
        String url = "jdbc:mysql://localhost:3306/2fac";
        String login="root";
        String pwd="";
        try{
           Connection conn = DriverManager.getConnection(url, login, pwd);
            return conn;
        }catch(SQLException ex){
            System.out.println("Error2: "+ ex.getMessage());
            return null;
        }
    }
     private void executeUpdate(String query) {
        Connection conn =getConnection();
         Statement       st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch(Exception ex){
            ex.printStackTrace();
        }
         
    
   
    }
     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now();  

   
   
     private void updatepub(){
    String query ="INSERT INTO articles (ID_Etab,datepub,Discription,titre) VALUES (1,'"+String.valueOf(dtf.format(now))+"','"+ TfContenuPub.getText()+"','"+TfNomPub.getText()+"')";
    executeUpdate(query);
    
}
     private void ajoutimg() throws SQLException, FileNotFoundException{
        Connection conn = getConnection();
        PreparedStatement st;
        String query="UPDATE articles set image=? where titre='"+TfNomPub.getText()+"'";
       st=conn.prepareStatement(query); 
        File theFile=new File(tfupload.getText());
        FileInputStream input = new FileInputStream(theFile);
        st.setBinaryStream(1, input);
        st.executeUpdate();
    
     }

    @FXML
    private void Upload(ActionEvent event) {
        Node node = (Node) event.getSource();
            
             FileChooser fc=new FileChooser();
            
             File seletFile= fc.showOpenDialog(node.getScene().getWindow());
             fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
             
             if (seletFile != null){
                   tfupload.setText(seletFile.getAbsolutePath());
                    name=seletFile.getName();
             }else{
                 System.out.println("Invalid!!");
             }
    }
    
}
