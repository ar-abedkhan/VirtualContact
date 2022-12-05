package com.abedkhan.virtualcontact;

public class ContactModel {
    String user_name,user_mail,user_pass,user_number,user_img,user_location,user_relation,user_message,user_id;



    public ContactModel(String user_name, String user_mail, String user_pass, String user_number, String user_img, String user_location, String user_relation, String user_message, String user_id) {
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.user_pass = user_pass;
        this.user_number = user_number;
        this.user_img = user_img;
        this.user_location = user_location;
        this.user_relation = user_relation;
        this.user_message = user_message;
        this.user_id = user_id;
    }

    public ContactModel(){

    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public String getUser_number() {
        return user_number;
    }

    public String getUser_img() {
        return user_img;
    }

    public String getUser_location() {
        return user_location;
    }

    public String getUser_relation() {
        return user_relation;
    }

    public String getUser_message() {
        return user_message;
    }

    public String getUser_id() {
        return user_id;
    }
}
