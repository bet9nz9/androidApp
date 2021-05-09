package com.example.kursach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kursach.SQLHelper.UserSqlHelper;
import com.example.kursach.model.UserDataModel;

public class LoginActivity extends AppCompatActivity {
    private EditText loginField;
    private EditText passField;
    private Button registryBtn;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginField = findViewById(R.id.loginField);
        passField = findViewById(R.id.passField);
        registryBtn = findViewById(R.id.registryBtn);
        loginBtn = findViewById(R.id.logInBtn);

        registryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserSqlHelper userSqlHelper = new UserSqlHelper(LoginActivity.this);

                UserDataModel userDataModel = userSqlHelper.getUserByParameter(loginField.getText().toString(), "LOGIN");
                if (userDataModel == null) {
                    Toast.makeText(LoginActivity.this, "User not found!", Toast.LENGTH_LONG).show();
                } else {
                    if (!userDataModel.getPassword().equals(passField.getText().toString())){
                        Toast.makeText(LoginActivity.this, "Wrong login or password!", Toast.LENGTH_LONG).show();
                    } else {
                        startActivity(new Intent(LoginActivity.this, MainScreenActivity.class));
                    }
                }
            }
        });
    }
}