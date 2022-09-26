package com.inmortal.messenger.calls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.RelativeLayout
import com.inmortal.messenger.R

class VidoeCallPanel : AppCompatActivity()
{

    lateinit var incoming_video_call:RelativeLayout
    lateinit var end_call:RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vidoe_call_panel)
        incoming_video_call= findViewById(R.id.incoming_video_call)
        end_call= findViewById(R.id.end_call)

        Handler().postDelayed({

            val intent = Intent(this@VidoeCallPanel , IncomingVideoCall::class.java)
            startActivity(intent)

        },3000)


        end_call.setOnClickListener(View.OnClickListener {
            val intent  = Intent(this@VidoeCallPanel , MakeCalls::class.java)
            startActivity(intent)
        })
    }
}