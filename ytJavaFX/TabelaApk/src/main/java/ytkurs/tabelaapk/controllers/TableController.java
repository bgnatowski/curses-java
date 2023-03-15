package ytkurs.tabelaapk.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ytkurs.tabelaapk.Product;

import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TableController implements Initializable{
    @FXML
    private TableView<Product> tableview;
    @FXML
    public TableColumn<Product, String> prodC1;
    @FXML
    public TableColumn<Product, Double> prodC2;
    @FXML
    public TableColumn<Product, Integer> prodC3;
    @FXML
    public TableColumn<Product, String> prodC4;
    @FXML
    public TableColumn<Product, String> prodC5;
    @FXML
    private Button add;
    @FXML
    private TextField textC1;
    @FXML
    private TextField textC2;
    @FXML
    private TextField textC3;
    @FXML
    private TextField textC4;
    @FXML
    private TextField textC5;
    @FXML
    private Label errorC2;
    @FXML
    private Label errorC3;

    @FXML
    private TextArea textArea;

//    private String lastC2;

    @FXML
    public void buttonAdd(){
        Product product = new Product(textC1.getText(), Double.parseDouble(textC2.getText()), Integer.parseInt(textC3.getText()), textC4.getText(), textC5.getText());
        tableview.getItems().add(product);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//"(?:-(?:[1-9](?:\\d{0,2}(?:,\\d{3})+|\\d*))|(?:0|(?:[1-9](?:\\d{0,2}(?:,\\d{3})+|\\d*))))(?:.\\d+|)"
////        System.out.println(Arrays.asList(x));
//        String[] matches = Pattern.compile("[0-9]{1,13}(\\\\.[0-9]+)?")
//                .matcher("ew23.64")
//                .results()
//                .map(MatchResult::group)
//                .toArray(String[]::new);
//        System.out.println(Arrays.asList(matches));
//
//        boolean isDouble = Pattern.matches("[0-9]{1,13}(\\.[0-9]*)?", "ew23.42");
//        System.out.println(isDouble);



        prodC1.setCellValueFactory(new PropertyValueFactory<>("ProdC1"));
        prodC2.setCellValueFactory(new PropertyValueFactory<>("ProdC2"));
        prodC3.setCellValueFactory(new PropertyValueFactory<>("ProdC3"));
        prodC4.setCellValueFactory(new PropertyValueFactory<>("ProdC4"));
        prodC5.setCellValueFactory(new PropertyValueFactory<>("ProdC5"));
        tableview.setItems(observableList);
        tableview.setEditable(true);
        prodC1.setCellFactory(TextFieldTableCell.forTableColumn());
    }


    @FXML
    void onEdit(TableColumn.CellEditEvent<Product, String> productStringCellEditEvent) {
        Product product = tableview.getSelectionModel().getSelectedItem();
        product.setProdC1(productStringCellEditEvent.getNewValue());
    }

    ObservableList<Product> observableList = FXCollections.observableArrayList(
            new Product("Name1", 2.12, 2, "cos1", "cos2"),
            new Product("Name2", 123.234, 2, "cosC1-1", "cosC1-2"),
            new Product("Name3", 3214.234, 2, "cosC1-1", "cosC1-2"),
            new Product("Name4", -3412.0034, 2, "cosC1-1", "cosC1-2")
    );

    @FXML
    void onKeyTypedC3(KeyEvent event) {
        String textInC3 = textC3.getText();
        try {
            Integer.parseInt(textInC3);
        } catch(NumberFormatException ex){
            errorC3.setText("Pole C3 musi zawierać liczbę typu int!");
            textC3.setText(String.join("",textInC3.split("[^0-9]+")));

        }
    }
    @FXML
    void onKeyTypedC2(KeyEvent event) {
        String textInC2 = textC2.getText();
        try{
            Double.parseDouble(textInC2);
        }catch (NumberFormatException ex){
            errorC2.setText("Pole C2 musi zawierać liczbę typu double!");
            String znak = event.getCharacter();
            textC2.setText(String.join("",textInC2.split(znak)));
        }

//        String text = textArea.getText();
//        try{
//            Double.parseDouble(text);
//        }catch (NumberFormatException ex){
//            errorC2.setText("Pole C2 musi zawierać liczbę typu double!");
//            String znak = event.getCharacter();
//            textArea.setText(String.join("",text.split(znak)));
//            textArea.positionCaret(textArea.getLength());
//        }
    }

    public static int indexOfNonDigit(final String str, final int startidx) {
        int ret = -1;
        if (str != null) {
            for (int i = startidx; i < str.length(); i++) {
                // get the first digit..
                if (!Character.isDigit(str.charAt(i))) {
                    ret = i;
                    break;
                }
            }
        }
        return ret;
    }

}

