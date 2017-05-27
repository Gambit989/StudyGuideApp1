package com.ecmediagroup.evan.studyguide.Notes_main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ecmediagroup.evan.studyguide.Data;
import com.ecmediagroup.evan.studyguide.calendar_events.Event_SQLite;

import java.util.ArrayList;

/**
 * Created by Evan on 5/10/17.
 */

public class Note_database extends SQLiteOpenHelper {


    // Database Info
    private static final String DATABASE_NAME = "comp296";
    private static final int DATABASE_VERSION = 32;

    // Table Names
    private static final String TABLE_INFORMATION = "user_info";
    private static final String TABLE_NOTES = "notes";
    private static final String TABLE_COUNTER = "counter";
    private static final String TABLE_INFORMATION_OTHER = "user_info_other";
    private static final String TABLE_EVENTS= "events";


    // Android Table Columns
    private static final String USERID1 = "id";
    private static final String USERID2 = "id";
    private static final String USERID3 = "id";
    private static final String USERID4 = "id";
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String SCHOOL = "school";
    private static final String PICTURE = "picture_url";
    private static final String MAJOR = "major";
    private static final String COUNTER = "counter";
    private static final String EVENT_TITLE = "event_title";
    private static final String EVENT_DATE = "date";
    private static final String EVENT_TIME = "time";



    private static final String NOTE_TITLE = "title";
    private static final String NOTE_TEXT = "text";

    Context c;

    private static Note_database mInstance = null;

