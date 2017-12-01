package com.example.elvina.quickshop;

import android.content.Intent;
import android.drm.DrmStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SuccessfullyIn extends AppCompatActivity implements View.OnClickListener{
    private Button logOutButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfully_in);

        ActionBar actionBar = getSupportActionBar();
        if (!(null == actionBar)) actionBar.hide();
        firebaseAuth = FirebaseAuth.getInstance();
        TextView proofStatementTextView = findViewById(R.id.proofStatementTextView);
        logOutButton = findViewById(R.id.logOutButton);

        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(this, "Access failed", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, Authenticate.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        proofStatementTextView.append(String.format("\n%s", user != null ? user.getEmail() : null));
        proofStatementTextView.setOnClickListener(this);
        logOutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == logOutButton) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Authenticate.class));
        }
    }
}
