package main.dao;

import main.model.Book;
import main.model.Br;
import main.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BookDao {

    public List<Book> getAllBook() throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from book";
        return runner.query(sql,new BeanListHandler<Book>(Book.class));
    }

    public Book getBookByBookID(int bookID) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from book where bookID=?";
        return runner.query(sql,new BeanHandler<Book>(Book.class),bookID);
    }

    public List<Book> getBookByBookName(String bookName) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from book where bookName=?";
        return runner.query(sql,new BeanListHandler<Book>(Book.class),bookName);
    }

    public boolean creatBook(Book book) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="insert into book values(?,?,?,?,?)";
        int affect = runner.execute(sql,book.getBookID(),book.getBookName(),book.getBookCategory(),book.getBookNum(),book.getBookInfo());
        return affect>=1?true:false;
    }

    public boolean modifyBook(Book book) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql = "update book set bookName=?,bookCategory=?,bookNum=?,bookInfo=? where bookID=?";
        int affect = runner.execute(sql,book.getBookName(),book.getBookCategory(),book.getBookNum(),book.getBookInfo(),book.getBookID());
        return affect>=1?true:false;
    }

    public boolean deleteBook(int bookID) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());

        String sql = "select * from br where bookID=?";
        List<Br> brList = runner.query(sql,new BeanListHandler<Br>(Br.class),bookID);

        if (brList == null || brList.size() == 0) {
            sql = "delete from book where bookID=?";
            int affect = runner.execute(sql,bookID);
            return affect>=1?true:false;
        }
        return false;
    }
}
