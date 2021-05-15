package com.example.kursach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kursach.SQLHelper.UserSqlHelper;
import com.example.kursach.SQLHelper.VacancySqlHelper;
import com.example.kursach.model.UserDataModel;
import com.example.kursach.model.UserFields;
import com.example.kursach.model.VacancyDataModel;

public class VacancyDescriptionActivity extends AppCompatActivity {

    private TextView vacancyName;
    private TextView fullDescText;
    private TextView price;
    private TextView author;
    private Button acceptBtn;
    private Button backBtn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy_description);

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        vacancyName = findViewById(R.id.vacancyName);
        fullDescText = findViewById(R.id.fullDescText);
        author = findViewById(R.id.author);
        price = findViewById(R.id.price);
        acceptBtn = findViewById(R.id.acceptBtn);
        backBtn = findViewById(R.id.backBtn);

        final UserSqlHelper userSqlHelper = new UserSqlHelper(VacancyDescriptionActivity.this);
        final UserDataModel user = userSqlHelper.getUserByParameter(sharedPreferences.getString("userId", ""), UserFields.ID_FIELD);

        Bundle arguments = getIntent().getExtras();
        final VacancyDataModel vacancyDataModel = (VacancyDataModel) arguments.getSerializable(VacancyDataModel.class.getSimpleName());
        vacancyName.setText(vacancyDataModel.getShortDesc());
        fullDescText.setText(vacancyDataModel.getFullDesc());
        price.setText(vacancyDataModel.getCoast().toString());
        author.setText(user.getUsername());

        if (vacancyDataModel.getEmployeeId().equals(Long.valueOf(sharedPreferences.getString("userId", ""))) || !vacancyDataModel.getExecutorId().equals(Long.valueOf(0))) {
            acceptBtn.setVisibility(View.GONE);
        }

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VacancySqlHelper vacancySqlHelper = new VacancySqlHelper(VacancyDescriptionActivity.this);
                vacancyDataModel.setExecutorId(Long.valueOf(sharedPreferences.getString("userId", "")));
                vacancySqlHelper.updateVacancy(VacancyDescriptionActivity.this, vacancyDataModel);

                user.setAssignedVacancies(user.getAssignedVacancies() + 1);
                userSqlHelper.updateUser(VacancyDescriptionActivity.this, user);

                startActivity(new Intent(VacancyDescriptionActivity.this, MainScreenActivity.class));
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VacancyDescriptionActivity.this, MainScreenActivity.class));
            }
        });
    }
}