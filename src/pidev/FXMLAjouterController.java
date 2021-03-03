/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Toumi
 */
public class FXMLAjouterController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private TextField tfNom;

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnupload;
    @FXML
    private Text tfupload;
    
    public String name;
    @FXML
    private TextField tfspecialite;
    @FXML
    private TextArea tfdiscription;
    @FXML
    private TableColumn<Cours, Integer> colid;
    @FXML
    private TableColumn<Cours, String> colnom;
    @FXML
    private TableColumn<Cours, String> colDiscription;
    @FXML
    private TableColumn<Cours, String> ColSpec;
    
    public int idd;
    private String nom;
    @FXML
    private Button Getcours;
    @FXML
    private TableView<Cours> tabSpec;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnsupprime;
    @FXML
    private Label tfid;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowCours(idd);
    }   
   
    public void set(int id,String nom){
        idd=id;
        this.nom=nom;
    }

    @FXML
    private void Onclick(ActionEvent event) throws IOException, SQLException {
        if(event.getSource()==btnajouter){
            if (verif()==true && verifupload()==true){
                AjouterEtab();

                AjouterCours();
            AlertBox.display("ALERT", "Votre Cours est ajouter");
            }else{
          
            AlertBox.display("ALERT", "Specialite Ou fichier pdf n'existe pas");
            }
        
        }else if (event.getSource()==btnCancel){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLEtablissement.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLEtablissementController doc=loader.getController();
                doc.set(nom,idd);
                
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
        }else if (event.getSource()==btnupload){
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
            
        }else if (event.getSource()==Getcours){
            Getcours();
        }
    }
    
    public Connection getConnetion(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/2fac","root","");
            return conn;
        }catch(Exception ex){
            System.out.println("error");
            return null;
        }
    }
    
    private void AjouterEtab() throws SQLException{ 
        
        Connection conn = getConnetion();
        Statement st;
        String query2="SELECT ID_SPEC FROM speciality WHERE Nom='"+tfspecialite.getText()+"'";
        st = conn.createStatement();
        ResultSet rs2=st.executeQuery(query2);
        while(rs2.next()){
            int id_spec=rs2.getInt("ID_SPEC");
         String query = "INSERT INTO Cours (ID_Etab,Nom,Discription,ID_SPEC) VALUES("+idd+",'"+tfNom.getText()+"','"+tfdiscription.getText()+"',"+id_spec+")";
        executeQuery(query);
        
    }
        ShowCours(idd);
    }
    
    private void executeQuery(String query) {
       Connection conn = getConnetion();
        Statement st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("heelb:!!!!");
            
        }
    }
    
    private boolean verif() throws SQLException{
        Connection conn = getConnetion();
        Statement st;
        String query2="SELECT ID_SPEC FROM speciality WHERE Nom='"+tfspecialite.getText()+"'";
        st = conn.createStatement();
        ResultSet rs2=st.executeQuery(query2);
        while(rs2.next()){
            return true;
        }
        
        return false;
    }
    
    public void AjouterCours() throws SQLException, FileNotFoundException{
        Connection conn = getConnetion();
        PreparedStatement st;
        String query="UPDATE cours set Cours=? where Nom='"+tfNom.getText()+"'";
       st=conn.prepareStatement(query); 
        File theFile=new File(tfupload.getText());
        FileInputStream input = new FileInputStream(theFile);
        st.setBinaryStream(1, input);
        st.executeUpdate();
    }
    
    public void Getcours() throws SQLException, FileNotFoundException, IOException{
        Connection conn=getConnetion();
        Statement st;
        ResultSet rs;
        st=conn.createStatement();
        String query="SELECT Cours from cours where Nom='"+tfNom.getText()+"'";
        rs=st.executeQuery(query);
        File theFile = new File(tfNom.getText()+".pdf");
			FileOutputStream output = new FileOutputStream(theFile);

			if (rs.next()) {

				InputStream input = rs.getBinaryStream("Cours");
				System.out.println("Reading resume from database...");
				System.out.println(query);

				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}
    }}
    
        public ObservableList<Cours> getCoursList(int id){
        ObservableList<Cours> CoursList = FXCollections.observableArrayList();
        Connection conn = getConnetion();
        String query= "SELECT * FROM Cours where ID_Etab="+id;
            System.out.println(idd);
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Cours cour;
            while(rs.next()){
                 int id_spec=rs.getInt("ID_SPEC");
                                                                                                                                                                                                                                     String query1="SELECT Nom FROM speciality WHERE ID_SPEC="+id_spec;
                 
                int id_cours=rs.getInt("ID_Cours");
                String Nom_cours=rs.getString("Nom");
                String Discription=rs.getString("Discription");
                Statement st1;
                 st1=conn.createStatement();
                ResultSet rs1=st1.executeQuery(query1);
                while(rs1.next()){
                   
                    cour=new Cours(id_cours, Discription,rs1.getString("Nom"), Nom_cours);
                    System.out.println(cour.toString());
                    
                CoursList.add(cour);
                }
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
        return CoursList;
        } 
        
        
        public void ShowCours(int id){
        ObservableList<Cours> list = getCoursList(id);
        
        colid.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("ID_Cours"));
        colnom.setCellValueFactory(new PropertyValueFactory<Cours, String>("Coursname"));
        colDiscription.setCellValueFactory(new PropertyValueFactory<Cours, String>("Discription"));
        ColSpec.setCellValueFactory(new PropertyValueFactory<Cours, String>("nom_spec"));
        tabSpec.setItems(list);
    
        
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Cours cours= tabSpec.getSelectionModel().getSelectedItem();
       tfspecialite.setText((cours.getNom_spec()));
       tfNom.setText(cours.getCoursname());
       tfdiscription.setText(cours.getDiscription());
       tfid.setText(String.valueOf(cours.getID_Cours()));
    }
    
    public boolean verifupload(){
        
        if(tfupload.getText().equals("Vide")){
            return false;
        }else{
            return true;
        }
        
    }

    @FXML
    private void HandleUpdate(ActionEvent event) throws SQLException, FileNotFoundException {
        if (verif()==true && verifupload()==true){
                ModifCours();

                AjouterCours();
            AlertBox.display("ALERT", "Votre Cours est modifier");
            }else{
          
            AlertBox.display("ALERT", "Specialite Ou fichier pdf n'existe pas");
            }
    }

    private void ModifCours() throws SQLException{
             Connection conn = getConnetion();
        Statement st;
        String query2="SELECT ID_SPEC FROM speciality WHERE Nom='"+tfspecialite.getText()+"'";
        st = conn.createStatement();
        ResultSet rs2=st.executeQuery(query2);
        while(rs2.next()){
            int id_spec=rs2.getInt("ID_SPEC");
         String query = "UPDATE Cours SET Nom='"+tfNom.getText()+"',Discription='"+tfdiscription.getText()+"',ID_SPEC="+id_spec+" WHERE ID_Cours="+tfid.getText();
        executeQuery(query);
       
    } ShowCours(idd);
        
        
    }
    @FXML
    private void handleSupprime(ActionEvent event) { 
        String query="DELETE from Cours where ID_Cours="+tfid.getText();
        executeQuery(query);
        ShowCours(idd);
        
        
    }
    
    
    
    }
        
    

