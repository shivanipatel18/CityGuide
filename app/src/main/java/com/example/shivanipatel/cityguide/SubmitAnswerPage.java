package com.example.shivanipatel.cityguide;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SubmitAnswerPage extends AppCompatActivity {
    ArrayList<HealthcareActivity.Answers> answersArrayList=new ArrayList<>();
    HealthcareActivity.Answers answers;
    Button btnSubmit;
    EditText etAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_answer_page);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);
        etAnswer=(EditText)findViewById(R.id.etAnswer);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               

            }
        });
    }
}
