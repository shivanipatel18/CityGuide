package com.example.shivanipatel.cityguide;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by shivani.patel on 06-04-2016.
 */
public class RegisterClass extends AppCompatActivity {

    EditText etName,etEmail,etPhone,etGender,etPassword,etRePassword;
    Button register;
    boolean NameisValid,EmailisValid,PhoneisValid,PassisValid,RePassisValid;

    String GenderArray[]={"Not Specified","Male","Female"};

    SharedPreferences sharedPreferences;
    public  static final String myPreference="mypref";
    public  static final String Name="NameKey";
    public  static final String Email="EmailKey";
    public static final String Phone="Phone";
    public static final String Gender="Gender";
    public  static final String Password="PasswordKey";
    public  static final String ReEnterPass="ReEnterKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etGender = (EditText) findViewById(R.id.etGender);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRePassword = (EditText) findViewById(R.id.etRePassword);

        //focus on edittext
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    checkName();
                }
            }
        });

        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    checkEmail();
                }
            }
        });

        etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    checkPhone();
                }
            }
        });

        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    checkPass();
                }
            }
        });

       //wheelview in gender edittext
        etGender.setFocusable(false);
        etGender.setClickable(true);
        etGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.wheel_view,null);
                WheelView wheelView=(WheelView)view.findViewById(R.id.wv_wheel);
                wheelView.setOffset(2);
                wheelView.setItems(Arrays.asList(GenderArray));
                wheelView.setSeletion(0);
                etGender.setText(GenderArray[0]);
                wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        etGender.setText(item);
                    }
                });

                new AlertDialog.Builder(RegisterClass.this)
                        .setTitle("Select Gender")
                        .setView(view)
                        .setPositiveButton("OK",null)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                etGender.setText("");
                            }
                        }).show();
            }
        });

        //for registration of new user
        register = (Button) findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();
                String repass = etRePassword.getText().toString().trim();


                if (isValidName(name) == 1) {
                    etName.setError("This field is required");
                } else if (isValidEmail(email) == 1) {
                    etEmail.setError("This field is required");
                } else if (isValidEmail(email) == 3) {
                    etEmail.setError("Please enter valid email id");
                } else if (isValidPhone(phone) == 1) {
                    etPhone.setError("This fiels is required");
                } else if (isValidPhone(phone) == 3) {
                    etPhone.setError("Please enter valid phone number");
                } else if (isValidPassword(pass) == 1) {
                    etPassword.setError("This field is required and must be atleast of 6 characters");
                } else if (isValidPassword(pass) == 3) {
                    etPassword.setError("Password should contain: a number,a symbol or a uppercase letter");
                } else if (isValidRePassword(repass) == 1) {
                    etRePassword.setError("This field is required");
                } else if (isValidRePassword(repass) == 3) {
                    etRePassword.setError("Confirmation of Password is required");
                } else {
                    sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Name, etName.getText().toString());
                    editor.putString(Email, etEmail.getText().toString());
                    editor.putString(Phone, etPhone.getText().toString());
                    editor.putString(Password, etPassword.getText().toString());
                    editor.putString(ReEnterPass, etRePassword.getText().toString());

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    editor.commit();
                }
            }
        });
    }

