package com.inmortal.messenger

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(
                this@MainActivity,
                R.color.black
            )
        }
        val updateHandler = Handler()

        val runnable = Runnable {
            updateDisplay() // some action(s)
        }
        updateHandler.postDelayed(runnable, 2000)
    }
    private fun updateDisplay() {
        startActivity(Intent(this@MainActivity,StartActivity::class.java))
        finish()

    }
}