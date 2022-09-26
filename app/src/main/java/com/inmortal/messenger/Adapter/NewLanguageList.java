package com.inmortal.messenger.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inmortal.messenger.R;

import java.util.List;

public class NewLanguageList extends RecyclerView.Adapter<NewLanguageList.AddDriverAdapter>{
    List list;
    Context context;
    int layout;
    ReturnView returnView;
    int from;
    public interface ReturnView {
        void getLangAdapterView(View view, List objects, int position, int from);
    }
    public NewLanguageList(List list1, Context context, int layout, ReturnView returnView , int i) {
        this.list = list1;
        this.context = context;
        this.layout = layout;
        this.returnView = returnView;
        this.from = from;
    }

    @NonNull
    @Override
    public NewLanguageList.AddDriverAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_lang_item,parent,false);
       itemView.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View view, MotionEvent motionEvent) {
               if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
               {
                   view.setBackgroundColor(Color.parseColor("#E3F4EA"));
               }
               if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL)
               {
                   view.setBackgroundColor(Color.TRANSPARENT);
               }
               return false;
           }
       });
        return new NewLanguageList.AddDriverAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewLanguageList.AddDriverAdapter holder, int position) {
        holder.setIsRecyclable(false);
        returnView.getLangAdapterView(holder.itemView, list, position, from);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class AddDriverAdapter extends RecyclerView.ViewHolder {
        public AddDriverAdapter(@NonNull View itemView) {
            super(itemView);
        }
    }
}
