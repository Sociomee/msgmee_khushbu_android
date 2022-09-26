package com.inmortal.messenger.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.inmortal.messenger.R
import com.inmortal.messenger.URL.ApiURL
import com.inmortal.messenger.URL.CustomProgressDialog
import com.inmortal.messenger.Adapter.CheckNameList
import com.inmortal.messenger.model.CheckNameModel
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.lang.Exception
import java.util.*
import androidx.recyclerview.widget.GridLayoutManager


class Profile : AppCompatActivity() , CheckNameList.ReturnView {
    var imageUser: CircleImageView? = null
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var header: LinearLayout? = null
    private var submit: LinearLayout? = null
    private var suggestion: RelativeLayout? = null
    private var name: EditText? = null
    private var ContinueTxt: TextView? = null
    lateinit var max: TextView
    lateinit var uax: TextView
    lateinit var nxt: TextView
    private var UserNameEditText: EditText? = null
    private val customDialog: CustomProgressDialog? = null
    private val arrNameModel: ArrayList<CheckNameModel> = ArrayList<CheckNameModel>()
    private val arrNameString: ArrayList<String> = ArrayList<String>()
    private val arrPic: ArrayList<String> = ArrayList<String>()
    var checknameAdapter: CheckNameList? = null
    var token = ""
    var suggestionName = ""
    var suggestionName1 = ""
    var strUserName = ""
    var strame = ""
    var newStr = ""
    var strNewtoken = ""
    var fileLienceData: File? = null
    private  var cont:TextView? = null
    private var filLience = ""
    lateinit var recyclerView: RecyclerView
    private var sharedPreferences: SharedPreferences? = null
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        token= intent.getStringExtra("token").toString()
        strNewtoken = "Bearer " + token
        Log.e("token", strNewtoken)

        sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        val profileSetupNameStatusLogin= sharedPreferences!!.getString("profileSetupNameStatus","")
        val userStatusLogin = sharedPreferences!!.getString("userStatus" , "")
        if (profileSetupNameStatusLogin.equals("complete") && userStatusLogin.equals("existingUser")){
            val intent = Intent(this@Profile, Dashboard::class.java)
            startActivity(intent)
            finish()
        }else
        {

        }
        imageUser = findViewById(R.id.profile_image)
        header = findViewById(R.id.header)
        name = findViewById(R.id.Name)
        UserNameEditText = findViewById(R.id.UserNameEditText)
        submit = findViewById(R.id.submiT)
        recyclerView =findViewById(R.id.recyclerViews)
        ContinueTxt =findViewById(R.id.ContinueTxt)
        max =findViewById(R.id.max)
        uax =findViewById(R.id.uax)
        nxt = findViewById<TextView>(R.id.nxt)
        cont = findViewById(R.id.to)
        suggestion = findViewById(R.id.next_suggestions)


