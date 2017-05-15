package com.example.evan.comp296.Notes;

/**
 * Created by Evan on 5/10/17.
 */

public class User_SQLite {




    public User_SQLite() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String user_id;
    public String email;
    public String name;
    public String school;


    public String user_id_2;
    public String major;
    public String picture_url;



    public User_SQLite(String user_id, String email, String name, String school) {

        this.user_id=user_id;
        this.email=email;
        this.name=name;
        this.school=school;

    }


    public User_SQLite(String user_id_2, String picture_url) {

        this.user_id_2=user_id_2;
        this.picture_url=picture_url;

    }





    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getMajor() {
        return major;
    }

    public String getUser_id_2() {
        return user_id_2;
    }

    public String getPicture_url() {
        return picture_url;
    }
}
