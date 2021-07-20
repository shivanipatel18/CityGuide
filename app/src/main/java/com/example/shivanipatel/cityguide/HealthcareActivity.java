package com.example.shivanipatel.cityguide;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HealthcareActivity extends AppCompatActivity {
    ArrayList<String> groupList;
    HashMap<HealthcareActivity.Answers, List<String>> childList=new HashMap<>();
    ArrayList<Answers> answers=new ArrayList<>();

    public static HealthcareAdapter healthcareAdapter;
    public static ExpandableListView elAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_answer_page);

//        createGroupList();
//        createChildList();

        Answers ans1=new Answers();
        ans1.setAns("Could my cell phone kill me?");

        Answers ans2=new Answers();
        ans2.setAns("Will vitamin D save my life? Should I really be taking four times the recommended daily dose?");

        Answers ans3=new Answers();
        ans3.setAns("Is it okay to cleanse your body by fasting from time to time?");

        Answers ans4=new Answers();
        ans4.setAns("Can I trust my tap water?");

        Answers ans5=new Answers();
        ans5.setAns("Is my microwave giving me cancer?");

        Answers ans6=new Answers();
        ans6.setAns("How long am I contagious when I have the flu or a cold?");

        Answers ans7=new Answers();
        ans7.setAns("Is it true that 48 hours after starting antibiotics I can't infect someone else?");

        answers.add(ans1);
        answers.add(ans2);
        answers.add(ans3);
        answers.add(ans4);
        answers.add(ans5);
        answers.add(ans6);
        answers.add(ans7);

        List<String> ans8=new ArrayList<String>();
        ans8.add("Comment");

        List<String> ans9=new ArrayList<String>();
        ans9.add("Comment");

        List<String> ans10=new ArrayList<String>();
        ans10.add("Comment");

        List<String> ans11=new ArrayList<String>();
        ans11.add("Comment");

        List<String> ans12=new ArrayList<String>();
        ans12.add("Comment");

        List<String> ans13=new ArrayList<String>();
        ans13.add("Comment");

        List<String> ans14=new ArrayList<String>();
        ans14.add("Comment");

        childList.put(answers.get(0), ans8);
        childList.put(answers.get(1),ans9);
        childList.put(answers.get(2),ans10);
        childList.put(answers.get(3),ans11);
        childList.put(answers.get(4),ans12);
        childList.put(answers.get(5),ans13);
        childList.put(answers.get(6),ans14);


        elAnswer=(ExpandableListView)findViewById(R.id.elAnswer);
        healthcareAdapter=new HealthcareAdapter(this,answers,childList);
        elAnswer.setAdapter(healthcareAdapter);

        elAnswer.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        elAnswer.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(HealthcareActivity.this, "Answer viewed", Toast.LENGTH_SHORT).show();
                return false;
            }

        });



       /* elAnswer.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != lastExpandedPosition) {
                    elAnswer.collapseGroup(lastExpandedPosition);
                    lastExpandedPosition = groupPosition;
                }
            }
        });*/

    }


    public  class Answers{
        public String ans;
        public int count;

        public String getAns() {
            return ans;
        }

        public void setAns(String ans) {
            this.ans = ans;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
