package com.inmortal.messenger.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.inmortal.messenger.R;

import java.util.List;

public class NewCountryList extends RecyclerView.Adapter<NewCountryList.AddDriverAdapter>{
    List list;
    Context context;
    int layout;
    ReturnView returnView;
    int from;

    public interface ReturnView {
        void getAdapterView(View view, List objects, int position, int from);
    }
    public NewCountryList(List list1, Context context, int layout, ReturnView returnView , int i) {
        this.list = list1;
        this.context = context;
        this.layout = layout;
        this.returnView = returnView;
        this.from = from;
    }

    @NonNull
    @Override
    public NewCountryList.AddDriverAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_layout,parent,false);
        return new NewCountryList.AddDriverAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewCountryList.AddDriverAdapter holder, int position) {
        holder.setIsRecyclable(false);
        returnView.getAdapterView(holder.itemView, list, position, from);
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
