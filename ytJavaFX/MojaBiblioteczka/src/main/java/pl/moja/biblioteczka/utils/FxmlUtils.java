package pl.moja.biblioteczka.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.util.ResourceBundle;

public class FxmlUtils {
	public static Pane fxmlLoader(String fxmlPath) {
		FXMLLoader fxmlLoader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
		fxmlLoader.setResources(getResourceBundle());
		try {
			return fxmlLoader.load();
		} catch (Exception e) {
			DialogsUtils.errorDialog(e.getMessage());
		}
		return null;
	}

	public static FXMLLoader getLoader(String fxmlPath) {
		FXMLLoader fxmlLoader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
		fxmlLoader.setResources(getResourceBundle());
		return fxmlLoader;
	}
	
	public static ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle("bundles.messages");
	}
}
