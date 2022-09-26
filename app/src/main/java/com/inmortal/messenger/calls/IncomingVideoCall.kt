package com.inmortal.messenger.calls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.inmortal.messenger.R

class IncomingVideoCall : AppCompatActivity() {

    lateinit var acceot:LinearLayout
    lateinit var decline:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_video_call)

        acceot = findViewById(R.id.acceot)
        decline = findViewById(R.id.decline)

        acceot.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@IncomingVideoCall , IncominVideoCallPanel::class.java)
            startActivity(intent)
        })
        decline.setOnClickListener(View.OnClickListener {
          onBackPressed()
        })
    }
}