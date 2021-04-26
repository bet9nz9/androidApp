package com.example.kursach;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainScreenActivity extends AppCompatActivity {
//TODO: Такой же код прийдется вставлять в каждой активити
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        drawerLayout = findViewById(R.id.drawerMain);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //TODO: Set menu items here
                switch (item.getItemId()) {
                    case R.id.contact:
//                        startActivity(new Intent(MainScreenActivity.this, Contact.class));
                        break;
                    case R.id.help:
//                        startActivity(new Intent(MainScreenActivity.this, Contact.class));
                        break;
                    case R.id.settings:
//                        startActivity(new Intent(MainScreenActivity.this, Contact.class));
                        break;
                }

                return false;
            }
        });
    }
}
