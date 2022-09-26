package com.inmortal.messenger.SocialChat

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.blue
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.inmortal.messenger.R

class GalleryFilter : AppCompatActivity() {

    lateinit var social: TextView
    lateinit var calls: TextView
    lateinit var social_bar: View
    lateinit var calls_bar: View
    lateinit var filter_view: LinearLayout
    lateinit var normal_filter: LinearLayout
    lateinit var clarendon_filter: LinearLayout
    lateinit var gingham_filter: LinearLayout
    lateinit var nature_filter: LinearLayout
    lateinit var edit_view: LinearLayout
    lateinit var brightness: ImageView
    lateinit var picture: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_filter)
        social = findViewById(R.id.social)
        social_bar = findViewById(R.id.social_bar)
        calls = findViewById(R.id.calls)
        calls_bar = findViewById(R.id.calls_bar)
        filter_view = findViewById(R.id.filter_view)
        edit_view = findViewById(R.id.edit_view)
        brightness = findViewById(R.id.brightness)
        picture = findViewById(R.id.main_view)
        normal_filter = findViewById(R.id.normal_filter)
        clarendon_filter = findViewById(R.id.clarendon_filter)
        gingham_filter = findViewById(R.id.gingham_filter)
        nature_filter = findViewById(R.id.nature_filter)

        social.setOnClickListener(View.OnClickListener {
            social_bar.visibility = View.VISIBLE
            calls_bar.visibility = View.INVISIBLE
//            calls.setTextColor("background: #4F4F4F")
            filter_view.visibility = View.VISIBLE
            edit_view.visibility = View.GONE
            calls.setTextColor(Color.GRAY)
            social.setTextColor(ContextCompat.getColor(this,R.color.black))
        })
        calls.setOnClickListener(View.OnClickListener
        {
            calls_bar.visibility = View.VISIBLE
            social_bar.visibility =View.INVISIBLE
            filter_view.visibility = View.GONE
            edit_view.visibility = View.VISIBLE
            social.setTextColor(Color.GRAY)
            calls.setTextColor(ContextCompat.getColor(this,R.color.black))
        })

        brightness.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@GalleryFilter , GalleryBrightness::class.java)
            startActivity(intent)
        })
        normal_filter.setOnClickListener(View.OnClickListener {
            picture.drawable.setColorFilter(Color.MAGENTA, PorterDuff.Mode.MULTIPLY )
        })
        clarendon_filter.setOnClickListener(View.OnClickListener {
            picture.drawable.setColorFilter(0xff00ff00.toInt(), PorterDuff.Mode.MULTIPLY )
        })
        gingham_filter.setOnClickListener(View.OnClickListener {
            picture.drawable.setColorFilter(resources.getColor(R.color.light_grey), PorterDuff.Mode.MULTIPLY )
        })
        nature_filter.setOnClickListener(View.OnClickListener {
            picture.drawable.setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY )
        })
    }

    fun back(view: View) {
        val intent = Intent(this@GalleryFilter , GallerySend::class.java)
        startActivity(intent)
    }
}