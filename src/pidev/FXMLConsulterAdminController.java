/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entités.Cours;
import Entités.Rate;
import Utils.AlertBox;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import org.controlsfx.control.Rating;
import org.jpedal.exception.PdfException;
import piamine.AcceuilController;
import services.ServiceCours;

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
    private TableColumn<Cours, Double> colRate;
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
    public int id_user;
    public String nom;
    @FXML
    private TextField filterField;
    @FXML
    private Button btnrate;
    @FXML
    private Rating Courrate;
  

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
     
       ShowCours();
    }      

        public void set(int ids,String noms,int id_us){
            
           id=ids;
           nom=noms;
           id_user=id_us;
           
        }
        public void set(int ids){
            id_user=ids;
        }
  

    @FXML
    private void onCick(ActionEvent event) throws SQLException, FileNotFoundException, PdfException, IOException {
        if(event.getSource()==btnGetcours){
       
        Getcourse();
        }else if (event.getSource()==btncancel){
             FXMLLoader loader=new FXMLLoader(getClass().getResource("/piamine/acceuil.fxml"));
                Parent Ajouter = (Parent)loader.load();    
                Scene scene = new Scene(Ajouter);
                AcceuilController e=loader.getController();
                e.setId_User(id_user);
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
    
      public void Getcourse() throws SQLException, FileNotFoundException, IOException,FileAlreadyExistsException{
          try{
        File theFile = new File("C:\\xampp\\htdocs\\Pidevpdf\\"+tfNom.getText()+".pdf");
	File toFile=new File("C:\\Users\\iHoussem\\Downloads"+tfNom.getText()+".pdf");
        Files.copy(theFile.toPath(),toFile.toPath(),StandardCopyOption.COPY_ATTRIBUTES);
          }
        catch(FileAlreadyExistsException e){
            AlertBox.display("Erreur", "Tu as deja telechargé ce fichier");
        }
}
   
    
     
      public void ShowCours(){
          ServiceCours sp=new ServiceCours();
        ObservableList<Cours> list = sp.getCoursList();
        
        colid.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("ID_Cours"));
        colcoursname.setCellValueFactory(new PropertyValueFactory<Cours, String>("Coursname"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Cours, String>("Discription"));
        colspec.setCellValueFactory(new PropertyValueFactory<Cours, String>("nom_spec"));
        coletab.setCellValueFactory(new PropertyValueFactory<Cours, String>("Etabnom"));
        colRate.setCellValueFactory(new PropertyValueFactory<Cours, Double>("rate"));
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
      
    @FXML
    private void Onhold(MouseEvent event) {
        Cours cours= tvetab.getSelectionModel().getSelectedItem();
       tfNom.setText(cours.getCoursname());
       tfdiscription.setText(cours.getDiscription());
       tfid.setText(String.valueOf(cours.getID_Cours()));
    }

    @FXML
    private void Clickrate(ActionEvent event) throws SQLException {
        ServiceCours sp=new ServiceCours();
        Rate c=new Rate(id_user, Integer.parseInt(tfid.getText()), Courrate.getRating());
        sp.Ajoutrate(c);
        ShowCours();
        
    }
    

        
        
       
    
}

