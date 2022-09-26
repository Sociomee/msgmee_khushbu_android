package com.inmortal.messenger.Adapter;

import android.content.Context
import android.util.Log

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import de.hdodenhof.circleimageview.CircleImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.inmortal.messenger.R
import com.inmortal.messenger.model.BlockUser_Model
import java.util.ArrayList

class BlockUser_Adapter(var list: ArrayList<BlockUser_Model>, var context: Context) :
    RecyclerView.Adapter<BlockUser_Adapter.BlockUserVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockUserVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_block_user, parent, false)
        return BlockUserVH(view)
    }

    override fun onBindViewHolder(holder: BlockUserVH, position: Int) {
        holder.block_image.setImageResource(list[position].image)
        holder.text_block_name.text = list[position].text_block_name
        holder.text_subname.text = list[position].text_subname

        holder.unblock_btn.setOnClickListener(View.OnClickListener {

            holder.constraint_blocked_layout.visibility = View.GONE





            val snackbarr = Snackbar.make(it , list[position].text_block_name , Snackbar.LENGTH_LONG)
            snackbarr.setAction("Undo" , View.OnClickListener {
                snackbarr.dismiss()
                Toast.makeText(context,"ABCD" + holder.constraint_blocked_layout,Toast.LENGTH_LONG).show()
                Log.d("TAG", "onBindViewHolder: "+holder.constraint_blocked_layout)
                 holder.constraint_blocked_layout.visibility  = View.VISIBLE
            })
            snackbarr.show()

        })

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class BlockUserVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var block_image: CircleImageView
        var text_block_name: TextView
        var text_subname: TextView
        var unblock_btn:Button
        lateinit var constraint_blocked_layout:ConstraintLayout

        init {
            block_image = itemView.findViewById(R.id.blocke_image)
            text_block_name = itemView.findViewById(R.id.text_block_name)
            text_subname = itemView.findViewById(R.id.text_subname)
            unblock_btn = itemView.findViewById(R.id.unblock_btn)
            constraint_blocked_layout = itemView.findViewById(R.id.constraint_blocked_layout)
        }
    }
}