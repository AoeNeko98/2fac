/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entités.Club;
import Entités.Cours;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServiceClub;

/**
 * FXML Controller class
 *
 * @author Toumi
 */
public class FXMLConsulterClubController implements Initializable {

    @FXML
    private TableView<Club> tabclubs;
    @FXML
    private TableColumn<Club, String> colNom;
    @FXML
    private TableColumn<Club, String> coldescription;
    @FXML
    private TableColumn<Club, String> col_nometab;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button btnretour;
    private int id;
    private String nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowClubs();
    }    
    public void set(int id,String nom){
        this.id=id;
        this.nom=nom;
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLEtablissement.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLEtablissementController doc=loader.getController();
                doc.set(nom,id);
                
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
    public void ShowClubs(){
       ServiceClub sp= new ServiceClub();
       ObservableList<Club> list=sp.getCoursLists();
        
        colNom.setCellValueFactory(new PropertyValueFactory<Club, String>("Nom"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Club, String>("Description"));
        col_nometab.setCellValueFactory(new PropertyValueFactory<Club, String>("nom_etab"));
        
        tabclubs.setItems(list);
    FilteredList<Club> filteredData = new FilteredList<>(list, b -> true);
		
		        tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Club -> {
	
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
			
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Club.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (Club.getNom_etab().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				} 
                                
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<Club> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(tabclubs.comparatorProperty());
		
		tabclubs.setItems(sortedData);
        
    }
    
}
