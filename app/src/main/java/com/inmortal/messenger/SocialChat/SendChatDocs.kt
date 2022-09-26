package com.inmortal.messenger.SocialChat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.inmortal.messenger.R

class SendChatDocs : AppCompatActivity() {

    private lateinit var returned:ImageView
    private lateinit var select_all:CheckBox

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_chat_docs)

        returned = findViewById(R.id.Return)
        select_all = findViewById(R.id.select_all)

        select_all.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SendChatDocs , ManageStorage::class.java)
            startActivity(intent)
        })

        returned.setOnClickListener {
            val intent = Intent(this@SendChatDocs , Chat::class.java)
            startActivity(intent)
        }
    }
}