    public static Note_database getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new Note_database(ctx.getApplicationContext());
        }
        return mInstance;
    }



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

        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS +
                "(" + USERID4 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EVENT_TITLE + " TEXT ," +
                EVENT_DATE + " TEXT ," +
                EVENT_TIME+ " TEXT" +
                ")";

        /*

        String CREATE_COUNTER_TABLE = "CREATE TABLE " + TABLE_COUNTER +
                "(" + "user_name" + " TEXT " +   //name of user
                COUNTER + " INTEGER, " +    //counter for notes
                ")";


        */

        String CREATE_OTHER_INFO_TABLE = "CREATE TABLE " + TABLE_INFORMATION_OTHER +
                "(" + USERID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     PICTURE + " TEXT " +
                ")";




        db.execSQL(CREATE_INFORMATION_TABLE);
        db.execSQL(CREATE_NOTE_TABLE);
        db.execSQL(CREATE_OTHER_INFO_TABLE);
        db.execSQL(CREATE_EVENTS_TABLE);
        //db.close();
        //db.execSQL(CREATE_COUNTER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFORMATION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFORMATION_OTHER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
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

        //db.close();

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

        //db.close();

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


        //db.close();
        //Toast.makeText(c, hello, Toast.LENGTH_LONG).show();

        Log.d("TAG", "**** UPDATE ROW CALLED **** userid "+ user.getUser_id()+ " email "+ user.getEmail() + " name " +
                user.getName()+ "********");

    }





    public void add_row_picture(User_SQLite user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //taskCount++;


        //add values
        values.put(USERID2, user.getUser_id_2());
        values.put(PICTURE, user.getPicture_url());

        //insert row into table

        db.insert(TABLE_INFORMATION_OTHER, null, values);


        //CharSequence hello = user.getName();

        //db.close();

        Log.d("TAG", "ADD ROW OTHER CALLED id " +user.getUser_id_2()+ " picture URL "+user.getPicture_url());

        //Toast.makeText(c, "Hello " +user.getName(), Toast.LENGTH_LONG).show();

    }


    public void update_row_picture(User_SQLite user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //taskCount++;


        //add values
        values.put(USERID3, user.getUser_id_2());
        values.put(PICTURE, user.getPicture_url());

        //insert row into table

        db.update(TABLE_INFORMATION_OTHER, values, "id=1",null);


        CharSequence hello = user.getName();


        //db.close();
        //Toast.makeText(c, hello, Toast.LENGTH_LONG).show();

        Log.d("TAG", "**** UPDATE ROW OTHER CALLED **** userid "+ user.getUser_id_2()+ "picture URL"+
                user.getPicture_url()+ "********");

    }



    public boolean Picture_Exists(int searchItem) {


        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = { "id" };
        String selection = "id" + " =?";
        String[] selectionArgs = { searchItem+"" };
        String limit = "1";

        Cursor cursor = db.query(TABLE_INFORMATION_OTHER, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }





    public String get_Picture_URL(int id) {

        SQLiteDatabase db = this.getReadableDatabase();



        Cursor cursor = null;
        String url = "";
        try {
            cursor = db.rawQuery("SELECT picture_url FROM user_info_other WHERE id=?", new String[]{id+""});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                url = cursor.getString(cursor.getColumnIndex(PICTURE));
            }
            return url;
        } finally {
            cursor.close();
        }


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
        res.close();
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
        res.close();
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
        res.close();

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
                cursor.close();
            }
            return email;
        } finally {
            cursor.close();

            Log.d("TAG", "******placeholder**********");

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



    // EVENTS QUERIES


    public void add_row_event(Event_SQLite event) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //taskCount++;


        try {
        //add values
        values.put(USERID4, event.getUser_id());
        values.put(EVENT_TITLE, event.getTitle());
        values.put(EVENT_DATE, event.getDate());
        values.put(EVENT_TIME, event.getTime());

        //insert row into table

        db.insert(TABLE_EVENTS, null, values);


        //CharSequence hello = user.getName();


        Log.d("TAG", "ADD EVENTS ROW CALLED id " +event.user_id+ " title "+event.getTitle() +
        " date " +event.getDate()+ " time " + event.getTime());
        } finally {
            db.close(); }
        //Toast.makeText(c, "Hello " +user.getName(), Toast.LENGTH_LONG).show();

    }





    public void add_row_event_2(Event_SQLite event) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //taskCount++;


        try {
            //add values
            //values.put(USERID4, event.getUser_id());
            values.put(EVENT_TITLE, event.getTitle());
            values.put(EVENT_DATE, event.getDate());
            values.put(EVENT_TIME, event.getTime());

            //insert row into table

            db.insert(TABLE_EVENTS, null, values);


            //CharSequence hello = user.getName();


            Log.d("TAG", "ADD EVENTS ROW CALLED, title " + event.getTitle() +
                    " date " + event.getDate() + " time " + event.getTime());
        } finally {
            db.close(); }
        //Toast.makeText(c, "Hello " +user.getName(), Toast.LENGTH_LONG).show();

    }


    public ArrayList<String> getEventTitles() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = null;

        try {
            res = db.rawQuery("select * from events", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                array_list.add(res.getString(res.getColumnIndex(EVENT_TITLE)));
                res.moveToNext();
            }
            return array_list;
        }finally {
            res.close();
            db.close();
        }
    }



    public ArrayList<String> getEventDate() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = null;
        try {
            res = db.rawQuery("select * from events", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                array_list.add(res.getString(res.getColumnIndex(EVENT_DATE)));
                res.moveToNext();
            }
            return array_list;
        }finally {
            res.close();
            db.close();
        }
    }


    public ArrayList<String> getEventTime() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= null;
        try {
            res = db.rawQuery("select * from events", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                array_list.add(res.getString(res.getColumnIndex(EVENT_TIME)));
                res.moveToNext();
            }
            return array_list;
        }finally{
            res.close();
            db.close();
        }
    }



    public boolean Event_ID_Exists(int searchItem) {


        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = { "id" };
        String selection = "id" + " =?";
        String[] selectionArgs = { searchItem+"" };
        String limit = "1";

        Cursor cursor = db.query(TABLE_EVENTS, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exists;
    }



    public void delete_row_event(String title) {


        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        //String note = "";
        try {
            cursor = db.rawQuery("SELECT * FROM events WHERE event_title=?", new String[]{title});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                db.delete("events", "event_title=?", new String[]{title});
            }

        }finally{
            cursor.close();
            db.close();
        }

    }



    public void delete_row_event_2(String title) {


        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            db.delete (TABLE_EVENTS, "event_title = ?", new String[] { title });

            Log.d("TAG", "delete row called");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.close();
        }
    }



    public void delete_row_3(String title) {


        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        //String note = "";
        try {
            cursor = db.rawQuery("SELECT * FROM events WHERE event_title=?", new String[]{title});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                db.delete("events", "event_title=?", new String[]{title});


                Log.d("TAG", "*******delete row called *********");
            }

        }finally{
            cursor.close();
            db.close();
        }

    }








    // END EVENT QUERIES





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