        imageUser!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent, pickImage)
        })


        nxt.setOnClickListener(View.OnClickListener {
//            Toast.makeText(this@Profile , "hi$it" , Toast.LENGTH_SHORT).show()
            CheckName()
        })

        UserNameEditText!!.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                strUserName = s.toString()


                uax.text = "Max " + (24 - strUserName.length ).toString() + " Characters"

                when
                {
                    strUserName.length >= 1 -> {
                        suggestion?.visibility = View.VISIBLE
                        recyclerView.visibility = View.VISIBLE
                        submit!!.background = ActivityCompat.getDrawable(this@Profile, R.drawable.green_box)
                        ContinueTxt!!.setTextColor(AppCompatResources.getColorStateList(this@Profile, R.color.white))
                        cont!!.setTextColor(Color.parseColor("#81C14B"))
                        CheckName(/*strUserName*/)
                    }

//                    strUserName.length >= 1 -> {
//                        CheckName()
//                    }

                    strUserName.length < 1 -> {
//                        UserNameEditText!!.error = "Enter At least 5 Character"
                        suggestion?.visibility = View.GONE
                        recyclerView.visibility = View.GONE
                        submit!!.background = ActivityCompat.getDrawable(this@Profile, R.drawable.purple_box)
                        ContinueTxt!!.setTextColor(AppCompatResources.getColorStateList(this@Profile, R.color.white))
                        cont!!.setTextColor(Color.parseColor("#9597A1"))
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {

            }
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



        submit!!.setOnClickListener(View.OnClickListener {
            Co()
        })

        cont!!.setOnClickListener(View.OnClickListener {
            Co()
        })
//        strame=name!!.text.toString()
//        strUserName=UserNameEditText!!.text.toString()
//        Green()
    }

    private fun Co()
    {
        strame=name!!.text.toString()
        strUserName=UserNameEditText!!.text.toString()

        when {
//                strame.isBlank() -> {
//                    name!!.error = "Enter Your Name."
//                }
            strame.length > 64 -> {
                name!!.error = "Max 64 Characters"
            }
            strUserName.isBlank() -> {
                UserNameEditText!!.error = "Enter UserName."
            }
            filLience == ""||filLience.equals(null)|| filLience == "null" ->
            {
                Toast.makeText(this@Profile, "Please Select Image", Toast.LENGTH_SHORT).show()
            }
            else-> {
                submit!!.background = ActivityCompat.getDrawable(this@Profile, R.drawable.green_box)
                ContinueTxt!!.setTextColor(AppCompatResources.getColorStateList(this@Profile, R.color.white))
                cont!!.setTextColor(Color.parseColor("#81C14B"))
                EditProfile()
                startActivity(Intent(this@Profile,Dashboard::class.java))
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage)
        {
//            imageUri = data?.data
//            imageUser!!.setImageURI(imageUri)

            imageUri = data?.data
            filLience = getRealPathFromURI(imageUri)
            fileLienceData = File(filLience)
            imageUser!!.setImageURI(imageUri)
            UploadDP()

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



    private fun CheckName(/*strUserName: String*/)
    {
        customDialog?.showProgressBar()
            Ion.with(this@Profile)
                .load("POST", ApiURL.CheckUsername_URL)
                .setHeader("Authorization",strNewtoken)
//                .setBodyParameter("fullName", strUserName)
                .setBodyParameter("userName",strUserName)
                .asString()
                .setCallback(FutureCallback<String?> { _, result ->
                    try
                    {
                        val jsonObject = JSONObject(result)
                        val success = jsonObject.getString("success")
                        Log.e("success ", success)

                        if (success == "false")
                        {
                            customDialog?.hideProgressBar()
                            arrNameString.clear()
                            var jsonObject1: JSONObject = jsonObject.getJSONObject("data")
                            var jsonObject2: JSONObject = jsonObject1.getJSONObject("errorResult")

//                            User Name Not EXISTS  --->

//                          val UserNameExists = arrNameString.filter { it.contains(UserNameEditText?.text.toString()) }
//                            val User = arrNameString.filter { it.contains(strUserName) }
//                           if (UserNameExists.equals(true))
//                           {
//                               recyclerView.visibility = View.GONE
//                           }
//                            if ((User.equals(true)))
//                            {
//                                recyclerView.visibility = View.GONE
//                            }
//                            if (strUserName in arrNameString)
//                            {
//                                recyclerView.visibility = View.GONE
//                            }

//                      ____----____________---------------------________________--
//                            if(strUserName == arrNameString[0])
//                            {
//                                recyclerView.visibility = View.GONE
//                                suggestion?.visibility = View.GONE
//
//                            }
//                            else
//                            {
//                                recyclerView.visibility = View.VISIBLE
//                                suggestion?.visibility = View.VISIBLE
//
//                            }
//                            ----_____------_____-----___

                           val message = jsonObject2.getString("message")
                            Log.e("message ", message)

                            var arr: JSONArray
                            try {
                                customDialog?.hideProgressBar()
                                arr = jsonObject2.getJSONArray("userNameList")
                                Log.e("arr", arr.toString())
                                for (i in 0 until arr.length())
                                {
                                    arrNameString.add(arr.get(i).toString())
                                    Log.e("arrNameString",arrNameString.toString())
                                }
                                checknameAdapter = CheckNameList(arrNameString, this@Profile, R.layout.checkname_layout, this, 1)
//                                val layoutManager1 = LinearLayoutManager(this@Profile, LinearLayoutManager.VERTICAL, false)
                                val numberOfColumns = 2

                                recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
//                                recyclerView.layoutManager = layoutManager1
                                recyclerView.setHasFixedSize(true)
                                recyclerView.adapter = checknameAdapter

                            } catch (e: Exception) {
                                customDialog?.hideProgressBar()
                                e.printStackTrace()
//                                Toast.makeText(this@Profile, e.toString(), Toast.LENGTH_SHORT)
//                                    .show()
                            }


                        } else {
                            customDialog?.hideProgressBar()
//                        startActivity(Intent(this@SignIn, Personalize::class.java))

//                            Toast.makeText(this@Profile, success, Toast.LENGTH_SHORT).show()

                        }
                    } catch (e1: Exception) {
                        e1.printStackTrace()
                        customDialog?.hideProgressBar()

//                        Toast.makeText(this@Profile, e1.toString(), Toast.LENGTH_SHORT).show()

                    }
                })


        }
  private  fun UploadDP()
  {
      customDialog?.showProgressBar()
      Ion.with(this@Profile)
          .load("POST", ApiURL.UploadDP_URL)
          .setHeader("Authorization",strNewtoken)
          .setMultipartParameter("uploadFor","userProfileDp")
          .setMultipartFile("",fileLienceData)
          .asString()
          .setCallback(FutureCallback<String?> { _, result ->
              try {
                  val jsonObject = JSONObject(result)
                  val success = jsonObject.getString("success")
                  if (success == "true") {
                      customDialog?.hideProgressBar()
                      var jsonObject1: JSONObject = jsonObject.getJSONObject("data")
                      val arr : JSONArray = jsonObject1.getJSONArray("successResult")
                      Log.e("arr" , arr.toString())

                      Log.e("arr", arr.toString())
                      arrPic.add(arr.get(0).toString())
                      Log.e("arrPic",arrPic.toString())

                      newStr=arrPic[0]
                      var imgeSting= "https://sociomee-dev.s3.ap-south-1.amazonaws.com/$newStr"
                      Log.e("imgeSting",imgeSting)
                      Picasso.get().load(imgeSting).into(imageUser)

                  }
                  else{
                      customDialog?.hideProgressBar()
                  }
              }
              catch (e1: Exception)
              {
                  e1.printStackTrace()
                  customDialog?.hideProgressBar()
//                  Toast.makeText(this@Profile, e1.toString(), Toast.LENGTH_SHORT).show()
              }
          })

  }

    private fun EditProfile()
    {
        customDialog?.showProgressBar()
        Ion.with(this@Profile)
            .load("POST", ApiURL.EditProfile_URL)
            .setHeader("Authorization",strNewtoken)
            .setMultipartParameter("fullName", strame)
            .setMultipartParameter("userName", strUserName)
            .setMultipartFile("profileImage",fileLienceData)
            .asString()
            .setCallback(FutureCallback<String?> { _, result ->
                try {
                    val jsonObject = JSONObject(result)
                    val success = jsonObject.getString("success")
                    if (success == "true")
                    {
                        customDialog?.hideProgressBar()
                        var jsonObject1: JSONObject = jsonObject.getJSONObject("data")
                        val successResult = jsonObject1.getString("successResult")
//                        Toast.makeText(this@Profile, successResult, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@Profile,Dashboard::class.java))
                        finish()
                    }
                    else
                    {
                        customDialog?.hideProgressBar()
//                        startActivity(Intent(this@SignIn, Personalize::class.java))
//                        Toast.makeText(this@Profile, success, Toast.LENGTH_SHORT).show()
                    }
                }
                catch (e1: Exception)
                {
                    e1.printStackTrace()
                    customDialog?.hideProgressBar()
//                    Toast.makeText(this@Profile, e1.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        if(strame.isEmpty())
        {
            strame = " "
        }
        if (strUserName.isEmpty())
        {
            strUserName = " "
        }
//        if (fileLienceData!!.equals(null) || filLience == "" || filLience.equals(null) || filLience == "null")
//        {
//            filLience = " "
//           filLience = getRealPathFromURI(imageUri)
//            fileLienceData = File(filLience)
//        }

//        else if ( filLience == ""|| filLience.equals(null) || filLience == "null" )
//        {
////            fileLienceData = null
////            (fileLienceData as Nothing?).equals(null)
//        }

    }

    override fun getAdapterView(view: View?, objects: MutableList<Any?>?, position: Int, from: Int) {
        val liner = view!!.findViewById<View>(R.id.liner_two) as LinearLayout
        val suggestionTxt = view.findViewById<View>(R.id.suggestionTxt) as TextView
        suggestionName=arrNameString[position]
        suggestionTxt.text = suggestionName
//       <---------------- USerName Dont Exists-------------------->
//        val ifUserNameExists = arrNameString.filter { it.contains(suggestionName) }
//         if(ifUserNameExists.equals(true))
//        {
//            recyclerView.visibility = View.GONE
//        }
//        if (arrNameString.contains(suggestionName))
//        {
//            recyclerView.visibility = View.GONE
//        }
//        ------------__________________________--------_________----------__________---------
        liner.setOnClickListener(View.OnClickListener {
            suggestionName=arrNameString[position]
            Log.e("usern" , suggestionName)
            UserNameEditText?.setText(suggestionName)

        })
    }
}