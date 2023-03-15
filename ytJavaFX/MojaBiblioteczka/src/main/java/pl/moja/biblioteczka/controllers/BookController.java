package pl.moja.biblioteczka.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.moja.biblioteczka.modelFx.AuthorFx;
import pl.moja.biblioteczka.modelFx.BookModel;
import pl.moja.biblioteczka.modelFx.CategoryFx;
import pl.moja.biblioteczka.utils.DialogsUtils;
import pl.moja.biblioteczka.utils.exceptions.ApplicationException;

public class BookController {
	@FXML
	private Button addBookButton;
	@FXML
	private ComboBox<AuthorFx> authorComboBox;
	@FXML
	private ComboBox<CategoryFx> categoryComboBox;

	@FXML
	private TextArea descriptionTextArea;

	@FXML
	private TextField isbnTextField;

	@FXML
	private Slider ratingSlider;

	@FXML
	private DatePicker releaseDatePicker;

	@FXML
	private TextField titleTextField;

	private BookModel bookModel;

	public void initialize(){
		initializeBookModel();
		bindings();
		validation();
	}

	private void initializeBookModel() {
		bookModel = new BookModel();
		try {
			bookModel.init();
		} catch (ApplicationException e) {
			DialogsUtils.errorDialog(e.getMessage());
		}
	}

	public void bindings() {
		categoryComboBox.setItems(bookModel.getCategoryFxObservableList());
		authorComboBox.setItems(bookModel.getAuthorFxObservableList());
		releaseDatePicker.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().releaseDateProperty());
		isbnTextField.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().isbnProperty());
		ratingSlider.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().ratingProperty());
		titleTextField.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().titleProperty());
		descriptionTextArea.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().descriptionProperty());
		authorComboBox.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().authorFxProperty());
		categoryComboBox.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().categoryFxProperty());
	}

	public void addBookOnAction() {
		try {
			bookModel.saveBookInDataBase();
			clearFields();
		} catch (ApplicationException e) {
			DialogsUtils.errorDialog(e.getMessage());
		}
	}

	private void clearFields() {
		authorComboBox.getSelectionModel().clearSelection();
		categoryComboBox.getSelectionModel().clearSelection();
		titleTextField.clear();
		ratingSlider.setValue(1);
		descriptionTextArea.clear();
		isbnTextField.clear();
		releaseDatePicker.getEditor().clear();

	}

	private void validation() {
		addBookButton.disableProperty().bind(authorComboBox.valueProperty().isNull()
				.or(categoryComboBox.valueProperty().isNull())
				.or(titleTextField.textProperty().isEmpty())
				.or(descriptionTextArea.textProperty().isEmpty())
				.or(isbnTextField.textProperty().isEmpty())
				.or(releaseDatePicker.valueProperty().isNull()));
	}

	public BookModel getBookModel() {
		return bookModel;
	}
}
