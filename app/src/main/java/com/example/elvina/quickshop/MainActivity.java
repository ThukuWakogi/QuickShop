package com.example.elvina.quickshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

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
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting this ready...");
        progressDialog.show();
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null ) {
            finish();
            startActivity(new Intent(this, Authenticate.class));
        } else {
            finish();
            startActivity(new Intent(this, SuccessfullyIn.class));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}
