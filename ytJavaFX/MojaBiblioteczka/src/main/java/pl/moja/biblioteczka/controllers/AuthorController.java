package pl.moja.biblioteczka.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import pl.moja.biblioteczka.modelFx.AuthorFx;
import pl.moja.biblioteczka.modelFx.AuthorModel;
import pl.moja.biblioteczka.utils.DialogsUtils;
import pl.moja.biblioteczka.utils.exceptions.ApplicationException;

import java.sql.SQLException;

public class AuthorController {
	@FXML
	private Button addAuthorButton;
	
	@FXML
	private TextField nameTextField;
	
	@FXML
	private TextField surnameTextField;
	@FXML
	private TableView<AuthorFx> authorTableView;
	@FXML
	private TableColumn<AuthorFx, String> nameColumn;
	@FXML
	private TableColumn<AuthorFx, String> surnameColumn;
	@FXML
	private MenuItem deleteMenuItem;
	@FXML
	private MenuItem copyMenuItem;
	private AuthorModel authorModel;
	
	@FXML
	public void initialize() {
		modelInitialization();
		bindings();
		bindingTableView();
	}
	
	private void modelInitialization() {
		authorModel = new AuthorModel();
		try {
			authorModel.init();
		} catch (ApplicationException e) {
			DialogsUtils.errorDialog(e.getMessage());
		}
	}
	
	private void bindingTableView() {
		authorTableView.setItems(authorModel.getAuthorFxObservableList());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
		
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		authorTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldAuthorFx, newAuthorFx) -> {
			authorModel.setAuthorFxObjectPropertyEdit(newAuthorFx);
		});
	}
	
	private void bindings() {
		authorModel.authorFxObjectProperty().get().nameProperty().bind(nameTextField.textProperty());
		authorModel.authorFxObjectProperty().get().surnameProperty().bind(surnameTextField.textProperty());
		addAuthorButton.disableProperty().bind(nameTextField.textProperty().isEmpty().or(surnameTextField.textProperty().isEmpty()));
		deleteMenuItem.disableProperty().bind(authorTableView.getSelectionModel().selectedItemProperty().isNull());
		copyMenuItem.disableProperty().bind(authorTableView.getSelectionModel().selectedItemProperty().isNull());
	}
	
	@FXML
	void addAuthorOnAction() {
		try {
			authorModel.saveAuthorInDataBase();
		} catch (ApplicationException e) {
			DialogsUtils.errorDialog(e.getMessage());
		}
		nameTextField.clear();
		surnameTextField.clear();
	}
	
	public void onEditName(TableColumn.CellEditEvent<AuthorFx, String> authorFxStringCellEditEvent) {
		authorModel.getAuthorFxObjectPropertyEdit().setName(authorFxStringCellEditEvent.getNewValue());
		updateInDataBase();
	}
	
	public void onEditSurname(TableColumn.CellEditEvent<AuthorFx, String> authorFxStringCellEditEvent) {
		authorModel.getAuthorFxObjectPropertyEdit().setSurname(authorFxStringCellEditEvent.getNewValue());
		updateInDataBase();
	}
	
	private void updateInDataBase() {
		try {
			authorModel.saveAuthorEditInDataBase();
		} catch (ApplicationException e) {
			DialogsUtils.errorDialog(e.getMessage());
			try {
				authorModel.init();
			} catch (ApplicationException ex) {
				DialogsUtils.errorDialog(e.getMessage());
			}
		}
	}
	
	public void deleteAuthorOnAction() {
		try {
			authorModel.deleteAuthorInDataBase();
		} catch (ApplicationException | SQLException  e) {
			DialogsUtils.errorDialog(e.getMessage());
		}
	}
	
	public void copyAuthorOnAction(ActionEvent actionEvent) {
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		final ClipboardContent content = new ClipboardContent();
		String nameAndSurname = authorModel.getAuthorFxObjectPropertyEdit().getNameAndSurname();
		content.putString(nameAndSurname);
		content.putHtml("<b>" + nameAndSurname + "</b>");
		clipboard.setContent(content);
		System.out.println(nameAndSurname);
	}
}
