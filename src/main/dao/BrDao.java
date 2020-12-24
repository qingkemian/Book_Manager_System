package main.dao;

import main.model.Br;
import main.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
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

    public boolean createBr(Br br) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="insert into br values(?,?,?,?,?,?)";
        int affect = runner.execute(sql,br.getThisbookID(),br.getBookID(),br.getBookstate(),br.getBookBRTime(),br.getReaderID(),br.getOutTime());
        return affect>=1?true:false;
    }


}
