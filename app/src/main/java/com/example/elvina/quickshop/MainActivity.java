package com.example.elvina.quickshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        This class will check whether the user is logged in or not.
        If logged in, user will be directed to the home page
        Else they will have to log in or sign up
        */
        ActionBar actionBar = getSupportActionBar();
        if (!(null == actionBar)) actionBar.hide();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting things ready...");
        progressDialog.show();
        if (FirebaseAuth.getInstance().getCurrentUser() == null ) startActivity(new Intent(this, Authenticate.class));
        else startActivity(new Intent(this, SuccessfullyIn.class));
    }
}
