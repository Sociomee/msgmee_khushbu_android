package com.inmortal.messenger.Settings

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.R

class SyncingInvite_Activity : AppCompatActivity() {

    lateinit var img_back_arow: ImageView
    lateinit var constraint_share_link:ConstraintLayout
    lateinit var constraint_invite_contact: ConstraintLayout

    lateinit var bottomsheet_sharelink: BottomSheetDialog

    val PERMISSIONS_REQUEST_READ_CONTACTS = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_syncing_invite)
        img_back_arow = findViewById(R.id.img_back_arow)
        constraint_share_link = findViewById(R.id.constraint_share_link)
        constraint_invite_contact = findViewById(R.id.constraint_invite_contact)


        img_back_arow.setOnClickListener(View.OnClickListener {

            onBackPressed()
        })

        constraint_share_link.setOnClickListener(View.OnClickListener {

            bottomsheet_sharelink = BottomSheetDialog(this,R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_sharelink,null)

            bottomsheet_sharelink.dismiss()
            bottomsheet_sharelink.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_sharelink.setContentView(view)
            bottomsheet_sharelink.show()

        })

        constraint_invite_contact.setOnClickListener(View.OnClickListener {


            requestContactPermission()


        })



    }

    fun requestContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS)) {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                    builder.setTitle("Read Contacts permission")
                    builder.setPositiveButton(android.R.string.ok, null)
                    builder.setMessage("Please enable access to contacts.")
                    builder.setOnDismissListener(DialogInterface.OnDismissListener {
                        requestPermissions(
                            arrayOf(android.Manifest.permission.READ_CONTACTS),
                            PERMISSIONS_REQUEST_READ_CONTACTS
                        )
                    })
                    builder.show()
                } else {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(android.Manifest.permission.READ_CONTACTS),
                        PERMISSIONS_REQUEST_READ_CONTACTS
                    )
                }
            } else {
                val intent = Intent(this@SyncingInvite_Activity,Contact_Invite_Activity::class.java)
                startActivity(intent)

            }
        } else {
//            val intent = intent(this@syncinginvite_activity,contact_invite_activity::class.java)
//            startActivity(intent)

        }
    }
}