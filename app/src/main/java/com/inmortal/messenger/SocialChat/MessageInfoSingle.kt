package com.inmortal.messenger.SocialChat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.inmortal.messenger.R

class MessageInfoSingle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_info_single)
    }

    fun back(view: View) {
        val intent = Intent(this@MessageInfoSingle , Chat::class.java)
        startActivity(intent)
    }
}