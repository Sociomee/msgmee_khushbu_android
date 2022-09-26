package com.inmortal.messenger.Settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inmortal.messenger.R
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog

class Privacy_Setting : AppCompatActivity() {

    lateinit var back_arr:ImageView
    lateinit var constraint_lastseen:ConstraintLayout
    lateinit var radiobtn_everyone:RadioButton
    lateinit var constraint_profile_photo:ConstraintLayout
    lateinit var constraint_online_status:ConstraintLayout
    lateinit var constraint_group_privacy:ConstraintLayout
    lateinit var constraint_groups:ConstraintLayout
    lateinit var constraint_live_location:ConstraintLayout
    lateinit var radiobtn_mycontact:RadioButton
    lateinit var radiobtn_mycontactexcept:RadioButton
    lateinit var radiobtn_nobody:RadioButton
    lateinit var radiobtn__profile_everyone:RadioButton
    lateinit var radiobtn_profile_mycontact:RadioButton
    lateinit var radiobtn_profile_mycontactexcept:RadioButton
    lateinit var radiobtn_profile_nobody:RadioButton
    lateinit var radiobtn__status_everyone:RadioButton
    lateinit var radiobtn_status_mycontact:RadioButton
    lateinit var radiobtn_status_mycontactexcept:RadioButton
    lateinit var radiobtn_status_nobody:RadioButton
    lateinit var radiobtn__groupsp_everyone:RadioButton
    lateinit var radiobtn_groupsp_mycontact:RadioButton
    lateinit var radiobtn_groupsp_mycontactexcept:RadioButton
    lateinit var radiobtn_groupsp_nobody:RadioButton
    lateinit var radiobtn__group_everyone:RadioButton
    lateinit var radiobtn_group_mycontact:RadioButton
    lateinit var radiobtn_group_mycontactexcept:RadioButton
    lateinit var radiobtn_group_nobody:RadioButton
    lateinit var radiobtn__location_everyone:RadioButton
    lateinit var radiobtn_location_mycontact:RadioButton
    lateinit var radiobtn_location_mycontactexcept:RadioButton
    lateinit var radiobtn_location_nobody:RadioButton

