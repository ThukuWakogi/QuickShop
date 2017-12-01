package com.example.elvina.quickshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private TextView queryAccountPresenceTextView;
    private Button signUpButton;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getSupportActionBar();
        if (!(null == actionBar)) actionBar.hide();
        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.signUpEmailEditText);
        passwordEditText = findViewById(R.id.signUpPasswordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        queryAccountPresenceTextView = findViewById(R.id.queryAccountPresenceTextView);
        signUpButton = findViewById(R.id.signUpButton);
        progressDialog = new ProgressDialog(this);
        queryAccountPresenceTextView.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    private void registerUser() {
        String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        String passwordConfirmation = confirmPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(passwordConfirmation)) {
            Toast.makeText(this,"please confirm password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(passwordConfirmation)) {
            Toast.makeText(this, "ensure passwords match", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Making you one of us...");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "registered successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), SuccessfullyIn.class));
                        } else {
                            progressDialog.dismiss();
                            emailEditText.setText("");
                            passwordEditText.setText("");
                            confirmPasswordEditText.setText("");
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this, "registration failed, please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (signUpButton == view) registerUser();
        if (queryAccountPresenceTextView == view) {
            finish();
            startActivity(new Intent(this, Authenticate.class));
        }
    }
}
