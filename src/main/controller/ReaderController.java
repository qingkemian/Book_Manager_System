package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Reader;
import main.services.ReaderServer;

import java.util.List;

public class ReaderController {
    @FXML
    private TextField QReaderID;

    @FXML
    private Button btQ;

    @FXML
    private Button btAll;

    @FXML
    private TableView<Reader> readerTable;

    @FXML
    private TableColumn<Reader, Integer> readerID;

    @FXML
    private TableColumn<Reader, String> readerName;

    @FXML
    private TableColumn<Reader, Reader.Sex> readerSex;

    @FXML
    private TableColumn<Reader, String> readerMajor;

    @FXML
    private TextField SReadID;

    @FXML
    private TextField SReaderName;


    @FXML
    private RadioButton rbmale;

    @FXML
    private ToggleGroup sex;

    @FXML
    private RadioButton rbfemale;

    @FXML
    private TextField SReaderMajor;

    @FXML
    private Button btAdd;

    @FXML
    private Button btM;

    @FXML
    private Button btD;

    public void initialize() {
        readerID.setCellValueFactory(new PropertyValueFactory<Reader,Integer>("readerID"));
        readerName.setCellValueFactory(new PropertyValueFactory<Reader,String>("readerName"));
        readerSex.setCellValueFactory(new PropertyValueFactory<Reader,Reader.Sex>("readerSex"));
        readerMajor.setCellValueFactory(new PropertyValueFactory<Reader,String>("readerMajor"));

        ReaderServer readerServer = new ReaderServer();
        List<Reader> readerList = readerServer.getAllReader();
        ObservableList<Reader> readerObservableList = FXCollections.observableList(readerList);

        readerTable.setItems(readerObservableList);

        readerTable.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> showDetails(newValue));
    }

    public void showDetails(Reader reader) {
        if (reader == null)
            return;
        else {
            SReadID.setText(String.valueOf(reader.getReaderID()));
            SReaderName.setText(reader.getReaderName());
            SReaderMajor.setText(reader.getReaderMajor());

            if (((reader.getReaderSex()).toString()).equals("male")) {
                rbmale.setSelected(true);
            } else {
                rbfemale.setSelected(true);
            }
        }
    }
}
