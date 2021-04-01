/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private TableView<Articles> TvConsulterPub;
    @FXML
    private TableColumn<Articles, String> ColDatePub;
    @FXML
    private TableView<Articles> TvCommentaire;
     @FXML
    private TableColumn<Articles,String> ColCommentaire;
    @FXML
    private Button BtnCreerP;
    @FXML
    private Button BtnSupprimer;
    @FXML
    private Button BtnModif;
    @FXML
    private Label TfIdPub;
    @FXML
    private TextField TfNomPub;
    @FXML
    private TextField TfContenuPub;
    @FXML
    private Button BtnAjoutCom;
    @FXML
    private Button BtnSupprimeCom;
    @FXML
    private Button BtnModifCom;
    @FXML
    private TextField TfCommentaire;
    @FXML
    private Label lbIdComnt;
    @FXML
    private TableColumn<Articles, String> ColNomUser;
    @FXML
    private TableColumn<Articles, String> ColCommentaire11;
    @FXML
     public void changesScreenreturn(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLDocumentController doc=loader.getController();
                doc.set(etabname, tfidd, nom);
                
        Scene Calculautoscene = new Scene (Ajouter);
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
    public String etabname;
    public int tfidd;
    public String nom;
    public void set(String Y,int id,String no){
        etabname=(Y);
        tfidd=id;
        nom=no;
        System.out.println(etabname);
        System.out.println(tfidd);
        
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
                article = new Articles(rs.getInt("ID_Etab"),rs.getString("Datepub"),rs.getString("Contenue_Article"),rs.getInt("ID_art"),rs.getString("Titre"));
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

    @FXML
    private void handleButtonAction2(MouseEvent event) {
          Articles articles= TvConsulterPub.getSelectionModel().getSelectedItem();
       TfIdPub.setText(String.valueOf(articles.getID_art()));
       TfNomPub.setText(articles.getTitre());
       TfContenuPub.setText(articles.getDiscription());
       showComnt();
    }
private void updatepub(){
    String query ="INSERT INTO articles (ID_Etab,datepub,Contenue_Article,titre) VALUES ("+tfidd+",'"+String.valueOf(dtf.format(now))+"','"+ TfContenuPub.getText()+"','"+TfNomPub.getText()+"')";
    executeUpdate(query);
    showArticles();
    
}
private void updateRecord(){
    String query ="UPdate articles SET titre='"+TfNomPub.getText()+"',Contenue_Article='"+TfContenuPub.getText()+ "' WHERE ID_art="+Integer.parseInt(TfIdPub.getText())+"";
    executeUpdate(query);
    showArticles();
    }
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now();  
   private void deleteButton(){
    String query ="DELETE FROM Articles WHERE ID_art="+TfIdPub.getText()+"";
    executeUpdate(query);
    showArticles();
     AlertBox.display("Pop-up", "supprission faite avec sucsee");
     
}
    @FXML
    private void handlebuttonAction(ActionEvent event) {
        if (event.getSource() == BtnCreerP) {
            updatepub();
        }else if (event.getSource() == BtnModif) {
            updateRecord();
        }else if (event.getSource() == BtnSupprimer) {
            deleteButton();
        }else if (event.getSource() == BtnAjoutCom) {
            ajoutComnt();  
        }else if (event.getSource() == BtnModifCom) {
            updateRecords();
        }else if (event.getSource() == BtnSupprimeCom) {
            deleteButtons();
        }
    }
private void deleteButtons(){
    String query ="DELETE FROM commentaire WHERE ID_comnt="+lbIdComnt.getText()+"";
    executeUpdate(query);
    showComnt();
     AlertBox.display("Pop-up", "supprission faite avec sucsee");
     }
     
      private void updateRecords(){
    String query ="UPdate Comnt SET commentaire='"+TfCommentaire.getText()+"' WHERE ID_Comnt="+lbIdComnt.getText();
    executeUpdate(query);
    showComnt();
    AlertBox.display("Pop-up", "modification faite avec sucsee");
    }

    
         
         private void ajoutComnt(){
    String query ="INSERT INTO commentaires  VALUES ("+tfidd+",'"+String.valueOf(dtf.format(now))+"','"+TfCommentaire.getText()+"','"+TfIdPub.getText()+"','"+etabname+"')";
    executeUpdate(query);
    showComnt();
    
    
}
 public void showComnt(){
        ObservableList<Articles> list = getComntList();
        
        ColCommentaire.setCellValueFactory(new PropertyValueFactory<Articles,String>("commentaire"));
        ColNomUser.setCellValueFactory(new PropertyValueFactory<Articles,String>("UserName"));
        ColCommentaire11.setCellValueFactory(new PropertyValueFactory<Articles,String>("DateComnt"));
        
        TvCommentaire.setItems(list);
    }

    private ObservableList<Articles> getComntList() {
    ObservableList<Articles> comntList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM commentaires WHERE ID_art="+TfIdPub.getText();
        Statement st;
        ResultSet rs;
        try{
            
            st= conn.createStatement();
            rs= st.executeQuery(query);
            Articles comnt;
            while(rs.next()){
                comnt = new Articles();
                comnt.setEtat(rs.getString("Etat"));
                if (comnt.getEtat() == "e") {
                    String query1 = "SELECT Nom FROM etablissement WHERE ID_Etab="+rs.getInt("ID_User");
                 Statement st1;
        ResultSet rs1;
        st1= conn.createStatement();
            rs1= st1.executeQuery(query1);
            while(rs1.next()){
                comnt.setUserName(rs1.getString("Nom"));
            } 
                }else if(comnt.getEtat() == "u"){
                        
               
        String query2 = "SELECT Nom FROM User WHERE ID_User="+rs.getInt("ID_User");
                 Statement st2;
        ResultSet rs2;
        st2= conn.createStatement();
            rs2= st2.executeQuery(query2);
            while(rs2.next()){
                comnt.setUserName(rs2.getString("Nom"));
            }}
                
                comnt.setCommentaire(rs.getString("contenue"));
                comnt.setID_Comnt(rs.getInt("ID_Comnt"));
                
               
                comnt.setDateComnt(rs.getString("datepubcom"));
                comntList.add(comnt);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return comntList;
    }

    @FXML
    private void handleButtonAction3(MouseEvent event) {
          Articles articles= TvCommentaire.getSelectionModel().getSelectedItem();
       TfCommentaire.setText(String.valueOf(articles.getCommentaire()));
       lbIdComnt.setText(String.valueOf(articles.getID_Comnt()));
    }

 
    
    
}