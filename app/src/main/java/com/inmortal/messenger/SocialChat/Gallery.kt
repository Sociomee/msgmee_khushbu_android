package com.inmortal.messenger.SocialChat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.inmortal.messenger.R

class Gallery : AppCompatActivity() {

    lateinit var close_gallery: ImageView
    lateinit var saved: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        close_gallery = findViewById(R.id.close_gallery)
        saved = findViewById(R.id.saved)
        close_gallery.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Gallery , Chat::class.java)
            startActivity(intent)
        })
        saved.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Gallery , GallerySend::class.java)
            startActivity(intent)
        })
    }
}