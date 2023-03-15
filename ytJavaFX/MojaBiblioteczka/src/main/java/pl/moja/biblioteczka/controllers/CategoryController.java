package pl.moja.biblioteczka.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import pl.moja.biblioteczka.modelFx.CategoryFx;
import pl.moja.biblioteczka.modelFx.CategoryModel;
import pl.moja.biblioteczka.utils.DialogsUtils;
import pl.moja.biblioteczka.utils.exceptions.ApplicationException;

import java.sql.SQLException;

public class CategoryController {
	@FXML
	private Button addCategoryButton;
	@FXML
	private Button deleteCategoryButton;
	@FXML
	private Button editCategoryButton;
	@FXML
	private TextField categoryTextField;
	@FXML
	private ComboBox<CategoryFx> categoryComboBox;
	@FXML
	private TreeView<String> categoryTreeView;
	private CategoryModel categoryModel;
	
	@FXML
	public void initialize(){
		categoryModel = new CategoryModel();
		try {
			categoryModel.init();
		} catch (ApplicationException e) {
			DialogsUtils.errorDialog(e.getMessage());
		}
		categoryComboBox.setItems(categoryModel.getCategoryList());
		categoryTreeView.setRoot(categoryModel.getRoot());
		initBindings();
	}
	
	private void initBindings() {
		addCategoryButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());
		deleteCategoryButton.disableProperty().bind(categoryModel.categoryProperty().isNull());
		editCategoryButton.disableProperty().bind(categoryModel.categoryProperty().isNull());
	}
	
	public void addCategoryOnAction() {
		try {
			categoryModel.saveCategoryInDataBase(categoryTextField.getText());
		} catch (ApplicationException e) {
			DialogsUtils.errorDialog(e.getMessage());
		}
		categoryTextField.clear();
		
	}
	
	public void deleteOnAction() {
		try {
			categoryModel.deleteCategoryById();
		} catch (ApplicationException | SQLException e) {
			DialogsUtils.errorDialog(e.getMessage());
		}
	}
	
	public void onActionComboBox() {
		CategoryFx selectedItem = categoryComboBox.getSelectionModel().getSelectedItem();
		categoryModel.setCategory(selectedItem);
	}
	
	public void onActionEditCategory() {
		String newCategoryName = DialogsUtils.editDialog(categoryModel.getCategory().getName());
		if (newCategoryName != null && !newCategoryName.isEmpty()) {
			categoryModel.getCategory().setName(newCategoryName);
			try {
				categoryModel.updateCategoryInDataBase();
			} catch (ApplicationException e) {
				DialogsUtils.errorDialog(e.getMessage());
			}
		}
	}
}
