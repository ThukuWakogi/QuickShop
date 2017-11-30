package com.example.elvina.quickshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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
    private Button loginButton;
    private TextView queryAccountAbsenceTextView;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        loginEmailEditText = findViewById(R.id.login_email_edittext);
        loginPasswordEditText = findViewById(R.id.login_password_edittext);
        loginButton = findViewById(R.id.login_button);
        queryAccountAbsenceTextView = findViewById(R.id.queryAccountAbsenceTextView);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(this);
        queryAccountAbsenceTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton) userLogin();

        if (view == queryAccountAbsenceTextView) {
            finish();
            startActivity(new Intent(this, SignUp.class));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

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
                        if (task.isComplete()) Toast.makeText(getApplicationContext(), "logged in!", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(getApplicationContext(), "email or password incorrect", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
