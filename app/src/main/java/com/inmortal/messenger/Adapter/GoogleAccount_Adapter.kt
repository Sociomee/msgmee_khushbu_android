package com.inmortal.messenger.Adapter;

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView

import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View

import de.hdodenhof.circleimageview.CircleImageView
import android.widget.RadioButton
import android.widget.TextView

import com.inmortal.messenger.R
import com.inmortal.messenger.model.GoogleAccountModel

class GoogleAccount_Adapter(var context: Context, var list: ArrayList<GoogleAccountModel>
) : RecyclerView.Adapter<GoogleAccount_Adapter.GoogleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoogleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.reycler_googleaccount, parent, false)
        return GoogleViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoogleViewHolder, position: Int) {
        holder.googleaccount_image.setImageResource(list[position].image)
        holder.text_name.text = list[position].name
        holder.text_email.text = list[position].gmail
    }

    override fun getItemCount(): Int {
        Log.d("TAG", "getItemCount: "+list.size)
        return list.size
    }

    inner class GoogleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var googleaccount_image: CircleImageView
        var text_name: RadioButton
        var text_email: TextView

        init {
            googleaccount_image = itemView.findViewById(R.id.googleaccount_image)
            text_name = itemView.findViewById(R.id.radiobtn_name)
            text_email = itemView.findViewById(R.id.text_email)
        }
    }
}