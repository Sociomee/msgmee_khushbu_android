package com.inmortal.messenger.activity


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.inmortal.messenger.R
import com.inmortal.messenger.URL.ApiURL
import com.inmortal.messenger.URL.CustomProgressDialog
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONObject
import java.io.File


class MVVM : AppCompatActivity() {

    private val customDialog: CustomProgressDialog? = null
    var token = ""
    var fileLienceData: File? = null
    var imageUser: CircleImageView? = null
    lateinit var text_m: TextView
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var filLience = ""
    private val arrPic: ArrayList<String> = ArrayList<String>()
    var newStr = ""
//  Fab Comps +=??>>
// Use the FloatingActionButton for all the add person
// and add alarm
lateinit var mAddAlarmFab: FloatingActionButton
    lateinit var mAddPersonFab: FloatingActionButton
  lateinit  var mAddFab: ExtendedFloatingActionButton
   lateinit var addAlarmActionText: RelativeLayout
   lateinit var addPersonActionText:  RelativeLayout
    var isAllFabsVisible: Boolean? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)

        imageUser = findViewById(R.id.profile_mvvm)

        imageUser!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent, pickImage)
        })

        token = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiOWExMjQ1ZDY5NGEwMDI4MzljZTMzMTNhMzNlZDg3MzVmNDVhZjk3MjU0ZTYwNDA3YTk2ZDkwZDUxYzM5ZmI2MmFkYWEyMDNjZDE3ZjAzMmEiLCJpYXQiOjE2NTUxODU5MDQsIm5iZiI6MTY1NTE4NTkwNCwiZXhwIjoxNjg2NzIxOTA0LCJzdWIiOiIyMzciLCJzY29wZXMiOltdfQ.HahnXq68zPJJU-1CsNCd5OT4RttkokGuYNtvpZOYcCRu3YTd6qWasqlrICzTRmHZ5EK-dGTigRPTD1B9FG5BDVeuqjzK4h7ZgLcIvfboVwjQbmLuapSkq1yAkXlRccwOvMOphadHeki4SjZFIZN2wBKtQF8oy9GrcOzUe3uL97I-ngq3cR7xqxv1jIuT6WaqlzIZH3S7xpxGGIJPbw0uHHeuOrIE4YjJN9p2N4WQ8C93SO5uTz6q1r-4Tp5fXip0T3AYBuVip_kU_c8zlIanMBCASXphfi_yhSbOcC_kEKWjihgjBHJmEBZv73ea4r3gdaHhe8SyQ6vVxLL1cc30xZlKVwK87lj4RrEWMtcYm8Zskqa2V7afer8-dtmWKfCsoS6eX8n3tIK5fCIz3263P40Vak1zx2-Y_DPbCRAaHpILD1ZPG17vy-TFbPC6zQfDoU-ZoAQK6xIugHEbzDWVEsam2-N3uLQopue5td06XYMJ4wsg1iVkHULDbe6Fl6nfFeuNl3hv6fNPh8DF6CN4mkt4CNmkk8sSg5lsLlmBr4F3S3UYw4Rg_DhtaIga73qVazXBjX9WxafTGes8kvjpv2kt9InzkPaFspXFrZtOnyWPgRnDeDDfjmPXr4VgIevEO4BxBzSYIFbsoCKlBumlhosyWTriNC4F_btYZ2pPiAI"
//        Test()

