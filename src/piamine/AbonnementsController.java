/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piamine;

import Entities.Abonnement;
import Services.AbonnementService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class AbonnementsController implements Initializable {

  
    

    AbonnementService as = new AbonnementService();

    ObservableList<Abonnement> AbonnementList = FXCollections.observableArrayList();

    @FXML
    private TableView<Abonnement> main_table;
    @FXML
    private TableColumn<Abonnement, Integer> id;
    @FXML
    private TableColumn<Abonnement, String> club;
    @FXML
    private TableColumn<Abonnement, Date> date;
    @FXML
    private TableColumn<Abonnement, String> Utilisateur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showAbonnements();
        } catch (SQLException ex) {
            Logger.getLogger(AbonnementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void mouseClicked(MouseEvent event) {
    }
    
    public void showAbonnements() throws SQLException {
        List<Abonnement> liste = new ArrayList<>();
        liste = as.readAll();

        for (Abonnement aux : liste) {
            AbonnementList.add(new Abonnement(aux.getId(), aux.getId_user(), aux.getId_club(), aux.getDate(), aux.getClub(), aux.getUsername()));
            main_table.setItems(AbonnementList);
        }

        id.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id"));
        date.setCellValueFactory(new PropertyValueFactory<Abonnement, Date>("date"));
        club.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("club"));
        Utilisateur.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("username"));

        main_table.setItems(AbonnementList);

    }
    
}
