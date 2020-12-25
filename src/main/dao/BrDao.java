package main.dao;

import main.model.Book;
import main.model.Br;
import main.services.BookServer;
import main.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class BrDao {

    public List<Br> getAllBr() throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from br";
        return runner.query(sql,new BeanListHandler<Br>(Br.class));
    }

    public Br getBrByThisBookID(int thisbookID) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from br where thisbookID=?";
        return runner.query(sql,new BeanHandler<Br>(Br.class),thisbookID);
    }

    public List<Br> getBrByBookID(int bookID) throws  SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from br where bookID=?";
        return runner.query(sql,new BeanListHandler<Br>(Br.class),bookID);
    }

    public List<Br> getBrByReaderID(int readerID) throws  SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from br where readerID=?";
        return runner.query(sql,new BeanListHandler<Br>(Br.class),readerID);
    }

    public boolean borrowBook(Br br) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql = "update br set bookstate=?,readerID=?,outTime=? where thisbookID=?";
        int reader = br.getReaderID();
        Timestamp d = new Timestamp(System.currentTimeMillis());

        int affect = runner.execute(sql,Br.state.out,reader,d,br.getThisbookID());
        return affect>=1?true:false;
    }

    public boolean returnBook(Br br) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql = "update br set bookstate=?,bookBRTime=?,readerID=?,outTime=? where thisbookID=?";
        int reader = br.getReaderID();
        int brtime = br.getBookBRTime();
        brtime++;
        Timestamp d = new Timestamp(System.currentTimeMillis());

        int affect = runner.execute(sql,Br.state.in,brtime,null,null,br.getThisbookID());
        return affect>=1?true:false;
    }

    public boolean createBr(Br br) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="insert into br values(?,?,?,?,?,?)";
        int affect = runner.execute(sql,br.getThisbookID(),br.getBookID(),br.getBookstate(),br.getBookBRTime(),br.getReaderID(),br.getOutTime());

        BookServer bookServer = new BookServer();
        Book book = bookServer.findBookByBookID(br.getBookID());
        int bookNum = book.getBookNum();
        bookNum += 1;
        int bookID = br.getBookID();

        if (affect >= 1) {
            sql = "update book set bookNum=? where bookID=?";
            affect = runner.execute(sql,bookNum,bookID);
        }
        return affect>=1?true:false;
    }

    public boolean modifyBr(Br br) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql = "update br set bookID=?,bookstate=?,bookBRTime=?,readerID=?,outTime=? where thisbookID=?";
        int affect = runner.execute(sql,br.getBookID(),br.getBookstate(),br.getBookBRTime(),br.getReaderID(),br.getOutTime(),br.getThisbookID());
        return affect>=1?true:false;
    }

    public boolean deleteBr(int thisbookID) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="delete from br where thisbookID=?";
        int affect = runner.execute(sql,thisbookID);

        sql="select * from br where thisbookID=?";
        Br br = runner.query(sql,new BeanHandler<Br>(Br.class),thisbookID);

        BookServer bookServer = new BookServer();
        Book book = bookServer.findBookByBookID(br.getBookID());
        int bookNum = book.getBookNum();
        bookNum -= 1;
        int bookID = br.getBookID();

        if (affect >= 1) {
            sql = "update book set bookNum=? where bookID=?";
            affect = runner.execute(sql,bookNum,bookID);
        }
        return affect>=1?true:false;
    }
}
