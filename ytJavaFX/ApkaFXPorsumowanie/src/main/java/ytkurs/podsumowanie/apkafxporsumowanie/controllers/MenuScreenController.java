package ytkurs.podsumowanie.apkafxporsumowanie.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuScreenController {

    private MainController mainController;

    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
    }
    @FXML
    void openApplication(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AppScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AppController appController = loader.getController();
        appController.setController(mainController);
        mainController.setScreen(pane);
    }
    @FXML
    void options(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("OptionsScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OptionsController optionsController = loader.getController();
        optionsController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}