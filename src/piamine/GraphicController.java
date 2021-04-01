/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piamine;

import Entities.Domaine;
import Entities.club;
import Services.ClubService;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class GraphicController implements Initializable {

    ClubService cs=new ClubService();
    
    @FXML
    private Button load;
    @FXML
    private BarChart<String, Integer> BarChart;
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void load(ActionEvent event) throws SQLException {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        List<club> l=new ArrayList<>();
        l=cs.readAll();
        for(club aux: l){
            System.out.println(aux.getPlaces());
            series.getData().add(new XYChart.Data<>(String.valueOf(aux.getDomaine()),aux.getPlaces()));
        }

        BarChart.getData().add(series);
    }

    
    
}
