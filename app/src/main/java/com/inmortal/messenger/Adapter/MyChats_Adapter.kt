package com.inmortal.messenger.Adapter;

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

import com.inmortal.messenger.R
import com.inmortal.messenger.model.MyChatModel

class MyChats_Adapter (

    var context: Context,
    var list: ArrayList<MyChatModel>
    ) : RecyclerView.Adapter<MyChats_Adapter.MyChatsVH>(){

    private val colors = context.resources.getIntArray(R.array.myColors)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyChatsVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_my_chats_layout,parent,false)
        return MyChatsVH(view)

    }

    override fun onBindViewHolder(holder: MyChatsVH, position: Int) {

        holder.text_character.text = list[position].text_character
        holder.text_name.text = list[position].text_name
        holder.text_mb.text = list[position].text_mb

        val shape:GradientDrawable = GradientDrawable()
        shape.shape = GradientDrawable.OVAL
        shape.setColor(colors[(colors.indices).random()])
        holder.circle_icon.setBackgroundColor(colors[(colors.indices).random()])
        holder.circle_icon.background = shape


        holder.checkbox_my_chat.isChecked = list[position].checked==true

        holder.checkbox_my_chat.setOnClickListener {
            list[position].checked = !list[position].checked

            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    inner class MyChatsVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        var text_character:TextView
        var text_name:TextView
        var text_mb:TextView
        var circle_icon:RelativeLayout
        var checkbox_my_chat:CheckBox

        init {
            text_character = itemView.findViewById(R.id.text_character)
            text_name = itemView.findViewById(R.id.text_name)
            text_mb = itemView.findViewById(R.id.text_mb)
            circle_icon = itemView.findViewById(R.id.circle_icon)
            checkbox_my_chat = itemView.findViewById(R.id.checkbox_my_chat)

        }

    }
}