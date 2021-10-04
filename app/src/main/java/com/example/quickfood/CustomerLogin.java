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
    // initiate database
    SQLiteDatabase CustomerData;

    public static String User_FirstName = "";
    public static String User_LastName = "";
    public static String User_Email = "";
    public static String User_Full_Name = "";

    // intiate the login page entites
    EditText Login_Email;
    EditText Login_Password;

    // condition
    boolean success_login = false;

    @Override
    // on create go to activity_customerlogin
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlogin);

        // register the email and password values from the text input
        Login_Email = findViewById(R.id.TextView_LoginEmail);
        Login_Password = findViewById(R.id.TextView_LoginPassword);
        // go to openDatabase class
        openDatabase();
    }

    // open database jonas
    public void openDatabase() {
        CustomerData = this.openOrCreateDatabase("Jonas", MODE_PRIVATE, null);
    }

    // save the written email and password
    public void App_Login(View view){
        String login_em = Login_Email.getText().toString();
        String login_pw = Login_Password.getText().toString();

        // technique to count the matching values for email and password from the database customer table
        Cursor count_values = CustomerData.rawQuery("SELECT * FROM Customer WHERE Email='" +login_em+"' AND Password='"+login_pw+"'", null);

        int EM = count_values.getColumnIndex("Email");
        int FN = count_values.getColumnIndex("FirstName");
        int LN = count_values.getColumnIndex("LastName");
        // convert the count_value cursor result into integer

        int count = count_values.getCount();
        count_values.moveToFirst();

        // if match found the login success
        if (count == 1) {
            success_login = true;
            User_FirstName = count_values.getString(FN);
            User_LastName = count_values.getString(LN);
            User_Email = count_values.getString(EM);
            User_Full_Name = User_FirstName+""+User_LastName;

        }

        // no matching login in not success
        else if ( count == 0){
            success_login = false;
        }

        // go to Success_login_page method
        Success_login_page();
    }

    //Success_login_page method send user to MainActivity class
    public void Success_login_page() {
        if (success_login== true){
            Intent done = new Intent (CustomerLogin.this, MainActivity.class);
            startActivity(done);

            // success login message
            Toast.makeText(CustomerLogin.this, "Login Success", Toast.LENGTH_SHORT).show();
        } else {

            // unsuccess login message
            Toast.makeText(CustomerLogin.this, getString(R.string.Registration_Mistake1), Toast.LENGTH_SHORT).show();
        }
    }
    public static String getUser_FirstName(){
        return User_FirstName;
    }
    public static String getUser_LastName(){
        return User_LastName;
    }
    public static String getUser_Email(){
        return User_Email;
    }

    // method to send user to the CustomerRegistration class in case he press the signup button
    public void App_Signup(View view){
        Intent signup_page = new Intent(CustomerLogin.this, CustomerRegistration.class);
        startActivity(signup_page);
    }
}

