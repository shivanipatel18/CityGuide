package com.example.shivanipatel.cityguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class QuestionPage extends AppCompatActivity {

    ArrayList<String> groupList,childList;
    Map<String,ArrayList<String>> answerList;
     ExpandableAdapter expandableAdapter;
    ExpandableListView expandableListView;
    String Questions[]={"Could my cell phone kill me? ","Will vitamin D save my life? Should I really be taking four times the recommended daily dose?",
    "Is it okay to cleanse your body by fasting from time to time?"," Can I trust my tap water? ","Is my microwave giving me cancer? ",
            "How long am I contagious when I have the flu or a cold?","Is it true that 48 hours after starting antibiotics I can't infect someone else?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_page);

        createGroupList();
        creteChildList();

        expandableListView=(ExpandableListView)findViewById(R.id.expandableListView);
       expandableAdapter=new ExpandableAdapter(this,answerList,groupList);
        expandableListView.setAdapter(expandableAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                //String selected = (String) expandableAdapter.getChild(groupPosition, childPosition);
                if (childPosition == 0) {
                    //Toast.makeText(QuestionPage.this, selected, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuestionPage.this, HealthcareActivity.class);
                    startActivity(intent);
                } else {
                    //Toast.makeText(QuestionPage.this, selected, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuestionPage.this, SubmitAnswerPage.class);
                    startActivity(intent);
                }
                return true;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                    lastExpandedPosition = groupPosition;
                }
            }
        });
    }

    public  void createGroupList(){
        groupList=new ArrayList<>();
        groupList.addAll(Arrays.asList(Questions));
    }

    public void creteChildList(){
        String child[]={"View Answer","Submit Answer"};

        answerList=new LinkedHashMap<String,ArrayList<String>>();
        for(String childName:groupList){
            for(int i=0; i<(Questions.length);i++) {
                if (childName.equals(Questions[i])) {
                    loadchild(child);
                }
            }
            answerList.put(childName,childList);
        }
    }

    private void loadchild(String[] child) {
        childList=new ArrayList<>();
        for(String childName:child)
            childList.add(childName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_question,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.AddQuestion){
            Intent intent=new Intent(QuestionPage.this,AddQuestionPage.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