//
    public  void checkName(){
        if(etName.getText().toString().length()==0){
            NameisValid= false;
            etName.setError("Name field is required");
        }
        else if(isValidName(etName.getText().toString())==1){
            NameisValid=false;
            etName.setError("This field is required");
        }else if(isValidName(etName.getText().toString())==2) {
            NameisValid = true;

        }else
            NameisValid=false;

    }

    public void checkEmail(){
        if(etEmail.getText().toString().length()==0){
            EmailisValid=false;
            etEmail.setError("Email field is required");
        } else if(isValidEmail(etEmail.getText().toString())==1)
        {
            EmailisValid=false;
            etEmail.setError("This field is required");
        }else if (isValidEmail(etEmail.getText().toString())==2) {
            EmailisValid = true;
        }else if(isValidEmail(etEmail.getText().toString())==3)
        {
            EmailisValid=false;
            etEmail.setError("Please enter valid email id");
        }
        else
            EmailisValid=false;

    }

    public void checkPhone() {
        if (etPhone.getText().toString().length() == 0) {
            PhoneisValid = false;
            etPhone.setError("Phone field is required");
        } else if (isValidPhone(etPhone.getText().toString()) == 1) {
            PhoneisValid = false;
            etPhone.setError("This fiels is required");
        } else if (isValidPhone(etPhone.getText().toString()) == 2) {
            PhoneisValid = true;
        } else if (isValidPhone(etPhone.getText().toString()) == 3) {
            PhoneisValid = false;
            etPhone.setError("Please enter phone number upto 10 digits");
        } else
            PhoneisValid = false;
    }

    public void checkPass() {
        if (etPassword.getText().toString().length() == 0) {
            PassisValid = false;
            etPassword.setError("Password field is required");
        } else if (isValidPassword(etPassword.getText().toString()) == 1) {
            PassisValid = false;
            etPassword.setError("This field is required");
        }
        else if(isValidPassword(etPassword.getText().toString())==2){
            PassisValid=true;
        }
        else
            PassisValid=false;
            //etPassword.setError("Please enter password contain atleat:a uppercase, a symbol and a number and greater than 6 characters");
    }

    public void checkRePass(){
        if(etRePassword.getText().toString().length()==0){
            RePassisValid=false;
            etRePassword.setError("Confirm Password is required");
        }
        else if(isValidRePassword(etRePassword.getText().toString())==1){
            RePassisValid=false;
            etRePassword.setError("This field is required");
        }
        else if(isValidRePassword(etRePassword.getText().toString())==2){
            RePassisValid=true;
        }
        else
            RePassisValid=false;
            //etRePassword.setError("Should be match with password you entered");
    }

//validations of edittext
    public int isValidName(String name){
        if(TextUtils.isEmpty(name)){
            return 1;
        }
        else if(name.length()<=15) {
            return 2;
        }
        else
            return 3;
    }


    public int isValidEmail(String email)
    {
        if(TextUtils.isEmpty(email)){
            return 1;
        }
        else if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return 2;
        }
        else
            return 3;
    }

    public int isValidPhone(String phone){
        if(TextUtils.isEmpty(phone)){
            return 1;
        }
        else if(phone.length()>=6 && phone.length()<=10)
            return 2;
        else
            return 3;
    }



    public int isValidPassword(String pass) {
        String pattern="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        if (pass == null || pass.length() < 6) {
            return 1;
        }
        else if(Pattern.compile(pattern).matcher(pass).find()){
            return 2;
        }
        /*else if(Pattern.compile("[0-9]").matcher(pass).find() && Pattern.compile("[a-z]").matcher(pass).find()){

            return 2;
        }else if(Pattern.compile("[a-z]").matcher(pass).find() && Pattern.compile("[@#$%^&+=]").matcher(pass).find()){

            return 2;
        }else if(Pattern.compile("[A-Z]").matcher(pass).find() && Pattern.compile("[a-z]").matcher(pass).find()){

            return 2;
        }*/
        else {
            return 3;
        }

    }

    public int isValidRePassword(String repass){
        if(TextUtils.isEmpty(repass))
        {
            return 1;
        }
       else  if(repass.equals(etPassword.getText().toString())){
           return  2;
        }
        else
            return 3;

    }

    @Override
    protected void onResume() {
        etName.setText("");
        etEmail.setText("");
        etPhone.setText("");
        etGender.setText("");
        etPassword.setText("");
        etRePassword.setText("");
        super.onResume();
        Log.e("--------------","On Resume called");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("---------------","onStart was called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("---------------", "onPause was called");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("--------------","onStop was called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("---------------","onRestart was called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("--------------", "onDestroy was called");
    }
}
