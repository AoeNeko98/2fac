/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piamine;

import Entities.User;
import Services.UserService;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class FXChartController implements Initializable {

    UserService us = new UserService();

    private ScatterChart<String, Integer> ScatterChart;

    @FXML
    private AreaChart<String, Integer> AreaChart;
    @FXML
    private Button bntload;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        XYChart.Series series = new XYChart.Series(); //Make a new XYChart object

        AreaChart.getData().addAll(series);
    }

    @FXML
    private void Load(ActionEvent event) throws SQLException {
        XYChart.Series<String, Integer> series = new XYChart.Series(); //Make a new XYChart object
        List<User> liste = new ArrayList<>();
        liste = us.readAll();
        for (User aux : liste) {
            series.getData().add(new XYChart.Data<>(String.valueOf(aux.getNom()), aux.getId()));
        }
        AreaChart.getData().add(series);
    }

}
