/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;


public class FXMLMain extends Application
{
    private Stage primaryStage;
    private AnchorPane rootLayout;
    static Connection cnx;
    static Statement st;
    static ResultSet rst;
    
   

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        primaryStage.setMaximized(true);
        initRootLayout();
    }
    

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("./view/biblio.fxml"));
            rootLayout = (AnchorPane) loader.load();
            // Show the scene containing the root layout.
            // rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
          
        } catch (IOException e) {
            System.err.println(e);
        }
    }
 

 public static void main(String[] args) {
        launch(args);
        
    
    }
}
