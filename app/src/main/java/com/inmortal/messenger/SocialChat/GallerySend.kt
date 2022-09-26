package com.inmortal.messenger.SocialChat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.inmortal.messenger.R

class GallerySend : AppCompatActivity() {

    lateinit var edit:ImageView
    lateinit var cardview:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_send)

        edit = findViewById(R.id.edit)
        cardview = findViewById(R.id.cardview)
        edit.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@GallerySend , GalleryFilter::class.java)
            startActivity(intent)
        })
        cardview.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@GallerySend , GalleryVideoEdit::class.java)
            startActivity(intent)
        })
    }

    fun back(view: View) {
       val intent = Intent(this@GallerySend , Gallery::class.java)
        startActivity(intent)
    }
}