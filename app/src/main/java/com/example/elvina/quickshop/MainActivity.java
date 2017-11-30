package com.example.elvina.quickshop;

import android.app.ProgressDialog;
import android.content.Intent;
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

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting this ready...");
        progressDialog.show();
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null ) {
            finish();
            startActivity(new Intent(this, SignUp.class));
        } else {
            finish();
            startActivity(new Intent(this, Authenticate.class));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}
