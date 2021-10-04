package com.example.quickfood;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerLogin extends AppCompatActivity {
    SQLiteDatabase CustomerData;

    EditText Login_Email;
    EditText Login_Password;


    boolean success_login = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlogin);
        Login_Email = findViewById(R.id.TextView_LoginEmail);
        Login_Password = findViewById(R.id.TextView_LoginPassword);
        openDatabase();

    }
    public void openDatabase() {
        CustomerData = this.openOrCreateDatabase("Jonas", MODE_PRIVATE, null);
    }
    public void App_Login(View view){

        String login_em = Login_Email.getText().toString();
        String login_pw = Login_Password.getText().toString();

        Cursor count_values = CustomerData.rawQuery("SELECT * FROM Customer WHERE Email='" +login_em+"' AND Password='"+login_pw+"'", null);



        int count = count_values.getCount();
        count_values.moveToFirst();

        if (count == 1) {
            success_login = true;

        }
        else if ( count == 0){
            success_login = false;
        }
        Success_login_page();
    }

    public void Success_login_page() {
        if (success_login== true){
            Intent done = new Intent (CustomerLogin.this, MainActivity.class);
            startActivity(done);

            Toast.makeText(CustomerLogin.this, "Login Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CustomerLogin.this, getString(R.string.Registration_Mistake1), Toast.LENGTH_SHORT).show();
        }

    }
    public void App_Signup(View view){
        Intent signup_page = new Intent(CustomerLogin.this, CustomerRegistration.class);
        startActivity(signup_page);
    }

}

