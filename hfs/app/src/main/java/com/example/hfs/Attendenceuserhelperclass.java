package com.example.hfs;

public class Attendenceuserhelperclass {
    String name, phone, hostelno, room, present_status,filleddate;

    public Attendenceuserhelperclass(String name, String phone, String hostelno, String room, String present_status, String filleddate) {
        this.name = name;
        this.phone = phone;
        this.hostelno = hostelno;
        this.room = room;
        this.present_status = present_status;
        this.filleddate = filleddate;
    }

    public Attendenceuserhelperclass() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHostelno() {
        return hostelno;
    }

    public void setHostelno(String hostelno) {
        this.hostelno = hostelno;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getPresent_status() {
        return present_status;
    }

    public void setPresent_status(String present_status) {
        this.present_status = present_status;
    }

    public String getFilleddate() {
        return filleddate;
    }

    public void setFilleddate(String filleddate) {
        this.filleddate = filleddate;
    }
}
