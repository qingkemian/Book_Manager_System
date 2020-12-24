package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;

public class BrController {
    @FXML
    private TextField QThisBookID;

    @FXML
    private Button btQThisBook;

    @FXML
    private TextField QBookID;

    @FXML
    private CheckBox checkIn;

    @FXML
    private Button btQBook;

    @FXML
    private TextField QReaderID;

    @FXML
    private Button btQReader;

    @FXML
    private Button btAll;

    @FXML
    private TableView<?> brTable;

    @FXML
    private TableColumn<?, ?> thisBookID;

    @FXML
    private TableColumn<?, ?> bookID;

    @FXML
    private TableColumn<?, ?> bookState;

    @FXML
    private TableColumn<?, ?> bookBRTime;

    @FXML
    private TableColumn<?, ?> readerID;

    @FXML
    private TableColumn<?, ?> outTime;

    @FXML
    private TextField SThisBID;

    @FXML
    private TextField SBookID;

    @FXML
    private RadioButton rbIn;

    @FXML
    private ToggleGroup stage;

    @FXML
    private RadioButton rbOut;

    @FXML
    private TextField SBookBRTime;

    @FXML
    private TextField SReadID;

    @FXML
    private TextField SOutTime;

    @FXML
    private Button btAdd;

    @FXML
    private Button btM;

    @FXML
    private Button btD;
}
