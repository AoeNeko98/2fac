/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piamine;

import Entities.Domaine;
import Entities.User;
import Entities.club;
import Services.ClubService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import pidev.FXMLEtablissementController;
import sun.plugin.dom.exception.NoModificationAllowedException;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class ClubController implements Initializable {

    ClubService cs = new ClubService();

    ObservableList<club> ClubList = FXCollections.observableArrayList();

    private int id_club = 0;
    private int labelrating=0;
    private int ratin;

    @FXML
    private TextField nom_text;
    @FXML
    private TextField description_text;

    @FXML
    private TextField placediponible_text;
    @FXML
    private TableView<club> main_table;
    @FXML
    private TableColumn<club, Integer> idetab_column;
    @FXML
    private TableColumn<club, String> nom_column;
    @FXML
    private TableColumn<club, String> description_column;
    @FXML
    private TableColumn<club, Domaine> domaine_column;
    @FXML
    private TableColumn<club, Integer> placedisponible_column;
    @FXML
    private Button bntcreate;
    @FXML
    private Button update_btn;
    @FXML
    private Button delete_btn;
    @FXML
    private TextField filterField;
    private Button get_button;
    private Button revert_button;
    @FXML
    private ComboBox<String> domaine_text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showClubs();
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> l = new ArrayList<>();
        l.add(String.valueOf(Domaine.Club));
        l.add(String.valueOf(Domaine.ReseauInfo));
        l.add(String.valueOf(Domaine.Sante));
        domaine_text.setItems(FXCollections.observableArrayList(l));
        
     
    }

    @FXML
    private void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 1) {
            main_table.setItems(ClubList);
            ObservableList<club> allClubs, r;
            allClubs = main_table.getItems();
            r = main_table.getSelectionModel().getSelectedItems();

            nom_text.setText(r.get(0).getNom());
            description_text.setText(r.get(0).getDescription());
            placediponible_text.setText(String.valueOf(r.get(0).getPlaces()));
            id_club = r.get(0).getId();
            domaine_text.setValue(String.valueOf(r.get(0).getDomaine()));
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {

        if (event.getSource() == bntcreate) {
            club c = new club(nom_text.getText(), description_text.getText(), Domaine.valueOf(domaine_text.getValue()), Integer.parseInt(placediponible_text.getText()));
            cs.add(c);

            nom_text.setText("");
            description_text.setText("");
            placediponible_text.setText("");

            Notifications notificationBuilder = Notifications.create()
                    .title("Alert").text("club ajouté avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.CENTER_LEFT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("clicked ON ");
                        }
                    });
            notificationBuilder.darkStyle();
            notificationBuilder.show();

        } else if (event.getSource() == update_btn) {
            if (id_club != 0) {
                club c = new club(id_club, nom_text.getText(), description_text.getText(), Domaine.valueOf(domaine_text.getValue()), Integer.parseInt(placediponible_text.getText()));
                cs.update(c);
                id_club = 0;
                nom_text.setText("");
                description_text.setText("");
                placediponible_text.setText("");
            }

        } else if (event.getSource() == delete_btn) {
            if (id_club != 0) {
                cs.delete(id_club);
                id_club = 0;
                nom_text.setText("");
                description_text.setText("");
                placediponible_text.setText("");
            }
        } else if (event.getSource() == revert_button) {
            Parent Ajouter = FXMLLoader.load(getClass().getResource("graphic.fxml"));
            Scene scene = new Scene(Ajouter);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else if (event.getSource() == get_button) {
            Parent Ajouter = FXMLLoader.load(getClass().getResource("FXMLAbonne.fxml"));
            Scene scene = new Scene(Ajouter);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        ClubList.clear();
        showClubs();
    }

    @FXML
    private void Search(ActionEvent event) {
    }

    public void showClubs() throws SQLException {
        List<club> liste = new ArrayList<club>();
        liste = cs.readAll();

        for (club aux : liste) {
            ClubList.add(new club(aux.getId(), aux.getNom(), aux.getDescription(), aux.getDomaine(), aux.getPlaces()));
            main_table.setItems(ClubList);
        }

        idetab_column.setCellValueFactory(new PropertyValueFactory<club, Integer>("id"));
        nom_column.setCellValueFactory(new PropertyValueFactory<club, String>("nom"));
        description_column.setCellValueFactory(new PropertyValueFactory<club, String>("description"));
        domaine_column.setCellValueFactory(new PropertyValueFactory<club, Domaine>("domaine"));
        placedisponible_column.setCellValueFactory(new PropertyValueFactory<club, Integer>("places"));

        main_table.setItems(ClubList);

    }

    @FXML
    private void recherche(KeyEvent event) throws SQLException {
       ClubList.clear();
        List<club> liste = new ArrayList<club>();
        liste = cs.search(filterField.getText());

        for (club aux : liste) {
            ClubList.add(new club(aux.getId(), aux.getNom(), aux.getDescription(), aux.getDomaine(), aux.getPlaces()));
            main_table.setItems(ClubList);
        }

        idetab_column.setCellValueFactory(new PropertyValueFactory<club, Integer>("id"));
        nom_column.setCellValueFactory(new PropertyValueFactory<club, String>("nom"));
        description_column.setCellValueFactory(new PropertyValueFactory<club, String>("description"));
        domaine_column.setCellValueFactory(new PropertyValueFactory<club, Domaine>("domaine"));
        placedisponible_column.setCellValueFactory(new PropertyValueFactory<club, Integer>("places"));

        main_table.setItems(ClubList);
    }
    private String etabname;
    private int idd;
    public void set(String nom,int id){
        etabname= nom;
        idd=id;
    }

    @FXML
    private void retour(ActionEvent event) throws IOException, SQLException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/pidev/FXMLEtablissement.fxml"));
                Parent Ajouter = (Parent)loader.load();
                FXMLEtablissementController doc=loader.getController();
                doc.set(etabname,idd);
                doc.Afficher(idd);
                    
                
                
                Scene scene = new Scene(Ajouter);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

}
