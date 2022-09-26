package com.inmortal.messenger.calls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.inmortal.messenger.R

class DuringVoiceCall : AppCompatActivity() {

    lateinit var decline:LinearLayout
    lateinit var close_call:RelativeLayout
    lateinit var incoming_call:RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_during_voice_call)

        decline = findViewById(R.id.decline)
        close_call = findViewById(R.id.close_call)
        incoming_call = findViewById(R.id.incoming_call)
        decline.setOnClickListener(View.OnClickListener {
            incoming_call.visibility = View.GONE
        })
        close_call.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@DuringVoiceCall , MakeCalls::class.java)
            startActivity(intent)
        })
    }
}