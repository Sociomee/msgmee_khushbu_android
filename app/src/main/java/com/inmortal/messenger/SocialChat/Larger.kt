package com.inmortal.messenger.SocialChat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.inmortal.messenger.R

class Larger : AppCompatActivity() {
    private lateinit var bck: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_larger)
        bck = findViewById(R.id.bck)
        bck.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Larger , ManageStorage::class.java)
            startActivity(intent)
        })
    }
}