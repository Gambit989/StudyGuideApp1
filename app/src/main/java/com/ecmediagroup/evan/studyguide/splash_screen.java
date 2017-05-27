package com.ecmediagroup.evan.studyguide;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * Created by Evan on 5/14/17.
 *
 */



public class splash_screen extends AppCompatActivity {

    ImageView image_load_icon;
    ImageView image_load_icon_2;
    ImageView image_load_icon_3;
    ImageView image_load_icon_4;
    ImageView image2;

    Button splash_text;
    public Animation fadeIn;
    public Animation fadeIn2;

    private ProgressWheel progressWheel;
    private ProgressWheel progressWheelInterpolated;
    private ProgressWheel progressWheelLinear;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //image_load_icon= (ImageView) findViewById(R.id.splash_image_load_icon);
        //image_load_icon_2= (ImageView) findViewById(R.id.splash_image_load_icon_2);
        //image_load_icon_3= (ImageView) findViewById(R.id.splash_image_load_icon_3);
        image_load_icon_4= (ImageView) findViewById(R.id.splash_image_load_icon_4);

        progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);

        splash_text= (Button) findViewById(R.id.splash_text);

        fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        fadeIn2 = AnimationUtils.loadAnimation(this,R.anim.fade_in_2);


        Animation up_and_down = AnimationUtils.loadAnimation(this, R.anim.up_and_down);
        Animation up_and_down_2 = AnimationUtils.loadAnimation(this, R.anim.up_and_down_2);
        Animation up_and_down_3 = AnimationUtils.loadAnimation(this, R.anim.up_and_down_3);

        Animation bottom_up = AnimationUtils.loadAnimation(this, R.anim.bottom_up);

        //Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump2);
        //image_load_icon.startAnimation(up_and_down);
        //image_load_icon_2.startAnimation(up_and_down_2);
        //image_load_icon_3.startAnimation(up_and_down_3);
        //image_load_icon.setVisibility(View.VISIBLE);
        //image2.setVisibility(View.VISIBLE);

        image_load_icon_4.setAnimation(bottom_up);

        progressWheel.setBarColor(Color.BLUE);
        //progressWheel.setBarColor(R.color.royal_blue);
        progressWheel.setRimColor(Color.GRAY);
        progressWheel.spin();



        //ProgressWheel a = new ProgressWheel(splash_screen.this,R.styleable.ProgressWheel);

        splash_text.startAnimation(fadeIn2);
        splash_text.setVisibility(View.VISIBLE);



        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {

                startActivity(new Intent(splash_screen.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1400);
    }
}
