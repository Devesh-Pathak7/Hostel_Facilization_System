package com.example.hfs;

public class noticeuserhelperclass {
    String filleddate,notice;

    public noticeuserhelperclass() {
    }

    public noticeuserhelperclass(String filleddate, String notice) {
        this.filleddate = filleddate;
        this.notice = notice;
    }

    public String getFilleddate() {
        return filleddate;
    }

    public void setFilleddate(String filleddate) {
        this.filleddate = filleddate;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
