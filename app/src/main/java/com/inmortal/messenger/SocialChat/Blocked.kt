package com.inmortal.messenger.SocialChat

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.inmortal.messenger.R
import com.inmortal.messenger.activity.Dashboard

class Blocked : AppCompatActivity() {
    lateinit var raza: RelativeLayout
    lateinit var unblock: RelativeLayout
    lateinit var main_view: LinearLayout
    lateinit var unblock_all: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blocked)

        raza = findViewById(R.id.raza)
        unblock = findViewById(R.id.unblock)
        main_view = findViewById(R.id.main_view)
        unblock_all = findViewById(R.id.unblock_all)
        unblock.setOnClickListener(View.OnClickListener {
            raza.visibility = View.GONE
            val snackbarr = Snackbar.make(it , "Unblocked “Razdar Hasan" , Snackbar.LENGTH_LONG)
            snackbarr.setAction("Undo" , View.OnClickListener {
                snackbarr.dismiss()
                raza.visibility = View.VISIBLE
            })
            snackbarr.show()
        })
        unblock_all.setOnClickListener(View.OnClickListener {
        main_view.visibility = View.GONE
        })
    }

    fun back(view: View) {
        val intent = Intent(this@Blocked , Dashboard::class.java)
        startActivity(intent)
    }
}