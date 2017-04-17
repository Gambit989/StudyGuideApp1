package com.example.evan.comp296;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

import android.content.Context;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;

import android.app.Activity;



/**
 * Created by Evan on 11/18/16.
 */

public class AndroidDatabaseHelper extends SQLiteOpenHelper {



    // Database Info
    private static final String DATABASE_NAME = "android";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_INFORMATION = "information";


    // Android Table Columns
    private static final String KEY_ANDROID_ID = "ID_email";
    private static final String ANDROID_FN = "first_name";
    private static final String ANDROID_LN = "last_name";
    private static final String ANDROID_PHONE = "phone_num";
    private static final String ANDROID_PASS = "password";

    // User Table Columns
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_PROFILE_PICTURE_URL = "profilePictureUrl";

    //private static AndroidDatabaseHelper sInstance;

    private int taskCount;


    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */

    Context c;
    public AndroidDatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        c=context;
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INFORMATION_TABLE = "CREATE TABLE " + TABLE_INFORMATION +
                "(" +
                KEY_ANDROID_ID + " TEXT ," + // Define a primary key
                ANDROID_FN + " TEXT ," +
                ANDROID_LN + " TEXT ," +
                ANDROID_PHONE + " BIGINT ," +
                ANDROID_PASS + " TEXT " +
                ")";



        db.execSQL(CREATE_INFORMATION_TABLE);
        //db.execSQL(CREATE_USERS_TABLE);
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFORMATION);
            //db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }
    }



    //******** DATABASE OPERATIONS: ADD, EDIT, DELETE


    //ADD
    public void add_row (user_info info) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //taskCount++;


        //add values
        values.put(KEY_ANDROID_ID, info.getID_email());
        values.put(ANDROID_FN, info.getFName());
        values.put(ANDROID_LN, info.getLName());
        values.put(ANDROID_PHONE, info.getPhone());
        values.put(ANDROID_PASS, info.getPassword());

        //insert row into table

        db.insert(TABLE_INFORMATION,null,values);

        //Context text = new Application();

        //setContentView(R.layout.display_layout);


        //Toast.setView(View view);


        /*





        */

        CharSequence hello = info.getID_email();


        Toast.makeText( c, hello, Toast.LENGTH_LONG).show();



    }


    //EDIT

    public void edit_row (user_info info) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ANDROID_ID, info.getID_email());
        values.put(ANDROID_FN, info.getFName());
        values.put(ANDROID_LN, info.getLName());
        values.put(ANDROID_PHONE, info.getPhone());
        values.put(ANDROID_PASS, info.getPassword());

        db.update(TABLE_INFORMATION, values, KEY_ANDROID_ID + " = ?",
                new String[]{String.valueOf(info.getID_email())
                });

        db.close();


    }


    //RETURN INFORMATION ON A SPECIFIC USER BY THEIR EMAIL

    public user_info get_all_info(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        //ContentValues values = new ContentValues();
        Cursor cursor = db.query (

        TABLE_INFORMATION, new String [] {KEY_ANDROID_ID, ANDROID_FN, ANDROID_LN, ANDROID_PHONE, ANDROID_PASS},
        KEY_ANDROID_ID + "=?", new String[]{String.valueOf(email)},null, null, null, null );

        if (cursor != null)
            cursor.moveToFirst();

        user_info info = new user_info(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4) );

        db.close();

        return info;

    }



    public Boolean check_Login_Credentials(String username, String password)
    {
        SQLiteDatabase sql_Db = this.getReadableDatabase();




        // Setting up the cursor which points to the desired table
        Cursor cursor = sql_Db.rawQuery("SELECT * FROM " + TABLE_INFORMATION + " Where " + KEY_ANDROID_ID +
                " = '" +username+"'" + " AND " + ANDROID_PASS + " = '" +password+ "' ; ",null);

        Boolean records_Exist;

        if(cursor==null)
        {
            //set boolean var to false
            records_Exist=false;
            //message no such records
        }

        else
        {
            //set boolean var to true
            records_Exist=true;
            cursor.moveToFirst();
        }






        // Checking if the table has values other than the header using the cursor
        if(cursor != null && cursor.getCount() > 0)
        {
            // Moving the cursor to the first row in the table
            cursor.moveToFirst();

            do
            {
                // Checking if the user name provided by the user exists in the database
                if(cursor.getString(cursor.getColumnIndex(KEY_ANDROID_ID)).equals(username))
                {
                    if(password.equals(cursor.getColumnIndex(ANDROID_PASS)))
                    {
                        records_Exist = true;
                        return records_Exist;
                    }
                }

            }while(cursor.moveToNext()); // Moves to the next row
        };





        // Closing the cursor
        cursor.close();

        // Closing the database
        sql_Db.close();

        return records_Exist;
    }








   /*

    // Insert a post into the database
    public void addPost(Post post) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).
            long userId = addOrUpdateUser(post.user);

            ContentValues values = new ContentValues();
            values.put(KEY_POST_USER_ID_FK, userId);
            values.put(KEY_POST_TEXT, post.text);

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_INFORMATION, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }



*/







}
