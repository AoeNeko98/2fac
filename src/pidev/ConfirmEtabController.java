/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entités.Cours;
import Entités.Etablissement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import services.ServiceCours;
import services.ServiceEtablissement;

/**
 * FXML Controller class
 *
 * @author Toumi
 */
public class ConfirmEtabController implements Initializable {

    @FXML
    private TableView<Etablissement> tvetab;
    @FXML
    private TableColumn<Etablissement, Integer> colid;
    @FXML
    private TableColumn<Etablissement, String> colNom;
    @FXML
    private TableColumn<Etablissement, String> colDiscription;
    @FXML
    private TableColumn<Etablissement, String> coladdress;
    @FXML
    private TableColumn<Etablissement, Integer> colnum;
    @FXML
    private Label tfnom;
    @FXML
    private Label tfaddress;
    @FXML
    private Label tfDiscription;
    @FXML
    private Label tfNum;
    @FXML
    private TextField FilterField;
    @FXML
    private Button btnvalide;
    @FXML
    private Button btninvalide;
    @FXML
    private Button btnretour;
    public String etabname;
    public int tfidd;
    @FXML
    private TableColumn<Etablissement, Integer> coletat;
    @FXML
    private Label tfid;
    ServiceEtablissement sp=new ServiceEtablissement();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ShowEtab();
    }    

     public void sett(String set,int id){
        etabname=set;
        tfidd=id;
    }
    @FXML
    private void Onclick(ActionEvent event) throws IOException, SQLException {
        if(event.getSource()==btnretour){
                     FXMLLoader loader=new FXMLLoader(getClass().getResource("/piamine/AcceuilAdmin.fxml"));
                Parent Ajouter = (Parent)loader.load();    
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
        }else if (event.getSource()==btnvalide){
            Etablissement e=new Etablissement(Integer.parseInt(tfid.getText()));
            sp.valide(e);
            ShowEtab();
            
        }else if (event.getSource()==btninvalide){
            Etablissement e=new Etablissement(Integer.parseInt(tfid.getText()));
            sp.invalide(e);
            ShowEtab();
        }
    }
    public void ShowEtab(){
          ServiceEtablissement sp=new ServiceEtablissement();
          
        ObservableList<Etablissement> list = sp.getCoursList();
        
        colid.setCellValueFactory(new PropertyValueFactory<Etablissement, Integer>("id"));
        colDiscription.setCellValueFactory(new PropertyValueFactory<Etablissement, String>("Discription"));
        coladdress.setCellValueFactory(new PropertyValueFactory<Etablissement, String>("Addresse"));
        colNom.setCellValueFactory(new PropertyValueFactory<Etablissement, String>("Nom"));
        colnum.setCellValueFactory(new PropertyValueFactory<Etablissement, Integer>("Num"));
        coletat.setCellValueFactory(new PropertyValueFactory<Etablissement,Integer>( "etat"));
        tvetab.setItems(list);
          FilteredList<Etablissement> filteredData = new FilteredList<>(list, b -> true);
		
		        FilterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Etablissement -> {
	
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
			
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Etablissement.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (Etablissement.getAddresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				} else if(Etablissement.getDiscription().toLowerCase().indexOf(lowerCaseFilter) != -1){
                                 return true;   
                                }
                                
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<Etablissement> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(tvetab.comparatorProperty());
		
		tvetab.setItems(sortedData);
    
        
    }

    @FXML
    private void onhold(MouseEvent event) {
         Etablissement etab= tvetab.getSelectionModel().getSelectedItem();
       tfid.setText(String.valueOf(etab.getId()));
       tfDiscription.setText(etab.getDiscription());
       tfNum.setText(String.valueOf(etab.getNum()));
       tfaddress.setText(etab.getAddresse());
       tfnom.setText(etab.getNom());
    }
}
