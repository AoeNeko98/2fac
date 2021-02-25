/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;

import com.sun.corba.se.impl.util.Utility;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.management.remote.JMXConnectorFactory;

/**
 *
 * @author iHoussem
 */
public class MainController implements Initializable {
    
    @FXML 
    private TextField tfid;
    @FXML
    private TextField tftitle;
    @FXML
    private TextField tfauthor;
    @FXML
    private TextField tfyear;
    @FXML
    private TextField tfpages;
    @FXML
    private TableView<Books> tvBooks;
    @FXML
    private TableColumn<Books, Integer> colid;
    @FXML
    private TableColumn<Books, String> coltitle;
    @FXML
    private TableColumn<Books, String> colauthor;
    @FXML
    private TableColumn<Books, Integer> colyear;
    @FXML
    private TableColumn<Books, Integer> colpages;
    @FXML
    private Button btInsert;
    @FXML
    private Button btUpdate;
    @FXML
    private Button btDelete;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
        if (event.getSource()==btInsert){
            insertRecord();
        }
        else if(event.getSource()==btUpdate)
                {
                updateRecord();}
        else if (event.getSource()==btDelete)
                {deleteButton();}
        
    }
    @FXML
    private void handleButtonAction2(MouseEvent event) {
       
       Books book= tvBooks.getSelectionModel().getSelectedItem();
       tfid.setText(String.valueOf(book.getId()));
       tftitle.setText(book.getTitle());
       tfauthor.setText(book.getAuthor());
       tfyear.setText(String.valueOf(book.getYear()));
       tfpages.setText(String.valueOf(+book.getYear()));
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showBooks();
    }    
    public Connection getConnection(){
        
        String url = "jdbc:mysql://localhost:3306/LIBRARY";
        String login="houssem";
        String pwd="1327";
        try{
           Connection conn = DriverManager.getConnection(url, login, pwd);
            return conn;
        }catch(SQLException ex){
            System.out.println("Error2: "+ ex.getMessage());
            return null;
        }
    }
    public ObservableList<Books> getBooksList(){
        ObservableList<Books> booklist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM books";
        Statement st;
        ResultSet rs;
        try{
            st= conn.createStatement();
            rs= st.executeQuery(query);
            Books books;
            while(rs.next()){
                books = new Books(rs.getInt("id"),rs.getString("title"),rs.getString("author"),rs.getInt("year"),rs.getInt("pages"));
                booklist.add(books);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return booklist;
    }
    public void showBooks(){
     
        ObservableList<Books> list = getBooksList();
        colid.setCellValueFactory(new PropertyValueFactory<Books,Integer>("id"));
        coltitle.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
        colauthor.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
        colyear.setCellValueFactory(new PropertyValueFactory<Books,Integer>("year"));
        colpages.setCellValueFactory(new PropertyValueFactory<Books,Integer>("pages"));
        tvBooks.setItems(list);
    }
    private void insertRecord(){
        String query ="INSERT INTO books VALUES ("+tfid.getText()+",'"+ tftitle.getText()+"','"+tfauthor.getText()+"',"
                +tfyear.getText()+","+tfpages.getText()+")";
    executeUpdate(query);
    showBooks();
    }
private void updateRecord(){
    String query ="UPdate books SET title='"+tftitle.getText()+"',author='"+tfauthor.getText()+"',year="+
            tfyear.getText()+",pages="+tfpages.getText()+ " WHERE id="+tfid.getText()+"";
    executeUpdate(query);
    showBooks();
}
private void deleteButton(){
    String query ="DELETE FROM books WHERE id="+tfid.getText()+"";
    executeUpdate(query);
    showBooks();
}
    private void executeUpdate(String query) {
        Connection conn =getConnection();
         Statement       st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    
}