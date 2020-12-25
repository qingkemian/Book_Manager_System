package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Br;
import main.services.BrServer;
import main.utils.SimpleTools;
import org.omg.PortableInterceptor.INACTIVE;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            else if (((br.getBookstate()).toString()) == "out")
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

    public void bt_findByThisBookID(ActionEvent event) {
        String thisIDText = QThisBookID.getText();

        try {
            int id = Integer.parseInt(thisIDText);
            BrServer brServer = new BrServer();
            Br br = brServer.findBrByThisBookID(id);

            if (br != null) {
                List<Br> brList = new ArrayList<>();
                brList.add(br);
                ObservableList<Br> brObservableList = FXCollections.observableList(brList);
                brTable.setItems(brObservableList);
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

    public void bt_findByBookID(ActionEvent event) {
        String IDText = QBookID.getText();

        try {
            int id = Integer.parseInt(IDText);
            BrServer brServer = new BrServer();

            List<Br> brList = null;

            if (checkIn.isSelected())
                brList = brServer.findInBrByReaderID(id);
            else
                brList = brServer.findBrByBookID(id);

            if (brList != null && brList.size() != 0) {
                ObservableList<Br> brObservableList = FXCollections.observableList(brList);
                brTable.setItems(brObservableList);
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

    public void bt_findByReaderID(ActionEvent event) {
        String readerIDText = QReaderID.getText();

        try {
            int id = Integer.parseInt(readerIDText);
            BrServer brServer = new BrServer();
            List<Br> brList = brServer.findBrByReaderID(id);

            if (brList != null && brList.size() != 0) {
                ObservableList<Br> brObservableList = FXCollections.observableList(brList);
                brTable.setItems(brObservableList);
            } else {
                SimpleTools simpleTools = new SimpleTools();
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "Remind", "warning", "无符合条件的结果.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            SimpleTools simpleTools = new SimpleTools();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "有误");
        }
    }

    public void bt_borrowBook(ActionEvent event) {
        String thisIDText = SThisBID.getText();
        String bookIDText = SBookID.getText();

        Br.state stateText = null;
        if (rbIn.isSelected())
            stateText = Br.state.in;
        else if (rbOut.isSelected())
            stateText = Br.state.out;

        String brTimeText = SBookBRTime.getText();
        String readerIDText = SReadID.getText();
        String outTimeText = SOutTime.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int thisid = Integer.parseInt(thisIDText);
            int bookid = Integer.parseInt(bookIDText);
            int brtime = Integer.parseInt(brTimeText);
            int readerid = 0;
            if(readerIDText != null && readerIDText != "" && readerIDText.length() != 0)
                readerid = Integer.parseInt(readerIDText);

            Timestamp time = null;
            if(outTimeText != null && outTimeText != "" && outTimeText.length() != 0)
                time = Timestamp.valueOf(outTimeText);

            Br newBr = new Br();
            newBr.setThisbookID(thisid);
            newBr.setBookID(bookid);
            newBr.setBookstate(stateText);
            newBr.setBookBRTime(brtime);
            newBr.setReaderID(readerid);
            newBr.setOutTime(time);

            BrServer brServer = new BrServer();

            boolean flag = brServer.borrowBook(newBr);

            if (flag) {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "借书成功");

                List<Br> brList = brServer.getAllBr();
                ObservableList<Br> brObservableList = FXCollections.observableList(brList);

                brTable.setItems(brObservableList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "借书失败");
            }
        }catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入有误");
        }catch (Exception e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "还书失败");
        }
    }

    public void bt_returnBook(ActionEvent event) {
        String thisIDText = SThisBID.getText();
        String bookIDText = SBookID.getText();

        Br.state stateText = null;
        if (rbIn.isSelected())
            stateText = Br.state.in;
        else if (rbOut.isSelected())
            stateText = Br.state.out;

        String brTimeText = SBookBRTime.getText();
        String readerIDText = SReadID.getText();
        String outTimeText = SOutTime.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int thisid = Integer.parseInt(thisIDText);
            int bookid = Integer.parseInt(bookIDText);
            int brtime = Integer.parseInt(brTimeText);
            int readerid = 0;
            if(readerIDText != null && readerIDText != "" && readerIDText.length() != 0)
                readerid = Integer.parseInt(readerIDText);

            Timestamp time = null;
            if(outTimeText != null && outTimeText != "" && outTimeText.length() != 0)
                time = Timestamp.valueOf(outTimeText);

            Br newBr = new Br();
            newBr.setThisbookID(thisid);
            newBr.setBookID(bookid);
            newBr.setBookstate(stateText);
            newBr.setBookBRTime(brtime);
            newBr.setReaderID(readerid);
            newBr.setOutTime(time);

            BrServer brServer = new BrServer();

            boolean flag = brServer.returnBook(newBr);

            if (flag) {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "还书成功");

                List<Br> brList = brServer.getAllBr();
                ObservableList<Br> brObservableList = FXCollections.observableList(brList);

                brTable.setItems(brObservableList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "还书失败");
            }
        }catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入有误");
        }catch (Exception e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "还书失败");
        }
    }

    public void bt_createBook(ActionEvent event) {
        String thisIDText = SThisBID.getText();
        String bookIDText = SBookID.getText();

        Br.state stateText = null;
        if (rbIn.isSelected())
            stateText = Br.state.in;
        else if (rbOut.isSelected())
            stateText = Br.state.out;

        String brTimeText = SBookBRTime.getText();
        String readerIDText = SReadID.getText();
        String outTimeText = SOutTime.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int thisid = Integer.parseInt(thisIDText);
            int bookid = Integer.parseInt(bookIDText);
            int brtime = Integer.parseInt(brTimeText);

            int readerid = 0;
            if(readerIDText != null && readerIDText != "" && readerIDText.length() != 0)
                readerid = Integer.parseInt(readerIDText);

            Timestamp time = null;
            if(outTimeText != null && outTimeText != "" && outTimeText.length() != 0)
                time = Timestamp.valueOf(outTimeText);

            Br newBr = new Br();
            newBr.setThisbookID(thisid);
            newBr.setBookID(bookid);
            newBr.setBookstate(stateText);
            newBr.setBookBRTime(brtime);
            newBr.setReaderID(readerid);
            newBr.setOutTime(time);

            BrServer brServer = new BrServer();

            boolean flag = brServer.createBr(newBr);

            if (flag) {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "添加成功");

                List<Br> brList = brServer.getAllBr();
                ObservableList<Br> brObservableList = FXCollections.observableList(brList);

                brTable.setItems(brObservableList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "添加失败");
            }
        }catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入有误");
        }catch (Exception e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入时间有误");
        }
    }

    public void bt_modifyBook(ActionEvent event) {
        String thisIDText = SThisBID.getText();
        String bookIDText = SBookID.getText();

        Br.state stateText = null;
        if (rbIn.isSelected())
            stateText = Br.state.in;
        else if (rbOut.isSelected())
            stateText = Br.state.out;

        String brTimeText = SBookBRTime.getText();
        String readerIDText = SReadID.getText();
        String outTimeText = SOutTime.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int thisid = Integer.parseInt(thisIDText);
            int bookid = Integer.parseInt(bookIDText);
            int brtime = Integer.parseInt(brTimeText);
            int readerid = 0;
            if(readerIDText != null && readerIDText != "" && readerIDText.length() != 0)
                readerid = Integer.parseInt(readerIDText);

            Timestamp time = null;
            if(outTimeText != null && outTimeText != "" && outTimeText.length() != 0)
                time = Timestamp.valueOf(outTimeText);

            Br newBr = new Br();
            newBr.setThisbookID(thisid);
            newBr.setBookID(bookid);
            newBr.setBookstate(stateText);
            newBr.setBookBRTime(brtime);
            newBr.setReaderID(readerid);
            newBr.setOutTime(time);

            BrServer brServer = new BrServer();

            boolean flag = brServer.modifyBr(newBr);

            if (flag) {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "修改成功");

                List<Br> brList = brServer.getAllBr();
                ObservableList<Br> brObservableList = FXCollections.observableList(brList);

                brTable.setItems(brObservableList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "修改失败");
            }
        }catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入有误");
        }catch (Exception e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入时间有误");
        }
    }

    public void bt_deleteBr(ActionEvent event) {
        String thisidText = SThisBID.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int id = Integer.parseInt(thisidText);

            BrServer brServer = new BrServer();

            boolean flag = brServer.deleteBr(id);

            if (flag) {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "删除成功");

                List<Br> brList = brServer.getAllBr();
                ObservableList<Br> brObservableList = FXCollections.observableList(brList);

                brTable.setItems(brObservableList);
            }  else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "删除失败");
            }
        }  catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入有误");
        }
    }
}
