package main.services;

import main.dao.ReaderDao;
import main.model.Reader;

import java.sql.SQLException;
import java.util.List;

public class ReaderServer {
    private ReaderDao readerDao = new ReaderDao();

    public List<Reader> getAllReader() {
        try {
            return readerDao.getAllReader();
        } catch (SQLException e) {
            System.out.println("getAllReader Error:"+e);
            return null;
        }
    }

    public Reader findReadByReaderID(int readerID) {
        try {
            return readerDao.getReaderByReaderID(readerID);
        }  catch (SQLException e) {
            System.out.println("findReadByReaderID Error:"+e);
            return null;
        }
    }

    public boolean createReader(Reader reader) {
        try {
            return readerDao.createReader(reader);
        } catch (SQLException e) {
            System.out.println("createReader Error:"+e);
            return false;
        }
    }

    public boolean modifyReader(Reader reader) {
        try {
            return readerDao.modifyReader(reader);
        } catch (SQLException e) {
            System.out.println("modifyReader Error:"+e);
            return false;
        }
    }

    public boolean deleteReader(int readerID) {
        try {
            return readerDao.deleteReader(readerID);
        } catch (SQLException e) {
            System.out.println("deleteReader Error:"+e);
            return false;
        }
    }
}
