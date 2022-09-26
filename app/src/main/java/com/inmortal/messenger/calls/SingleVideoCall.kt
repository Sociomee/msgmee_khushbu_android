package com.inmortal.messenger.calls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.inmortal.messenger.R

class SingleVideoCall : AppCompatActivity() {

    lateinit var connect: LinearLayout
    lateinit var cancel_call: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_video_call)

        connect = findViewById(R.id.connect)
        cancel_call = findViewById(R.id.cancel_call)

        cancel_call.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SingleVideoCall , MakeCalls::class.java)
            startActivity(intent)
        })
        Handler().postDelayed({
            connect.visibility = View.GONE
            val intent = Intent(this@SingleVideoCall , VidoeCallPanel::class.java)
            startActivity(intent)
        },3000)
    }
}