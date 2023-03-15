package ytkurs.podsumowanie.apkafxporsumowanie.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OptionsController {
    private MainController mainController;
    @FXML
    void backMenu(ActionEvent event) {
        mainController.loadMainScreen();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}