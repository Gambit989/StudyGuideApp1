package com.example.evan.comp296;

import android.support.v7.app.AppCompatActivity;

import android.content.Context;

/**
 * Created by Evan on 4/23/17.
 */

public class PrefUtils extends AppCompatActivity {

    public static void setCurrentUser(Fb_users currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        complexPreferences.putObject("current_user_value", currentUser);
        complexPreferences.commit();
    }

    public static Fb_users getCurrentUser(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        Fb_users currentUser = complexPreferences.getObject("current_user_value", Fb_users.class);
        return currentUser;
    }

    public static void clearCurrentUser( Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        complexPreferences.clearObject();
        complexPreferences.commit();
    }



}
