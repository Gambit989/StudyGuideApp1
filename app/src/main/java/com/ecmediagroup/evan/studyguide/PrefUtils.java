package com.ecmediagroup.evan.studyguide;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

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
