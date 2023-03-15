package pl.moja.biblioteczka.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.moja.biblioteczka.modelFx.AuthorFx;
import pl.moja.biblioteczka.modelFx.BookFx;
import pl.moja.biblioteczka.modelFx.CategoryFx;
import pl.moja.biblioteczka.modelFx.ListBooksModel;
import pl.moja.biblioteczka.utils.DialogsUtils;
import pl.moja.biblioteczka.utils.FxmlUtils;
import pl.moja.biblioteczka.utils.exceptions.ApplicationException;

import java.io.IOException;
import java.time.LocalDate;

public class ListBooksController {
	public static final String ICON_EDIT_PATH = "/icons/edit.png";
	public static final String ICON_DELETE_PATH = "/icons/delete.png";
	@FXML
	private ComboBox categoryComboBox;
	@FXML
	private ComboBox authorComboBox;
	@FXML
	private TableView<BookFx> booksTableView;
	@FXML
	private TableColumn<BookFx, String > titleColumn;
	@FXML
	private TableColumn<BookFx, String> descColumn;
	@FXML
	private TableColumn<BookFx, AuthorFx> authorColumn;
	@FXML
	private TableColumn<BookFx, CategoryFx> categoryColumn;
	@FXML
	private TableColumn<BookFx, Number> ratingColumn;
	@FXML
	private TableColumn<BookFx, String> isbnColumn;
	@FXML
	private TableColumn<BookFx, LocalDate> releaseColumn;
	@FXML
	private TableColumn<BookFx, BookFx> deleteColumn;
	@FXML
	public TableColumn<BookFx, BookFx> editColumn;

	private ListBooksModel listBooksModel;

	@FXML
	void initialize(){
		listBooksModel = new ListBooksModel();
		try {
			listBooksModel.init();
		} catch (ApplicationException e) {
			DialogsUtils.errorDialog(e.getMessage());
		}
		bindings();
	}

	private void bindings() {
		categoryComboBox.setItems(listBooksModel.getCategoryFxObservableList());
		authorComboBox.setItems(listBooksModel.getAuthorFxObservableList());

		listBooksModel.categoryFxObjectPropertyProperty().bind(categoryComboBox.valueProperty());
		listBooksModel.authorFxObjectPropertyProperty().bind(authorComboBox.valueProperty());

		booksTableView.setItems(listBooksModel.getBookFxObservableList());
		titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		descColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorFxProperty());
		categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryFxProperty());
		ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
		isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
		releaseColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
		deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
		editColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

		createDeleteButtonsInCells();
		createEditButtonsInCells();
	}

	private void createDeleteButtonsInCells() {
		deleteColumn.setCellFactory(param ->  new TableCell<BookFx, BookFx>(){
			Button button = createButton(ICON_DELETE_PATH);
			@Override
			protected void updateItem(BookFx bookFx, boolean empty) {
				 super.updateItem(bookFx, empty);

				 if (empty) {
					 setGraphic(null);
					 return;
				 } else {
					 setGraphic(button);
					 button.setOnAction(event ->{
						 try {
							 listBooksModel.deleteBook(bookFx);
						 } catch (ApplicationException e) {
							 DialogsUtils.errorDialog(e.getMessage());
						 }
					 });
				 }
			}
		});
	}

	private void createEditButtonsInCells() {
		editColumn.setCellFactory(param ->  new TableCell<BookFx, BookFx>(){
			Button button = createButton(ICON_EDIT_PATH);
			@Override
			protected void updateItem(BookFx item, boolean empty) {
				super.updateItem(item, empty);

				if (empty) {
					setGraphic(null);
					return;
				} else {
					setGraphic(button);
					button.setOnAction(event ->{
						FXMLLoader loader = FxmlUtils.getLoader("/fxml/AddBook.fxml");
						Scene editScene = null;
						try {
							editScene = new Scene(loader.load());
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
						BookController controller = loader.getController();
						controller.getBookModel().setBookFxObjectProperty(item);
						controller.bindings();

						Stage stage = new Stage();
						stage.setScene(editScene);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.showAndWait();
					});
				}
			}
		});
	}

	private Button createButton(String path){
		Button button = new Button();
		Image image = new Image(getClass().getResource(path).toString());
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(15);
		imageView.setFitWidth(15);
		button.setGraphic(imageView);
		return button;
	}

	public void filterOnActionComboBox() {
		listBooksModel.filterBooksList();
	}

	public void clearCategoryComboBox(ActionEvent actionEvent) {
		categoryComboBox.getSelectionModel().clearSelection();
	}

	public void clearAuthorComboBox(ActionEvent actionEvent) {
		authorComboBox.getSelectionModel().clearSelection();
	}
}
