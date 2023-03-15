module mojabiblioteczka.mojabiblioteczka {
    requires javafx.controls;
    requires javafx.fxml;
	requires org.controlsfx.controls;

	requires java.sql;
	requires ormlite.jdbc;
	
	opens pl.moja.biblioteczka.controllers to javafx.fxml;
    opens pl.moja.biblioteczka to javafx.fxml;
    exports pl.moja.biblioteczka;
	
	opens pl.moja.biblioteczka.database.models to ormlite.jdbc;
	exports pl.moja.biblioteczka.database.models;
	
	
}