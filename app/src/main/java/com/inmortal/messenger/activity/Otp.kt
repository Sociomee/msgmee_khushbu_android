package com.inmortal.messenger.activity

import android.Manifest
import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings.Secure
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.chaos.view.PinView
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.inmortal.messenger.FetchOTP.SmsBroadcastReceiver
import com.inmortal.messenger.R
import com.inmortal.messenger.StartActivity
import com.inmortal.messenger.URL.ApiURL
import com.inmortal.messenger.URL.CustomProgressDialog
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import org.json.JSONObject
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*
import java.util.regex.Pattern


class Otp : AppCompatActivity() {

    var mobile = ""
    var str_mobile = ""
    var mobFour = ""
    var langId = ""
    var countryId = ""
    var strcountry_id = ""
    var strlanguage_id = ""

    var jsonOOO = "/chathead" // sd/shdg/"
    private val sharedPrefFile = "kotlinsharedpreference"
    var platform = ""
    var ipAddress = ""
    var deviceInfo = ""
    var locationLAT = ""
    var locationLONG = ""
    var fcmToken = ""
    var strotp = ""
    var otp = ""
    var selectedcountrycode = ""

    var pinView: PinView? = null
    lateinit var Verify: LinearLayout
    private var mTextViewCountDown: TextView? = null
    private var invalid: ImageView? = null
    private var lastFour: TextView? = null
    private  var mButtonReset:TextView? = null
    private  var yet:TextView? = null
    private  var cont:TextView? = null
    private  var arrow:ImageView? = null
    private val START_TIME_IN_MILLIS: Long = 20000
    private var mCountDownTimer: CountDownTimer? = null
    var token = ""
    var profileSetupNameStatus = ""
    var userStatus =""
    private var mTimerRunning = false

    private var mTimeLeftInMillis = START_TIME_IN_MILLIS

    private val customDialog: CustomProgressDialog? = null
    lateinit var confirm_phone: TextView
    lateinit var linerEdit: LinearLayout
    // declare a global variable of FusedLocationProviderClient
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var sharedPreferences: SharedPreferences? = null
    var android_id = ""

    private val REQ_USER_CONSENT = 200
    var smsBroadcastReceiver : SmsBroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        sharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        val profileSetupNameStatusLogin= sharedPreferences!!.getString("profileSetupNameStatus","")
        val userStatusLogin = sharedPreferences!!.getString("userStatus " , "")
       if (profileSetupNameStatusLogin.equals("complete") && userStatusLogin.equals("existingUser"))
       {
           val intent = Intent(this@Otp,Dashboard::class.java)
           startActivity(intent)
           finish()
       }
       else
       {

       }
        selectedcountrycode= intent.getStringExtra("selected_country_code").toString()
        langId= intent.getStringExtra("language_id").toString()

        mobile= intent.getStringExtra("mobile_no").toString()
        str_mobile= intent.getStringExtra("strMobile_number").toString()
        strcountry_id = intent.getStringExtra("country_idd").toString()
        strlanguage_id = intent.getStringExtra("language_idd").toString()
        Log.e("la",strlanguage_id)
        Log.e("sa",strcountry_id)
        Log.e("nooo",str_mobile)

        mobFour = mobile.substring(Math.max(mobile.length - 4, 0))
        lastFour = findViewById(R.id.four)
        Verify = findViewById(R.id.otP)
        lastFour!!.setText(mobFour)

//       Dialog Box popup appears when come to otp screen
//        val view =  View.inflate(this@Otp, R.layout.login_dailog ,null)
//        val builder = AlertDialog.Builder(this@Otp)
//        builder.setView(view)
//        val dialog = builder.create()
//
//        confirm_phone = view.findViewById<TextView>(R.id.confirm_phone)
//        linerEdit = view.findViewById<LinearLayout>(R.id.linerEdit)
//        confirm_phone.text = selectedcountrycode +" "+mobile
//        var ok: LinearLayout = view.findViewById<LinearLayout>(R.id.ok)
//        ok.setOnClickListener(View.OnClickListener {
//            SendOTP()
//            dialog.cancel()
//        })
//        linerEdit.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this@Otp, StartActivity::class.java)
//            intent.putExtra("result", mobile)
//            setResult(RESULT_OK,intent)
//            finish()
//
////            startActivity(intent)
//            dialog.cancel()
//        })
//
//        dialog.show()
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        dialog.setCancelable(true)
//        dialog.setCanceledOnTouchOutside(true)
        /*ends*/

