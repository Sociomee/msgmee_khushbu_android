package com.inmortal.messenger.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inmortal.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class CheckNameList extends  RecyclerView.Adapter<CheckNameList.AddDriverAdapter> {
    ArrayList<String> arrNameString;
    Context context;
    int layout;
    ReturnView returnView;
    int from;
    public interface ReturnView {
        void getAdapterView(View view, List objects, int position, int from);
    }
    public CheckNameList(ArrayList<String> arrNameString, Context context, int layout, ReturnView returnView , int i) {
        this.arrNameString = arrNameString;
        this.context = context;
        this.layout = layout;
        this.returnView = returnView;
    }

    @NonNull
    @Override
    public CheckNameList.AddDriverAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkname_layout,parent,false);
        return new CheckNameList.AddDriverAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckNameList.AddDriverAdapter holder, int position) {
        returnView.getAdapterView(holder.itemView, arrNameString, position, from);
    }

    @Override
    public int getItemCount() {
        int limit = 6;
        return Math.min(arrNameString.size() , limit);
    }

    public class AddDriverAdapter extends RecyclerView.ViewHolder {
        public AddDriverAdapter(@NonNull View itemView) {
            super(itemView);
        }
    }
}
