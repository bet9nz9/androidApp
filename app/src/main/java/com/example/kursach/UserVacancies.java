package com.example.kursach;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach.SQLHelper.VacancySqlHelper;
import com.example.kursach.model.VacancyFields;
import com.google.android.material.navigation.NavigationView;

public class UserVacancies extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_vacancies);

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        recyclerView = findViewById(R.id.userVacancies);

        drawerLayout = findViewById(R.id.drawerMain);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view);


        VacancySqlHelper sqlHelper = new VacancySqlHelper(UserVacancies.this);
        ItemsAdapter itemsAdapter = new ItemsAdapter(UserVacancies.this,
                sqlHelper.getAllVacanciesByParameter(sharedPreferences.getString("userId", ""), VacancyFields.EMPLOYEE_ID_FIELD, UserVacancies.this));
        recyclerView.setAdapter(itemsAdapter);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //TODO: Set menu items here
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
    }

    private void changeActivity(Class clazz) {
        startActivity(new Intent(UserVacancies.this, clazz));
    }
}