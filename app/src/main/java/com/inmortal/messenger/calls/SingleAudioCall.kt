package com.inmortal.messenger.calls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.os.postDelayed
import com.inmortal.messenger.R
import com.inmortal.messenger.activity.Dashboard

class SingleAudioCall : AppCompatActivity() {

    lateinit var connect:LinearLayout
    lateinit var connecting:LinearLayout
    lateinit var ringing:LinearLayout
    lateinit var call_cut:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_audio_call)

        connect = findViewById(R.id.connect)
        ringing = findViewById(R.id.ringing)
        connecting = findViewById(R.id.Connecting)

        call_cut = findViewById(R.id.call_cut)

        Handler().postDelayed({ connect.visibility = View.GONE

                           if (connect.visibility == View.GONE)
                           {
                               ringing.visibility = View.VISIBLE

                               Handler().postDelayed({ringing.visibility = View.GONE
                                   if (ringing.visibility == View.GONE)
                                   {
                                       connecting.visibility = View.VISIBLE
                                       Handler().postDelayed({
                                           val intent = Intent(this@SingleAudioCall  , VoiceCallPanel::class.java)
                                           startActivity(intent)},1000)
                                   }
                               },1000)
                           }

                              }, 1000)


        call_cut.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SingleAudioCall  , Dashboard::class.java )
            intent.putExtra("key" , "0")
            startActivity(intent)
        })
    }
}