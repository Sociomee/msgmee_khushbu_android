package com.inmortal.messenger.Settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.R
import com.inmortal.messenger.SocialChat.ManageStorage

class StorageSettings : AppCompatActivity() {

     lateinit var bcs: ImageView
     lateinit var store: RelativeLayout
    lateinit var mobile_dataview: RelativeLayout
    lateinit var btnok_mobiledata: AppCompatButton
    lateinit var btnok_wifi:AppCompatButton
    lateinit var btnok_roaming:AppCompatButton
    lateinit var wifi_view:RelativeLayout
    lateinit var roaming_view:RelativeLayout

    lateinit var bottomSheetDialog_clear: BottomSheetDialog
    lateinit var bottomSheetDialog_wifi: BottomSheetDialog
    lateinit var bottomSheetDialog_roaming: BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_settings)
        bcs = findViewById(R.id.arr)
        store = findViewById(R.id.store)
        mobile_dataview = findViewById(R.id.mobile_dataview)
        wifi_view = findViewById(R.id.wifi_view)
        roaming_view = findViewById(R.id.roaming_view)

        bcs.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@StorageSettings , SettingsMain::class.java)
            startActivity(intent)
        })
        store.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@StorageSettings , ManageStorage::class.java)
            startActivity(intent)
        })
        mobile_dataview.setOnClickListener(View.OnClickListener {
            bottomSheetDialog_clear = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_mobiledata, null)

            btnok_mobiledata = view.findViewById(R.id.btnok_mobiledata);

            btnok_mobiledata.setOnClickListener(View.OnClickListener {

                bottomSheetDialog_clear.dismiss()

            })


            bottomSheetDialog_clear.dismiss()
            bottomSheetDialog_clear.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog_clear.setContentView(view)
            bottomSheetDialog_clear.show()


        })

        wifi_view.setOnClickListener(View.OnClickListener {


            bottomSheetDialog_wifi = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_wi_fi,null)

            btnok_wifi = view.findViewById(R.id.btnok_wifi)
            btnok_wifi.setOnClickListener(View.OnClickListener {

                bottomSheetDialog_wifi.dismiss()

            })

            bottomSheetDialog_wifi.dismiss()
            bottomSheetDialog_wifi.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog_wifi.setContentView(view)
            bottomSheetDialog_wifi.show()

        })

        roaming_view.setOnClickListener(View.OnClickListener {


            bottomSheetDialog_roaming = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_roaming,null)

            btnok_roaming = view.findViewById(R.id.btnok_roaming)
            btnok_roaming.setOnClickListener(View.OnClickListener {

                bottomSheetDialog_roaming.dismiss();
            })

            bottomSheetDialog_roaming.dismiss()
            bottomSheetDialog_roaming.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog_roaming.setContentView(view)
            bottomSheetDialog_roaming.show()



        })
    }
}