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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Authenticate extends AppCompatActivity implements View.OnClickListener{
    private EditText loginEmailEditText;
    private EditText loginPasswordEditText;
    private Button logInButton;
    private TextView queryAccountAbsenceTextView;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        ActionBar actionBar = getSupportActionBar();
        if (!(null == actionBar)) actionBar.hide();
        loginEmailEditText = findViewById(R.id.logInEmailEdittext);
        loginPasswordEditText = findViewById(R.id.logInPasswordEdittext);
        logInButton = findViewById(R.id.logInButton);
        queryAccountAbsenceTextView = findViewById(R.id.queryAccountAbsenceTextView);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        logInButton.setOnClickListener(this);
        queryAccountAbsenceTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == logInButton) userLogin();

        if (view == queryAccountAbsenceTextView) {
            startActivity(new Intent(this, SignUp.class));
        }
    }

    //This method authenticates the user.
    private void userLogin() {
        String email = loginEmailEditText.getText().toString().trim();
        String loginPassword = loginPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(loginPassword)) {
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("logging in...");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, loginPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            Toast.makeText(getApplicationContext(), "logged in!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), SuccessfullyIn.class));
                        }
                    }
                });
    }
}