// _________________--------------------_____Fab_____________________________________

        mAddFab = findViewById(R.id.add_fab)
        mAddAlarmFab = findViewById(R.id.add_alarm_fab)
        mAddPersonFab = findViewById(R.id.add_person_fab)
        addAlarmActionText = findViewById(R.id.add_alarm_action_text)
        addPersonActionText = findViewById(R.id.add_person_action_text)
        mAddAlarmFab.setVisibility(View.GONE)
        mAddPersonFab.setVisibility(View.GONE)
        addAlarmActionText.setVisibility(View.GONE)
        addPersonActionText.setVisibility(View.GONE)

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are
        // invisible

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are
        // invisible
        isAllFabsVisible = false

        // Set the Extended floating action button to
        // shrinked state initially

        // Set the Extended floating action button to
        // shrinked state initially
        mAddFab.shrink()

        // We will make all the FABs and action name texts
        // visible only when Parent FAB button is clicked So
        // we have to handle the Parent FAB button first, by
        // using setOnClickListener you can see below

        // We will make all the FABs and action name texts
        // visible only when Parent FAB button is clicked So
        // we have to handle the Parent FAB button first, by
        // using setOnClickListener you can see below
        mAddFab.setOnClickListener(
            View.OnClickListener {
                isAllFabsVisible = if (!isAllFabsVisible!!) {

                    // when isAllFabsVisible becomes
                    // true make all the action name
                    // texts and FABs VISIBLE.
                    mAddAlarmFab.show()
                    mAddPersonFab.show()
                    addAlarmActionText.setVisibility(View.VISIBLE)
                    addPersonActionText.setVisibility(View.VISIBLE)

                    // Now extend the parent FAB, as
                    // user clicks on the shrinked
                    // parent FAB
                    mAddFab.extend()

                    // make the boolean variable true as
                    // we have set the sub FABs
                    // visibility to GONE
                    true
                } else {

                    // when isAllFabsVisible becomes
                    // true make all the action name
                    // texts and FABs GONE.
                    mAddAlarmFab.hide()
                    mAddPersonFab.hide()
                    addAlarmActionText.setVisibility(View.GONE)
                    addPersonActionText.setVisibility(View.GONE)

                    // Set the FAB to shrink after user
                    // closes all the sub FABs
                    mAddFab.shrink()

                    // make the boolean variable false
                    // as we have set the sub FABs
                    // visibility to GONE
                    false
                }
            })

        // below is the sample action to handle add person
        // FAB. Here it shows simple Toast msg. The Toast
        // will be shown only when they are visible and only
        // when user clicks on them

        // below is the sample action to handle add person
        // FAB. Here it shows simple Toast msg. The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
        mAddPersonFab.setOnClickListener(
            View.OnClickListener {
                Toast.makeText(this@MVVM, "Person Added", Toast.LENGTH_SHORT).show()
            })

        // below is the sample action to handle add alarm
        // FAB. Here it shows simple Toast msg The Toast
        // will be shown only when they are visible and only
        // when user clicks on them

        // below is the sample action to handle add alarm
        // FAB. Here it shows simple Toast msg The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
        mAddAlarmFab.setOnClickListener(
            View.OnClickListener {
                Toast.makeText(
                    this@MVVM,
                    "Alarm Added",
                    Toast.LENGTH_SHORT
                ).show()
            })
// _________________--------------------_____Fab_____________________________________
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
            Test()

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



    private fun Test() {
        customDialog?.showProgressBar()
        Ion.with(this@MVVM)
            .load("POST", ApiURL.Test_URL)
            .setHeader("Authorization",token)
            .setMultipartFile("image", fileLienceData)
            .setMultipartParameter("_method", "PUT")
            .asString()
            .setCallback(FutureCallback<String?> { _, result ->
                try {
                    val jsonObject = JSONObject(result)
                    jsonObject.getString("status")
                    jsonObject.getString("code")
                    val message = jsonObject.getString("message")
                    if (message == "Profile Updated Successfully.")
                    {
                        customDialog?.hideProgressBar()
                        val jsonObject1: JSONObject = jsonObject.getJSONObject("data")
                        Toast.makeText(this@MVVM , jsonObject.toString() , Toast.LENGTH_SHORT).show()
                        val image_base_url = jsonObject1.getString("image_base_url")
                        if (image_base_url == "http://perfectdemo.xyz/eagle/old-jerusalem-restaurant/storage/") {
                            var JSONObject2: JSONObject = jsonObject1.getJSONObject("user_info")

                            val id = JSONObject2.getString("id")
                            val role = JSONObject2.getString("role")
                            val username = JSONObject2.getString("username" )
                            val gender = JSONObject2.getString("gender")
                            val name = JSONObject2.getString("name")
                            val email = JSONObject2.getString("email")
                            val mobile_number = JSONObject2.getString("mobile_number")
                            val dob = JSONObject2.getString("dob")
                            val user_status = JSONObject2.getString("user_status")
                            val sender_referral_status = JSONObject2.getString("sender_referral_status")
                            val receiver_referral_status = JSONObject2.getString("receiver_referral_status")
                            val created_at = JSONObject2.getString("created_at")
                            val updated_at = JSONObject2.getString("updated_at")

                            text_m = findViewById(R.id.mobile)
                            text_m.setText(mobile_number)
                        }

                    }
                    else
                    {
                        customDialog?.hideProgressBar()

                    }
                }
                catch (e1: Exception)
                {
                    e1.printStackTrace()
                    customDialog?.hideProgressBar()
//                    Toast.makeText(this@Profile, e1.toString(), Toast.LENGTH_SHORT).show()
                }
            })

    }
}