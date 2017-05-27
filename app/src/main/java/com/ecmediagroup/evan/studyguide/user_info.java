package com.ecmediagroup.evan.studyguide;

/**
 * Created by Evan on 11/18/16.
 */

public class user_info {


    //ATTRIBUTES

    String ID_email;
    String FName;
    String LName;
    String phone;
    String password;



    public user_info (String id, String f, String l, String phone2, String p){
        ID_email= id;
        FName = f;
        LName = l;
        phone = phone2;
        password = p;


    }


    public String getID_email() {
        return ID_email;
    }

    public String getFName() {
        return FName;
    }

    public String getLName() {
        return LName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getALL( ){


        return  getID_email()+ getFName() + getLName()+ getPhone()+ getPassword();
    }










}
