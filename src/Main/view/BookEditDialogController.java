/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.view;

import Util.PostFile;
import entity.Book;
import entity.Category;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.BookService;
import service.CategoryService;

/**
 *
 * @author Aoe Neko 98
 */
public class BookEditDialogController {

    @FXML
    private TextField NameField;
    @FXML
    private ComboBox<Category> TypeField;
    @FXML
    private TextField DiscretionField;
    @FXML
    private TextField PrixField;
    @FXML
    private TextField ImageField;

    private Stage dialogStage;
    private Book book;
    private boolean okClicked = false;
    private File f;
    @FXML
    private ImageView img;

    private BookService bs = new BookService();
    CategoryService cs = new CategoryService();
    private ObservableList<Category> types;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        types = FXCollections.observableArrayList();
        ArrayList<Category> lsc = cs.getAllCategories();
        lsc.stream().forEach((j) -> {
            types.add(j);
        });
        TypeField.setItems(types);
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() throws Exception {

        book.setNom(NameField.getText());
        book.setCategory(TypeField.getValue());
        book.setDiscreption(DiscretionField.getText());
        try {
            book.setPrix(Float.parseFloat(PrixField.getText()));
        } catch (NumberFormatException exception) {
            book.setPrix(0);
        }
        if (f.getName().length()> 0) {
            book.setImage(PostFile.upload(f.getAbsolutePath()));
        }

        System.out.println("boook : " + book.getId());
        bs.editBook(book);
        okClicked = true;
        System.out.println(okClicked);
        dialogStage.close();

    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public void setBook(Book book) {
        this.book = book;

        NameField.setText(book.getNom());
        TypeField.setValue(book.getCategory());
        DiscretionField.setText(book.getDiscreption());
        //ImageField.setText(book.getImage());
        PrixField.setText(book.getPrix() + "");

    }

    public void uploadPhoto() throws MalformedURLException, Exception {
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(imageFilter);
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            String imageFile = selectedFile.toURI().toURL().toString();
            this.f = selectedFile;
            System.out.println(imageFile);
            System.out.println(selectedFile.getAbsolutePath());
            //PostFile.upload(selectedFile.getAbsolutePath());         
            Image image = new Image(imageFile);
            img.setImage(image);
            book.setImage(selectedFile.getName());
        }
    }

}
