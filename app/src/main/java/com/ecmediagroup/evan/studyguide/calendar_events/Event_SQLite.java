package com.ecmediagroup.evan.studyguide.calendar_events;

/**
 * Created by Evan on 5/21/17.
 */

public class Event_SQLite {

    public String user_id;
    public String title;
    public String date;
    public String time;


    public Event_SQLite() {

    }


    public Event_SQLite(String title, String date, String time) {

        this.title=title;
        this.date=date;
        this.time=time;

    }

    public Event_SQLite(String user_id, String title, String date, String time) {

        this.user_id=user_id;
        this.title=title;
        this.date=date;
        this.time=time;

    }


    public String getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
