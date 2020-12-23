package main.model;

public class Reader {
    public enum Sex{
        male,
        female
    }

    private int readerID;
    private String readerName;
    private Sex readerSex;
    private String readerMajor;

    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public Sex getReaderSex() {
        return readerSex;
    }

    public void setReaderSex(Sex readerSex) {
        this.readerSex = readerSex;
    }

    public String getReaderMajor() {
        return readerMajor;
    }

    public void setReaderMajor(String readerMajor) {
        this.readerMajor = readerMajor;
    }
}
