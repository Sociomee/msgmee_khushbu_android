package com.inmortal.messenger.Broadcast

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import com.inmortal.messenger.R
import com.inmortal.messenger.activity.Dashboard

class NewBroadcast : AppCompatActivity() {
    lateinit var checkBox: CheckBox
    lateinit var checkBox_2: CheckBox
    lateinit var done: TextView
    lateinit var titlee: TextView
    lateinit var filter: ImageView
    lateinit var select_count: LinearLayout
    override fun onCreate(donedInstanceState: Bundle?)
    {
        super.onCreate(donedInstanceState)
        setContentView(R.layout.activity_new_broadcast)
        checkBox = findViewById(R.id.checkbox)
        checkBox_2 = findViewById(R.id.checkbox_2)
        done = findViewById(R.id.done)
        titlee = findViewById(R.id.titlee)
        filter = findViewById(R.id.filter)
        select_count = findViewById(R.id.select_count)
        checkBox.setOnClickListener(View.OnClickListener {
            if (checkBox.isChecked||checkBox_2.isChecked)
            {
                done.setTextColor(Color.parseColor("#81C14B"))
                filter.visibility= VISIBLE
                select_count.visibility= VISIBLE
                titlee.text = "Add Participants"
            }
            else
            {
                done.setTextColor(Color.parseColor("#9D9D9D"))
                filter.visibility=GONE
                select_count.visibility=GONE
                titlee.text = "New Broadcast"
            }
        })
        checkBox_2.setOnClickListener(View.OnClickListener {
            if (checkBox_2.isChecked||checkBox.isChecked)
            {
                done.setTextColor(Color.parseColor("#81C14B"))
                filter.visibility= VISIBLE
                select_count.visibility= VISIBLE
                titlee.text = "Add Participants"
            }
            else
            {
                done.setTextColor(Color.parseColor("#9D9D9D"))
                filter.visibility=GONE
                select_count.visibility=GONE
                titlee.text = "New Broadcast"
            }
        })
titlee.setOnClickListener(View.OnClickListener {
    val intent = Intent(this@NewBroadcast , BroadcastInfo::class.java)
    startActivity(intent)
})
        done.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@NewBroadcast , BroadcastInfo::class.java)
            startActivity(intent)
        })
    }

    fun back(view: View) {
        val intent = Intent(this@NewBroadcast , Dashboard::class.java)
        startActivity(intent)
    }
}