package com.inmortal.messenger.calls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.inmortal.messenger.R

class IncomingVoiceCall : AppCompatActivity() {

    lateinit var accept_call: LinearLayout
    lateinit var cancel_call: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call)

        accept_call = findViewById(R.id.acceot)
        cancel_call = findViewById(R.id.cancel_call)

        accept_call.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@IncomingVoiceCall  , DuringVoiceCall::class.java)
            startActivity(intent)
        })
        cancel_call.setOnClickListener(View.OnClickListener {
           onBackPressed()
        })
    }
}