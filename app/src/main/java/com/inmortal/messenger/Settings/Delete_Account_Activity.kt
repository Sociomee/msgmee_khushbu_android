package com.inmortal.messenger.Settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.R

class Delete_Account_Activity : AppCompatActivity() {

    lateinit var back_arroww: ImageView
    lateinit var btn_delete_account: Button
    lateinit var edt_mobile_no:EditText
    lateinit var edt_password: EditText
    lateinit var btnok_delete_account: AppCompatButton
    lateinit var btnok_delete_cancel:AppCompatButton

    lateinit var bottomsheet_delete_account: BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)

        back_arroww = findViewById(R.id.back_arroww)
        btn_delete_account = findViewById(R.id.btn_delete_account)
        edt_mobile_no = findViewById(R.id.edt_mobile_no)
        edt_password = findViewById(R.id.edt_password)

        back_arroww.setOnClickListener(View.OnClickListener {

            onBackPressed()

        })

        btn_delete_account.setOnClickListener(View.OnClickListener {


            if(edt_mobile_no.text.toString().isEmpty()){
                edt_mobile_no.error = "Please enter mobile no"
                edt_mobile_no.requestFocus()

            }
            else if (edt_mobile_no.text.toString().length<10){
                edt_mobile_no.error = "Please enter atleast 10 digits"
                edt_mobile_no.requestFocus()
            }


            else if (edt_password.text.toString().isEmpty()){
                edt_password.error = "Please Enter Password"
                edt_password.requestFocus()
            }
            else{

                bottomsheet_delete_account = BottomSheetDialog(this,R.style.BottomSheetTheme)
                val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_delete_account,null)

                btnok_delete_account = view.findViewById(R.id.btnok_delete_account)
                btnok_delete_account.setOnClickListener(View.OnClickListener {

                    Toast.makeText(this,"Account Deleted",Toast.LENGTH_SHORT).show()
                    bottomsheet_delete_account.dismiss()

                })

                btnok_delete_cancel = view.findViewById(R.id.btnok_delete_cancel)
                btnok_delete_cancel.setOnClickListener(View.OnClickListener {

                    bottomsheet_delete_account.dismiss()
                })

                bottomsheet_delete_account.dismiss()
                bottomsheet_delete_account.requestWindowFeature(Window.FEATURE_NO_TITLE)
                bottomsheet_delete_account.setContentView(view)
                bottomsheet_delete_account.show()
            }


        })
    }
}