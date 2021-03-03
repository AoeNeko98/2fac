/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.Forum;

import java.awt.event.MouseEvent;
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
 * @author kais
 */
public class ModifierpublictionController implements Initializable {

    @FXML
    private Label TfIdPub;
    @FXML
    private TextField TfNomPub;
    @FXML
    private TextField TfContenuPub;
    @FXML
    private Button BtnModif;
    @FXML
    private Button BtnAnnulerModif;
    @FXML
    private TableView<Articles> TvModif;
    @FXML
    private TableColumn<Articles, Integer> ColIdPubM;
    @FXML
    private TableColumn<Articles, String> ColNomPubM;
    @FXML
    private TableColumn<Articles, String> ColContenuM;
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
    private void handleButtonAction(ActionEvent event) {
       
        if(event.getSource()==BtnModif)
                {
                updateRecord();}}
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showArticles();
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
    
public ObservableList<Articles> getArticlesesList(){
        ObservableList<Articles> articleslist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM articles";
        Statement st;
        ResultSet rs;
        try{
            st= conn.createStatement();
            rs= st.executeQuery(query);
            Articles articles;
            while(rs.next()){
                articles = new Articles(rs.getInt("ID_art"),rs.getString("Titre"),rs.getString("Discription"));
                articleslist.add(articles);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return articleslist;
    }
        public void showArticles(){
     
        ObservableList<Articles> list = getArticlesesList();
        ColIdPubM.setCellValueFactory(new PropertyValueFactory<Articles,Integer>("ID_art"));
        ColNomPubM.setCellValueFactory(new PropertyValueFactory<Articles,String>("Titre"));
        ColContenuM.setCellValueFactory(new PropertyValueFactory<Articles,String>("Discription"));
        TvModif.setItems(list);}
        
        private void updateRecord(){
    String query ="UPdate articles SET titre='"+TfNomPub.getText()+"',Discription='"+TfContenuPub.getText()+ "' WHERE ID_art="+Integer.parseInt(TfIdPub.getText())+"";
    executeUpdate(query);
    showArticles();
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

    @FXML
    private void handleButtonAction2(javafx.scene.input.MouseEvent event) {
        
       
       Articles articles= TvModif.getSelectionModel().getSelectedItem();
       TfIdPub.setText(String.valueOf(articles.getID_art()));
       TfNomPub.setText(articles.getTitre());
       TfContenuPub.setText(articles.getDiscription());
    }
}