package com.example.kursach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kursach.SQLHelper.VacancySqlHelper;
import com.example.kursach.exception.ValidationException;
import com.example.kursach.model.VacancyDataModel;
import com.example.kursach.validator.VacancyValidator;

import java.math.BigDecimal;

public class AddVacancyActivity extends AppCompatActivity {

    private EditText shortDescription;
    private EditText fullDescription;
    private EditText price;
    private Button acceptBtn;
    private final SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacancy);

        shortDescription = findViewById(R.id.shortDesc);
        fullDescription = findViewById(R.id.fullDescription);
        price = findViewById(R.id.price);
        acceptBtn = findViewById(R.id.acceptBtn);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shortDescription.getText() != null || fullDescription.getText() != null || price.getText() != null){
                    if (!shortDescription.getText().toString().equals("") ||
                            !fullDescription.getText().toString().equals("") ||
                            !price.getText().toString().equals("")){
                        try {
                            VacancyValidator.validateCoast(price.getText().toString());
                            VacancySqlHelper sqlHelper = new VacancySqlHelper(AddVacancyActivity.this);
                            sqlHelper.createVacancy(AddVacancyActivity.this, new VacancyDataModel(
                                    new BigDecimal(price.getText().toString()),
                                    shortDescription.getText().toString(),
                                    fullDescription.getText().toString(),
                                    false,
                                    null,
                                    sharedPreferences.getLong("userId", 0)
                            ));

                        } catch (ValidationException exception){
                            Toast.makeText(AddVacancyActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }
}