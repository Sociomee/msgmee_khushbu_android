package com.inmortal.messenger.SocialChat

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.R

class AddMemberToGrp : AppCompatActivity() {
    lateinit var checkBox: CheckBox
    lateinit var checkBox_2: CheckBox
    lateinit var done: TextView
    lateinit var titlee: TextView
    lateinit var filter: ImageView
    lateinit var select_count: LinearLayout
    lateinit var bottomSheetDialog_history: BottomSheetDialog
    lateinit var cancel_dialog: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member_to_grp)

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
            bottomSheetDialog_history = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view_delete: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_history, null)
            cancel_dialog = view_delete.findViewById<LinearLayout>(R.id.ok)
            cancel_dialog.setOnClickListener(View.OnClickListener {
                bottomSheetDialog_history.dismiss()
            })
            bottomSheetDialog_history.dismiss()
            bottomSheetDialog_history.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog_history.setContentView(view_delete)
            bottomSheetDialog_history.show()

        })
    }

    fun back(view: View) {
        val intent = Intent(this@AddMemberToGrp , GroupInfo::class.java)
        startActivity(intent)
    }
}