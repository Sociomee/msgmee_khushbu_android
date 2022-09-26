package com.inmortal.messenger.profile

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.inmortal.messenger.R
import com.inmortal.messenger.SocialChat.Chat

class ProfileInfo : AppCompatActivity() {

    lateinit var dots:ImageView
    lateinit var bottomSheetDialog_clear: BottomSheetDialog
    lateinit var social: TextView
    lateinit var calls: TextView
    lateinit var links: TextView
    lateinit var social_bar: View
    lateinit var calls_bar: View
    lateinit var link_bar: View
    lateinit var block: LinearLayout
    lateinit var report: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)

        dots = findViewById(R.id.dots)
        social = findViewById(R.id.social)
        social_bar = findViewById(R.id.social_bar)
        calls = findViewById(R.id.calls)
        links = findViewById(R.id.Links)
        calls_bar = findViewById(R.id.calls_bar)
        link_bar = findViewById(R.id.links_bar)
        dots.setOnClickListener(View.OnClickListener {
            bottomSheetDialog_clear = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_profile_info, null)
            block = view.findViewById(R.id.clea)
            report = view.findViewById(R.id.phone_connections)
            block.setOnClickListener(View.OnClickListener {
                bottomSheetDialog_clear.dismiss()
              Toast.makeText(this@ProfileInfo, "Blocked Hema Akhtar" , Toast.LENGTH_SHORT).show()
            })
            report.setOnClickListener(View.OnClickListener {
                bottomSheetDialog_clear.dismiss()
              Toast.makeText(this@ProfileInfo, "Hema Akhtar Reported" , Toast.LENGTH_SHORT).show()
            })
            bottomSheetDialog_clear.dismiss()
            bottomSheetDialog_clear.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog_clear.setContentView(view)
            bottomSheetDialog_clear.show()
        })

        social.setOnClickListener(View.OnClickListener {
            social_bar.visibility = View.VISIBLE
            calls_bar.visibility = View.INVISIBLE
            link_bar.visibility = View.INVISIBLE
//            calls.setTextColor("background: #4F4F4F")
            calls.setTextColor(Color.BLACK)
            links.setTextColor(Color.BLACK)
            social.setTextColor(ContextCompat.getColor(this,R.color.light_green))
        })

        calls.setOnClickListener(View.OnClickListener
        {
            calls_bar.visibility = View.VISIBLE
            social_bar.visibility =View.INVISIBLE
            link_bar.visibility =View.INVISIBLE
            social.setTextColor(Color.BLACK)
            links.setTextColor(Color.BLACK)
            calls.setTextColor(ContextCompat.getColor(this,R.color.light_green))
        })

        links.setOnClickListener(View.OnClickListener
        {
            link_bar.visibility = View.VISIBLE
            social_bar.visibility =View.INVISIBLE
            calls_bar.visibility =View.INVISIBLE
            social.setTextColor(Color.BLACK)
            calls.setTextColor(Color.BLACK)
            links.setTextColor(ContextCompat.getColor(this,R.color.light_green))
        })

    }

    fun back(view: View) {
        val intent = Intent(this@ProfileInfo , Chat::class.java)
        startActivity(intent)
    }
}