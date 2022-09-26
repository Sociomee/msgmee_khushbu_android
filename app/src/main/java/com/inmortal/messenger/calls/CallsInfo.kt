package com.inmortal.messenger.calls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.inmortal.messenger.R

class CallsInfo : AppCompatActivity() {

    lateinit var to_calls:ImageView
    lateinit var to_videoCalls:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calls_info)

        to_calls = findViewById(R.id.indi_call)
        to_videoCalls = findViewById(R.id.indi_video_call)

        to_calls.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@CallsInfo , SingleAudioCall::class.java)
            startActivity(intent)
        })
        to_videoCalls.setOnClickListener {
            val intent = Intent(this@CallsInfo, SingleVideoCall::class.java)
            startActivity(intent)
        }
    }

    fun backk(view: View) {
        onBackPressed()
    }
}