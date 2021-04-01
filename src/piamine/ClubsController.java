/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piamine;

import Entities.Abonnement;
import Entities.Domaine;
import Entities.club;
import Services.AbonnementService;
import Services.ClubService;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class ClubsController implements Initializable {

    ClubService cs = new ClubService();
    AbonnementService as = new AbonnementService();

    ObservableList<club> ClubList = FXCollections.observableArrayList();

    private int id_club = 0;
    private int id_user = 0;
    private String nom = "";

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showClubs();
        } catch (SQLException ex) {
            Logger.getLogger(ClubsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 1) {
            main_table.setItems(ClubList);
            ObservableList<club> allClubs, r;
            allClubs = main_table.getItems();
            r = main_table.getSelectionModel().getSelectedItems();
            id_club = r.get(0).getId();
            nom = r.get(0).getNom();
        }
    }

    public void showClubs() throws SQLException {
        List<club> liste = new ArrayList<club>();
        liste = cs.readAll();

        for (club aux : liste) {
            ClubList.add(new club(aux.getId(), aux.getNom(), aux.getDescription(), aux.getDomaine(), aux.getPlaces(), aux.getPlaceDispo()));
            main_table.setItems(ClubList);
        }

        idetab_column.setCellValueFactory(new PropertyValueFactory<club, Integer>("id"));
        nom_column.setCellValueFactory(new PropertyValueFactory<club, String>("nom"));
        description_column.setCellValueFactory(new PropertyValueFactory<club, String>("description"));
        domaine_column.setCellValueFactory(new PropertyValueFactory<club, Domaine>("domaine"));
        placedisponible_column.setCellValueFactory(new PropertyValueFactory<club, Integer>("placeDispo"));

        main_table.setItems(ClubList);

    }

    @FXML
    private void abonner(ActionEvent event) throws SQLException {
        if (id_club != 0) {
            if (!as.Verification(id_user, id_club)) {
                LocalDate currentDate = LocalDate.now();
                java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
                Abonnement a = new Abonnement(id_user, id_club, sqlDate);
                as.add(a);

                Notifications notificationBuilder = Notifications.create()
                        .title("Alert").text("Vous etes abonné dans le club " + nom).graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                        .position(Pos.CENTER_LEFT)
                        .onAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                System.out.println("clicked ON ");
                            }
                        });
                notificationBuilder.darkStyle();
                notificationBuilder.show();

                ClubList.clear();
                showClubs();
            } else {
                Notifications notificationBuilder = Notifications.create()
                        .title("Alert").text("Vous etes deja abonné dans ce club!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                        .position(Pos.CENTER_LEFT)
                        .onAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                System.out.println("clicked ON ");
                            }
                        });
                notificationBuilder.darkStyle();
                notificationBuilder.show();
            }

        } else {
            Notifications notificationBuilder = Notifications.create()
                    .title("Alert").text("selectionnez un club!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.CENTER_LEFT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("clicked ON ");
                        }
                    });
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        }

    }

    public void setId_User(int id) throws SQLException {
        id_user = id;
    }

}
