package com.ecmediagroup.evan.studyguide;

/**
 * Created by Evan on 5/3/17.
 */

public class User {

    public String username;
    public String email;
    public String gender;
    public String birthday;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }


    public User(String username, String email, String birthday, String gender) {
        this.username = username;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
    }

}
