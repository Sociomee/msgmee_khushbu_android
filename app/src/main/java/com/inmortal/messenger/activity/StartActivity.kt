package com.inmortal.messenger

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.Adapter.NewCountryList
import com.inmortal.messenger.Adapter.NewLanguageList
import com.inmortal.messenger.URL.ApiURL
import com.inmortal.messenger.URL.CustomProgressDialog
import com.inmortal.messenger.activity.*
import com.inmortal.messenger.model.CountryModel
import com.inmortal.messenger.model.LanguageModel
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*


private val customDialog: CustomProgressDialog? = null
var countryAdapter: NewCountryList? = null
var languageAdapter: NewLanguageList? = null
private val arrModelList: ArrayList<LanguageModel> = ArrayList<LanguageModel>()
private val arrCountryModel: ArrayList<CountryModel> = ArrayList<CountryModel>()
private lateinit var  tempArrayList: ArrayList<CountryModel>
var hashSet = HashSet<CountryModel>()

private val LangArrayList: ArrayList<LanguageModel> = ArrayList<LanguageModel>()
lateinit var recyclerView: RecyclerView
lateinit var recyclerViewL: RecyclerView



lateinit var bottomSheetDialog: BottomSheetDialog

var strModelList = ArrayList<String>()


class StartActivity : AppCompatActivity(), NewCountryList.ReturnView , NewLanguageList.ReturnView{
    lateinit var dialog: TextView
    lateinit var searchView: SearchView
    lateinit var searchL: SearchView
    lateinit var CountryTxt: TextView
    lateinit var trm: TextView
    lateinit var priv: TextView
    lateinit var coo: TextView
    lateinit var Country_box: LinearLayout
    lateinit var lang_picker: LinearLayout
    lateinit var ChqTxt: TextView
    lateinit var submit: LinearLayout
    lateinit var imageViewFlag: ImageView
    lateinit var modelEdit: Spinner
    lateinit var phone: EditText
    var strModelEdit = ""
    var strMobileNumber = ""
    var strMobile_number = ""
    var strcountry_id = ""
    var strlanguage_id = ""
    var selected_country_code = ""
    var isphone = false
    var language_id = ""
    var myCountry = ""
    var SelectCountry = ""
    private var sharedPreferences: SharedPreferences? = null
    private val sharedPrefFile = "kotlinsharedpreference"
    var gpsStatus: Boolean = false
    var networkStatus: Boolean = false
    lateinit var confirm_phone: TextView
    lateinit var linerEdit: LinearLayout

    private lateinit var internetLayout: RelativeLayout
    private lateinit var noInternetLayout: RelativeLayout
    private lateinit var tryAgainButton: Button

//    Get Country Code without sim
var countryCodeWithoutSim: String? = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
strcountry_id="ca2ca6f5-c2ca-486a-a576-79cd571d8e6b"
        strlanguage_id = "12c840f6-fddf-44d3-9680-8c6411ecaff7"
        SelectCountry = "IN"

        tempArrayList = arrayListOf<CountryModel>()

        hashSet.addAll(tempArrayList)
        tempArrayList.clear()
        tempArrayList.addAll(hashSet)
        Log.e("gfdjgfdxd", tempArrayList.toString())

        locationEnabled()
        if (gpsStatus && networkStatus)
        {
        //            Toast.makeText(this@StartActivity , "Location Services Is Enabled" , Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(this@StartActivity , "Please On GPS" , Toast.LENGTH_LONG).show()
            OpenLocationSettings()
        }

        sharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)

        val profileSetupNameStatusLogin= sharedPreferences!!.getString("profileSetupNameStatus","")
        val userStatusLogin = sharedPreferences!!.getString("userStatus " , "")

        if (profileSetupNameStatusLogin.equals("complete") && userStatusLogin.equals("existingUser"))
        {
            val intent = Intent(this@StartActivity, Dashboard::class.java)
            startActivity(intent)
            finish()
        }
        else
        {

        }
        LanguageApi()

        getUserCountry()
        Log.e("ssss" ,getUserCountry().toString())
        Log.d("tag" ,myCountry)
        Log.e("mY" ,strlanguage_id)

        noInternetLayout = findViewById(R.id.noInternetLayout)
        internetLayout = findViewById(R.id.internetLayout)
        tryAgainButton = findViewById(R.id.try_again_button)

