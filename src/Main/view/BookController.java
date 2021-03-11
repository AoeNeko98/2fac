/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.view;

import Main.Main;
import entity.Book;
import entity.Category;
import entity.User;

import java.io.IOException;
import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.BookService;
import service.CategoryService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Aoe Neko 98
 */
public class BookController {

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> bookNameColumn;
    @FXML
    private TableColumn<Book, String> bookTypeColumn;
    @FXML
    private TableColumn<Book, String> bookUserColumn;

    @FXML
    private ListView simi;

    @FXML
    private Label nameLbl;
    @FXML
    private Label typeLbl;
    @FXML
    private Label descriptionLbl;
    @FXML
    private Label prixLabel;
    @FXML
    private ImageView imgView;
    @FXML
    private TextField searchBook;
    @FXML
    private ComboBox<Category> bookType;
    @FXML
    private Slider price;

    @FXML
    private ComboBox<String> bookTypes;

    private ObservableList<Book> data;
    private ObservableList<Category> types;
    private ObservableList<User> user;

    BookService bs = new BookService();
    CategoryService cs = new CategoryService();
    UserService us = new UserService();
    Book b;

    public void initialize() {
        System.out.println("=========================================================");
        // TODO

        List<Book> ls = bs.showAllBooks();
        ArrayList<Category> lsc = cs.getAllCategories();
        ArrayList<User> luc = us.getAllUsers();
        data = FXCollections.observableArrayList();
        types = FXCollections.observableArrayList();
        user = FXCollections.observableArrayList();
        lsc.stream().forEach((j) -> {
            types.add(j);
        });
        ls.stream().forEach((j) -> {
            data.add(j);
        });
        luc.stream().forEach((j) -> {
            user.add(j);
        });

        bookType.setItems(types);
        ObservableList<String> types2
                = FXCollections.observableArrayList(
                        "Tous",
                        "Vendre",
                        "Echange",
                        "Demande"
                );
        bookTypes.setItems(types2);

        bookTable.setItems(data);
        bookNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        bookTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getNom() + ""));
        bookUserColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getNom() + ""));
        // ListeView.setCellFactory((ListView<ProduitHerbo> param) -> new ListViewPHerboItemCell());
        showBookDetails(null);
        bookTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBookDetails(newValue));
         bookTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> handleSim(newValue));
        price.setMax(bs.maxPrice());
        String c = "lapin";
        System.out.println(bs.similaire(c));

    }

    public void showBookDetails(Book p) {
        if (p != null) {
            nameLbl.setText(p.getNom());
            descriptionLbl.setText(p.getDiscreption());
            typeLbl.setText(p.getType());
            prixLabel.setText(p.getPrix() + "");
            Image image = new Image("http://localhost/appJava/" + p.getImage());
            imgView.setImage(image);
        } else {
            nameLbl.setText("");
            descriptionLbl.setText("");
            typeLbl.setText("");
            prixLabel.setText("");
            //imgLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteBook() {
        int selectedIndex = bookTable.getSelectionModel().getSelectedIndex();
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirmation Dialog");
        a.setHeaderText("Really ?");
        a.setContentText("Are you ok with this?");

        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (selectedIndex >= 0) {
                bookTable.getItems().remove(selectedIndex);
                bs.deleteBook(selectedBook);
            } else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                //alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Service Selected");
                alert.setContentText("Please select a Service in the table.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleAddBook() {
        Book book = new Book();
        boolean okClicked = showBookEditDialog(book);
        System.out.println(okClicked);
        if (okClicked) {
            bs.addBook(book);
            initialize();
        }

    }

    @FXML
    private void handleEditBook() {

        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            boolean okClicked = showBookEditDialog(selectedBook);
            if (okClicked) {
                showBookDetails(selectedBook);
                bs.editBook(selectedBook);
                initialize();
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            ////alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a book in the table.");

            alert.showAndWait();

        }
    }

    public boolean showBookEditDialog(Book book) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(Main.class.getResource("view/BookEditDialog.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader2.load();
            dialogStage.setTitle("Edit book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            // dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            BookEditDialogController controller = loader2.getController();
            controller.setDialogStage(dialogStage);
            controller.setBook(book);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            System.err.println(e.toString());
            return false;
        }
    }

    @FXML
    private void handleSearchBook() {
        String txt = searchBook.getText();
        Category cate = bookType.getValue();
        String t = new String();
        float p = (float) price.getValue();

        System.out.println(txt);
        System.out.println(cate);
        System.out.println(p);
        data = FXCollections.observableArrayList();
        String test = bookTypes.getSelectionModel().getSelectedItem();
        try {
            if (test.equals("Tous")) {
                t = "";
            } else {
                t = test;

            }
        } catch (Exception e) {
            t = "";
        }

        List<Book> lsc = bs.searchBookByName(txt, cate, t, p);
        lsc.stream().forEach((j) -> {
            data.add(j);
        });
        bookTable.setItems(data);
        bookNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        bookTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getNom()));
        bookUserColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getNom() + ""));
   

    }

    @FXML
    private void handleReset() {
        bookType.getSelectionModel().clearSelection();
        bookTypes.setValue("Tous");
        //  System.out.println(bookType.getValue());
        price.setValue(0);
        initialize();

    }

    
    private void handleSim(Book t) {
        
           int i;
        Integer value;
        ArrayList<String> list=new ArrayList<>();
        Map<String, Integer> s = bs.similaire(t.getNom());
        String key;
        for (Iterator<Map.Entry<String, Integer>> entries = s.entrySet().iterator(); entries.hasNext();) {
            Map.Entry<String, Integer> entry = entries.next();
            key = entry.getKey();
            value = entry.getValue();
           
            if (value != 0 && value < key.length()) {
               list.add(key);
            }
        }
        simi.getItems().clear();
        for(i=0;i<=2 && i< list.size() ;i++){
        
        simi.getItems().add(list.get(i));
        
        }
       
    }

}
