package pl.moja.biblioteczka.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;
import pl.moja.biblioteczka.MyLibraryApp;
import pl.moja.biblioteczka.utils.DialogsUtils;

import java.util.Locale;

public class TopMenuButtonsController {
    public static final String LIST_BOOKS_FXML = "/fxml/ListBooks.fxml";
    public static final String ADD_BOOK_FXML = "/fxml/AddBook.fxml";
    public static final String ADD_CATEGORY_FXML = "/fxml/AddCategory.fxml";
    public static final String ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";
    public static final String PL_LANG = "pl";
    public ToggleSwitch languageSwitch;
    private MainController mainController;
    
    public void initialize() {
        if (Locale.getDefault().getLanguage().equals(PL_LANG)) {
            languageSwitch.setSelected(false);
        } else
            languageSwitch.setSelected(true);
    }

    @FXML
    ToggleGroup toggleButtons;

    @FXML
    public void openListBooks() {
        mainController.setCenter(LIST_BOOKS_FXML);
    }

    @FXML
    public void addBook() {
        resetToggleButtons();
        mainController.setCenter(ADD_BOOK_FXML);
    }
    
    public void addAuthor() {
        resetToggleButtons();
        mainController.setCenter(ADD_AUTHOR_FXML);
    }
    
    public void addCategory() {
        resetToggleButtons();
        mainController.setCenter(ADD_CATEGORY_FXML);
    }
    
    private void resetToggleButtons() {
        if (toggleButtons.getSelectedToggle() != null)
            toggleButtons.getSelectedToggle().setSelected(false);
    }
    public void switchLanguage(MouseEvent mouseEvent) {
        boolean valueOfLocale = ((ToggleSwitch) mouseEvent.getSource()).selectedProperty().get();
        System.out.println(valueOfLocale);
        
        if (valueOfLocale){
            Locale.setDefault(new Locale("eng"));
            mainController.closeStage();
            Platform.runLater( () -> {
                try {
                    new MyLibraryApp().start( new Stage() );
                } catch (Exception e) {
                    DialogsUtils.errorDialog(e.getMessage());
                }
            });
            mainController.closeStage();
        } else {
        
        }
//
    }
    
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
	

}
