package com.inmortal.messenger.Adapter;

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inmortal.messenger.R
import com.inmortal.messenger.model.MyChatss_ClearModel

class MyChats_ClearAdapter(
    var context: Context,
    var list: ArrayList<MyChatss_ClearModel>)
    : RecyclerView.Adapter<MyChats_ClearAdapter.MyClear_ChatVH>() {

    private val colors = context.resources.getIntArray(R.array.myColors)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyClear_ChatVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_clear_chat_layout,parent,false)
        return MyClear_ChatVH(view)
    }

    override fun onBindViewHolder(holder: MyClear_ChatVH, position: Int) {
       holder.text_char.text = list[position].text_char
        holder.text_clear_name.text = list[position].text_clear_name
        holder.text_mb1.text = list[position].text_mb1

        val shape:GradientDrawable = GradientDrawable()
        shape.shape = GradientDrawable.OVAL
        shape.setColor(colors[(colors.indices).random()])
        holder.circle_clear_icon!!.setBackgroundColor(colors[(colors.indices).random()])
        holder.circle_clear_icon!!.background = shape

        holder.checkbox_clear_chat.isChecked = list[position].checked == true
        holder.checkbox_clear_chat.setOnClickListener {
            list[position].checked = !list[position].checked

            notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int {
      return list.size
    }

    inner class MyClear_ChatVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_char: TextView
        var text_clear_name: TextView
        var text_mb1: TextView
        var circle_clear_icon: RelativeLayout
        var checkbox_clear_chat:CheckBox

        init {
            text_char = itemView.findViewById(R.id.text_char)
            text_clear_name = itemView.findViewById(R.id.text_clear_name)
            text_mb1 = itemView.findViewById(R.id.text_mb1)
            circle_clear_icon = itemView.findViewById(R.id.circle_clear_icon)
            checkbox_clear_chat = itemView.findViewById(R.id.checkbox_clear_chat)
        }

    }


}