package com.example.evan.comp296.Notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.evan.comp296.Data;
import com.example.evan.comp296.user_info;

import java.util.ArrayList;

/**
 * Created by Evan on 5/10/17.
 */

public class Note_database extends SQLiteOpenHelper {


    // Database Info
    private static final String DATABASE_NAME = "comp296";
    private static final int DATABASE_VERSION = 2;

    // Table Names
    private static final String TABLE_INFORMATION = "user_info";
    private static final String TABLE_NOTES = "notes";


    // Android Table Columns
    private static final String USERID1 = "id";
    private static final String USERID2 = "id";
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String SCHOOL = "school";


    private static final String NOTE_TITLE = "title";
    private static final String NOTE_TEXT = "text";

    Context c;


    public Note_database (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.c=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_INFORMATION_TABLE = "CREATE TABLE " + TABLE_INFORMATION +
                "(" + USERID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EMAIL + " TEXT ," + // Define a primary key
                NAME + " TEXT ," +
                SCHOOL + " TEXT" +
                ")";



        String CREATE_NOTE_TABLE = "CREATE TABLE " + TABLE_NOTES +
                "(" +USERID2+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NOTE_TITLE + " TEXT ," + // Define a primary key
                NOTE_TEXT + " TEXT" +
                ")";


        db.execSQL(CREATE_INFORMATION_TABLE);
        db.execSQL(CREATE_NOTE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFORMATION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
            onCreate(db);}

        }






        //ADD
    public void add_row_notes (Note_data note) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //taskCount++;


        //add values
        values.put(NOTE_TITLE, note.getNote_title());
        values.put(NOTE_TEXT, note.getNote_text());

        //insert row into table

        db.insert(TABLE_NOTES, null, values);


        CharSequence hello = note.getNote_title();


        Toast.makeText( c, hello, Toast.LENGTH_LONG).show();

    }


    public void add_row_info (User_SQLite user) {

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


        Toast.makeText( c, hello, Toast.LENGTH_LONG).show();

    }





    public void edit_school_row (String school) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SCHOOL, school);


        db.update(TABLE_INFORMATION, values, USERID1 + " = 1",
                null);

        db.close();


    }



    public ArrayList<String> getTitles()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select title from notes", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(NOTE_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }



    public ArrayList<String> getText()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from notes", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(NOTE_TEXT)));
            res.moveToNext();
        }
        return array_list;
    }


    public ArrayList<Data> getText_data()
    {
        ArrayList<Data> array_list = new ArrayList();

        //hp = new HashMap();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from notes", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Data(res.getString(res.getColumnIndex(NOTE_TEXT))));
            res.moveToNext();
        }
        return array_list;
    }






}
