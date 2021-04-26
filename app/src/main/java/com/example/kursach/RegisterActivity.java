package com.example.kursach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText loginField;
    private EditText passField;
    private EditText confPassField;
    private EditText usernameField;
    private Button registryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginField = findViewById(R.id.loginField);
        passField = findViewById(R.id.passField);
        confPassField = findViewById(R.id.confirmPassField);
        usernameField = findViewById(R.id.usernameField);
        registryBtn = findViewById(R.id.registryBtn);

        registryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}