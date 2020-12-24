package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

public class ReaderController {
    @FXML
    private TextField QReaderID;

    @FXML
    private Button btQ;

    @FXML
    private Button btAll;

    @FXML
    private TableView<?> readerTable;

    @FXML
    private TableColumn<?, ?> readerID;

    @FXML
    private TableColumn<?, ?> readerName;

    @FXML
    private TableColumn<?, ?> readerSex;

    @FXML
    private TableColumn<?, ?> readerMajor;

    @FXML
    private TextField SReadID;

    @FXML
    private TextField SReaderName;

    @FXML
    private TextField SReaderSex;

    @FXML
    private TextField SReaderMajor;

    @FXML
    private Button btAdd;

    @FXML
    private Button btM;

    @FXML
    private Button btD;
}
