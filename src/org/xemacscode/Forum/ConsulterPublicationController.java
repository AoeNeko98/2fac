/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.Forum;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kais
 */
public class ConsulterPublicationController implements Initializable {


    @FXML
    private TableColumn<Articles, String> ColNomPub;
    @FXML
    private TableColumn<Articles, Integer> ColIdPub;
    @FXML
    private TableColumn<Articles, String> ColContenuPub;
    @FXML
    private Button BtnRetourConsult;
    @FXML
    private TableView<Articles> TvConsulterPub;
    @FXML
    private TableColumn<Articles, String> ColDatePub;
    @FXML
    public void changesScreenreturns(ActionEvent event) throws IOException
    {
        Parent CalautoSc= FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
     
     public ObservableList<Articles> getArticlesList(){
        ObservableList<Articles> articleslist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM Articles";
        Statement st;
        ResultSet rs;
        try{
            st= conn.createStatement();
            rs= st.executeQuery(query);
            Articles article;
            while(rs.next()){
                article = new Articles(rs.getInt("ID_Etab"),rs.getString("Datepub"),rs.getString("Discription"),rs.getInt("ID_art"),rs.getString("Titre"));
                articleslist.add(article);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return articleslist;
    }
    public void showArticles(){
     
        ObservableList<Articles> list = getArticlesList();
        ColIdPub.setCellValueFactory(new PropertyValueFactory<Articles,Integer>("ID_art"));
        ColNomPub.setCellValueFactory(new PropertyValueFactory<Articles,String>("Titre"));
        ColContenuPub.setCellValueFactory(new PropertyValueFactory<Articles,String>("Discription"));
        ColDatePub.setCellValueFactory(new PropertyValueFactory<Articles,String>("datepub"));
        TvConsulterPub.setItems(list);
    }
    public void changesScreenAfficherPub(ActionEvent event) throws IOException
    {
        Parent CalautoSc= FXMLLoader.load(getClass().getResource("AfficherPub.fxml"));
        Scene Calculautoscene = new Scene (CalautoSc);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Calculautoscene);
        window.show();
    }

 
    
    
}