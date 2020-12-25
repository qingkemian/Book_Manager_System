package main.model;

import java.sql.Timestamp;

public class Br {
    public enum state{
        in,
        out
    }

    private int thisbookID;
    private int bookID;
    private state bookstate;
    private int bookBRTime;
    private int readerID;
    private Timestamp outTime;

    public int getThisbookID() {
        return thisbookID;
    }

    public void setThisbookID(int thisbookID) {
        this.thisbookID = thisbookID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public state getBookstate() {
        return bookstate;
    }

    public void setBookstate(state bookstate) {
        this.bookstate = bookstate;
    }

    public int getBookBRTime() {
        return bookBRTime;
    }

    public void setBookBRTime(int bookBRTime) {
        this.bookBRTime = bookBRTime;
    }

    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }

    public Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }
}
