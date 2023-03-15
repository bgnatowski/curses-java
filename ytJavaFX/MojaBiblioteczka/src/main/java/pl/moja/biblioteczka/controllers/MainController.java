package pl.moja.biblioteczka.controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.moja.biblioteczka.utils.DialogsUtils;
import pl.moja.biblioteczka.utils.FxmlUtils;

import java.util.Optional;

public class MainController{
    @FXML
    private TopMenuButtonsController topMenuButtonsController;

    @FXML
    private BorderPane borderPane;
    
    public void initialize() {
        topMenuButtonsController.setMainController(this);
    }

    public void setCenter(String fxmlPath){
        borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
    }

    public void closeApp() {
        Optional<ButtonType> result = DialogsUtils.confirmationDialog();
        if (result.get() == ButtonType.OK){
            Platform.exit();
            System.exit(0);
        }
    }

    public void setCaspian() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    public void setModena() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }

    public void setAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) borderPane.getScene().getWindow();

        boolean valueOfAlwaysOnTop = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(valueOfAlwaysOnTop);
    }

    public void aboutApp() {
        DialogsUtils.dialogAboutApplication();
    }
    
    public void closeStage(){
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }
}
