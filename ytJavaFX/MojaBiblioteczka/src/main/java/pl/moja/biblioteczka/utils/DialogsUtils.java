package pl.moja.biblioteczka.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import pl.moja.biblioteczka.utils.exceptions.ApplicationException;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtils {
    private static ResourceBundle bundle = FxmlUtils.getResourceBundle();

    public static void dialogAboutApplication(){
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(bundle.getString("about.title"));
        informationAlert.setHeaderText(bundle.getString("about.header"));
        informationAlert.setContentText(bundle.getString("about.content"));
        informationAlert.showAndWait();
    }
    
    public static Optional<ButtonType> confirmationDialog(){
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle(bundle.getString("exit.title"));
        confirmationAlert.setHeaderText(bundle.getString("exit.header"));
    
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        return result;
    }
    
    public static void errorDialog(String errorMessage){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.title"));
        errorAlert.setHeaderText(bundle.getString("error.header"));
        TextArea textArea = new TextArea(errorMessage);
        textArea.setEditable(false);
        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();
    }
    public static String editDialog(String value) {
        TextInputDialog dialog = new TextInputDialog(value);
        dialog.setTitle(bundle.getString("edit.title"));
        dialog.setHeaderText(bundle.getString("edit.header"));
        dialog.setContentText(bundle.getString("edit.content"));
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
            return result.get();
        else
            return null;
    }
}