//        drawLayout()

//        tryAgainButton.setOnClickListener {
//            drawLayout()
//        }

        dialog = findViewById(R.id.dialog)
        CountryTxt = findViewById(R.id.CountryTxt)
        Country_box = findViewById(R.id.Country_box)
        lang_picker = findViewById(R.id.lang_picker)
        ChqTxt = findViewById(R.id.chqTxt)
        submit = findViewById(R.id.submit)
        imageViewFlag = findViewById(R.id.imageViewFlag)
//        val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider, null)
        modelEdit = findViewById(R.id.modelEdit)
        phone = findViewById(R.id.mobile_no_edit)
        trm = findViewById(R.id.termTxt)
        priv = findViewById(R.id.priv)
        coo = findViewById(R.id.coo)
        selected_country_code="+91"

//
//        imageViewFlag.setImageDrawable(
//            ContextCompat.getDrawable(
//                applicationContext, // Context
//                R.drawable.flag_india // Drawable
//            )
//        )


//        Spinner

        modelEdit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, l: Long) {

                strModelEdit = arrModelList[position].name

                if (position > 0) {
                    strModelEdit = arrModelList[position - 1].name
                }
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        dialog.setOnClickListener(View.OnClickListener
        {
        Continue()
        })

        Country_box.setOnClickListener(View.OnClickListener {
            tempArrayList.clear()
            CountryApi()
            bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View =
                LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider, null)
            recyclerView = view.findViewById(R.id.recyclerView)
            searchView = view.findViewById(R.id.editTxt)
            val linearLayout1 = searchView.getChildAt(0) as LinearLayout
            val linearLayout2 = linearLayout1.getChildAt(2) as LinearLayout
            val linearLayout3 = linearLayout2.getChildAt(1) as LinearLayout
            val autoComplete = linearLayout3.getChildAt(0) as AutoCompleteTextView
            autoComplete.textSize = 13f
            searchView.clearFocus()
            searchView.maxWidth = Int.MAX_VALUE
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    tempArrayList.clear()
                    return true
                }
                override fun onQueryTextChange(newText: String): Boolean {
                    tempArrayList.clear()
                    val searchtext = newText!!.toLowerCase(Locale.getDefault())
                    if (searchtext.isNotEmpty())
                    {
                        tempArrayList.clear()
                        arrCountryModel.forEach {
                            if (it.name.toLowerCase(Locale.getDefault()).contains(searchtext)){
                                tempArrayList.clear()
                                tempArrayList.add(it)
                            }
                        }
//                        arrCountryModel.forEach
//                        {
//                            if (it.name.toLowerCase(Locale.getDefault()).contains(searchtext))
//                            {
//                                tempArrayList.add(it)
//                            }
//                            }
                        recyclerView!!.adapter?.notifyDataSetChanged()
                    }
                    else
                    {
                    tempArrayList.clear()
                        tempArrayList.addAll(arrCountryModel)
                        recyclerView.adapter?.notifyDataSetChanged()
                    }
//                    filterlist(newText)
//                    val searchtext = newText!!.toLowerCase(Locale.getDefault())
                    return true
                }
            })

            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()

        })

