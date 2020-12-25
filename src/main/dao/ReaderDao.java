package main.dao;

import main.model.Br;
import main.model.Reader;
import main.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ReaderDao {
    // 展示所有
    public List<Reader> getAllReader() throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from reader";
        return runner.query(sql,new BeanListHandler<Reader>(Reader.class));
    }

    // 通过读者id查找
    public Reader getReaderByReaderID(int readerID) throws SQLException
    {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="select * from reader where readerID=?";
        return runner.query(sql,new BeanHandler<Reader>(Reader.class),readerID);
    }

    // 新增读者
    public boolean createReader(Reader reader) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql="insert into reader values(?,?,?,?)";
        int affect = runner.execute(sql,reader.getReaderID(),reader.getReaderName(),reader.getReaderSex().toString(),reader.getReaderMajor());
        return affect>=1?true:false;
    }

    // 修改读者信息
    public boolean modifyReader(Reader reader) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());
        String sql = "update reader set readerName=?,readerSex=?,readerMajor=? where readerID=?";
        int affect = runner.execute(sql,reader.getReaderName(),reader.getReaderSex().toString(),reader.getReaderMajor(),reader.getReaderID());
        return affect>=1?true:false;
    }

    // 删除读者信息
    public boolean deleteReader(int readerID) throws SQLException {
        QueryRunner runner=new QueryRunner(DBUtils.getDataSource());

        String sql = "select * from br where readerID=?";
        List<Br> brList = runner.query(sql,new BeanListHandler<Br>(Br.class),readerID);

        if (brList == null || brList.size() == 0) {
            // 将改用户从u表删除
            sql = "delete from reader where readerID=?";
            int affect = runner.execute(sql,readerID);
            return affect>=1?true:false;
        }
        return false;
    }
}
