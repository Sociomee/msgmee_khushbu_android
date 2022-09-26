package com.inmortal.messenger.Settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inmortal.messenger.Adapter.BlockUser_Adapter
import com.inmortal.messenger.R
import com.inmortal.messenger.model.BlockUser_Model

class Blockedusers_Activity : AppCompatActivity() {

    lateinit var img_back_arrow: ImageView
    lateinit var recycler_blocked_user: RecyclerView
    lateinit var text_unblock_all: TextView

    lateinit var adapter: BlockUser_Adapter
    val list = ArrayList<BlockUser_Model>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blockedusers)


        img_back_arrow = findViewById(R.id.img_back_arrow)
        recycler_blocked_user = findViewById(R.id.recycler_blocked_user)
        text_unblock_all = findViewById(R.id.text_unblock_all)

        img_back_arrow.setOnClickListener(View.OnClickListener {

            onBackPressed()

        })

        for (i in 0..1){
            if (i%2==0){
                val model = BlockUser_Model(R.drawable.fashion8,"Theresa Webb","theresa_test")
                list.add(model)
            }
            else{
                val model = BlockUser_Model(R.drawable.fashion14,"Jacob Jones","jacob_test")
                list.add(model)

            }
        }


        adapter = BlockUser_Adapter(list,this)
        recycler_blocked_user.adapter = adapter

        text_unblock_all.setOnClickListener(View.OnClickListener {

            recycler_blocked_user.visibility = View.GONE

        })
    }
}