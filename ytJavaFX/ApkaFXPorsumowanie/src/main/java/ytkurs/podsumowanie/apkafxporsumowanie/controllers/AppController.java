package ytkurs.podsumowanie.apkafxporsumowanie.controllers;

import javafx.fxml.FXML;

public class AppController {
    private MainController mainController;
    @FXML
    public void backMenu(){
        mainController.loadMainScreen();
    }

    public void setController(MainController controller) {
        this.mainController = controller;
    }
}