//        tempArrayList = arrayListOf(CountryModel())
//        if (getUserCountry() == "in")
//        {
//            imageViewFlag.setImageDrawable(
//                ContextCompat.getDrawable(
//                    applicationContext, // Context
//                    R.drawable.flag_india // Drawable
//                )
//            )
//            selected_country_code="+91"
//            strcountry_id="ca2ca6f5-c2ca-486a-a576-79cd571d8e6b"
//
//        }
 if (myCountry == "IN"|| countryCodeWithoutSim == "IN")
        {
            imageViewFlag.setImageDrawable(ContextCompat.getDrawable(applicationContext, // Context
                    R.drawable.flag_india // Drawable
                )
            )
                    CountryTxt.text =selected_country_code

            selected_country_code="+91"
            strcountry_id="ca2ca6f5-c2ca-486a-a576-79cd571d8e6b"
            strlanguage_id = "12c840f6-fddf-44d3-9680-8c6411ecaff7"
            SelectCountry = "IN"
        }
        else
             {
     imageViewFlag.setImageDrawable(ContextCompat.getDrawable(applicationContext, // Context
         R.drawable.flag_india // Drawable
     )
     )
 }

        lang_picker.setOnClickListener(View.OnClickListener {
            LangArrayList.clear()
            LanguageLatestApi()
            bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_lang, null)
//            val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from<View>(view)
//            behavior.peekHeight = 150
            recyclerViewL = view.findViewById(R.id.recyclerViewL)
            searchL = view.findViewById(R.id.editTxt1)
            searchL.clearFocus()
            searchL.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {

                    return false
                }
                override fun onQueryTextChange(newText: String): Boolean {
                    LangArrayList.clear()
                    val searchtext = newText.toLowerCase(Locale.getDefault())
                    if (searchtext.isNotEmpty())
                    {
                        arrModelList.forEach {
                            if (it.name.toLowerCase(Locale.getDefault()).contains(searchtext)){
                                LangArrayList.clear()
                                LangArrayList.add(it)
                            }
                        }
//                        arrModelList.forEach {
//                            if (it.name.toLowerCase(Locale.getDefault()).contains(searchtext)){
//                                LangArrayList.add(it)
//                            } }

                        recyclerViewL.adapter!!.notifyDataSetChanged()
                    }
                    else
                    {
                        LangArrayList.clear()
                        LangArrayList.addAll(arrModelList)
                        recyclerViewL.adapter?.notifyDataSetChanged()
                    }
//                    filterlist(newText)
//                    val searchtext = newText!!.toLowerCase(Locale.getDefault())
                    return false
                }
            })
            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()
        })
        phone.addTextChangedListener(object : TextWatcher
        {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                strMobileNumber = s.toString()

                when
                {
                    strMobileNumber.length == 10 -> {
                        submit.background = ActivityCompat.getDrawable(this@StartActivity, R.drawable.green_box)
                        dialog.setTextColor(AppCompatResources.getColorStateList(this@StartActivity, R.color.light_green))
                    }
                    strMobileNumber.length < 10 -> {
                        submit.background = ActivityCompat.getDrawable(this@StartActivity, R.drawable.purple_box)
                        dialog.setTextColor(AppCompatResources.getColorStateList(this@StartActivity, R.color.light_grey))
                    }
                    strMobileNumber.isValidPhoneNumber() -> {
                        submit.background = ActivityCompat.getDrawable(this@StartActivity, R.drawable.purple_box)
                        dialog.setTextColor(AppCompatResources.getColorStateList(this@StartActivity, R.color.light_grey))
                    }
                    else -> {
                        submit.background = ActivityCompat.getDrawable(this@StartActivity, R.drawable.purple_box)
                        dialog.setTextColor(AppCompatResources.getColorStateList(this@StartActivity, R.color.light_grey))

                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {

            }
        })


        submit.setOnClickListener(View.OnClickListener {
            Continue()


//            if (phone!!.text.toString().isEmpty())
//            {
//                phone!!.error = resources.getString(R.string.mob_error)
//                phone!!.requestFocus()
//                isphone = false
//            }
//            else if(phone!!.text.toString().isBlank())
//            {
//                phone!!.error = resources.getString(R.string.mob_empty)
//                phone!!.requestFocus()
//                isphone = false
//            }
//            else if (phone!!.text.toString().length < 10 )
//            {
//                val snackBar = Snackbar.make(it, "Invalid Phone number.", Snackbar.LENGTH_LONG).setAction("Action", null)
//                snackBar.setActionTextColor(Color.WHITE)
//                val snackBarView = snackBar.view
//                snackBarView.setBackgroundColor(Color.BLACK)
//                val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
//                textView.setTextColor(Color.WHITE)
//                snackBar.show()
//            }
//            else if (!phone!!.text.toString().isValidPhoneNumber())
//            {
//                val snackBar = Snackbar.make(it, "Wrong Phone number.", Snackbar.LENGTH_LONG).setAction("Action", null)
//                snackBar.setActionTextColor(Color.WHITE)
//                val snackBarView = snackBar.view
//                snackBarView.setBackgroundColor(Color.BLACK)
//                val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
//                textView.setTextColor(Color.WHITE)
//                snackBar.show()
//            }
//            else
//            {
//                isphone = true
//            }
//
//            if (isphone == true)
//            {
//                val mobile = phone!!.getText().toString()
//                val i = Intent(this@StartActivity, Otp::class.java)
//                i.putExtra("message_key", mobile)
//                startActivity(i)
//                finish()
//            }
        })
//        CountryTxt.setOnClickListener { View.OnClickListener {
//            bottomSheetDialog = BottomSheetDialog(applicationContext, R.style.BottomSheetTheme)
//           val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider, null)
//            recyclerView =view.findViewById(R.id.recyclerView)
//
////            bottomSheetDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            bottomSheetDialog.setContentView(view)
//            bottomSheetDialog.show()
//        } }
        if (ActivityCompat.checkSelfPermission(this@StartActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        }
        else
        {
            ActivityCompat.requestPermissions(this@StartActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 44)
        }

       trm.setOnClickListener(View.OnClickListener {
           val intent = Intent(this@StartActivity, Terms::class.java)
           startActivity(intent)

       })

        priv.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@StartActivity, com.inmortal.messenger.activity.Privacy::class.java)
            startActivity(intent)

        })

        coo.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@StartActivity, Cookie::class.java)
            startActivity(intent)
        })
