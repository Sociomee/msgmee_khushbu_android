package com.inmortal.messenger.Broadcast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.inmortal.messenger.R

class BroadcastInfo : AppCompatActivity() {
    lateinit var saved:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_info)

        saved = findViewById(R.id.saved)
        saved.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@BroadcastInfo , BroadcastChat::class.java)
            startActivity(intent)
        })
    }

    fun back(view: View) {
        val intent = Intent(this@BroadcastInfo , NewBroadcast::class.java)
        startActivity(intent)
    }
}