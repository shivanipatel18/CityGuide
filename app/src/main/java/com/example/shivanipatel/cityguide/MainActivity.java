package com.example.shivanipatel.cityguide;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {


    //for sign in
    Button btnSignin,btnNewAccount;
    EditText etUsername,etPassword;

    LoginButton btnFacebook;
    private CallbackManager callbackManager;

    SharedPreferences sharedPreferences;
    public  static final String myPreference="mypref";
    public  static final String Email="EmailKey";
    public  static final String Password="PasswordKey";
    String email,pass,editemail,editpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();



        //shared preferences
        sharedPreferences=getSharedPreferences(myPreference, Context.MODE_PRIVATE);
        email=sharedPreferences.getString(Email, "");
        pass=sharedPreferences.getString(Password, "");

        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);

        btnSignin=(Button)findViewById(R.id.btnSignin);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editemail=etUsername.getText().toString();
                editpassword=etPassword.getText().toString();

                if (editemail.equals("")) {
                        etUsername.setError("This field is required");
                    }

                else if(editpassword.equals("")) {
                        etPassword.setError("This field is required");
                }

                else if(editemail.equals(email) && editpassword.equals(pass)) {
                     Intent intent = new Intent(getApplicationContext(), HomePage.class);
                     startActivity(intent);
                 }
                else
                     Toast.makeText(MainActivity.this, "Enter valid Username or Password", Toast.LENGTH_SHORT).show();


            }
        });

        //new account sign in
               btnNewAccount=(Button)findViewById(R.id.btnNewAccount);
                btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,RegisterClass.class);
                startActivity(intent);
            }
        });


        btnFacebook=(LoginButton)findViewById(R.id.btnFacebook);
        btnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Login Attempt cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        etUsername.setText("");
        etPassword.setText("");
        super.onResume();
        Log.e("------------", "onResume called");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("---------------","onStart was called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("---------------","onPause was called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("----------------","onStop was called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("-----------------", "onRestart was called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("--------------","onDestroy was called");
    }

}
