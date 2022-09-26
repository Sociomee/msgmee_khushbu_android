package com.inmortal.messenger.profile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.R
import com.inmortal.messenger.Settings.SettingsMain
import de.hdodenhof.circleimageview.CircleImageView


class ProfileSettings : AppCompatActivity() {

    lateinit var profile_image:CircleImageView
    private var profile_dp_value = ""
    private var name: EditText? = null
    var strame = ""
    var strDayss = ""
    var strMonths = ""
    var strYears = ""
    var strDob = ""
    lateinit var max: TextView
    private var UserNameEditText: EditText? = null
    private var email: EditText? = null
    lateinit var dob: EditText
    lateinit var day: EditText
    lateinit var month: EditText
    lateinit var year: EditText
    lateinit var gender: EditText
    lateinit var interest: RelativeLayout
    lateinit var open_camera: LinearLayout
    lateinit var open_gallery: LinearLayout
    lateinit var remove_pic: LinearLayout
    lateinit var other_gender_view: LinearLayout
    lateinit var other_gender_button: RadioButton
    lateinit var radioGroupTop : RadioGroup
    lateinit var radioGroupBottom : RadioGroup
    private var submit: LinearLayout? = null

    //        Intializing Multiple Bottom Sheets
    lateinit var bottomSheetDialog_clear: BottomSheetDialog
    lateinit var bottomSheetDialog_dob: BottomSheetDialog
    lateinit var bottomSheetDialog_gender: BottomSheetDialog
    lateinit var bottomSheetDialog_interest: BottomSheetDialog

    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_settings)

        name = findViewById(R.id.Name)
        max =findViewById(R.id.max)
        profile_image = findViewById(R.id.profile_image)
        UserNameEditText = findViewById(R.id.UserNameEditText)
        email = findViewById(R.id.email)
        dob = findViewById(R.id.dob)
        gender = findViewById(R.id.gender)
        interest = findViewById(R.id.interest)
        submit = findViewById(R.id.submiT)

        profile_image.setOnClickListener(View.OnClickListener {
            bottomSheetDialog_clear = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_pic, null)
            open_camera = view.findViewById(R.id.open_camera)
            open_gallery = view.findViewById(R.id.open_gallery)
            remove_pic = view.findViewById(R.id.remove_pic)



            open_camera.setOnClickListener(View.OnClickListener {
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
                startActivityForResult(intent , 7)
                bottomSheetDialog_clear.dismiss()
            })
            open_gallery.setOnClickListener(View.OnClickListener {
                val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(intent, pickImage)
                bottomSheetDialog_clear.dismiss()
            })
            remove_pic.setOnClickListener(View.OnClickListener {
                profile_image.setImageResource(R.drawable.pic)
                bottomSheetDialog_clear.dismiss()
            })

            bottomSheetDialog_clear.dismiss()
            bottomSheetDialog_clear.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog_clear.setContentView(view)
            bottomSheetDialog_clear.show()
        })

        name!!.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                strame = s.toString()

                if (strame.length > 64)
                {
                    name!!.error = "Max 64 Characters"
                }
                max.text = "Max " + (64 - strame.length ).toString() + " Characters"
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {

            }
        })


        dob.setOnClickListener(View.OnClickListener {
            bottomSheetDialog_dob = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_dob, null)
            day = view.findViewById(R.id.day)
            month = view.findViewById(R.id.month)
            year = view.findViewById(R.id.year)
            strDayss = day.text.toString()
            strMonths = month.text.toString()
            strYears = year.text.toString()


    day.addTextChangedListener(object : TextWatcher{
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
     strDayss  = p0.toString()
        strDob = strDayss + strMonths + strYears
    }

    override fun afterTextChanged(p0: Editable?) {

    }

})

    month.addTextChangedListener(object : TextWatcher{
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
     strMonths  = p0.toString()
        strDob = strDayss + strMonths + strYears
    }

    override fun afterTextChanged(p0: Editable?) {

    }

})

            year.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    strYears = p0.toString()
                    if (strYears.length==4){
                        strDob = "$strDayss - $strMonths - $strYears"
                        dob.setText(strDob)
                        Log.d("TAG" ,  strDob)
                        bottomSheetDialog_dob.dismiss()
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })

            bottomSheetDialog_dob.dismiss()
            bottomSheetDialog_dob.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog_dob.setContentView(view)
            bottomSheetDialog_dob.show()
        })

        gender.setOnClickListener(View.OnClickListener {
            bottomSheetDialog_gender = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_gender, null)
           other_gender_view = view.findViewById(R.id.other_gender_view)
            other_gender_button = view.findViewById(R.id.radio_three)
            radioGroupTop = view.findViewById(R.id.radioGroup)
            radioGroupBottom = view.findViewById(R.id.radioGroupBottom)


            radioGroupTop.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.radio_one -> {
                   gender.setText("Male")
                        bottomSheetDialog_gender.dismiss()
                    }
                    R.id.radio_two -> {
                        gender.setText("Female")
                        bottomSheetDialog_gender.dismiss()
                    }
                  }
            })

            other_gender_button.setOnCheckedChangeListener { compoundButton, b ->
                if (b)
                {
                    other_gender_view.visibility=View.VISIBLE
                    radioGroupBottom.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
                        when (checkedId) {
                            R.id.radioButtonHe -> {
                                gender.setText("He")
                                bottomSheetDialog_gender.dismiss()
                            }
                            R.id.radioButtonShe -> {
                                gender.setText("She")
                                bottomSheetDialog_gender.dismiss()
                            }
                            R.id.radioButtonNo -> {
                                gender.setText("Rather not specify")
                                bottomSheetDialog_gender.dismiss()
                            }
                        }
                    })
                }
                else
                {
                    other_gender_view.visibility=View.GONE
                }
            }
            bottomSheetDialog_gender.dismiss()
            bottomSheetDialog_gender.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog_gender.setContentView(view)
            bottomSheetDialog_gender.show()
        })

        interest.setOnClickListener(View.OnClickListener {
            bottomSheetDialog_interest = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_interest, null)

            val photography_tab:RelativeLayout = view.findViewById(R.id.photography_tab)
            val books_tab:RelativeLayout = view.findViewById(R.id.books_tab)
            val photography_txt:TextView = view.findViewById(R.id.photography_txt)
            val books_txt:TextView = view.findViewById(R.id.books_txt)

            if(bottomSheetDialog_interest.isShowing)
            {
                interest.visibility=View.GONE
            }
            else
            {
                interest.visibility=View.VISIBLE
            }

            if (photography_tab.isPressed)
            {
                Toast.makeText(this , "Selected" , Toast.LENGTH_SHORT).show()
                photography_tab.background = ActivityCompat.getDrawable(this@ProfileSettings, R.drawable.green_box)
                photography_tab.setBackgroundColor(Color.parseColor("#81C14B"));
                photography_txt.setTextColor(AppCompatResources.getColorStateList(this@ProfileSettings, R.color.white))
            }
            bottomSheetDialog_interest.setCanceledOnTouchOutside(false)
            bottomSheetDialog_interest.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog_interest.setContentView(view)
            bottomSheetDialog_interest.show()
        })

        submit!!.setOnClickListener(View.OnClickListener {
            if (name!!.text.toString().isBlank())
            {
               name!!.error = "Enter Name"
                name!!.requestFocus()
            }
            else if(profile_dp_value == ""||profile_dp_value.equals(null)|| profile_dp_value == "null")
            {
                Toast.makeText(this@ProfileSettings , "Upload Photo" , Toast.LENGTH_SHORT).show()
            }
            else if(email!!.text.toString().isBlank())
            {
                email!!.error = "Enter Email"

            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email!!.text.toString()).matches())
            {
                email!!.error = "Email is not valid"
                name!!.requestFocus()

            }
            else if(UserNameEditText!!.text.toString().isBlank())
            {
                UserNameEditText!!.error = "Enter Username"
            }
//            else if(dob.text.toString().isBlank())
//            {
//                dob.error = "Enter Dob"
//            }
            else if(gender.text.toString().isBlank())
            {
                gender.error = "Choose Gender"
            }
            else
            {
                Toast.makeText(this@ProfileSettings , "Profile Edited Successfully" , Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            profile_dp_value = getRealPathFromURI(imageUri)
            profile_image.setImageURI(imageUri)
        }
        else if (requestCode == 7 && resultCode == RESULT_OK)
        {
            val bitmap = data?.extras!!["data"] as Bitmap?
            profile_dp_value = bitmap.toString()
            profile_image.setImageBitmap(bitmap)
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


    fun back(view: View) {
        val intent = Intent(this@ProfileSettings , SettingsMain::class.java)
        startActivity(intent)
    }
}