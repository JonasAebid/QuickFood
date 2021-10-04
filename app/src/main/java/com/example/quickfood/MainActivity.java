package com.example.quickfood;

import static java.lang.System.exit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.ActionBar;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //kode for menyen
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle action = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.menu_open, R.string.menu_close);

        drawer.addDrawerListener(action);
        action.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Menu_Home()).commit();
            navigationView.setCheckedItem(R.id.menu_home);

        }

        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Menu_Home()).commit();
                break;
            case R.id.menu_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Menu_Devinfo()).commit();
                break;
            case R.id.menu_pasta:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Pasta()).commit();
                break;

            case R.id.menu_logout:
                exit(0);
                break;


        }
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
    public void pasta(View view){
        Intent pasta = new Intent(MainActivity.this, Pasta.class);
        startActivity(pasta);
    }

}