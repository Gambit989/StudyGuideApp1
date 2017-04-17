package com.example.evan.comp296;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Evan on 12/1/16.
 */

public class onehundred_level extends AppCompatActivity {


    // TextView one_sixty_six;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.onehundred_level);

        getSupportActionBar().setHomeButtonEnabled(true);



        /*
        TextView one_sixty_six = (TextView) findViewById(R.id.textView_166);
        one_sixty_six.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                go_to_class();


            }


        });

*/




    }



    public void go_to_class () {


        startActivity(new Intent(this,MainActivity.class));



    }



    public void open_class(View v){

        startActivity(new Intent(this,comp166.class));
    }






}