       SendOTP()
        mTextViewCountDown = findViewById(R.id.textView)
        invalid = findViewById(R.id.invalid_Otp)
        mButtonReset = findViewById(R.id.resend)
        pinView = findViewById(R.id.pin_view)
        cont = findViewById(R.id.topp)
        arrow = findViewById(R.id.arrow)

        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )

        pinView!!.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.toString().length == 6) {
//            Toast.makeText(this@Otp, "Continue", Toast.LENGTH_SHORT).show();
                    Verify.background = ActivityCompat.getDrawable(this@Otp, R.drawable.green_box)
                    cont!!.setTextColor(Color.parseColor("#81C14B"));
//                    pinView!!.setLineColor(ResourcesCompat.getColor(getResources(), R.color.light_green, getTheme()));
                }
//                else if (charSequence.toString().length >= 1)
//                {
//                    pinView!!.setLineColor(ResourcesCompat.getColor(getResources(), R.color.light_green, getTheme()));
//                }
                else
                {
                    Verify.background = ActivityCompat.getDrawable(this@Otp, R.drawable.purple_box)
                    cont!!.setTextColor(Color.parseColor("#9597A1"));
//                    pinView!!.setLineColor(ResourcesCompat.getColor(getResources(), R.color.dark_grey, getTheme()));

                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        yet = findViewById(R.id.yet)

        if (mTimerRunning) {
            pauseTimer()
        } else {
            startTimer()
        }
        mButtonReset!!.setOnClickListener(View.OnClickListener
        {
            SendOTP()
            val params: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
            params.setMargins(120, 10, 10, 10)
            yet!!.setLayoutParams(params)

            resetTimer()
            startTimer()
            mTextViewCountDown!!.setVisibility(View.VISIBLE)
        })

        updateCountDownText()


//        pinView.setPinViewEventListener(object : PinViewEventListener {
//            override fun onDataEntered(pinview: Pinview?, fromUser: Boolean) {
//                Toast.makeText(this@MainActivity, pinview!!.value, Toast.LENGTH_SHORT).show()
//                strotp = pinView!!.getText().toString()
//                if (strotp == "" || strotp.isEmpty() || strotp.length < 6) {
//                    Toast.makeText(this@Otp, "Please enter otp", Toast.LENGTH_SHORT).show()
//
//                    Verify.background = ActivityCompat.getDrawable(this@Otp, R.drawable.purple_box)
//                } else{
//                    Verify.background = ActivityCompat.getDrawable(this@Otp, R.drawable.green_box)
//                    OTPVerified()
//                }
//            }
//        })

        Verify.setOnClickListener(View.OnClickListener {
            Conti()
        })

    startSmartUserConsent()

        arrow!!.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@Otp, StartActivity::class.java))
        })

        android_id  =   Secure.getString(this@Otp.getContentResolver(), Secure.ANDROID_ID)
        deviceInfo   =    android.os.Build.MODEL

