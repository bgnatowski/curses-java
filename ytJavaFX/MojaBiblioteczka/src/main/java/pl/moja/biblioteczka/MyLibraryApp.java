package pl.moja.biblioteczka;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.moja.biblioteczka.database.dbutils.DbManager;
import pl.moja.biblioteczka.utils.FillDatabase;
import pl.moja.biblioteczka.utils.FxmlUtils;

public class MyLibraryApp extends Application {
    
    
    public static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";
    
    @Override
    public void start(Stage stage) throws Exception {
//        Locale.setDefault(new Locale("eng"));
        Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PANE_MAIN_FXML);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        stage.show();
    
        DbManager.initDatabase();
        FillDatabase.fillDatabase();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
