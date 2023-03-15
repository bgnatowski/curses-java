module ytkurs.podsumowanie.apkafxporsumowanie {
    requires javafx.controls;
    requires javafx.fxml;

    opens ytkurs.podsumowanie.apkafxporsumowanie to javafx.fxml;
    exports ytkurs.podsumowanie.apkafxporsumowanie;

    exports ytkurs.podsumowanie.apkafxporsumowanie.controllers;
    opens ytkurs.podsumowanie.apkafxporsumowanie.controllers to javafx.fxml;
}