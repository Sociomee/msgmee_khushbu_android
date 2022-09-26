package com.inmortal.messenger.Settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.inmortal.messenger.R

class HelpSupport_Activity : AppCompatActivity() {

    lateinit var img_back_arr: ImageView
    lateinit var constraint_report_problem: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_support)

        img_back_arr = findViewById(R.id.img_back_arr)
        constraint_report_problem = findViewById(R.id.constraint_report_problem)

        img_back_arr.setOnClickListener(View.OnClickListener {

            onBackPressed()

        })

        constraint_report_problem.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@HelpSupport_Activity,Report_Problem_Activity::class.java)
            startActivity(intent)

        })
    }
}