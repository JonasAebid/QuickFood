package com.example.quickfood;

import static java.lang.System.exit;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.ActionBar;
// Extends activity and implements navigation view
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    SQLiteDatabase database;

    TextView nameHeader;
    TextView mailHeader;

    String user_mail = CustomerLogin.getUser_Email();
    String user_firstname = CustomerLogin.getUser_FirstName();
    String user_lastname = CustomerLogin.getUser_LastName();
    String user_fullname = user_firstname + " " + user_lastname;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //code for menu
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
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.menu_user_name);
        TextView navMail = (TextView) headerView.findViewById(R.id.menu_user_email);
        navUsername.setText(user_fullname);
        navMail.setText(user_mail);
    }
    // menu items
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
            case R.id.menu_snacks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Snacks()).commit();
                break;
            case R.id.menu_salads:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Salads()).commit();
                break;
            case R.id.menu_desserts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Desserts()).commit();
                break;
            case R.id.menu_main_courses:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Main_Courses()).commit();
                break;
            case R.id.menu_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Menu_Settings()).commit();
                break;
            case R.id.menu_order:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Menu_Order()).commit();
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
}