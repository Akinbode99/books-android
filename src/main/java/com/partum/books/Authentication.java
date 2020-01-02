package com.partum.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Authentication extends AppCompatActivity {

    private Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        signUp = findViewById(R.id.signup);

        signUp.setOnClickListener(v -> {
            startActivity(new Intent(Authentication.this, Home.class));
            finish();
        });
    }
}
