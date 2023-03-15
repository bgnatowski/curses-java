module ytkurs.tabelaapk {
    requires javafx.controls;
    requires javafx.fxml;


    opens ytkurs.tabelaapk to javafx.fxml;
    exports ytkurs.tabelaapk;
    exports ytkurs.tabelaapk.controllers;
    opens ytkurs.tabelaapk.controllers to javafx.fxml;
}