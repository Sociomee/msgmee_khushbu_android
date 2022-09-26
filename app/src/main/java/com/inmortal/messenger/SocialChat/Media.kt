package com.inmortal.messenger.SocialChat

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.inmortal.messenger.R

class Media : AppCompatActivity() {
    lateinit var social: TextView
    lateinit var calls: TextView
    lateinit var links: TextView
    lateinit var social_bar: View
    lateinit var calls_bar: View
    lateinit var link_bar: View
    lateinit var mediaTab: LinearLayout
    lateinit var docsTab: LinearLayout
    lateinit var link_tab: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

        social = findViewById(R.id.social)
        social_bar = findViewById(R.id.social_bar)
        calls = findViewById(R.id.calls)
        links = findViewById(R.id.Links)
        calls_bar = findViewById(R.id.calls_bar)
        link_bar = findViewById(R.id.links_bar)
        docsTab = findViewById(R.id.docs)
        mediaTab = findViewById(R.id.media)
        link_tab = findViewById(R.id.link)

        social.setOnClickListener(View.OnClickListener {
            social_bar.visibility = View.VISIBLE
            calls_bar.visibility = View.INVISIBLE
            link_bar.visibility = View.INVISIBLE
//            calls.setTextColor("background: #4F4F4F")
            calls.setTextColor(Color.BLACK)
            links.setTextColor(Color.BLACK)
            social.setTextColor(ContextCompat.getColor(this,R.color.light_green))
            mediaTab.visibility = View.VISIBLE
            docsTab.visibility = View.GONE
            link_tab.visibility = View.GONE

        })
        calls.setOnClickListener(View.OnClickListener
        {
            calls_bar.visibility = View.VISIBLE
            social_bar.visibility =View.INVISIBLE
            link_bar.visibility =View.INVISIBLE
            social.setTextColor(Color.BLACK)
            links.setTextColor(Color.BLACK)
            calls.setTextColor(ContextCompat.getColor(this,R.color.light_green))
            mediaTab.visibility = View.GONE
            docsTab.visibility = View.VISIBLE
            link_tab.visibility = View.GONE

        })
  links.setOnClickListener(View.OnClickListener
        {
            link_bar.visibility = View.VISIBLE
            social_bar.visibility =View.INVISIBLE
            calls_bar.visibility =View.INVISIBLE
            social.setTextColor(Color.BLACK)
            calls.setTextColor(Color.BLACK)
            links.setTextColor(ContextCompat.getColor(this,R.color.light_green))
            mediaTab.visibility = View.GONE
            docsTab.visibility = View.GONE
            link_tab.visibility = View.VISIBLE

        })

    }

    fun back(view: View) {
        val intent = Intent(this@Media, Chat::class.java)
        startActivity(intent)
    }
}