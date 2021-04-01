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
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import piamine.AcceuilController;
import pidev.FXMLEtablissementController;

/**
 * FXML Controller class
 *
 * @author kais
 */
public class CommenController implements Initializable {

    @FXML
    private TableView<Articles> TvConsulterPub;
    @FXML
    private TableColumn<Articles, String> ColNomPub;
    @FXML
    private TableColumn<Articles, String> ColDatePub;
    @FXML
    private TableView<Articles> TvCommentaire;
    @FXML
    private Button BtnAjoutCom;
    @FXML
    private Button BtnSupprimeCom;
    @FXML
    private Button BtnModifCom;
    @FXML
    private TableColumn<Articles,String> ColCommentaire;
    @FXML
    private TextField TfCommentaire;
    @FXML
    private Label lbtitre;
    @FXML
    private Label lbdate;
    @FXML
    private Label lbdiscription;
    @FXML
    private Label lbID;
    @FXML
    private Label lbIdComnt;
    @FXML
    private TableColumn<Articles,String> ColNomUser;
    @FXML
    private TableColumn<Articles,String> ColCommentaire11;
    @FXML
    public void changesScreenreturn(ActionEvent event) throws IOException, SQLException
    {
               FXMLLoader loader=new FXMLLoader(getClass().getResource("/piamine/acceuil.fxml"));
                Parent Ajouter = (Parent)loader.load();
                AcceuilController doc=loader.getController();
                doc.setId_User(tfidd);
                
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showArticles();    }    
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
        ColNomPub.setCellValueFactory(new PropertyValueFactory<Articles,String>("Titre"));
        ColDatePub.setCellValueFactory(new PropertyValueFactory<Articles,String>("datepub"));
        TvConsulterPub.setItems(list);
    }
    public String etabname;
    public int tfidd;
    public void set(String Y,int id){
        etabname=(Y);
        tfidd=id;
        System.out.println(etabname);
        System.out.println(tfidd);
        
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
        String query = "SELECT * FROM commentaires WHERE ID_art="+lbID.getText();
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
    private void handlebuttonAction2(MouseEvent event) {
        Articles articles= TvConsulterPub.getSelectionModel().getSelectedItem();
       lbtitre.setText(String.valueOf(articles.getTitre()));
       lbdate.setText(articles.getDatepub());
       lbdiscription.setText(articles.getDiscription());
       lbID.setText(String.valueOf(articles.getID_art()));
       showComnt();
    }
     private void deleteButtons(){
    String query ="DELETE FROM commentaires WHERE ID_comnt="+lbIdComnt.getText()+"";
    executeUpdate(query);
    showComnt();
     AlertBox.display("Pop-up", "supprission faite avec sucsee");
     }
     
      private void updateRecords(){
    String query ="UPdate commentaires SET contenue='"+TfCommentaire.getText()+"' WHERE ID_Comnt="+lbIdComnt.getText();
    executeUpdate(query);
    showComnt();
    AlertBox.display("Pop-up", "modification faite avec sucsee");
    }

    @FXML
    private void handleButtonAction3(MouseEvent event) {
          Articles articles= TvCommentaire.getSelectionModel().getSelectedItem();
       TfCommentaire.setText(String.valueOf(articles.getCommentaire()));
       lbIdComnt.setText(String.valueOf(articles.getID_Comnt()));
    }
         
        private void ajoutComnt(){
    String query ="INSERT INTO commentaires (ID_User,datepubcom,contenue,ID_art,Etat)  VALUES ("+tfidd+",'"+String.valueOf(dtf.format(now))+"','"+TfCommentaire.getText()+"','"+lbID.getText()+"','"+etabname+"')";
    executeUpdate(query);
    showComnt();
    
    
}
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now(); 
    @FXML
    private void handlebuttonAction(ActionEvent event) {
        if (event.getSource() == BtnAjoutCom) {
            ajoutComnt();
            
        }else if (event.getSource() == BtnModifCom) {
            updateRecords();
        }else if (event.getSource() == BtnSupprimeCom) {
            deleteButtons();
        }
                
    }

    


}

