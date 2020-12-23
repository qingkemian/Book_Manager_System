package main.dao;

import main.model.Admin;
import main.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminDao {
    public boolean AdminLogin(Admin admin) throws SQLException {
        QueryRunner runner= new QueryRunner(DBUtils.getDataSource());
        String sql="select * from admin where adminID=? and adminPassword=?";
        Admin temp=runner.query(sql, new BeanHandler<Admin>(Admin.class),admin.getAdminID(),admin.getAdminPassword());
        if(temp!=null)
            return true;
        else
            return false;
    }
}
