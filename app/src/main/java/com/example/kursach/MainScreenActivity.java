package com.example.kursach;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach.SQLHelper.VacancySqlHelper;
import com.google.android.material.navigation.NavigationView;

public class MainScreenActivity extends AppCompatActivity {
    //TODO: Такой же код прийдется вставлять в каждой активити
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        drawerLayout = findViewById(R.id.drawerMain);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById(R.id.list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view);


        VacancySqlHelper sqlHelper = new VacancySqlHelper(MainScreenActivity.this);
        ItemsAdapter itemsAdapter = new ItemsAdapter(MainScreenActivity.this,
                sqlHelper.getAllVacancies(MainScreenActivity.this));
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
        startActivity(new Intent(MainScreenActivity.this, clazz));
    }
}
