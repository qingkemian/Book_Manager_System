package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Book;
import main.services.BookServer;

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
}
