package com.example.kursach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kursach.SQLHelper.UserSqlHelper;
import com.example.kursach.exception.ValidationException;
import com.example.kursach.model.UserDataModel;
import com.example.kursach.validator.UserValidator;

public class RegisterActivity extends AppCompatActivity {

    private EditText loginField;
    private EditText passField;
    private EditText confPassField;
    private EditText usernameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginField = findViewById(R.id.loginField);
        passField = findViewById(R.id.passField);
        confPassField = findViewById(R.id.confirmPassField);
        usernameField = findViewById(R.id.usernameField);
        Button registryBtn = findViewById(R.id.registryBtn);

        registryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (!passField.getText().toString().equals(confPassField.getText().toString())) {
                        throw new ValidationException("Password is not confirmed!");
                    }

                    UserSqlHelper sqlHelper = new UserSqlHelper(RegisterActivity.this);
                    if (sqlHelper.createUser(RegisterActivity.this,
                            new UserDataModel(
                                    loginField.getText().toString(),
                                    passField.getText().toString(),
                                    usernameField.getText().toString()
                            ))) {
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }

                } catch (ValidationException exception) {
                    Toast.makeText(RegisterActivity.this, exception.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}