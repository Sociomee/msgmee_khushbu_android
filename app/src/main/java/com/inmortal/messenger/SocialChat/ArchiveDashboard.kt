package com.inmortal.messenger.SocialChat

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.R
import com.inmortal.messenger.activity.Dashboard

class ArchiveDashboard : AppCompatActivity() {

    lateinit var raza:RelativeLayout
    lateinit var header_after:RelativeLayout
    lateinit var main_header:RelativeLayout
    lateinit var hide_popup:LinearLayout
    lateinit var popup_options:ImageView
    lateinit var bottomSheetDialog_clear: BottomSheetDialog
    lateinit var delete: LinearLayout
    lateinit var cancel: LinearLayout
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive_dashboard)
        raza = findViewById(R.id.raza)
        header_after = findViewById(R.id.header_after)
        main_header = findViewById(R.id.main_header)
        hide_popup = findViewById(R.id.hide_popup)
        popup_options = findViewById(R.id.popup_options)
        hide_popup.setOnClickListener(View.OnClickListener {
            main_header.visibility=View.VISIBLE
            header_after.visibility=View.GONE
            raza.setBackgroundColor(Color.TRANSPARENT)
        })
        popup_options.setOnClickListener(View.OnClickListener {
            val popupMenu = PopupMenu(this , it)
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId){
                    R.id.all_s -> {
                        Toast.makeText(this , "select all" , Toast.LENGTH_SHORT).show()
                    true
                    }
                    R.id.all_c -> {
                        bottomSheetDialog_clear = BottomSheetDialog(this, R.style.BottomSheetTheme)
                        val view_delete: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_delete_archive, null)
                        delete = view_delete.findViewById(R.id.delete)
                        delete.setOnClickListener(View.OnClickListener {
                            bottomSheetDialog_clear.dismiss()
                            Toast.makeText(this@ArchiveDashboard , "Archived List deleted" , Toast.LENGTH_SHORT).show()
                            raza.visibility=View.GONE
                            header_after.visibility=View.GONE
                            main_header.visibility=View.VISIBLE
                        })
                        cancel = view_delete.findViewById(R.id.cancel)
                        cancel.setOnClickListener(View.OnClickListener {
                            bottomSheetDialog_clear.dismiss()
                        })
                        bottomSheetDialog_clear.dismiss()
                        bottomSheetDialog_clear.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        bottomSheetDialog_clear.setContentView(view_delete)
                        bottomSheetDialog_clear.show()

                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.menu_main)
            popupMenu.show()
        })
        raza.setOnClickListener(View.OnClickListener {
            raza.setOnTouchListener(){ _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                    main_header.visibility=View.GONE
                    header_after.visibility=View.VISIBLE
                        raza.setBackgroundColor(Color.parseColor("#E3F4EA"))
                    }
                    MotionEvent.ACTION_UP ->
                    {
                        main_header.visibility=View.GONE
                        header_after.visibility=View.VISIBLE
                        raza.setBackgroundColor(Color.parseColor("#E3F4EA"))
                    }
                }
                true
            }
        })
    }

    fun back(view: View) {
        val intent = Intent(this@ArchiveDashboard , Dashboard::class.java)
        startActivity(intent)
    }
}