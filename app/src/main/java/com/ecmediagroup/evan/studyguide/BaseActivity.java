package com.ecmediagroup.evan.studyguide;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Evan on 4/24/17.
 */


import android.app.ProgressDialog;
import android.support.annotation.VisibleForTesting;


public class BaseActivity extends AppCompatActivity {

        @VisibleForTesting
        public ProgressDialog mProgressDialog;

        public void showProgressDialog() {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Loading");
                mProgressDialog.setIndeterminate(true);
            }

            mProgressDialog.show();
        }

        public void hideProgressDialog() {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }

        @Override
        public void onStop() {
            super.onStop();
            hideProgressDialog();
        }

    }

