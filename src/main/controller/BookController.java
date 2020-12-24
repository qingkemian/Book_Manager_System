package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class BookController {
    @FXML
    private TextField QBookID;

    @FXML
    private Button btQID;

    @FXML
    private TextField QBookName;

    @FXML
    private Button btQName;

    @FXML
    private Button btAll;

    @FXML
    private TableView<?> bookTable;

    @FXML
    private TableColumn<?, ?> bookID;

    @FXML
    private TableColumn<?, ?> bookName;

    @FXML
    private TableColumn<?, ?> bookCategory;

    @FXML
    private TableColumn<?, ?> bookNum;

    @FXML
    private TableColumn<?, ?> bookInfo;

    @FXML
    private TextField SBookID;

    @FXML
    private TextField SBookName;

    @FXML
    private TextField SBookCategory;

    @FXML
    private TextField SBookNum;

    @FXML
    private TextArea SBookInfo;

    @FXML
    private Button btAdd;

    @FXML
    private Button btM;

    @FXML
    private Button btD;
}
