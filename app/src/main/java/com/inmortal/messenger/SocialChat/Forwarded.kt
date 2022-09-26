package com.inmortal.messenger.SocialChat

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import com.inmortal.messenger.R

class Forwarded : AppCompatActivity() {

    lateinit var bck:LinearLayout
    lateinit var main_view:LinearLayout
    lateinit var select_all:CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forwarded)
        bck = findViewById(R.id.bck)
        main_view = findViewById(R.id.main_view)
        select_all = findViewById(R.id.select_all)
        bck.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Forwarded , ManageStorage::class.java)
            startActivity(intent)
        })

    }
}