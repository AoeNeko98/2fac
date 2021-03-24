/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.view.BookEditDialogController;
import entity.Book;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import Main.view.BookController;

import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.stage.Modality;

public class Main extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;
    static Connection cnx;
    static Statement st;
    static ResultSet rst;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("view/bib_1.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("Light");
        primaryStage.setScene(scene);
        primaryStage.show();

        //this.primaryStage.setTitle("AddressApp");
        primaryStage.setMaximized(true);
        //  initRootLayout();
    }

    public static void main(String[] args) {

        launch(args);

    }
}
