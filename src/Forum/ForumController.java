/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author kais
 */
public class ForumController implements Initializable {

    @FXML
    private TableColumn<Articles, Integer> ColIdPub;
    @FXML
    private TableColumn<Articles, String> ColNomPub;
    @FXML
    private TableColumn<Articles, String> ColContenuPub;
    @FXML
    private TableColumn<Articles, String> ColCommentaire;
    @FXML
    private TableView<Articles> TvModif;
    @FXML
    private TableColumn<Articles, String> ColContenu;
    @FXML
    private TextField TfNomPub;
    @FXML
    private TextField TfContenuPub;
    @FXML
    private Button BtnUpload;
    @FXML
    private Button BtnCreerP;
    @FXML
    private Button BtnModif;
    @FXML
    private Button BtnSupprimer;
    @FXML
    private TableView<Articles> TvCommentaire;
    @FXML
    private Button BtnAjoutCom;
    @FXML
    private Button BtnModifCom;
    @FXML
    private Button BtnSupprimeCom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction2(MouseEvent event) {
    }

    @FXML
    private void Upload(ActionEvent event) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void handlebuttonAction3(MouseEvent event) {
    }

    @FXML
    private void handlebuttonAction(ActionEvent event) {
    }
    
}
