package com.inmortal.messenger.Broadcast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.inmortal.messenger.R

class BroadcastList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_list)
    }

    fun back(view: View) {
        val intent  = Intent(this@BroadcastList , BroadcastChat::class.java)
        startActivity(intent)
    }
}