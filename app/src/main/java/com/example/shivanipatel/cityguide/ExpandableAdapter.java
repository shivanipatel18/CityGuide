package com.example.shivanipatel.cityguide;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by shivani.patel on 05-05-2016.
 */
public class ExpandableAdapter extends BaseExpandableListAdapter {

    Context context;
    Map<String,ArrayList<String>> listMap;
    ArrayList<String> list;

    public ExpandableAdapter(Context context, Map<String, ArrayList<String>> listMap, ArrayList<String> list) {
        this.context = context;
        this.listMap = listMap;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listMap.get(list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listMap.get(list.get(groupPosition)).get(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group=(String)getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.group_layout,null);
        }
        TextView tvQuestion=(TextView)convertView.findViewById(R.id.tvQuestion);
        tvQuestion.setTypeface(null, Typeface.BOLD);
        tvQuestion.setText(group);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String item=(String)getChild(groupPosition,childPosition);
        if(convertView==null){
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.child_layout,parent,false);
        }
        TextView tvAnswer=(TextView)convertView.findViewById(R.id.tvAnswer);
        tvAnswer.setText(item);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
