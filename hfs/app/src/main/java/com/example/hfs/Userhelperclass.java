package com.example.hfs;

public class Userhelperclass {
    String name,email,phone,password,room,status;

    public Userhelperclass() {

    }

    public Userhelperclass(String name, String email, String phone, String password, String room, String status) {
        System.out.println("HELPER "+name+" "+email+" "+password+" "+room+" "+phone+" "+status);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.room = room;
        this.status = status;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getroom() {
        return room;
    }

    public void setroom(String room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
