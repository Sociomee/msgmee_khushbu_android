package com.inmortal.messenger.Settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.R

class Notification_setting : AppCompatActivity() {

    lateinit var back_arow:ImageView
    lateinit var constraint_chat_notification:ConstraintLayout
    lateinit var radiobtn_vibrate:RadioButton
    lateinit var radiobtn_tone:RadioButton
    lateinit var radiobtn_vibrate_tone:RadioButton
    lateinit var radiobtn_silent:RadioButton
    lateinit var constraint_group_notification:ConstraintLayout
    lateinit var radiobtn_group_vibrate:RadioButton
    lateinit var radiobtn_group_tone:RadioButton
    lateinit var radiobtn_group_vibrate_tone:RadioButton
    lateinit var radiobtn_group_silent:RadioButton
    lateinit var constraint_call_notification:ConstraintLayout
    lateinit var radiobtn_call_vibrate:RadioButton
    lateinit var radiobtn_call_tone:RadioButton
    lateinit var radiobtn_call_vibrate_tone:RadioButton
    lateinit var radiobtn_call_silent:RadioButton

    lateinit var bottomsheet_chat_notification:BottomSheetDialog
    lateinit var bottomsheet_group_notification:BottomSheetDialog
    lateinit var bottommsheet_call_notification:BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_setting)


        back_arow = findViewById(R.id.back_arow)
        constraint_chat_notification = findViewById(R.id.constraint_chat_notification)
        constraint_group_notification = findViewById(R.id.constraint_group_notification)
        constraint_call_notification = findViewById(R.id.constraint_call_notification)

        back_arow.setOnClickListener(View.OnClickListener {

            onBackPressed()
        })

        constraint_chat_notification.setOnClickListener(View.OnClickListener {

            bottomsheet_chat_notification = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_chat_notification,null)

            radiobtn_vibrate = view.findViewById(R.id.radiobtn_vibrate)
            radiobtn_vibrate.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){

                    bottomsheet_chat_notification.dismiss()
                }
            }
            radiobtn_tone = view.findViewById(R.id.radiobtn_tone)
            radiobtn_tone.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    bottomsheet_chat_notification.dismiss()
                }
            }
            radiobtn_vibrate_tone = view.findViewById(R.id.radiobtn_vibrate_tone)
            radiobtn_vibrate_tone.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    bottomsheet_chat_notification.dismiss()
                }
            }

            radiobtn_silent = view.findViewById(R.id.radiobtn_silent)
            radiobtn_silent.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){

                    bottomsheet_chat_notification.dismiss()
                }
            }

            bottomsheet_chat_notification.dismiss()
            bottomsheet_chat_notification.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_chat_notification.setContentView(view)
            bottomsheet_chat_notification.show()

        })

        constraint_group_notification.setOnClickListener(View.OnClickListener {

            bottomsheet_group_notification = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_group_notification,null)

            radiobtn_group_vibrate = view.findViewById(R.id.radiobtn_group_vibrate)
            radiobtn_group_vibrate.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    bottomsheet_group_notification.dismiss()
                }
            }

            radiobtn_group_tone = view.findViewById(R.id.radiobtn_group_tone)
            radiobtn_group_tone.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    bottomsheet_group_notification.dismiss()
                }
            }
            radiobtn_group_vibrate_tone = view.findViewById(R.id.radiobtn_group_vibrate_tone)
            radiobtn_group_vibrate_tone.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    bottomsheet_group_notification.dismiss()
                }
            }

            radiobtn_group_silent = view.findViewById(R.id.radiobtn_group_silent)
            radiobtn_group_silent.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    bottomsheet_group_notification.dismiss()
                }
            }


            bottomsheet_group_notification.dismiss()
            bottomsheet_group_notification.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_group_notification.setContentView(view)
            bottomsheet_group_notification.show()

        })

        constraint_call_notification.setOnClickListener(View.OnClickListener {

            bottommsheet_call_notification = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_call_notification,null)

            radiobtn_call_vibrate = view.findViewById(R.id.radiobtn_call_vibrate)
            radiobtn_call_vibrate.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    bottommsheet_call_notification.dismiss()
                }
            }
            radiobtn_call_tone = view.findViewById(R.id.radiobtn_call_tone)
            radiobtn_call_tone.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    bottommsheet_call_notification.dismiss()
                }
            }
            radiobtn_call_vibrate_tone = view.findViewById(R.id.radiobtn_call_vibrate_tone)
            radiobtn_call_vibrate_tone.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    bottommsheet_call_notification.dismiss()
                }
            }

            radiobtn_call_silent = view.findViewById(R.id.radiobtn_call_silent)
            radiobtn_call_silent.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    bottommsheet_call_notification.dismiss()
                }
            }

            bottommsheet_call_notification.dismiss()
            bottommsheet_call_notification.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottommsheet_call_notification.setContentView(view)
            bottommsheet_call_notification.show()
        })
    }
}