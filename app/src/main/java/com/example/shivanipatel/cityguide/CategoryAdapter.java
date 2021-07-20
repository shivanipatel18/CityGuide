package com.example.shivanipatel.cityguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by shivani.patel on 04-05-2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    Context context;
    ArrayList<CategoryClass> list;
    ClickListener clicklistener;
    public CategoryAdapter(Context context,ArrayList<CategoryClass> list){
        this.context=context;
        this.list=list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView image;
        Context context;


        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            image = (ImageView) itemView.findViewById(R.id.ivImage);

        }
        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context,QuestionPage.class));
            if(clicklistener!=null){
                clicklistener.itemClicked(v,getPosition());
            }
        }
    }
    public interface ClickListener
    {
        public  void itemClicked(View view,int position);
    }

    public void setClickListener(ClickListener clickListener){

        this.clicklistener=clickListener;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        TextView title=holder.title;
        ImageView image=holder.image;
        title.setText(list.get(position).getTitle());
        image.setImageResource(list.get(position).getImageid());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    }
