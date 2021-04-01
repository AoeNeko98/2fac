/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piamine;

import Entities.Abonnement;
import Entities.club;
import Services.AbonnementService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class MesAbonnementsController implements Initializable {

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

    private int id_user = 0;
    private int id_abonnement = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 1) {
            main_table.setItems(AbonnementList);
            ObservableList<Abonnement> allAbonnement, r;
            allAbonnement = main_table.getItems();
            r = main_table.getSelectionModel().getSelectedItems();

            id_abonnement = r.get(0).getId();
        }
    }

    public void showAbonnements() throws SQLException {
        List<Abonnement> liste = new ArrayList<>();
        liste = as.readAllByUserId(id_user);

        for (Abonnement aux : liste) {
            AbonnementList.add(new Abonnement(aux.getId(), aux.getId_user(), aux.getId_club(), aux.getDate(), aux.getClub(), aux.getUsername()));
            main_table.setItems(AbonnementList);
        }

        id.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id"));
        date.setCellValueFactory(new PropertyValueFactory<Abonnement, Date>("date"));
        club.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("club"));

        main_table.setItems(AbonnementList);

    }

    public void setId_User(int id) throws SQLException {
        id_user = id;
        showAbonnements();
    }

    @FXML
    private void desabonner(ActionEvent event) throws SQLException {
        System.out.println(id_abonnement);
        if (id_abonnement != 0) {
            as.delete(id_abonnement);
            Notifications notificationBuilder = Notifications.create()
                    .title("Alert").text("Vous etes désabonné!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.CENTER_LEFT)
                    .onAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("clicked ON ");
                        }
                    });
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            AbonnementList.clear();
            showAbonnements();
            id_abonnement = 0;
        }

    }
}
