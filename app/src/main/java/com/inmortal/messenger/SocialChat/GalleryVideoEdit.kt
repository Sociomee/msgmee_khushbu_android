package com.inmortal.messenger.SocialChat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.inmortal.messenger.R

class GalleryVideoEdit : AppCompatActivity() {

    lateinit var filter_tab:TextView
    lateinit var trim_tab:TextView
    lateinit var cover_tab:TextView

    lateinit var title_name:TextView

    lateinit var filter_bar:View
    lateinit var trim_bar:View
    lateinit var cover_bar:View

    lateinit var filter_view:LinearLayout
    lateinit var trim_view:RelativeLayout
    lateinit var cover_view:RelativeLayout

    lateinit var back_cover:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_video_edit)

        filter_tab = findViewById(R.id.filter_tab)
        trim_tab = findViewById(R.id.trim_tab)
        cover_tab = findViewById(R.id.cover_tab)

        title_name = findViewById(R.id.title_name)

        filter_bar = findViewById(R.id.filter_bar)
        trim_bar = findViewById(R.id.trim_bar)
        cover_bar = findViewById(R.id.cover_bar)

        filter_view = findViewById(R.id.filter_view)
        trim_view = findViewById(R.id.trim_view)
        cover_view = findViewById(R.id.cover_view)

        back_cover = findViewById(R.id.back_cover)

        back_cover.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@GalleryVideoEdit , GallerySend::class.java)
            startActivity(intent)
        })

        filter_tab.setOnClickListener(View.OnClickListener {
            filter_bar.visibility = View.VISIBLE
            trim_bar.visibility = View.INVISIBLE
            cover_bar.visibility = View.INVISIBLE

            filter_tab.setTextColor(ContextCompat.getColor(this,R.color.black))
            trim_tab.setTextColor(ContextCompat.getColor(this,R.color.light_grey))
            cover_tab.setTextColor(ContextCompat.getColor(this,R.color.light_grey))

            filter_view.visibility = View.VISIBLE
            trim_view.visibility = View.GONE
            cover_view.visibility = View.GONE

            title_name.text = "Filter"
        })
        trim_tab.setOnClickListener(View.OnClickListener {
            filter_bar.visibility = View.INVISIBLE
            trim_bar.visibility = View.VISIBLE
            cover_bar.visibility = View.INVISIBLE

            filter_tab.setTextColor(ContextCompat.getColor(this,R.color.light_grey))
            trim_tab.setTextColor(ContextCompat.getColor(this,R.color.black))
            cover_tab.setTextColor(ContextCompat.getColor(this,R.color.light_grey))

            filter_view.visibility = View.GONE
            trim_view.visibility = View.VISIBLE
            cover_view.visibility = View.GONE

            title_name.text = "Trim"
        })
        cover_tab.setOnClickListener(View.OnClickListener {
            filter_bar.visibility = View.INVISIBLE
            trim_bar.visibility = View.INVISIBLE
            cover_bar.visibility = View.VISIBLE

            filter_tab.setTextColor(ContextCompat.getColor(this,R.color.light_grey))
            trim_tab.setTextColor(ContextCompat.getColor(this,R.color.light_grey))
            cover_tab.setTextColor(ContextCompat.getColor(this,R.color.black))

            filter_view.visibility = View.GONE
            trim_view.visibility = View.GONE
            cover_view.visibility = View.VISIBLE

            title_name.text = "Cover"
        })
    }
}