package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Br;
import main.services.BrServer;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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
    private TableView<Br> brTable;

    @FXML
    private TableColumn<Br,Integer > thisBookID;

    @FXML
    private TableColumn<Br, Integer> bookID;

    @FXML
    private TableColumn<Br, Br.state> bookState;

    @FXML
    private TableColumn<Br, Integer> bookBRTime;

    @FXML
    private TableColumn<Br, Integer> readerID;

    @FXML
    private TableColumn<Br, Timestamp> outTime;

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
    private Button btOut;

    @FXML
    private Button btIn;

    @FXML
    private Button btAdd;

    @FXML
    private Button btM;

    @FXML
    private Button btD;

    public void initialize() {
        thisBookID.setCellValueFactory(new PropertyValueFactory<Br,Integer>("thisbookID"));
        bookID.setCellValueFactory(new PropertyValueFactory<Br,Integer>("bookID"));
        bookState.setCellValueFactory(new PropertyValueFactory<Br,Br.state>("bookstate"));
        bookBRTime.setCellValueFactory(new PropertyValueFactory<Br,Integer>("bookBRTime"));
        readerID.setCellValueFactory(new PropertyValueFactory<Br,Integer>("readerID"));
        outTime.setCellValueFactory(new PropertyValueFactory<Br,Timestamp>("outTime"));

        BrServer brServer = new BrServer();
        List<Br> brList = brServer.getAllBr();
        ObservableList<Br> brObservableList = FXCollections.observableList(brList);

        brTable.setItems(brObservableList);

        brTable.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> showDetails(newValue));
    }

    public void showDetails(Br br) {
        if (br == null) {
            return;
        } else {
            SThisBID.setText(String.valueOf(br.getThisbookID()));
            SBookID.setText(String.valueOf(br.getBookID()));
            if (((br.getBookstate()).toString()) == "in")
                rbIn.setSelected(true);
            else
                rbOut.setSelected(true);
            SBookBRTime.setText(String.valueOf(br.getBookBRTime()));

            int id = br.getReaderID();
            String str_id = id + "";
            if ("".equals(str_id)){
                SReadID.setText("");
            } else {
                SReadID.setText(String.valueOf(id));
            }


            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String theTime = "";
            try {
                theTime = sdf.format(br.getOutTime());
            } catch (Exception e) {
                e.printStackTrace();
            }

            SOutTime.setText(theTime);
        }
    }

    public void showall(){
        BrServer brServer = new BrServer();
        List<Br> brList = brServer.getAllBr();
        ObservableList<Br> brObservableList = FXCollections.observableList(brList);

        brTable.setItems(brObservableList);
    }
}
