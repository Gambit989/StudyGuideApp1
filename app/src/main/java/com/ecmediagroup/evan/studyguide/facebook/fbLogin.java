package com.ecmediagroup.evan.studyguide.facebook;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ecmediagroup.evan.studyguide.MainHomeScreen;
import com.ecmediagroup.evan.studyguide.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Evan on 5/4/17.
 */

public class fbLogin extends Fragment {


    private CallbackManager callbackManager;
    private LoginButton loginButton;
    String Name;
    String FEmail;
    //View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fb_login, container, false);

        Log.d("TAG", "******FRAGMENT CALLED*******");

        //FACEBOOK CODE

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) view.findViewById(R.id.login_button1);

        loginButton.setReadPermissions("email", "public_profile");
        // If using in a fragment
        loginButton.setFragment(this);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();


                // Facebook Email address
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                Log.v("LoginActivity Response ", response.toString());

                                try {
                                    Name = object.getString("name");

                                    FEmail = object.getString("email");
                                    Log.v("Email = ", " " + FEmail);
                                    Toast.makeText(getApplicationContext(), "Name " + Name, Toast.LENGTH_LONG).show();


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                String success = "User ID: "
                        + loginResult.getAccessToken().getUserId()
                        + "\n" +
                        "Auth Token: "
                        + loginResult.getAccessToken().getToken();

                Snackbar snackbar = Snackbar
                        .make(getActivity().findViewById(R.id.email), success
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken(), Snackbar.LENGTH_LONG);

                snackbar.show();

                startActivity(new Intent(getActivity(), MainHomeScreen.class));
                //setContentView(R.layout.activity_main_home_screen);

                //test();
                //setContentView(R.layout.activity_main_home_screen);
            }

            @Override
            public void onCancel() {

                Snackbar snackbar = Snackbar
                        .make(getActivity().findViewById(R.id.email), ("Login attempt canceled."), Snackbar.LENGTH_LONG);

                snackbar.show();

            }

            @Override
            public void onError(FacebookException e) {

                Snackbar snackbar = Snackbar
                        .make(getActivity().findViewById(R.id.email), ("Login attempt Failed."), Snackbar.LENGTH_LONG);

                snackbar.show();


            }
        });

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);







    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
