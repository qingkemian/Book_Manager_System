package main.services;

import main.dao.BookDao;
import main.model.Book;

import java.sql.SQLException;
import java.util.List;

public class BookServer {
    private BookDao bookDao = new BookDao();

    public List<Book> getAllBook() {
        try {
            return bookDao.getAllBook();
        } catch (SQLException e) {
            System.out.println("getAllBook Error:" + e);
            return null;
        }
    }

    public Book findBookByBookID(int bookID) {
        try {
            return bookDao.getBookByBookID(bookID);
        }  catch (SQLException e) {
            System.out.println("findBookByBookID Error:" + e);
            return null;
        }
    }

    public List<Book> findBooksByBookName(String bookName) {
        try {
            return bookDao.getBookByBookName(bookName);
        } catch (SQLException e) {
            System.out.println("findBooksByBookName Error:" + e);
            return null;
        }
    }

    public boolean createBook(Book book) {
        try {
            return bookDao.creatBook(book);
        } catch (SQLException e) {
            System.out.println("createBook Error:" + e);
            return false;
        }
    }

    public boolean modifyBook(Book book) {
        try {
            return bookDao.modifyBook(book);
        } catch (SQLException e) {
            System.out.println("modifyBook Error:" + e);
            return false;
        }
    }

    public boolean deleteBook(int bookID) {
        try {
            return bookDao.deleteBook(bookID);
        } catch (SQLException e) {
            System.out.println("deleteBook Error:" + e);
            return false;
        }
    }
}
