package com.ecmediagroup.evan.studyguide;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Created by Evan on 11/18/16.
 */

public class display_info extends AppCompatActivity {



    TextView Email, Name, Phone, Pass;
    String email,name,phone,pass;

    CharSequence test;

    private static final String TAG = display_info.class.getSimpleName();



    //CONSTRUCTORS

    public display_info () {

    }


  public display_info (String a, String b, String c, String d) {


        email=a;
        name=b;
        phone=c;
        pass=d;

      Log.d(TAG, pass); }



    // GETS AND SETS



    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPass() {
        return pass;
    }





    // ON CREATE

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.display_layout);

        getSupportActionBar().setHomeButtonEnabled(true);

        Bundle bundle = getIntent().getExtras();
        String e = bundle.getString("email");
        String f = bundle.getString("first");
        String l = bundle.getString("last");
        String ph = bundle.getString("phone");
        String p = bundle.getString("pass");



        Email = (TextView) findViewById(R.id.textView_email);
        Name = (TextView) findViewById(R.id.textView_name);
        Phone = (TextView) findViewById(R.id.textView_phone);
        Pass = (TextView) findViewById(R.id.textView_password);


        Log.d(TAG, pass  + " second");

        Log.d(TAG, e + " email");
        Log.d(TAG, f + " first name");


        Email.setText(e);
        Name.setText(f + " " + l);
        Phone.setText(ph);
        Pass.setText(p);




        //random();

        Button display = (Button) findViewById(R.id.display_button);
        display.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                back_to_main();

            }
        });



    }

    // END ON CREATE METHOD



public void back_to_main () {

    startActivity(new Intent(this,MainActivity.class));

    }






public void random () {



    Log.d(TAG, pass + "third");

    //test = "test test test";

    Email.setText(getEmail());
    Name.setText(getName());
    Phone.setText("hello");
    Pass.setText(getPass()); }











    public void add_information (user_info info) {


        email = info.getID_email();
        name =  info.getFName() + " " + info.getLName();
        phone = info.getPhone();
        pass = info.getPassword();




        Email = (TextView) findViewById(R.id.textView_email);
        Name = (TextView) findViewById(R.id.textView_name);
        Phone = (TextView) findViewById(R.id.textView_phone);
        Pass = (TextView) findViewById(R.id.textView_password);

        //email = "test";
        //name= "name name";

        Email.setText(email);
        Name.setText(name);
        Phone.setText("hello");
        Pass.setText(test);



    }






}








