package com.example.shivanipatel.cityguide;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddQuestionPage extends AppCompatActivity {

    Spinner spinner;
    /* String SpinnerCategory[]={"Healthcare","Education","Jobs","Household","Recharge","Map","Beauty and Cosmetics","Laundary","Hotels and Restaurants",
             "Ticket Booking","Ola or Uber","Shopping","Entertainment"};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_question_page);

        /*spinner=(Spinner)findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.features, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddQuestionPage.this, "Category Selected", Toast.LENGTH_SHORT).show();

            }
        });*/




    }
}
