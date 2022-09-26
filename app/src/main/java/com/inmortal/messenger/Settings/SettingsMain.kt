package com.inmortal.messenger.Settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.R
import com.inmortal.messenger.activity.Dashboard
import com.inmortal.messenger.profile.ProfileSettings

class SettingsMain : AppCompatActivity() {

     lateinit var storage:TextView
     lateinit var storageandData:TextView
     lateinit var to_profile:TextView
    lateinit var chats_setting: TextView
    lateinit var gp:TextView
    lateinit var broadcast:TextView
    lateinit var block:TextView
    lateinit var syncing:TextView
    lateinit var text_help_support:TextView
    lateinit var log_out:TextView
    lateinit var bottomsheet_logout:BottomSheetDialog
    lateinit var constraint_cancel: ConstraintLayout
    lateinit var delete_account:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_main)

        storage = findViewById(R.id.block)
        storageandData = findViewById(R.id.storage)
        to_profile = findViewById(R.id.neww)
        chats_setting = findViewById(R.id.chats_setting)
        gp = findViewById(R.id.gp);
        broadcast = findViewById(R.id.broadcast);
        block = findViewById(R.id.block);
        syncing = findViewById(R.id.syncing);
        text_help_support = findViewById(R.id.text_help_support)
        log_out = findViewById(R.id.log_out)
        delete_account = findViewById(R.id.delete_account);


        storage.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SettingsMain , StorageSettings::class.java)
            startActivity(intent)
        })

        storageandData.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SettingsMain , StorageSettings::class.java)
            startActivity(intent)
        })

        to_profile.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SettingsMain , ProfileSettings::class.java)
            startActivity(intent)
        })
        chats_setting.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@SettingsMain,Chats_Setting::class.java)
            startActivity(intent)

        })
        gp.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@SettingsMain,Privacy_Setting::class.java)
            startActivity(intent)

        })
        broadcast.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@SettingsMain,Notification_setting::class.java)
            startActivity(intent)

        })
        block.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@SettingsMain,Blockedusers_Activity::class.java)
            startActivity(intent)

        })
        syncing.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@SettingsMain,SyncingInvite_Activity::class.java)
            startActivity(intent)

        })
        text_help_support.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@SettingsMain,HelpSupport_Activity::class.java)
            startActivity(intent)

        })


        log_out.setOnClickListener(View.OnClickListener {

            bottomsheet_logout = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_logout,null)

            constraint_cancel = view.findViewById(R.id.constraint_cancel)
            constraint_cancel.setOnClickListener(View.OnClickListener {

                bottomsheet_logout.dismiss()

            })

            bottomsheet_logout.dismiss()
            bottomsheet_logout.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_logout.setContentView(view)
            bottomsheet_logout.show()


        })

        delete_account.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@SettingsMain,Delete_Account_Activity::class.java)
            startActivity(intent)

        })
    }

    fun back(view: View)
    {
        val intent = Intent(this@SettingsMain , Dashboard::class.java)
        startActivity(intent)
    }
}