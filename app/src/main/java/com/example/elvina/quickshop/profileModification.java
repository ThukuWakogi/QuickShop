package com.example.elvina.quickshop;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.graphics.Color.parseColor;

public class profileModification extends AppCompatActivity implements View.OnClickListener{
    private FirebaseUser user;
    private EditText firstNameEditText, lastNameEditText, phoneNumberEditText, streetEditText, postalAddressEditText;
    private Button backButton, changeDetailsButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modification);

        if (!(null == getSupportActionBar())) getSupportActionBar().setBackgroundDrawable(new ColorDrawable(parseColor("#3F3F3F")));
        FirebaseAuth auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        streetEditText = findViewById(R.id.streetEditText);
        postalAddressEditText = findViewById(R.id.postalAddressEditText);
        backButton = findViewById(R.id.backButton);
        changeDetailsButton = findViewById(R.id.changeDetailsButton);
        backButton.setOnClickListener(this);
        changeDetailsButton.setOnClickListener(this);
        Toast.makeText(this, "Logged in as " + user.getEmail(), Toast.LENGTH_LONG).show();
    }

    private void changeDetails() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String phoneNumber = phoneNumberEditText.getText().toString().trim();
        String street = streetEditText.getText().toString().trim();
        String postalAddress = postalAddressEditText.getText().toString().trim();
        Receiver changingReceiver = new Receiver(user.getUid(), firstName, lastName, user.getEmail(), phoneNumber, street, postalAddress);
        databaseReference.child(user.getUid()).setValue(changingReceiver).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) Toast.makeText(getApplicationContext(), "Profile Modified", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(), "Modification failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view == backButton) startActivity(new Intent(this, SuccessfullyIn.class));
        if(view == changeDetailsButton) changeDetails();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