//       (Func Method)   Fetch Country Code using Without Sim
        getAddress(20.5937, 78.9629)
        Log.d("tagg", countryCodeWithoutSim.toString())
    }
    //    (Func Call)    Fetch Country Code using Without Sim
    private fun getAddress(latitude: Double, longitude: Double): String? {
        val result = java.lang.StringBuilder()
        try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses.size > 0) {
                val address = addresses[0]
                //                result.append(address.getLocality()).append("\n");
                countryCodeWithoutSim = String(result.append(address.countryName.uppercase(Locale.getDefault()).substring(0, 2)))
            }
        } catch (e: IOException) {
            Log.e("tag", e.message!!)
        }
        return result.toString()
    }


private fun Continue()
{
    when {
        strMobileNumber.isBlank() -> {
//                    phone.error = "Mobile Number is mandatory."
        }
        strMobileNumber.length < 10 -> {
//                    phone.error = "Wrong Phone number."
        }
        !strMobileNumber.isValidPhoneNumber() -> {
//                    phone.error = "Invalid Phone number."
        }
        (myCountry != SelectCountry && countryCodeWithoutSim  != SelectCountry)->
        {
            Toast.makeText(this@StartActivity , "Please Choose Your Country  code/out Sim Card", Toast.LENGTH_SHORT).show()
        }
        strlanguage_id.equals(null)||strlanguage_id == "" ||strlanguage_id.isBlank()||strlanguage_id.isEmpty()-> {
            Toast.makeText(this@StartActivity , "Please Choose Language", Toast.LENGTH_SHORT).show()
        }
        (myCountry.equals(null)||countryCodeWithoutSim.equals(null) )-> {
            Toast.makeText(this@StartActivity , "Please Check Networks", Toast.LENGTH_SHORT).show()
        }
        (!isNetworkAvailable()) ->
        {
            Toast.makeText(this@StartActivity , "Please Check Internet Connectivity", Toast.LENGTH_SHORT).show()

        }
        else ->
        {
            strMobile_number=selected_country_code+" "+strMobileNumber;

            //       Dialog Box popup appears when come to otp screen
            val view =  View.inflate(this@StartActivity, R.layout.login_dailog ,null)
            val builder = AlertDialog.Builder(this@StartActivity)
            builder.setView(view)
            val dialog = builder.create()

            confirm_phone = view.findViewById<TextView>(R.id.confirm_phone)
            linerEdit = view.findViewById<LinearLayout>(R.id.linerEdit)
            confirm_phone.text = selected_country_code +" "+strMobileNumber
            var ok: LinearLayout = view.findViewById<LinearLayout>(R.id.ok)
            ok.setOnClickListener(View.OnClickListener {
                val intent = Intent(this@StartActivity, Otp::class.java)
                intent.putExtra("selected_country_code", selected_country_code)
                intent.putExtra("mobile_no", strMobileNumber)
                intent.putExtra("strMobile_number", strMobile_number)
                intent.putExtra("language_idd",strlanguage_id)
                intent.putExtra("country_idd",strcountry_id)
                Log.e("ln",strlanguage_id)
                Log.e("sjhgchk", strcountry_id.toString())
//            startActivityForResult(intent , 1)
                startActivity(intent)
                finish()
                dialog.cancel()
            })
            linerEdit.setOnClickListener(View.OnClickListener {
//                val intent = Intent(this@StartActivity, StartActivity::class.java)
////                intent.putExtra("result", mobile)
//                setResult(RESULT_OK,intent)
//                finish()
//            startActivity(intent)
                dialog.cancel()
            })
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            /*ends*/
        }
    }

}
    private fun OpenLocationSettings()
    {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(R.string.dialogTitle)
        //set message for alert dialog
        builder.setMessage(R.string.dialogMessage)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }

        //performing cancel action
