package com.lorenzo.mytabapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.lorenzo.mytabapp.ui.main.SectionsPagerAdapter;

//import android.widget.Toolbar;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.app_bar);
        viewpager = findViewById(R.id.view_pager_drawer);

        viewpager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewpager);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat App");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);


        View view = navigationView.inflateHeaderView(R.layout.drawer_header);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile:
                Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.friends:
                Toast.makeText(this, "friends", Toast.LENGTH_SHORT).show();
                break;
            case R.id.findFriends:
                Toast.makeText(this, "findFriends", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat:
                Toast.makeText(this, "chat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == android.R.id.home) ;
        {
            drawerLayout.openDrawer(GravityCompat.START);
            // getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            return true;
        }

    }
}