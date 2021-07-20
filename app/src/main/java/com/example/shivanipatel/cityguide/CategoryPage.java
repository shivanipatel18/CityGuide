package com.example.shivanipatel.cityguide;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryPage extends AppCompatActivity implements CategoryAdapter.ClickListener{

    String title[]={"Healthcare","Education","Jobs","Household","Recharge","Map","Beauty and Cosmetics","Laundary","Hotels and Restaurants",
                        "Ticket Booking","Ola or Uber","Shopping","Entertainment"};
    int image_id[]={R.drawable.right_arrow,R.drawable.right_arrow,R.drawable.right_arrow,R.drawable.right_arrow,R.drawable.right_arrow,R.drawable.right_arrow,
            R.drawable.right_arrow,R.drawable.right_arrow,R.drawable.right_arrow,R.drawable.right_arrow,R.drawable.right_arrow,R.drawable.right_arrow,
            R.drawable.right_arrow};

    RecyclerView recyclerView;
    ArrayList<CategoryClass> list=new ArrayList<>();
    CategoryAdapter adapter;

   CategoryAdapter.ClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_page);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        for(int i=0;i<(title.length);i++){
            CategoryClass categoryClass=new CategoryClass(""+title[i],image_id[i]);
            list.add(categoryClass);
        }

        adapter=new CategoryAdapter(CategoryPage.this,list);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
          }

    @Override
    public void itemClicked(View view, int position) {
       //Toast.makeText(CategoryPage.this, "Recycler view item clicked", Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(CategoryPage.this,QuestionPage.class);
            startActivity(intent);



    }


}
