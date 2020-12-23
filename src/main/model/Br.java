package main.model;

import java.sql.Timestamp;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-23 22:20
 */
public class Br {
    public enum state{
        male,
        female
    }

    private int thisbookID;
    private int bookID;
    private state bookstate;
    private int bookBRTime;
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

    public Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }
}
