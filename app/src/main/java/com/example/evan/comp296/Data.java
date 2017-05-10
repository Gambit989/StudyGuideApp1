package com.example.evan.comp296;

/**
 * Created by Evan on 5/6/17.
 */

public class Data {


        public int imageId;
        public String java;


        public String note_title;
        public String note_text;

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

    }