    lateinit var bottomsheet_lastseen: BottomSheetDialog
    lateinit var bottomsheet_profile_photo:BottomSheetDialog
    lateinit var bottomsheet_online_status:BottomSheetDialog
    lateinit var bottomsheet_group_privacy:BottomSheetDialog
    lateinit var bottomsheet_group:BottomSheetDialog
    lateinit var bottomsheet_live_location:BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_setting)


        back_arr = findViewById(R.id.back_arr)
        constraint_lastseen = findViewById(R.id.constraint_lastseen)
        constraint_profile_photo = findViewById(R.id.constraint_profile_photo)
        constraint_online_status = findViewById(R.id.constraint_online_status)
        constraint_group_privacy = findViewById(R.id.constraint_group_privacy)
        constraint_groups = findViewById(R.id.constraint_groups)
        constraint_live_location = findViewById(R.id.constraint_live_location)

        back_arr.setOnClickListener(View.OnClickListener {

            onBackPressed()

        })

        constraint_lastseen.setOnClickListener(View.OnClickListener {

            bottomsheet_lastseen = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_lastseen,null)

            radiobtn_everyone  = view.findViewById(R.id.radiobtn_everyone)

            radiobtn_everyone.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                {
                    bottomsheet_lastseen.dismiss()
                }
            }


            radiobtn_mycontact = view.findViewById(R.id.radiobtn_mycontact)

            radiobtn_mycontact.setOnCheckedChangeListener { buttonView, isChecked ->

                if(isChecked){
                    bottomsheet_lastseen.dismiss()
                }
            }

            radiobtn_mycontactexcept = view.findViewById(R.id.radiobtn_mycontactexcept)
            radiobtn_mycontactexcept.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){
                    bottomsheet_lastseen.dismiss()
                }
            }

            radiobtn_nobody = view.findViewById(R.id.radiobtn_nobody)
            radiobtn_nobody.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){

                    bottomsheet_lastseen.dismiss()
                }
            }

            bottomsheet_lastseen.dismiss()
            bottomsheet_lastseen.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_lastseen.setContentView(view)
            bottomsheet_lastseen.show()



        })

        constraint_profile_photo.setOnClickListener(View.OnClickListener {

            bottomsheet_profile_photo = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_profile_photo,null)

            radiobtn__profile_everyone = view.findViewById(R.id.radiobtn__profile_everyone)
            radiobtn__profile_everyone.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){

                    bottomsheet_profile_photo.dismiss()
                }
            }
            radiobtn_profile_mycontact = view.findViewById(R.id.radiobtn_profile_mycontact)
            radiobtn_profile_mycontact.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){

                    bottomsheet_profile_photo.dismiss()
                }
            }
            radiobtn_profile_mycontactexcept = view.findViewById(R.id.radiobtn_profile_mycontactexcept)
            radiobtn_profile_mycontactexcept.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){

                    bottomsheet_profile_photo.dismiss()
                }
            }

            radiobtn_profile_nobody = view.findViewById(R.id.radiobtn_profile_nobody)
            radiobtn_profile_nobody.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){

                    bottomsheet_profile_photo.dismiss()
                }
            }

            bottomsheet_profile_photo.dismiss()
            bottomsheet_profile_photo.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_profile_photo.setContentView(view)
            bottomsheet_profile_photo.show()


        })

        constraint_online_status.setOnClickListener(View.OnClickListener {

            bottomsheet_online_status = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_online_status,null)

            radiobtn__status_everyone = view.findViewById(R.id.radiobtn__status_everyone)
            radiobtn__status_everyone.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){

                    bottomsheet_online_status.dismiss()
                }
            }

            radiobtn_status_mycontact = view.findViewById(R.id.radiobtn_status_mycontact)
            radiobtn_status_mycontact.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){

                    bottomsheet_online_status.dismiss()
                }
            }

            radiobtn_status_mycontactexcept = view.findViewById(R.id.radiobtn_status_mycontactexcept)
            radiobtn_status_mycontactexcept.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){

                    bottomsheet_online_status.dismiss()
                }
            }

            radiobtn_status_nobody = view.findViewById(R.id.radiobtn_status_nobody)
            radiobtn_status_nobody.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){

                    bottomsheet_online_status.dismiss()
                }
            }

            bottomsheet_online_status.dismiss()
            bottomsheet_online_status.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_online_status.setContentView(view)
            bottomsheet_online_status.show()

        })

        constraint_group_privacy.setOnClickListener(View.OnClickListener {


            bottomsheet_group_privacy = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_group_privacy,null)

            radiobtn__groupsp_everyone = view.findViewById(R.id.radiobtn__groupsp_everyone)
            radiobtn__groupsp_everyone.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){

                    bottomsheet_group_privacy.dismiss()
                }
            }

            radiobtn_groupsp_mycontact = view.findViewById(R.id.radiobtn_groupsp_mycontact)
            radiobtn_groupsp_mycontact.setOnCheckedChangeListener { buttonView, isChecked ->

                if(isChecked){

                    bottomsheet_group_privacy.dismiss()
                }
            }

            radiobtn_groupsp_mycontactexcept = view.findViewById(R.id.radiobtn_groupsp_mycontactexcept)
            radiobtn_groupsp_mycontactexcept.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){

                    bottomsheet_group_privacy.dismiss()
                }
            }

            radiobtn_groupsp_nobody = view.findViewById(R.id.radiobtn_groupsp_nobody)
            radiobtn_groupsp_nobody.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){

                    bottomsheet_group_privacy.dismiss()
                }
            }

            bottomsheet_group_privacy.dismiss()
            bottomsheet_group_privacy.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_group_privacy.setContentView(view)
            bottomsheet_group_privacy.show()

        })

        constraint_groups.setOnClickListener(View.OnClickListener {

            bottomsheet_group = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_group,null)

            radiobtn__group_everyone = view.findViewById(R.id.radiobtn__group_everyone)
            radiobtn__group_everyone.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    bottomsheet_group.dismiss()
                }
            }

            radiobtn_group_mycontact = view.findViewById(R.id.radiobtn_group_mycontact)
            radiobtn_group_mycontact.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    bottomsheet_group.dismiss()
                }
            }

            radiobtn_group_mycontactexcept = view.findViewById(R.id.radiobtn_group_mycontactexcept)

            radiobtn_group_mycontactexcept.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){

                    bottomsheet_group.dismiss()
                }
            }

            radiobtn_group_nobody = view.findViewById(R.id.radiobtn_group_nobody)
            radiobtn_group_nobody.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){

                    bottomsheet_group.dismiss()
                }
            }
            bottomsheet_group.dismiss()
            bottomsheet_group.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_group.setContentView(view)
            bottomsheet_group.show()

        })

        constraint_live_location.setOnClickListener(View.OnClickListener {

            bottomsheet_live_location = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_live_location,null)

            radiobtn__location_everyone = view.findViewById(R.id.radiobtn__location_everyone)
            radiobtn__location_everyone.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){

                    bottomsheet_live_location.dismiss()
                }
            }

            radiobtn_location_mycontact = view.findViewById(R.id.radiobtn_location_mycontact)
            radiobtn_location_mycontact.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){

                    bottomsheet_live_location.dismiss()
                }
            }

            radiobtn_location_mycontactexcept = view.findViewById(R.id.radiobtn_location_mycontactexcept)
            radiobtn_location_mycontactexcept.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){

                    bottomsheet_live_location.dismiss()
                }
            }

            radiobtn_location_nobody = view.findViewById(R.id.radiobtn_location_nobody)
            radiobtn_location_nobody.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){

                    bottomsheet_live_location.dismiss()
                }
            }

            bottomsheet_live_location.dismiss()
            bottomsheet_live_location.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_live_location.setContentView(view)
            bottomsheet_live_location.show()

        })
    }
}