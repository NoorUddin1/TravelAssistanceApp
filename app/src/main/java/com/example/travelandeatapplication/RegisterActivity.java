package com.example.travelandeatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, reTypePassword;
    Button signup, signin;
    DBHelper db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

          username = (EditText) findViewById(R.id.username);
          password = (EditText) findViewById(R.id.password);
          reTypePassword = (EditText) findViewById(R.id.retypePassword);
          signin = (Button) findViewById(R.id.btnSignin);
          signup = (Button) findViewById(R.id.btnSignup);
          db = new DBHelper(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String reTypePass = reTypePassword.getText().toString();

                if(user.equals("") || pass.equals("") || reTypePass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please Enter The Required Details", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(reTypePass)){
                        Boolean checkuser = db.checkUserName(user);
                        if(checkuser==false) {
                            Boolean insert = db.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this, "You have successfully registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration has failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

    }
}