//        builder.setNeutralButton("Cancel"){dialogInterface , which ->

//        }
//        //performing negative action
//        builder.setNegativeButton("No"){dialogInterface, which ->
//        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

    private fun locationEnabled() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        networkStatus = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
        if (resultCode == 1) {
            if (resultCode == RESULT_OK) {
                var result = data?.getIntExtra("result", 0);
                if (result != null) {
                    phone.setText(result)
                }
            }
    }}

    /**
     * Get ISO 3166-1 alpha-2 country code for this device (or null if not available)
     * @param context Context reference to get the TelephonyManager instance from
     * @return country code or null
     */
    fun getUserCountry(): String? {
        try {
            val tm = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
            val simCountry = tm.simCountryIso
            myCountry = simCountry.toUpperCase()
            if (simCountry != null && simCountry.length == 2) { // SIM country code is available
                return simCountry.lowercase(Locale.US)
            } else if (tm.phoneType != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                val networkCountry = tm.networkCountryIso
                if (networkCountry != null && networkCountry.length == 2) { // network country code is available
                    return networkCountry.lowercase(Locale.US)
                }
            }
        } catch (e: Exception) {
        }
        return null
    }
    private fun LanguageApi()
    {
        customDialog?.showProgressBar()

        Ion.with(this@StartActivity)
            .load("POST", ApiURL.Language_URL)
            .asString()
            .setCallback(FutureCallback<String?> { _, result ->
                try
                {
                    val jsonObject = JSONObject(result)
                    val success = jsonObject.getString("success")
                    if (success == "true")
                    {
                        arrModelList.clear()
                        customDialog?.hideProgressBar()
                        var jsonObject1: JSONObject = jsonObject.getJSONObject("data")
                        var jsonObject2: JSONObject = jsonObject1.getJSONObject("successResult")
                        var arr: JSONArray
                        try {
                            customDialog?.hideProgressBar()
                            arr = jsonObject2.getJSONArray("rows")
                            for (i in 0 until arr.length())
                            {
                                val jasonobject3 = arr.getJSONObject(i)
//                                language_id = jasonobject3.getString("id")
//                                Log.e("language_id",language_id)
                                arrModelList.add(
                                    LanguageModel(jasonobject3.getString("id"),
                                        jasonobject3.getString("name"),
                                        jasonobject3.getString("iconURL"))
                                )
                                strModelList.clear()
//                                strModelList.add("Select Model")
                                for (k in arrModelList.indices) {
                                    strModelList.add(arrModelList[k].name)
                                    modelEdit.setSelection(k)
                                }
                                val adapter = ArrayAdapter<String>(this@StartActivity, R.layout.spinner_item, R.id.textview, strModelList)
                                modelEdit.adapter = adapter
                            }

                        }

                        catch (e: Exception)

                        {
                            customDialog?.hideProgressBar()
                            e.printStackTrace()
//                            Toast.makeText(this@StartActivity, e.toString(), Toast.LENGTH_SHORT)
//                                .show()
                        }
                    }

                    else
                    {
                        customDialog?.hideProgressBar()
//                        startActivity(Intent(this@SignIn, Personalize::class.java))
//                        Toast.makeText(this@StartActivity, success, Toast.LENGTH_SHORT).show()

                    }
                }
                catch (e1: Exception)
                {
                    e1.printStackTrace()
                    customDialog?.hideProgressBar()

//                    Toast.makeText(this@StartActivity, e1.toString(), Toast.LENGTH_SHORT).show()

                }
            })
    }

