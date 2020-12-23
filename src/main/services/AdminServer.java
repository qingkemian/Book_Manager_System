package main.services;

import main.dao.AdminDao;
import main.model.Admin;

import java.sql.SQLException;

public class AdminServer {
    private AdminDao adminDao = new AdminDao();

    public boolean adminLogin(String id,String password)
    {
        Admin admin = new Admin();
        admin.setAdminID(id);
        admin.setAdminPassword(password);
        try {
            boolean result = adminDao.AdminLogin(admin);
            return result;
        } catch (SQLException e) {
            System.out.println("AdminServer Error:"+e);
            return false;
        }
    }
}
