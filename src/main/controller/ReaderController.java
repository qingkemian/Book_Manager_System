package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Reader;
import main.services.ReaderServer;
import main.utils.SimpleTools;

import java.util.ArrayList;
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

    public void showall(ActionEvent event) {
        ReaderServer readerServer = new ReaderServer();
        List<Reader> readerList = readerServer.getAllReader();
        ObservableList<Reader> readerObservableList = FXCollections.observableList(readerList);

        readerTable.setItems(readerObservableList);
    }

    public void bt_findByReaderID(ActionEvent event) {
        String readerID = QReaderID.getText();

        try {
            int id = Integer.parseInt(readerID);
            ReaderServer readerServer = new ReaderServer();
            Reader reader = readerServer.findReadByReaderID(id);

            if (reader != null) {
                List<Reader> readerList = new ArrayList<>();
                readerList.add(reader);
                ObservableList<Reader> readerObservableList = FXCollections.observableList(readerList);
                readerTable.setItems(readerObservableList);
            } else {
                SimpleTools simpleTools = new SimpleTools();
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "Remind", "warning", "无符合条件的结果");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            SimpleTools simpleTools = new SimpleTools();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "有误");
        }
    }

    public void bt_createReader(ActionEvent event) {
        String idText = SReadID.getText();
        String nameText = SReaderName.getText();
        Reader.Sex sexText = null;
        if (rbmale.isSelected())
            sexText = Reader.Sex.male;
        else if (rbfemale.isSelected())
            sexText = Reader.Sex.female;
        String majorText = SReaderMajor.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int id = Integer.parseInt(idText);

            Reader myReader = new Reader();
            myReader.setReaderID(id);
            myReader.setReaderName(nameText);
            myReader.setReaderSex(sexText);
            myReader.setReaderMajor(majorText);

            ReaderServer readerServer = new ReaderServer();

            boolean falg = readerServer.createReader(myReader);

            if (falg) {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "添加成功");

                List<Reader> readerList = readerServer.getAllReader();
                ObservableList<Reader> readerObservableList = FXCollections.observableList(readerList);

                readerTable.setItems(readerObservableList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "添加失败");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入有误");
        }
    }

    public void bt_modifyReader(ActionEvent event) {
        String idText = SReadID.getText();
        String nameText = SReaderName.getText();
        Reader.Sex sexText = null;
        if (rbmale.isSelected())
            sexText = Reader.Sex.male;
        else if (rbfemale.isSelected())
            sexText = Reader.Sex.female;
        String majorText = SReaderMajor.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int id = Integer.parseInt(idText);

            Reader myReader = new Reader();
            myReader.setReaderID(id);
            myReader.setReaderName(nameText);
            myReader.setReaderSex(sexText);
            myReader.setReaderMajor(majorText);

            ReaderServer readerServer = new ReaderServer();

            boolean falg = readerServer.modifyReader(myReader);

            if (falg) {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "修改成功");

                List<Reader> readerList = readerServer.getAllReader();
                ObservableList<Reader> readerObservableList = FXCollections.observableList(readerList);

                readerTable.setItems(readerObservableList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "修改失败");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入有误");
        }
    }

    public void bt_deleteReader(ActionEvent event) {
        String idText = SReadID.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int id = Integer.parseInt(idText);

            ReaderServer readerServer = new ReaderServer();

            boolean falg = readerServer.deleteReader(id);

            if (falg) {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "删除成功");

                List<Reader> readerList = readerServer.getAllReader();
                ObservableList<Reader> readerObservableList = FXCollections.observableList(readerList);

                readerTable.setItems(readerObservableList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "删除失败");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入有误");
        }
    }
}