private fun LanguageLatestApi()
    {
        customDialog?.showProgressBar()
        Ion.with(this@StartActivity)
            .load("POST", ApiURL.Language_URL)
            .asString()
            .setCallback(FutureCallback<String?> { _, result ->
                try {
                    val jsonObject = JSONObject(result)
                    val success = jsonObject.getString("success")
                    if (success == "true") {
                        arrModelList.clear()
                        customDialog?.hideProgressBar()
                        var jsonObject1: JSONObject = jsonObject.getJSONObject("data")
                        var jsonObject2: JSONObject = jsonObject1.getJSONObject("successResult")
                        var arr: JSONArray
                        try {
                            customDialog?.hideProgressBar()
                            arr = jsonObject2.getJSONArray("rows")
                            for (i in 0 until arr.length())
                            {
                                val jasonobject3 = arr.getJSONObject(i)
                                arrModelList.add(LanguageModel(
                                    jasonobject3.getString("id"),
                                    jasonobject3.getString("name"),
                                    jasonobject3.getString("iconURL")))
//                                Have Created another Array List -> temp Array List for filteration for search and store Country Array list to it delete it when no filteration use

//                                LangArrayList.addAll(arrModelList)
                                languageAdapter = NewLanguageList(
                                    /*arrModelList*/ LangArrayList,
                                    this@StartActivity,
                                    R.layout.spinner_lang_item,
                                    this,
                                    1
                                )
                                val layoutManager1 = LinearLayoutManager(this@StartActivity, LinearLayoutManager.VERTICAL, false)
                                recyclerViewL.layoutManager = layoutManager1
                                recyclerViewL.setHasFixedSize(true)
                                recyclerViewL.adapter = languageAdapter
//                                strModelList.clear()
                            }
                            LangArrayList.addAll(arrModelList)

                        }

                        catch (e: Exception)

                        {
                            customDialog?.hideProgressBar()
                            e.printStackTrace()
//                            Toast.makeText(this@StartActivity, e.toString(), Toast.LENGTH_SHORT)
//                                .show()
                        }


                    }
                    else
                    {
                        customDialog?.hideProgressBar()
//                        startActivity(Intent(this@SignIn, Personalize::class.java))
//                        Toast.makeText(this@StartActivity, success, Toast.LENGTH_SHORT).show()

                    }
                }
                catch (e1: Exception)
                {
                    e1.printStackTrace()
                    customDialog?.hideProgressBar()

//                    Toast.makeText(this@StartActivity, e1.toString(), Toast.LENGTH_SHORT).show()

                }
            })
    }


    private fun CountryApi()
    {
        customDialog?.showProgressBar()

        Ion.with(this@StartActivity)
            .load("POST", ApiURL.Country_URL)
            .asString()
            .setCallback(FutureCallback<String?> { _, result ->
                try
                {
                    val jsonObject = JSONObject(result)
                    val success = jsonObject.getString("success")
                    if (success == "true") {
                        customDialog?.hideProgressBar()
                        var jsonObject1: JSONObject = jsonObject.getJSONObject("data")
                        var jsonObject2: JSONObject = jsonObject1.getJSONObject("successResult")
                        var arr: JSONArray
                        try
                        {
                            customDialog?.hideProgressBar()
                            arr = jsonObject2.getJSONArray("rows")
                            for (i in 0 until arr.length()) {
                                val jasonobject3 = arr.getJSONObject(i)
//                                country_id = jasonobject3.getString("id")
//                                Log.e("country_id",country_id)
                                arrCountryModel.add(
                                    CountryModel
                                        (
                                        jasonobject3.getString("id"),
                                        jasonobject3.getString("name"),
                                        jasonobject3.getString("code"),
                                        jasonobject3.getString("flagURL"),
                                        jasonobject3.getString("teleCode"),
                                        jasonobject3.getString("isDefault")
                                    )
                                )

//          Have Created another Array List -> temp Array List for filteration for search and store Country Array list to it delete it when no filteration use

//                                -------------------
                                countryAdapter = NewCountryList(
                      /*Revert it to arrCoModel from tempArrList*/              /*arrCountryModel*/ tempArrayList,
                                    this@StartActivity,
                                    R.layout.country_layout,
                                    this,
                                    1
                                )


//                                countryAdapter = Country_Adapter(arrCountryModel)
//                                countryAdapter = NewCountryList(arrCountryModel, this@StartActivity, R.layout.country_layout, this, 1)

//                                vehicleListAdapter =VehicleListAdapter(arrVehicleType, this@Fleet_add_vehicle,1)
                                val layoutManager1 = LinearLayoutManager(
                                    this@StartActivity,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                                recyclerView.layoutManager = layoutManager1
                                recyclerView.setHasFixedSize(true)
                                recyclerView.adapter = countryAdapter

                            }
                            tempArrayList.addAll(arrCountryModel)

                        } catch (e: Exception) {
                            customDialog?.hideProgressBar()
                            e.printStackTrace()
//                            Toast.makeText(this@StartActivity, e.toString(), Toast.LENGTH_SHORT)
//                                .show()
                        }


                    } else {
                        customDialog?.hideProgressBar()
//                        startActivity(Intent(this@SignIn, Personalize::class.java))

//                        Toast.makeText(this@StartActivity, success, Toast.LENGTH_SHORT).show()

                    }
                } catch (e1: Exception) {
                    e1.printStackTrace()
                    customDialog?.hideProgressBar()
//                    Toast.makeText(this@StartActivity, e1.toString(), Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun getAdapterView(view: View?, objects: MutableList<Any?>?, position: Int, from: Int)
    {
        val liner = view!!.findViewById<View>(R.id.liner) as LinearLayout
        val flagImage = view.findViewById<View>(R.id.flagImage) as ImageView
        val CountryTxt1 = view.findViewById<View>(R.id.CountryTxt1) as TextView
        val CountryCode = view.findViewById<View>(R.id.CountryCode) as TextView

        Log.e("temp" , tempArrayList.toString())
        Picasso.get().load(tempArrayList[position].flagURL).into(flagImage)

        // sets the text to the textview from our itemHolder class
        CountryTxt1.text = tempArrayList[position].name
        CountryCode.text = "+"+ tempArrayList[position].teleCoded
        strcountry_id = tempArrayList[position].id
        SelectCountry = tempArrayList[position].code

        liner.setOnClickListener(View.OnClickListener {
            selected_country_code="+"+ tempArrayList[position].teleCoded
            strcountry_id=tempArrayList[position].id

            SelectCountry=tempArrayList[position].code

            CountryTxt.text = selected_country_code
            Picasso.get().load(tempArrayList[position].flagURL).into(imageViewFlag)

            bottomSheetDialog.dismiss()

        })
    }

    override fun getLangAdapterView(view: View?, objects: MutableList<Any?>?, position: Int, from: Int)
    {
        val linerr = view!!.findViewById<View>(R.id.linerr) as LinearLayout
        val lang = view.findViewById<View>(R.id.lang) as TextView

        lang.text = LangArrayList[position].name
        strlanguage_id = LangArrayList[position].id

        linerr.setOnClickListener(View.OnClickListener {
            ChqTxt.text = LangArrayList[position].name
            strlanguage_id= LangArrayList[position].id
            bottomSheetDialog.dismiss()
        })

    }

    fun CharSequence?.isValidPhoneNumber(): Boolean {
        return !isNullOrEmpty() && Patterns.PHONE.matcher(this).matches()
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

        return (capabilities != null && capabilities.hasCapability(NET_CAPABILITY_INTERNET))

    }

    private fun drawLayout() {
        if (isNetworkAvailable()) {
            Toast.makeText(applicationContext,"You are connected to Internet",Toast.LENGTH_LONG).show()
            internetLayout.visibility = GONE
            noInternetLayout.visibility = GONE
        } else {
            noInternetLayout.visibility = VISIBLE
            internetLayout.visibility = GONE
        }
    }
}