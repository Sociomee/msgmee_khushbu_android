package com.inmortal.messenger.calls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.R

class IncominVideoCallPanel : AppCompatActivity() {

    lateinit var redirect:ImageView
    lateinit var add_grp_members:RelativeLayout
    lateinit var close_call:RelativeLayout
    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var bottomSheetSearch: BottomSheetDialog
    lateinit var add: View

    lateinit var grp_videocall: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incomin_video_call_panel)

        redirect = findViewById(R.id.redirect)
        add_grp_members = findViewById(R.id.add_grp_members)
        close_call = findViewById(R.id.close_call)


        close_call.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@IncominVideoCallPanel  , MakeCalls::class.java)
            startActivity(intent)
        })
        add_grp_members.setOnClickListener(View.OnClickListener {
            bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_video_call, null)

            Handler().postDelayed({
                bottomSheetDialog.dismiss()
                bottomSheetSearch = BottomSheetDialog(this, R.style.BottomSheetTheme)
                val view1: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_videoe_search, null)
                grp_videocall = view1.findViewById(R.id.grp_videocall)
                grp_videocall.setOnClickListener(View.OnClickListener {
                    //       Dialog Box popup appears when come to otp screen
                    val view_dialog =  View.inflate(this@IncominVideoCallPanel, R.layout.add_in_video_call ,null)
                    val builder = AlertDialog.Builder(this@IncominVideoCallPanel)
                    builder.setView(view_dialog)
                    val dialog = builder.create()
                    dialog.show()
                    val cancel : TextView = view_dialog.findViewById(R.id.cancel_add)
                    cancel.setOnClickListener(View.OnClickListener {
                        dialog.dismiss()
                    })
                    val proceed_add : TextView = view_dialog.findViewById(R.id.proceed_add)
                    proceed_add.setOnClickListener(View.OnClickListener {
                        add_grp_members.visibility = View.VISIBLE
                        dialog.dismiss()
                    })
                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    dialog.setCancelable(true)
                    dialog.setCanceledOnTouchOutside(true)
                    /*ends*/
                    bottomSheetSearch.dismiss()
                })
                bottomSheetSearch.requestWindowFeature(Window.FEATURE_NO_TITLE)
                bottomSheetSearch.setContentView(view1)
                bottomSheetSearch.show()

            },2000)
//            add = view.findViewById(R.id.temp_click)
//            add.setOnClickListener(View.OnClickListener {
//                bottomSheetDialog.dismiss()
//                bottomSheetSearch = BottomSheetDialog(this, R.style.BottomSheetTheme)
//                val view1: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_videoe_search, null)
//                grp_videocall = view1.findViewById(R.id.grp_videocall)
//                grp_videocall.setOnClickListener(View.OnClickListener {
//                    //       Dialog Box popup appears when come to otp screen
//                    val view_dialog =  View.inflate(this@IncominVideoCallPanel, R.layout.add_in_video_call ,null)
//                    val builder = AlertDialog.Builder(this@IncominVideoCallPanel)
//                    builder.setView(view_dialog)
//                    val dialog = builder.create()
//                    dialog.show()
//                    val cancel : TextView = view_dialog.findViewById(R.id.cancel_add)
//                    cancel.setOnClickListener(View.OnClickListener {
//                        dialog.dismiss()
//                    })
//                    val proceed_add : TextView = view_dialog.findViewById(R.id.proceed_add)
//                    proceed_add.setOnClickListener(View.OnClickListener {
//                        add_grp_members.visibility = View.VISIBLE
//                        dialog.dismiss()
//                    })
//                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//                    dialog.setCancelable(true)
//                    dialog.setCanceledOnTouchOutside(true)
//                    /*ends*/
//                    bottomSheetSearch.dismiss()
//                })
//                bottomSheetSearch.requestWindowFeature(Window.FEATURE_NO_TITLE)
//                bottomSheetSearch.setContentView(view1)
//                bottomSheetSearch.show()
//            })
            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()
        })
    }
}