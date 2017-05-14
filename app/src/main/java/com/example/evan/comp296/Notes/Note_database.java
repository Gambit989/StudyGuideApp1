package com.example.evan.comp296.Notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.evan.comp296.Data;
import com.example.evan.comp296.user_info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Evan on 5/10/17.
 */

public class Note_database extends SQLiteOpenHelper {


    // Database Info
    private static final String DATABASE_NAME = "comp296";
    private static final int DATABASE_VERSION = 9;

    // Table Names
    private static final String TABLE_INFORMATION = "user_info";
    private static final String TABLE_NOTES = "notes";
    private static final String TABLE_COUNTER = "counter";


    // Android Table Columns
    private static final String USERID1 = "id";
    private static final String USERID2 = "id";
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String SCHOOL = "school";
    //private static final String MAJOR = "major";
    private static final String COUNTER = "counter";


    private static final String NOTE_TITLE = "title";
    private static final String NOTE_TEXT = "text";

    Context c;


    public Note_database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_INFORMATION_TABLE = "CREATE TABLE " + TABLE_INFORMATION +
                "(" + USERID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EMAIL + " TEXT ," + // Define a primary key
                NAME + " TEXT ," +
                SCHOOL + " TEXT " +
                ")";


        String CREATE_NOTE_TABLE = "CREATE TABLE " + TABLE_NOTES +
                "(" + USERID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOTE_TITLE + " TEXT ," + // Define a primary key
                NOTE_TEXT + " TEXT" +
                ")";

        /*

        String CREATE_COUNTER_TABLE = "CREATE TABLE " + TABLE_COUNTER +
                "(" + "user_name" + " TEXT " +   //name of user
                COUNTER + " INTEGER, " +    //counter for notes
                ")";


        */

