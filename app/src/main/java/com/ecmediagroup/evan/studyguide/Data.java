package com.ecmediagroup.evan.studyguide;

import java.util.ArrayList;

/**
 * Created by Evan on 5/6/17.
 */

public class Data {


        public int imageId;
        public String java;


        public String note_title;
        public String note_text;

        public ArrayList<String> titles;

        public Data(int imageId) {
            //this.title = title;
            // this.description = description;
            this.imageId = imageId;
        }


        public Data(String java) {
            this.java=java;
        }


        public Data(String title, String text) {
        this.note_title=title;
            this.note_text=text;
        }




        public Data(ArrayList <String> e) {

            this.titles=e;

        }

    public int getImageId() {
        return imageId;
    }

    public String getJava() {
        return java;
    }

    public String getNote_title() {
        return note_title;
    }

    public String getNote_text() {
        return note_text;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }
}
