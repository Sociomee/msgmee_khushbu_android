package com.inmortal.messenger.Settings

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.inmortal.messenger.R

class Report_Problem_Activity : AppCompatActivity() {

    private var edt_reason: EditText? = null
    lateinit var max: TextView
    var strame = " "
    lateinit var text_add_screenshot:TextView
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var profile_dp_value = ""
    lateinit var image_add: ImageView

    lateinit var img_back_aroww:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_problem)


        edt_reason = findViewById(R.id.edt_reason)
        max =findViewById(R.id.max)
        img_back_aroww = findViewById(R.id.img_back_aroww)
        text_add_screenshot = findViewById(R.id.text_add_screenshot)
        image_add = findViewById(R.id.image_add)

        edt_reason!!.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                strame = s.toString()

                if (strame.length > 360)
                {
                    edt_reason!!.error = "Max 360 Characters"
                }
                max.text = "Max " + (360 - strame.length ).toString() + " Characters"
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        img_back_aroww.setOnClickListener(View.OnClickListener {

            onBackPressed()

        })

        text_add_screenshot.setOnClickListener(View.OnClickListener {

            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent, pickImage)

        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            profile_dp_value = getRealPathFromURI(imageUri)
            image_add.setImageURI(imageUri)
        }

        else
        {
            Toast.makeText(this , "Pic format is not supportable" , Toast.LENGTH_SHORT).show()
        }
    }
    private fun getRealPathFromURI(selectedImage: Uri?): String {
        val result: String
        val cursor =
            selectedImage?.let { contentResolver.query(it, null, null, null, null) }
        if (cursor == null) {
            result = selectedImage?.path.toString()
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }
}