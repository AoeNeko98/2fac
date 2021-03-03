/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.File;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import org.jpedal.PdfDecoder;
import org.jpedal.exception.PdfException;
import sun.reflect.Reflection;

/**
 * FXML Controller class
 *
 * @author Toumi
 */
public class FXMLConsulterAdminController implements Initializable {

    @FXML
    private TableColumn<Cours, Integer> colid;
    private Button btnCour;
    
    @FXML
    private TableView<Cours> tvetab;
    private TextField tfidn;

    @FXML
    private TableColumn<Cours, String> colcoursname;
    @FXML
    private TableColumn<Cours, String> coldescription;
    @FXML
    private TableColumn<Cours, String> colspec;
    @FXML
    private TableColumn<Cours, String> coletab;
    @FXML
    private Button btnGetcours;
    @FXML
    private Button btncancel;
    @FXML
    private Label tfid;
    @FXML
    private Label tfNom;
    @FXML
    private Label tfdiscription;
    public int id;
    public String nom;
    @FXML
    private TextField filterField;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
     
       ShowCours();
    }      

        public void set(int ids,String noms){
            
           id=ids;
           nom=noms;
           
        }
  

    @FXML
    private void onCick(ActionEvent event) throws SQLException, FileNotFoundException, PdfException, IOException {
        if(event.getSource()==btnGetcours){
       
        Getcourse();
        }else if (event.getSource()==btncancel){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLEtablissement.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLEtablissementController doc=loader.getController();
                doc.set(nom,id);
                
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            
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
    
     public void Getcours() throws SQLException, FileNotFoundException, IOException, SQLException, SQLException, SQLException, SQLException, SQLException, SQLException, SQLException, FileNotFoundException, SQLException, SQLException, IOException{
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
     
     public ObservableList<Cours> getCoursList(){
        ObservableList<Cours> CoursList = FXCollections.observableArrayList();
        Connection conn = getConnetion();
        String query= "SELECT * FROM Cours ";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Cours cour;
            while(rs.next()){
                 int id_spec=rs.getInt("ID_SPEC");
                 String query1="SELECT Nom FROM speciality WHERE ID_SPEC="+id_spec;
                 String query2="SELECT Nom FROM etablissement WHERE ID_Etab="+rs.getInt("ID_Etab");
                 
                int id_cours=rs.getInt("ID_Cours");
                String Nom_cours=rs.getString("Nom");
                String Discription=rs.getString("Discription");
                Statement st1;
                 st1=conn.createStatement();
                 Statement st2=conn.createStatement();
                ResultSet rs1=st1.executeQuery(query1);
                ResultSet rs2=st2.executeQuery(query2);
                while(rs1.next() && rs2.next()){
                   
                    cour=new Cours(id_cours, Discription,rs1.getString("Nom"), Nom_cours,rs2.getString("Nom"));
                    System.out.println(cour.toString());
                    
                CoursList.add(cour);
                }
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
        return CoursList;
        } 
     
      public void ShowCours(){
        ObservableList<Cours> list = getCoursList();
        
        colid.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("ID_Cours"));
        colcoursname.setCellValueFactory(new PropertyValueFactory<Cours, String>("Coursname"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Cours, String>("Discription"));
        colspec.setCellValueFactory(new PropertyValueFactory<Cours, String>("nom_spec"));
        coletab.setCellValueFactory(new PropertyValueFactory<Cours, String>("Etabnom"));
        tvetab.setItems(list);
          FilteredList<Cours> filteredData = new FilteredList<>(list, b -> true);
		
		        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Cours -> {
	
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
			
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Cours.getCoursname().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (Cours.getEtabnom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				} else if(Cours.getNom_spec().toLowerCase().indexOf(lowerCaseFilter) != -1){
                                 return true;   
                                }
                                
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<Cours> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(tvetab.comparatorProperty());
		
		tvetab.setItems(sortedData);
    
        
    }
      
   
    private void Getcourse() throws SQLException, FileNotFoundException, IOException{
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


    
    @FXML
    private void Onhold(MouseEvent event) {
        Cours cours= tvetab.getSelectionModel().getSelectedItem();
       tfNom.setText(cours.getCoursname());
       tfdiscription.setText(cours.getDiscription());
       tfid.setText(String.valueOf(cours.getID_Cours()));
    }

        
        
       
    
}

