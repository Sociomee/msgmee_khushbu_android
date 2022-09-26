package com.inmortal.messenger.SocialChat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.inmortal.messenger.R

class AttachContact : AppCompatActivity() {

    lateinit var next:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attach_contact)

        next = findViewById(R.id.save)
        next.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@AttachContact , Chat::class.java)
//            intent.putExtra("key" , "0")
            startActivity(intent)
        })
    }

    fun back(view: View) {
        val intent = Intent(this@AttachContact , Chat::class.java)
        startActivity(intent)
    }
}