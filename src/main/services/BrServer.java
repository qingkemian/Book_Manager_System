package main.services;

import main.dao.BrDao;
import main.model.Br;

import java.sql.SQLException;
import java.util.List;

public class BrServer {
    private BrDao brDao = new BrDao();

    public List<Br> getAllBr() throws SQLException {
        try {
            return brDao.getAllBr();
        }  catch (SQLException e) {
            System.out.println("getAllBr Error:"+e);
            return null;
        }
    }

    public Br getBrByThisBookID(int thisbookID) {
        try {
            return brDao.getBrByThisBookID(thisbookID);
        }  catch (SQLException e) {
            System.out.println("getBrByThisBookID Error:"+e);
            return null;
        }
    }

    public List<Br> getBrByBookID(int bookID) {
        try {
            return brDao.getBrByBookID(bookID);
        }  catch (SQLException e) {
            System.out.println("getBrByBookID Error:"+e);
            return null;
        }
    }

    public List<Br> getBrByReaderID(int readerID){
        try {
            return brDao.getBrByReaderID(readerID);
        }  catch (SQLException e) {
            System.out.println("getBrByReaderID Error:"+e);
            return null;
        }
    }

    public boolean borrowBook(Br br){
        try {
            return brDao.borrowBook(br);
        }  catch (SQLException e) {
            System.out.println("borrowBook Error:"+e);
            return false;
        }
    }

    public boolean returnBook(Br br) {
        try {
            return brDao.returnBook(br);
        }  catch (SQLException e) {
            System.out.println("returnBook Error:"+e);
            return false;
        }
    }

    public boolean createBr(Br br) {
        try {
            return brDao.createBr(br);
        }  catch (SQLException e) {
            System.out.println("createBr Error:"+e);
            return false;
        }
    }

    public boolean modifyBr(Br br) {
        try {
            return brDao.modifyBr(br);
        }  catch (SQLException e) {
            System.out.println("modifyBr Error:"+e);
            return false;
        }
    }

    public boolean deleteBr(int thisbookID) {
        try {
            return brDao.deleteBr(thisbookID);
        }  catch (SQLException e) {
            System.out.println("deleteBr Error:"+e);
            return false;
        }
    }
}
