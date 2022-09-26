package com.inmortal.messenger.SocialChat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.inmortal.messenger.R
import com.inmortal.messenger.activity.Dashboard
import com.inmortal.messenger.activity.Otp

class UnknownChat : AppCompatActivity() {
    lateinit var unknownAdd : LinearLayout
    lateinit var unknownBlock : LinearLayout
    lateinit var unknownReport : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unknown_chat)
        unknownAdd = findViewById(R.id.unknown_add)
        unknownBlock = findViewById(R.id.unknown_block)
        unknownReport = findViewById(R.id.unknown_report)

        unknownAdd.setOnClickListener(View.OnClickListener {
            //       Dialog Box popup appears when come to otp screen
            val view =  View.inflate(this@UnknownChat, R.layout.unknown_add_popup ,null)
            val builder = AlertDialog.Builder(this@UnknownChat)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            /*ends*/
        })
        unknownBlock.setOnClickListener(View.OnClickListener {
            //       Dialog Box popup appears when come to otp screen
            val view =  View.inflate(this@UnknownChat, R.layout.unknown_block_popup ,null)
            val builder = AlertDialog.Builder(this@UnknownChat)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
            val cancel : TextView = view.findViewById(R.id.cancelll)
            cancel.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
            })
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            /*ends*/
        })
        unknownReport.setOnClickListener(View.OnClickListener {
            //       Dialog Box popup appears when come to otp screen
            val view =  View.inflate(this@UnknownChat, R.layout.unknown_report_popup ,null)
            val builder = AlertDialog.Builder(this@UnknownChat)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
            val cancel : TextView = view.findViewById(R.id.cancell)
            cancel.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
            })
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            /*ends*/
        })
    }

    fun back(view: View) {
        val intent = Intent(this@UnknownChat , Dashboard::class.java)
        startActivity(intent)
    }
}