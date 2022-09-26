package com.inmortal.messenger.SocialChat

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.inmortal.messenger.Broadcast.BroadcastInfo
import com.inmortal.messenger.R

class ForwardMessage : AppCompatActivity() {
    lateinit var checkBox: CheckBox
    lateinit var checkBox_2: CheckBox
    lateinit var done: TextView
    lateinit var titlee: TextView
    lateinit var filter: ImageView
    lateinit var select_count: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forward_message)

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
                filter.visibility= View.VISIBLE
                select_count.visibility= View.VISIBLE
//                titlee.text = "Add Participants"
            }
            else
            {
                done.setTextColor(Color.parseColor("#9D9D9D"))
                filter.visibility= View.GONE
                select_count.visibility= View.INVISIBLE
//                titlee.text = "New Broadcast"
            }
        })
        checkBox_2.setOnClickListener(View.OnClickListener {
            if (checkBox_2.isChecked||checkBox.isChecked)
            {
                done.setTextColor(Color.parseColor("#81C14B"))
                filter.visibility= View.VISIBLE
                select_count.visibility= View.VISIBLE
//                titlee.text = "Add Participants"
            }
            else
            {
                done.setTextColor(Color.parseColor("#9D9D9D"))
                filter.visibility= View.GONE
                select_count.visibility= View.GONE
//                titlee.text = "New Broadcast"
            }
        })
//        titlee.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this@ForwardMessage , BroadcastInfo::class.java)
//            startActivity(intent)
//        })
        done.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ForwardMessage , RecieverChat::class.java)
            startActivity(intent)
        })
    }

    fun back(view: View) {
        val intent = Intent(this@ForwardMessage , Chat::class.java)
        startActivity(intent)
    }
}