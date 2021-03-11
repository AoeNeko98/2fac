/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import DB.DataBase;
import Entités.Club;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceClub;

/**
 * FXML Controller class
 *
 * @author Toumi
 */
public class FXMLAjouterClubController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextArea tfDescription;
    @FXML
    private Button btnretour;
    @FXML
    private TableColumn<Club, Integer> colID;
    @FXML
    private TableColumn<Club, String> colNom;
    @FXML
    private TableColumn<Club, String> colDescription;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    private int id;
    private String name;
    @FXML
    private TableView<Club> colClub;
    @FXML
    private Label labelid;
    @FXML
    private TextField tfrecherche;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowClubs(id);
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        Club c=new Club(tfNom.getText(), tfDescription.getText(),id);
        ServiceClub sp=new ServiceClub();
        sp.Ajouter(c);
        ShowClubs(id);
    }
    public void set(int idd,String nam){
        id=idd;
        name=nam;
    }
    @FXML
    private void modifier(ActionEvent event) throws SQLException {

         Club c=new Club(Integer.parseInt(labelid.getText()),tfNom.getText(), tfDescription.getText(),id);
        ServiceClub sp=new ServiceClub();
        sp.Modifier(c);
        ShowClubs(id);
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
        Club c=new Club(Integer.parseInt(labelid.getText()),tfNom.getText(), tfDescription.getText(),id);
        ServiceClub sp=new ServiceClub();
        sp.Supprimer(c);
        ShowClubs(id);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLEtablissement.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLEtablissementController doc=loader.getController();
                doc.set(name,id);
                
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
    

    
    public void ShowClubs(int id){
       ServiceClub sp= new ServiceClub();
        ObservableList<Club> list = sp.getCoursList(id);
        
        colID.setCellValueFactory(new PropertyValueFactory<Club, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Club, String>("Nom"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Club, String>("Description"));
        colClub.setItems(list);
        
         FilteredList<Club> filteredData = new FilteredList<>(list, b -> true);
		
		        tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Club -> {
	
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
			
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Club.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}  
                                
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<Club> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(colClub.comparatorProperty());
		
		colClub.setItems(sortedData);
    
        
    }

    @FXML
    private void Onhold(MouseEvent event) {
        Club clubs= colClub.getSelectionModel().getSelectedItem();
        labelid.setText(String.valueOf(clubs.getId()));
        tfNom.setText(clubs.getNom());
        tfDescription.setText(clubs.getDescription());
    }
    
    
}
