package com.example.shivanipatel.cityguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shivani.patel on 18-05-2016.
 */
public class HealthcareAdapter extends BaseExpandableListAdapter {

    Context contextAnswer;
    ArrayList<HealthcareActivity.Answers> listAnswer;
    HashMap<HealthcareActivity.Answers,List<String>> listMapAnswer;
    LayoutInflater inflater;

    public HealthcareAdapter(Context contextAnswer, ArrayList<HealthcareActivity.Answers> listAnswer,  HashMap<HealthcareActivity.Answers,List<String>> listMapAnswer) {
        this.contextAnswer = contextAnswer;
        this.listAnswer = listAnswer;
        this.listMapAnswer = listMapAnswer;
    }

    @Override
    public int getGroupCount() {
        return listAnswer.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listMapAnswer.get(listAnswer.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listAnswer.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listMapAnswer.get(listAnswer.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //String group=(String)getGroup(groupPosition);
        final GroupViewHolder groupViewHolder;
        final HealthcareActivity.Answers ans=listAnswer.get(groupPosition);


        if(convertView==null){
            groupViewHolder=new GroupViewHolder();

            inflater=(LayoutInflater)contextAnswer.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.view_answer_group_layout,null);

            groupViewHolder.tvHealthAone=(TextView)convertView.findViewById(R.id.tvHealthAone);
            groupViewHolder.tvLike=(TextView)convertView.findViewById(R.id.tvLike);
            groupViewHolder.tvComment=(TextView)convertView.findViewById(R.id.tvComment);
            groupViewHolder.tvCount=(TextView)convertView.findViewById(R.id.tvCount);
            convertView.setTag(groupViewHolder);
        }
        else {
            groupViewHolder=(GroupViewHolder)convertView.getTag();
        }


        groupViewHolder.tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HealthcareActivity.elAnswer.isGroupExpanded(groupPosition)){
                    HealthcareActivity.elAnswer.collapseGroup(groupPosition);
                }
                else{
                    HealthcareActivity.elAnswer.expandGroup(groupPosition);
                }
                int count=HealthcareActivity.healthcareAdapter.getGroupCount();
                for(int i=0;i<count;i++){
                    if(i != groupPosition){
                        HealthcareActivity.elAnswer.collapseGroup(i);
                    }
                }
            }
        });

        groupViewHolder.tvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int count=ans.getCount();
                count++;
                ans.setCount(count);
                if(count != 0){
                    groupViewHolder.tvCount.setText(String.valueOf(count));
                }
            }
        });


        groupViewHolder.tvHealthAone.setText(ans.getAns());
        groupViewHolder.tvCount.setText(String.valueOf(ans.getCount()));
        return convertView;



    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String item=(String)getChild(groupPosition, childPosition);
        if(convertView==null){
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.view_answer_child_layout,parent,false);
        }
        EditText etComment=(EditText)convertView.findViewById(R.id.etComment);
        etComment.setText(item);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



    public static  class GroupViewHolder{
        TextView tvHealthAone;
        TextView tvLike;
        TextView tvComment;
        TextView tvCount;
    }

}
