package com.ecmediagroup.evan.studyguide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Evan on 5/14/17.
 */
public class Task2 extends AsyncTask<String, Integer, Bitmap> {


    private static final String TAG = "Tag";


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG + " PreExceute", "On pre Exceute......");
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        Log.d(TAG + " Background", "Background thread.....");

        String url = params[0];


        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;

        /*


        URL myUrl = null;
        try {
            myUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream result = null;
        try {
            result = (InputStream)myUrl.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;


        */

    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


    protected void onPostExecute(Bitmap result) {
        Log.d("TAG", "Post executed" + result);

        //Drawable drawable = Drawable.createFromStream(result, null);
        //nav_pic.setImageDrawable(drawable);


    }
}
