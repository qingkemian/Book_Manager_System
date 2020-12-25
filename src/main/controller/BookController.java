package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Book;
import main.services.BookServer;
import main.utils.SimpleTools;

import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

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
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, Integer> bookID;

    @FXML
    private TableColumn<Book, String> bookName;

    @FXML
    private TableColumn<Book, String> bookCategory;

    @FXML
    private TableColumn<Book, Integer> bookNum;

    @FXML
    private TableColumn<Book, String> bookInfo;

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

    public void initialize() {
        bookID.setCellValueFactory(new PropertyValueFactory<Book,Integer>("bookID"));
        bookName.setCellValueFactory(new PropertyValueFactory<Book,String>("bookName"));
        bookCategory.setCellValueFactory(new PropertyValueFactory<Book,String>("bookCategory"));
        bookNum.setCellValueFactory(new PropertyValueFactory<Book,Integer>("bookNum"));
        bookInfo.setCellValueFactory(new PropertyValueFactory<Book,String>("bookInfo"));

        BookServer bookServer = new BookServer();
        List<Book> bookList = bookServer.getAllBook();
        ObservableList<Book> bookObservableList = FXCollections.observableList(bookList);

        bookTable.setItems(bookObservableList);

        bookTable.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> showDetails(newValue));
    }

    public void showDetails(Book book) {
        if (book == null)
            return;
        else {
            SBookID.setText(String.valueOf(book.getBookID()));
            SBookName.setText(book.getBookName());
            SBookCategory.setText(book.getBookCategory());
            SBookNum.setText(String.valueOf(book.getBookNum()));
            SBookInfo.setText(book.getBookInfo());
        }
    }

    public void showall(){
        BookServer bookServer = new BookServer();
        List<Book> bookList = bookServer.getAllBook();
        ObservableList<Book> bookObservableList = FXCollections.observableList(bookList);

        bookTable.setItems(bookObservableList);
    }

    public void bt_findByBookID(ActionEvent event) {
        String idText = QBookID.getText();

        try {
            int id = Integer.parseInt(idText);
            BookServer bookServer = new BookServer();
            Book book = bookServer.findBookByBookID(id);

            if(book != null) {
                List<Book> bookList = new ArrayList<>();
                bookList.add(book);
                ObservableList<Book> bookObservableList = FXCollections.observableList(bookList);
                bookTable.setItems(bookObservableList);
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

    public void bt_findByBookName(ActionEvent event) {
        String nameText = QBookName.getText();

        BookServer bookServer = new BookServer();
        List<Book> bookList = bookServer.findBooksByBookName(nameText);

        if(bookList != null && bookList.size() != 0) {
            ObservableList<Book> bookObservableList = FXCollections.observableList(bookList);
            bookTable.setItems(bookObservableList);
        } else {
            SimpleTools simpleTools = new SimpleTools();
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "Remind", "warning", "无符合条件的结果");
        }
    }

    public void bt_createBook(ActionEvent event) {
        String idText = SBookID.getText();
        String nameText = SBookName.getText();
        String categoryText = SBookCategory.getText();
        String numText = SBookNum.getText();
        String infoText = SBookInfo.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int id = Integer.parseInt(idText);
            int num = Integer.parseInt(numText);

            Book myBook = new Book();
            myBook.setBookID(id);
            myBook.setBookName(nameText);
            myBook.setBookCategory(categoryText);
            myBook.setBookNum(num);
            myBook.setBookInfo(infoText);

            BookServer bookServer = new BookServer();

            boolean flag = bookServer.createBook(myBook);

            if (flag) {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "添加成功");

                List<Book> bookList = bookServer.getAllBook();
                ObservableList<Book> bookObservableList = FXCollections.observableList(bookList);

                bookTable.setItems(bookObservableList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "添加失败");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入有误");
        }
    }

    public void bt_modifyBook(ActionEvent event) {
        String idText = SBookID.getText();
        String nameText = SBookName.getText();
        String categoryText = SBookCategory.getText();
        String numText = SBookNum.getText();
        String infoText = SBookInfo.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int id = Integer.parseInt(idText);
            int num = Integer.parseInt(numText);

            Book myBook = new Book();
            myBook.setBookID(id);
            myBook.setBookName(nameText);
            myBook.setBookCategory(categoryText);
            myBook.setBookNum(num);
            myBook.setBookInfo(infoText);

            BookServer bookServer = new BookServer();

            boolean flag = bookServer.modifyBook(myBook);

            if (flag) {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "修改成功");

                List<Book> bookList = bookServer.getAllBook();
                ObservableList<Book> bookObservableList = FXCollections.observableList(bookList);

                bookTable.setItems(bookObservableList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "修改失败");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入有误");
        }
    }

    public void bt_deleteBook(ActionEvent event) {
        String idText = SBookID.getText();

        SimpleTools simpleTools = new SimpleTools();

        try {
            int id = Integer.parseInt(idText);

            BookServer bookServer = new BookServer();

            boolean flag = bookServer.deleteBook(id);

            if (flag) {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "删除成功");

                List<Book> bookList = bookServer.getAllBook();
                ObservableList<Book> bookObservableList = FXCollections.observableList(bookList);

                bookTable.setItems(bookObservableList);
            } else {
                simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "删除失败，书库中还有库存");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            simpleTools.informationDialog(Alert.AlertType.WARNING, "Remind", "warning", "输入有误");
        }
    }
}
