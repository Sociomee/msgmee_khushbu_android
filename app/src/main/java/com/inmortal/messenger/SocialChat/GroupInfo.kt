package com.inmortal.messenger.SocialChat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.inmortal.messenger.R

class GroupInfo : AppCompatActivity() {
    lateinit var search: SearchView
    lateinit var leave: TextView
    lateinit var change: TextView
    lateinit var save: TextView
    lateinit var add: TextView
    lateinit var info_desc: LinearLayout
    lateinit var edit: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_info)

        search = findViewById(R.id.search_g)
        leave = findViewById(R.id.leave)
        info_desc = findViewById(R.id.info_desc)
        edit = findViewById(R.id.edit)
        change = findViewById(R.id.change)
        save = findViewById(R.id.save)
        save.setOnClickListener(View.OnClickListener {
            save.visibility = View.GONE
            change.visibility = View.GONE
            edit.visibility = View.GONE
            info_desc.visibility = View.VISIBLE

        })
        add = findViewById(R.id.add)
        add.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@GroupInfo , AddMemberToGrp::class.java)
            startActivity(intent)
        })
        leave.setOnClickListener(View.OnClickListener {
            leave.visibility = View.GONE
            info_desc.visibility = View.GONE
            edit.visibility = View.VISIBLE
            change.visibility = View.VISIBLE
            save.visibility = View.VISIBLE
            add.visibility = View.VISIBLE
        }
        )
    }

    fun back(view: View) {
        val intent = Intent(this@GroupInfo , Group::class.java)
        startActivity(intent)
    }
}