        db.execSQL(CREATE_INFORMATION_TABLE);
        db.execSQL(CREATE_NOTE_TABLE);
        //db.execSQL(CREATE_COUNTER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFORMATION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTER);
            onCreate(db);
        }

    }


    //ADD
    public void add_row_notes(Note_data note) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //taskCount++;


        //add values
        values.put(NOTE_TITLE, note.getNote_title());
        values.put(NOTE_TEXT, note.getNote_text());

        //insert row into table

        db.insert(TABLE_NOTES, null, values);


        CharSequence hello = note.getNote_title();


        Toast.makeText(c, hello, Toast.LENGTH_LONG).show();

    }


    public void add_row_info(User_SQLite user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //taskCount++;


        //add values
        values.put(USERID1, user.getUser_id());
        values.put(EMAIL, user.getEmail());
        values.put(NAME, user.getName());
        values.put(SCHOOL, user.getSchool());

        //insert row into table

        db.insert(TABLE_INFORMATION, null, values);


        CharSequence hello = user.getName();


        Log.d("TAG", "ADD ROW CALLED id " +user.user_id+ " email "+user.getEmail());
        Toast.makeText(c, "Hello " +user.getName(), Toast.LENGTH_LONG).show();

    }



    public void update_row_info(User_SQLite user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //taskCount++;


        //add values
        values.put(USERID1, user.getUser_id());
        values.put(EMAIL, user.getEmail());
        values.put(NAME, user.getName());
        values.put(SCHOOL, user.getSchool());

        //insert row into table

        db.update(TABLE_INFORMATION, values, "id=1",null);


        CharSequence hello = user.getName();


        //Toast.makeText(c, hello, Toast.LENGTH_LONG).show();

        Log.d("TAG", "**** UPDATE ROW CALLED **** userid "+ user.getUser_id()+ " email "+ user.getEmail() + " name " +
                user.getName()+ "********");

    }








    public void edit_school_row(String school) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SCHOOL, school);


        db.update(TABLE_INFORMATION, values, USERID1 + " = 1",
                null);

        db.close();


    }


    public ArrayList<String> getTitles() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select title from notes", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(NOTE_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }


    public ArrayList<String> getText() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from notes", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(NOTE_TEXT)));
            res.moveToNext();
        }
        return array_list;
    }


    public ArrayList<Data> getText_data() {
        ArrayList<Data> array_list = new ArrayList();

        //hp = new HashMap();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from notes", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(new Data(res.getString(res.getColumnIndex(NOTE_TEXT))));
            res.moveToNext();
        }
        return array_list;
    }


    public String getNote_Text(int position) {

        //String note;

        position = position + 1;


        SQLiteDatabase db = this.getReadableDatabase();

        /*
        Cursor c = db.rawQuery("SELECT * FROM notes WHERE id = '"+position+"'", null);

        note=c.getString(c.getColumnIndex(NOTE_TEXT));

        return note;

        */

        Cursor cursor = null;
        String note = "";
        try {
            cursor = db.rawQuery("SELECT text FROM notes WHERE id=?", new String[]{position + ""});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                note = cursor.getString(cursor.getColumnIndex(NOTE_TEXT));
            }
            return note;
        } finally {
            cursor.close();
        }


    }



    public String getNote_Text(String title) {

        SQLiteDatabase db = this.getReadableDatabase();



        Cursor cursor = null;
        String note = "";
        try {
            cursor = db.rawQuery("SELECT text FROM notes WHERE title=?", new String[]{title});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                note = cursor.getString(cursor.getColumnIndex(NOTE_TEXT));
            }
            return note;
        } finally {
            cursor.close();
        }


    }








    public void delete_row(int position) {

        position = position + 1;


        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        String note = "";
        try {
            cursor = db.rawQuery("SELECT * FROM notes WHERE id=?", new String[]{position + ""});
            if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            db.delete("notes", "id=?", new String[]{position + ""});
             }

        } finally {
            cursor.close();
        }

    }



    public String getEmail(int position) {

        //String note;

        //position = position + 1;


        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        String email = "";
        try {
            cursor = db.rawQuery("SELECT * FROM user_info WHERE id=?", new String[]{position + ""});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                email = cursor.getString(cursor.getColumnIndex(EMAIL));
            }
            return email;
        } finally {
            cursor.close();
        }


    }



    public String getEmail() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        String email = "";
        try {
            cursor = db.rawQuery("SELECT email FROM user_info WHERE id= 1", null);
            if (cursor.getCount() > 0) {
                 cursor.moveToFirst();
                //email = cursor.getString(0);
                email= cursor.getString(cursor.getColumnIndex(EMAIL));
            }
            return email;
        } finally {
            cursor.close();
        }
    }



    public String getEmail1(int i) {

        String email="";

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = { "email" };
        String selection = "id" + " =?";
        String[] selectionArgs = { i+ "" };
        String limit = "1";

        Cursor cursor = db.query(TABLE_INFORMATION, columns, selection, selectionArgs, null, null, null, limit);

        if (cursor.getCount() > 0) {

        email= cursor.getString(0);

        }else {
            email="test";
        }

        cursor.close();

        return email;
    }




    public ArrayList<String> get_user_info() {

        //String note;

        ArrayList<String> info= new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();

        String name="", email="", school="", major="";


        Cursor cursor = null;
        //String email = "";
        try {
            cursor = db.rawQuery("SELECT name, email, school FROM user_info WHERE id= 1", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                name = (cursor.getString(0));
                email=(cursor.getString(1));
                school=(cursor.getString(2));

                info.add(0,name);
                info.add(1,email);
                info.add(2,school);


                Log.d("TAG", "****** name "+name+ " email "+email+ " school "+school+ "**********");

            }
            return info;
        } finally {
            cursor.close();
        }


    }



    public ArrayList<String> get_user_info1() {

        //String note;

        ArrayList<String> info= new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();

        String name="", email="", school="";


        Cursor cursor = null;
        //String email = "";
        try {
            cursor = db.rawQuery("SELECT * FROM user_info WHERE id= 1", null);
             if (cursor.moveToFirst()) {
                do {
                    name = (cursor.getString(0));
                    email=(cursor.getString(1));
                    school=(cursor.getString(2));

                    info.add(0,name);
                    info.add(1,email);
                    info.add(2,school);
                } while (cursor.moveToNext());
            }
                Log.d("TAG", "****** name "+name+ " email "+email+ " school "+school+ "**********");


            return info;
        } finally {
            cursor.close();
        }


    }





    public boolean Exists(String searchItem) {


        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = { "email" };
        String selection = "email" + " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";

        Cursor cursor = db.query(TABLE_INFORMATION, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }


    public boolean ID_Exists(int searchItem) {


        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = { "id" };
        String selection = "id" + " =?";
        String[] selectionArgs = { searchItem+"" };
        String limit = "1";

        Cursor cursor = db.query(TABLE_INFORMATION, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }







    public void delete_row(String title) {


        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        //String note = "";
        try {
            cursor = db.rawQuery("SELECT * FROM notes WHERE title=?", new String[]{title});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                db.delete("notes", "title=?", new String[]{title});
                }

            }finally{
                cursor.close();
            }

        }






    }