getLocalIpAddress()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext!!)
       getLastKnownLocation()

        if (locationLAT.equals(null)||locationLONG == "null"||locationLAT == ""||locationLONG.isEmpty()||locationLAT.isBlank())
        {
            locationLAT = "28.6084"
            locationLONG = "77.2931"
        }

        Log.e("lat", locationLAT)
        Log.e("loc", locationLONG)

        cont?.setOnClickListener(View.OnClickListener {

            Conti()
//            Toast.makeText(this@Otp, locationLAT, Toast.LENGTH_SHORT).show()
//            Log.e("device_id" , android_id)
//            Log.e("deviceInfo" , deviceInfo)
        })
    }

    private fun Conti() {
        getLastKnownLocation()
        strotp = pinView!!.getText().toString()
        if (strotp == "" || strotp.isEmpty() || strotp.length < 6) {
//                Toast.makeText(this@Otp, "Please enter otp", Toast.LENGTH_SHORT).show()

            Verify.background = ActivityCompat.getDrawable(this@Otp, R.drawable.purple_box)
        } else{
            Verify.background = ActivityCompat.getDrawable(this@Otp, R.drawable.green_box)
            if (locationLAT.equals(null)||locationLONG == "null"||locationLAT == ""||locationLONG.isEmpty()||locationLAT.isBlank())
            {
                locationLAT = "28.6084"
                locationLONG = "77.2931"
            }
            OTPVerified()
        }
    }

    fun getLocalIpAddress(): String?
    {
        try
        {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements())
            {
                val intf = en.nextElement()
                val enumIpAddr = intf.inetAddresses
                while (enumIpAddr.hasMoreElements())
                {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address)
                    {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        }
        catch (ex: SocketException)
        {
            ex.printStackTrace()
        }
        return null
    }

    private fun SendOTP()
    {
        customDialog?.showProgressBar()
        Ion.with(this@Otp)
            .load("POST", ApiURL.SendOTP_URL)
            .setBodyParameter("mobile",str_mobile)
            .setBodyParameter("type", "messagmee")
            .asString()
            .setCallback(FutureCallback<String?> { _, result ->
                try {
                    val jsonObject = JSONObject(result)
                    val success = jsonObject.getString("success")
                    if (success == "true") {
                        customDialog?.hideProgressBar()
                        var jsonObject1: JSONObject = jsonObject.getJSONObject("data")
                        val successResult = jsonObject1.getString("successResult")
//                        Toast.makeText(this@Otp, successResult, Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        customDialog?.hideProgressBar()
//                        startActivity(Intent(this@SignIn, Personalize::class.java))

//                        Toast.makeText(this@Otp, success, Toast.LENGTH_SHORT).show()

                    }
                }
                catch (e1: Exception)
                {
                    e1.printStackTrace()
                    customDialog?.hideProgressBar()

//                    Toast.makeText(this@Otp, e1.toString(), Toast.LENGTH_SHORT).show()

                }
            })
    }


    private fun OTPVerified()
    {
    customDialog?.showProgressBar()
    Ion.with(this@Otp)
        .load("POST", ApiURL.OTPVerified_URL)
        .setBodyParameter("mobile",str_mobile)
        .setBodyParameter("otp" , strotp)
        .setBodyParameter("languagId" , strlanguage_id)
        .setBodyParameter("countryId" ,strcountry_id)
        .setBodyParameter("platform"  , "Android")
        .setBodyParameter("ipAddress" ,getLocalIpAddress())
        .setBodyParameter("deviceId" ,android_id)
        .setBodyParameter("deviceInfo", deviceInfo)
        .setBodyParameter("locationLAT", locationLAT)
        .setBodyParameter("locationLONG", locationLONG)
        .setBodyParameter("fcmToken" ,"21312")
        .asString()
        .setCallback(FutureCallback<String?> { _, result ->
            try {
                val jsonObject = JSONObject(result)
                val success = jsonObject.getString("success")
                if (success == "true")
                {
                    customDialog?.hideProgressBar()
                    var jsonObject1: JSONObject = jsonObject.getJSONObject("data")
                    var JSONObject2: JSONObject = jsonObject1.getJSONObject("successResult")
                    val id = JSONObject2.getString("id")
                    val mobile = JSONObject2.getString("mobile")
                    val email = JSONObject2.getString("email" )
                    val userName = JSONObject2.getString("userName")
                    val fullName = JSONObject2.getString("fullName")
                    val dob = JSONObject2.getString("dob")
                    val gender = JSONObject2.getString("gender")
                    val addressBy = JSONObject2.getString("addressBy")
                    val loginMode = JSONObject2.getString("loginMode")
                    val profileImage = JSONObject2.getString("profileImage")
                    val profileImageThumb = JSONObject2.getString("profileImageThumb")
                    val homeAddress = JSONObject2.getString("homeAddress")
                    val maritalStatusId = JSONObject2.getString("maritalStatusId")
                    val bio = JSONObject2.getString("bio")
                    val countryId = JSONObject2.getString("countryId")
                    val languagId = JSONObject2.getString("languagId")
                    val professionId = JSONObject2.getString("professionId")
                    val isPrivate = JSONObject2.getString("isPrivate")
                    val blockedByAdmin = JSONObject2.getString("blockedByAdmin")
                    val blockMessage = JSONObject2.getString("blockMessage")
                    val blockCode = JSONObject2.getString("blockCode")
                    val isActive = JSONObject2.getString("isActive")
                    val isDeleted = JSONObject2.getString("isDeleted")
                    val createdAt = JSONObject2.getString("createdAt")
                    val updatedAt = JSONObject2.getString("updatedAt")
                    val deletedAt = JSONObject2.getString("deletedAt")
                    val sequenceNo = JSONObject2.getString("sequenceNo")
                    val userdeactivationreason = JSONObject2.getString("userdeactivationreason")
                    val googleId = JSONObject2.getString("googleId")
                    val iCloudId = JSONObject2.getString("iCloudId")
                    val languge = JSONObject2.getString("languge")
                    val langugeId = JSONObject2.getString("langugeId")
                    val langugeCode = JSONObject2.getString("langugeCode")
                    token = JSONObject2.getString("token")
                     userStatus = JSONObject2.getString("userStatus")
                     profileSetupNameStatus = JSONObject2.getString("profileSetupNameStatus")
                    Log.e("akash" , profileSetupNameStatus.toString())
                    Log.e("ashish" , userStatus.toString())

//                    _______________Sending Token For Friends Sync to Invite.Class------------------->

                    val sharedPref:SharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
                    val editor1:SharedPreferences.Editor = sharedPref.edit()
                    editor1.putString("token", token)
                    editor1.apply()
//                    editor1.commit()

//                    -------------------____________---------/////_---------________--)))))))))

                    val editor:SharedPreferences.Editor =  sharedPreferences!!.edit()

                    editor.putString("profileSetupNameStatus",profileSetupNameStatus)
                    editor.putString("userStatus",userStatus)
                    editor.apply()
                    editor.commit()
                    val intent = Intent(this@Otp,Profile::class.java)
                    intent.putExtra("token",token)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    customDialog?.hideProgressBar()
//                        startActivity(Intent(this@SignIn, Personalize::class.java))

                    if(success.equals(true))
                    {
//                        Toast.makeText(this@Otp, "Successfully Logged In", Toast.LENGTH_SHORT).show()

                    }
                    else
                    {
//                        Toast.makeText(this@Otp, "Unsuuccessful sign in", Toast.LENGTH_SHORT).show()
                        invalid!!.visibility = View.VISIBLE

                    }

                }
            }
            catch (e1: Exception)
            {
                e1.printStackTrace()
                customDialog?.hideProgressBar()

//                Toast.makeText(this@Otp, e1.toString(), Toast.LENGTH_SHORT).show()
            }
        })

    }
    /*public void get(View view) {
        Intent intent=new Intent(otp.this, details.class);
        startActivity(intent);
        finish();
    }
*/
    private fun startSmartUserConsent() {

        val client = SmsRetriever.getClient(this)
        client.startSmsUserConsent(null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_USER_CONSENT){
            if (resultCode == RESULT_OK && data!=null){
            val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                getOtpFromMessage(message)
            }
        }

    }

    private fun getOtpFromMessage(message: String?)
    {
        val otpPattern =  Pattern.compile("(|^)\\d{6}")
        val matcher = otpPattern.matcher(message)
        if (matcher.find())
        {
            pinView!!.setText(matcher.group(0))
        }
    }

    private  fun registerBroadcastReceiver()
    {

        smsBroadcastReceiver = SmsBroadcastReceiver()
        smsBroadcastReceiver!!.smsBroadcastReceiverListener = object : SmsBroadcastReceiver.SmsBroadcastReceiverListener{
            override fun onSuccess(intent: Intent?) {
                startActivityForResult(intent,REQ_USER_CONSENT)
            }

            override fun onFailure()
            {

            }

        }

        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        registerReceiver(smsBroadcastReceiver,intentFilter)
    }

    override fun onStart()
    {
        super.onStart()
        registerBroadcastReceiver()

    }

    override fun onStop()
    {
        super.onStop()
        unregisterReceiver(smsBroadcastReceiver)
    }


    private fun startTimer()
    {
        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
        params.setMargins(120, 10, 10, 10)
        yet!!.setLayoutParams(params)
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000)
        {
            override fun onTick(millisUntilFinished: Long)
            {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }
            override fun onFinish()
            {
                mTimerRunning = false
                mButtonReset!!.visibility = View.VISIBLE

                val params: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
                params.setMargins(0, 0, 0, 0)
                yet!!.setLayoutParams(params)
                val params2: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
                params.setMargins(10, 15, 10, 10)
                mButtonReset!!.setLayoutParams(params2)
//                mTextViewCountDown!!.visibility = View.INVISIBLE
                mTextViewCountDown!!.visibility = View.VISIBLE
            }
        }.start()
        mTimerRunning = true
        mButtonReset!!.visibility = View.INVISIBLE
    }

    private fun pauseTimer()
    {
        mCountDownTimer!!.cancel()
        mTimerRunning = false
        mButtonReset!!.visibility = View.VISIBLE
    }

    private fun resetTimer()
    {
        mTimeLeftInMillis = /*com.inmortal.messenger.Otp.*/START_TIME_IN_MILLIS
        updateCountDownText()
        mButtonReset!!.visibility = View.INVISIBLE
    }

    private fun updateCountDownText()
    {
        val minutes = (mTimeLeftInMillis / 1000).toInt() / 60
        val seconds = (mTimeLeftInMillis / 1000).toInt() % 60
        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        mTextViewCountDown!!.text = timeLeftFormatted +" sec"
    }
    fun getLastKnownLocation() {
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers: List<String> = locationManager.allProviders
        var location: Location? = null
        for (i in providers.size - 1 downTo 0) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            location= locationManager.getLastKnownLocation(providers[i])

            if (location != null)
                break
//            else
//            {
//                Toast.makeText(this, "location is getting null" , Toast.LENGTH_SHORT ).show()
//            }
        }
        val gps = DoubleArray(2)
        if (location != null)
        {
            gps[0] = location.getLatitude()
            gps[1] = location.getLongitude()

            Log.e("gpsLat",gps[0].toString())
            locationLAT = gps[0].toString()
            locationLONG = gps[1].toString()
            Log.d("gpsLong",gps[1].toString())
        }
//        else
//        {
//            Toast.makeText(this, "Unable to fetch Coardinates" , Toast.LENGTH_SHORT).show()
//        }
    }
//    fun show(view: View) {
//        startActivity(Intent(this@Otp, Profile::class.java))
//        finish()
//    }

}

