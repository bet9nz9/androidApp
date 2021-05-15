package com.example.kursach;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kursach.SQLHelper.UserSqlHelper;
import com.example.kursach.SQLHelper.VacancySqlHelper;
import com.example.kursach.exception.ValidationException;
import com.example.kursach.model.UserDataModel;
import com.example.kursach.model.UserFields;
import com.example.kursach.model.VacancyDataModel;
import com.example.kursach.validator.VacancyValidator;

import java.math.BigDecimal;

public class AddVacancyActivity extends AppCompatActivity {

    private EditText shortDescription;
    private EditText fullDescription;
    private EditText price;
    private Button acceptBtn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacancy);

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        shortDescription = findViewById(R.id.shortDesc);
        fullDescription = findViewById(R.id.fullDescription);
        price = findViewById(R.id.price);
        acceptBtn = findViewById(R.id.acceptBtn);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shortDescription.getText() != null || fullDescription.getText() != null || price.getText() != null) {
                    if (!shortDescription.getText().toString().equals("") ||
                            !fullDescription.getText().toString().equals("") ||
                            !price.getText().toString().equals("")) {
                        try {
                            VacancyValidator.validateCoast(price.getText().toString());
                            VacancySqlHelper sqlHelper = new VacancySqlHelper(AddVacancyActivity.this);
                            UserSqlHelper userSqlHelper = new UserSqlHelper(AddVacancyActivity.this);
                            VacancyDataModel vacancy = new VacancyDataModel(
                                    new BigDecimal(price.getText().toString()),
                                    shortDescription.getText().toString(),
                                    fullDescription.getText().toString(),
                                    false,
                                    null,
                                    Long.valueOf(sharedPreferences.getString("userId", ""))
                            );
                            sqlHelper.createVacancy(AddVacancyActivity.this, vacancy);
                            UserDataModel user = userSqlHelper.getUserByParameter(sharedPreferences.getString("userId", ""), UserFields.ID_FIELD);
                            user.setUserVacancies(user.getUserVacancies() + 1);
                            userSqlHelper.updateUser(AddVacancyActivity.this, user);
                            Intent intent = new Intent(AddVacancyActivity.this, VacancyDescriptionActivity.class);
                            intent.putExtra(VacancyDataModel.class.getSimpleName(), vacancy);
                            startActivity(intent);
                        } catch (ValidationException exception) {
                            Toast.makeText(AddVacancyActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }
}