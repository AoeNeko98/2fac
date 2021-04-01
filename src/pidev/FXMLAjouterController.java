/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Speciality;
import Utils.AlertBox;
import Entités.Cours;
import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ServiceCours;

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
    private Button Getcourses;
    @FXML
    private TableView<Cours> tabSpec;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnsupprime;
    @FXML
    private Label tfid;
    @FXML
    private TextField filter;
    
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
    private TextField filterField;
    @FXML
    private Label tfid1;
    @FXML
    private Label tfNom1;
    @FXML
    private Label tfdiscription1;
    @FXML
    private TableColumn<Cours, Integer> colid1;
     public int id;
    public int id_user;
    @FXML
    private Button Getcourse;
    @FXML
    private Button btnback;
    @FXML
    private ComboBox<String> spec;
    ServiceCours spp=new ServiceCours();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowCours(idd);
        ShowCours();
        try {
            spec.setItems(FXCollections.observableArrayList(spp.getspec()));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAjouterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
   
    public void set(int id,String nom){
        idd=id;
        this.nom=nom;
    }

    @FXML
    private void Onclick(ActionEvent event) throws IOException, SQLException, Exception {
        if(event.getSource()==btnajouter){
            if ( verifupload()==true){
                Cours cour=new Cours(tfdiscription.getText(), spec.getValue(),tfNom.getText(), idd);
                ServiceCours sp=new ServiceCours();
               if ( sp.AjouterEtab(cour)==true){
                        AjouterCours();
            AlertBox.display("ALERT", "Votre Cours est ajouter");
            ShowCours(idd);
            ShowCours();
               }
            }else{
          
            AlertBox.display("ALERT", "Ajouter un fichier pdf");
            }
        
        }else if (event.getSource()==btnCancel){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLEtablissement.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLEtablissementController doc=loader.getController();
                doc.set(nom,idd);
                doc.Afficher(idd);
                    
                
                
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
            
        }else if (event.getSource()==Getcourses){
            Getcourse();
        }else if (event.getSource()==Getcourse){
            Getcours();
        }else if (event.getSource()==btnback){
             FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLEtablissement.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLEtablissementController doc=loader.getController();
                doc.set(nom,idd);
                doc.Afficher(idd);
                    
                
                
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
    
 
    
    public void AjouterCours() throws SQLException, FileNotFoundException, IOException{
 
        File theFile=new File(tfupload.getText());
        File toFile=new File("C:\\xampp\\htdocs\\Pidevpdf\\"+tfNom.getText()+".pdf");
        Files.copy(theFile.toPath(),toFile.toPath(),StandardCopyOption.COPY_ATTRIBUTES);

    }
    
    public void Getcours() throws SQLException, FileNotFoundException, IOException,FileAlreadyExistsException{
        try{
        File theFile = new File("C:\\xampp\\htdocs\\Pidevpdf\\"+tfNom.getText()+".pdf");
	File toFile=new File("C:\\Users\\Toumi\\Downloads\\"+tfNom.getText()+".pdf");
        Files.copy(theFile.toPath(),toFile.toPath(),StandardCopyOption.COPY_ATTRIBUTES);
        }catch(FileAlreadyExistsException e){
            AlertBox.display("Erreur", "Tu as deja telechargé ce fichier");
        }
        }
      public void Getcourse() throws SQLException, FileNotFoundException, IOException,FileAlreadyExistsException{
          try{
              
        File theFile = new File("C:\\xampp\\htdocs\\Pidevpdf\\"+tfNom1.getText()+".pdf");
	File toFile=new File("C:\\Users\\Toumi\\Downloads\\"+tfNom1.getText()+".pdf");
        Files.copy(theFile.toPath(),toFile.toPath(),StandardCopyOption.COPY_ATTRIBUTES);
        }catch(FileAlreadyExistsException e){
            AlertBox.display("Erreur", "Tu as deja telechargé ce fichier");
        }
}
      public void SupprimeCours() throws SQLException, FileNotFoundException, IOException,FileAlreadyExistsException{
        File theFile = new File("C:\\xampp\\htdocs\\Pidevpdf\\"+tfNom.getText()+".pdf");
        Files.delete(theFile.toPath());
        }
        
    
        
        
        public void ShowCours(int id){
            ServiceCours sp = new ServiceCours();
        ObservableList<Cours> list = sp.getCoursList(id);
        
        colid.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("ID_Cours"));
        colnom.setCellValueFactory(new PropertyValueFactory<Cours, String>("Coursname"));
        colDiscription.setCellValueFactory(new PropertyValueFactory<Cours, String>("Discription"));
        ColSpec.setCellValueFactory(new PropertyValueFactory<Cours, String>("nom_spec"));
        tabSpec.setItems(list);
        FilteredList<Cours> filteredData = new FilteredList<>(list, b -> true);
		
		        filter.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Cours -> {
	
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
			
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Cours.getCoursname().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				
				} else if(Cours.getNom_spec().toLowerCase().indexOf(lowerCaseFilter) != -1){
                                 return true;   
                                }
                                
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<Cours> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(tabSpec.comparatorProperty());
		
		tabSpec.setItems(sortedData);
    
        
    }
        
        
     
      public void ShowCours(){
          ServiceCours sp=new ServiceCours();
          
        ObservableList<Cours> list = sp.getCoursList();
        
        colid1.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("ID_Cours"));
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
    private void handleMouseAction(MouseEvent event) {
        Cours cours= tabSpec.getSelectionModel().getSelectedItem();
       spec.setValue((cours.getNom_spec()));
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
    private void HandleUpdate(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
       
                ServiceCours sp=new ServiceCours();
             Cours c=new Cours(Integer.parseInt(tfid.getText()), tfdiscription.getText(), String.valueOf(spec.getValue()), tfNom.getText());
             sp.modifier(c);
         ShowCours(idd);
         ShowCours();
         if ( verifupload()==true){
           UpdateCours();  
                }
            AlertBox.display("ALERT", "Votre Cours est modifier");
            
  
    }
    public void UpdateCours() throws SQLException, FileNotFoundException, IOException{
 
        File theFile=new File(tfupload.getText());
        File toFile=new File("C:\\xampp\\htdocs\\Pidevpdf\\"+tfNom.getText()+".pdf");
        Files.copy(theFile.toPath(),toFile.toPath(),StandardCopyOption.REPLACE_EXISTING);

    }
    
    @FXML
    private void handleSupprime(ActionEvent event) throws SQLException, IOException { 
        ServiceCours sp=new ServiceCours();
        Cours c=new Cours(Integer.parseInt(tfid.getText()));
        sp.Supprimer(c);
        
        SupprimeCours();
        ShowCours();
        ShowCours(idd);
        
        
    }



    @FXML
    private void handleMouseAction1(MouseEvent event) {
        Cours cours= tvetab.getSelectionModel().getSelectedItem();
       tfNom1.setText(cours.getCoursname());
       tfdiscription1.setText(cours.getDiscription());
       tfid1.setText(String.valueOf(cours.getID_Cours()));
    }
    
    
    
    }
        
    

