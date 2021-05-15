package com.example.kursach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kursach.SQLHelper.UserSqlHelper;
import com.example.kursach.exception.Messages;
import com.example.kursach.exception.ValidationException;
import com.example.kursach.model.UserDataModel;
import com.example.kursach.model.UserFields;
import com.google.android.material.navigation.NavigationView;

public class PersonalCabinetActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private SharedPreferences sharedPreferences;

    private TextView username;
    private TextView vacanciesCount;
    private TextView acceptedVacanciesCount;
    private EditText newPassword;
    private EditText confirmNewPassword;
    private Button acceptBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_cabinet);
        sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE);

        if (!sharedPreferences.contains("userId")) {
            changeActivity(LoginActivity.class);
        }

        username = findViewById(R.id.usernameText);
        vacanciesCount = findViewById(R.id.vacanciesCount);
        acceptedVacanciesCount = findViewById(R.id.assignedVacanciesCount);
        newPassword = findViewById(R.id.editPassword);
        confirmNewPassword = findViewById(R.id.confirmNewPassword);
        acceptBtn = findViewById(R.id.acceptBtn);

        drawerLayout = findViewById(R.id.drawerMain);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.personalCabinet:
                        changeActivity(PersonalCabinetActivity.class);
                        break;
                    case R.id.addVacancy:
                        changeActivity(AddVacancyActivity.class);
                        break;
                    case R.id.myVacancies:
                        changeActivity(UserVacancies.class);
                        break;
                    case R.id.assignedVacancies:
                        changeActivity(AssignedVacancies.class);
                        break;
                    case R.id.allVacancies:
                        changeActivity(MainScreenActivity.class);
                        break;
                }

                return false;
            }
        });

        UserSqlHelper sqlHelper = new UserSqlHelper(PersonalCabinetActivity.this);
        UserDataModel user = sqlHelper.getUserByParameter(sharedPreferences.getString("userId", ""), UserFields.ID_FIELD);

        username.setText(user.getUsername());
        vacanciesCount.setText(user.getUserVacancies().toString());
        acceptedVacanciesCount.setText(user.getAssignedVacancies().toString());

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newPassword.getText() != null) {
                    UserSqlHelper sqlHelper = new UserSqlHelper(PersonalCabinetActivity.this);
                    UserDataModel user = sqlHelper.getUserByParameter(sharedPreferences.getString("userId", ""), UserFields.ID_FIELD);
                    String newPass = newPassword.getText().toString();
                    String confirmNewPass = confirmNewPassword.getText().toString();
                    if (newPass.equals(confirmNewPass)) {
                        user.setPassword(newPass);
                        try {
                            sqlHelper.updateUser(PersonalCabinetActivity.this, user);
                            newPassword.getText().clear();
                            confirmNewPassword.getText().clear();
                        } catch (ValidationException exception) {
                            Toast.makeText(PersonalCabinetActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(PersonalCabinetActivity.this, Messages.PASS_IS_NOT_CONFIRMED, Toast.LENGTH_LONG).show();
                    }
                } else {
                    changeActivity(MainScreenActivity.class);
                }
            }
        });

    }

    private void changeActivity(Class clazz) {
        startActivity(new Intent(PersonalCabinetActivity.this, clazz));
    }
}