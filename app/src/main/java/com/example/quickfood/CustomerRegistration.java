package com.example.quickfood;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerRegistration extends AppCompatActivity {

    // intiate database
    SQLiteDatabase CustomerData;
    //create registration entities variables
    EditText Register_FirstName;
    EditText Register_LastName;
    EditText Register_Email;
    EditText Register_Password;
    EditText Register_Phone;
    EditText Register_Address;

// one create run activity_customerregistration
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerregistration);

        // call CreateCustomerData method
        CreateCustomerData();

        // get the entities values from activity_customerregistration inputs
        Register_FirstName = findViewById(R.id.TextView_FirstName);
        Register_LastName = findViewById(R.id.TextView_LastName);
        Register_Email = findViewById(R.id.TextView_Email);
        Register_Password = findViewById(R.id.TextView_Password);
        Register_Phone = findViewById(R.id.TextView_Phone);
        Register_Address = findViewById(R.id.TextView_Address);
    }
// method to create the database Jonas and the internal table customer
    public void CreateCustomerData() {
        CustomerData = this.openOrCreateDatabase("Jonas", MODE_PRIVATE, null);
        CustomerData.execSQL("CREATE TABLE IF NOT EXISTS customer (Email VARCHAR(45) PRIMARY KEY, FirstName VARCHAR(45), LastName VARCHAR(45), Phone VARCHAR(20), Address VARCHAR(50), Password VARCHAR(45))");
        //CustomerData.execSQL("INSERT INTO Customer (Email, FirstName, LastName, Phone, Address, Password) VALUES ('Jonas', 'Ebid', '223322@usn.no', '12345678', 'Hønefoss', '12345678')");
    }
    // method will save all the entered registration entities
    public void Registration_button(View view){
        String firstname = Register_FirstName.getText().toString();
        String lastname = Register_LastName.getText().toString();
        String email = Register_Email.getText().toString();
        String password = Register_Password.getText().toString();
        String phone = Register_Phone.getText().toString();
        String address = Register_Address.getText().toString();

        // function to control the entries condition
        if (firstname.matches("") || lastname.matches("") || email.matches("") || password.matches("")){
            Toast.makeText(CustomerRegistration.this, getString(R.string.Registration_Mistake2), Toast.LENGTH_SHORT).show();
        }else {
            // inserting the saved entities into customer table
            CustomerData.execSQL("INSERT INTO Customer (FirstName, LastName, Email, Password, Phone, Address) VALUES" +
                    " ('" + firstname + "', '" + lastname + "', '" + email + "', '" + password + "', '" + phone  + "', '" +  address + "')");

            // after inserting go to CustomerLogin class
            Intent login_page = new Intent(CustomerRegistration.this, CustomerLogin.class);
            startActivity(login_page);
        }
    }
}




