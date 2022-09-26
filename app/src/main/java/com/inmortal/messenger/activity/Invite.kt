package com.inmortal.messenger.activity

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.Adapter.CustomAdapter
import com.inmortal.messenger.Adapter.FriendsSyncList
import com.inmortal.messenger.R
import com.inmortal.messenger.SocialChat.Chat
import com.inmortal.messenger.URL.ApiURL
import com.inmortal.messenger.URL.CustomProgressDialog
import com.inmortal.messenger.model.ContactModel
import com.inmortal.messenger.model.FriendsSyncModel
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject


class Invite :AppCompatActivity() , FriendsSyncList.ReturnView {

    var friendsSyncAdapter: FriendsSyncList? = null
    lateinit var dot: ImageView
    lateinit var all_connections: LinearLayout
    lateinit var sociomee_connections: LinearLayout
    lateinit var msgmee_connections: LinearLayout
    lateinit var phone_connections: LinearLayout
    lateinit var connections: TextView
    lateinit var message: TextView
    lateinit var filter: ImageView
    lateinit var bottomSheetDialog: BottomSheetDialog

    private val arrFriendsSyncModel: ArrayList<FriendsSyncModel> = ArrayList<FriendsSyncModel>()
    lateinit var recyclerView: RecyclerView

    lateinit var searchView: SearchView
   lateinit var listView: ListView
    private var customAdapter: CustomAdapter? = null
    private var contactModelArrayList: ArrayList<ContactModel>? = null
    private var filteredlist: ArrayList<ContactModel>?=null
    private val PERMISSIONS_REQUEST_READ_CONTACTS = 1
    private val customDialog: CustomProgressDialog? = null


    val arrayList = ArrayList<String>();
   val mobileNumbers:Array<String> = arrayOf<String>("7300865101","9991947908","9870982238","9793969692","7999718138","8920401689","8920703157","9810719551")
   var mobileNumbersArray=""
    var mob = ""
    var strNewtoken = ""
    var token = ""
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite)


        recyclerView = findViewById(R.id.recyclerViewF)
        dot = findViewById(R.id.dot)
        connections = findViewById(R.id.connections)
        filter = findViewById(R.id.filter)
        val sharedPreferences:SharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
        token = sharedPreferences.getString("token", "").toString()
        Log.e("token", token)
        strNewtoken = "Bearer " + token

//        requestContactPermission()
//        getContacts()

        enableContactPermission()

//        Log.e("array", mobileNumbers.contentToString())

        //Creating an empty arraylist in a
        arrayList.add("Ajay")//Adding objectrraylist
        arrayList.add("Vijay")
        arrayList.add("Prakash")
        arrayList.add("Rohan")
        arrayList.add("Vijay")

        mob = "8920401689"
        listView = findViewById<ListView>(R.id.listView)

//        image =  findViewById<ImageView>(R.id.invi)

        friendSync()


//       << -------------Search View Functionality Starts ----------------------------->>
        searchView = findViewById(R.id.search)
        searchView.clearFocus()
//        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // on query submit we are clearing the focus for our search view.
                searchView.clearFocus()
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean
            {
                // on changing the text in our search view we are calling
                // a filter method to filter our array list.
                filter(newText.toLowerCase())
                return false
            }
        })

//       << -------------Search View Functionality Ends ----------------------------->>



        dot.setOnClickListener(View.OnClickListener {
            mobileNumbersArray = mobileNumbers.toString().replace("[", "").replace("]", "");//remove brackets([) convert it to string
//            friendSync()
            bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_contacts, null)
                message = view.findViewById(R.id.ne)
                message.setOnClickListener(View.OnClickListener
                {
                    val intent = Intent(this@Invite, Chat::class.java)
                    startActivity(intent)
                    finish()
                })

                bottomSheetDialog.dismiss()
            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()

        })

        filter.setOnClickListener(View.OnClickListener {

            bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_filter, null)
            all_connections = view.findViewById(R.id.all_connections)
            all_connections.setOnClickListener(View.OnClickListener {
                connections.visibility = View.VISIBLE
                recyclerView.visibility = View.VISIBLE
//                all_connections.setBackgroundColor(Color.parseColor("#E3F4EA"))
                bottomSheetDialog.dismiss()
            })
            sociomee_connections = view.findViewById(R.id.sociomee_connections)
            sociomee_connections.setOnClickListener(View.OnClickListener {
                connections.visibility = View.VISIBLE
                recyclerView.visibility = View.VISIBLE
                bottomSheetDialog.dismiss()
            })
            msgmee_connections = view.findViewById(R.id.msgmee_connections)
            msgmee_connections.setOnClickListener(View.OnClickListener {
                connections.visibility = View.VISIBLE
                recyclerView.visibility = View.VISIBLE
                bottomSheetDialog.dismiss()
            })
            phone_connections = view.findViewById(R.id.phone_connections)
            phone_connections.setOnClickListener(View.OnClickListener {
                connections.visibility = View.GONE
                recyclerView.visibility = View.GONE
                bottomSheetDialog.dismiss()
            })
            bottomSheetDialog.dismiss()
            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()

        })

    }

    private fun enableContactPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
        {
            getContacts()
        }
        else
        {
            ActivityCompat.requestPermissions(this@Invite, arrayOf(Manifest.permission.READ_CONTACTS),1)
        }
    }

    private fun filter(text: String) {
        // in this method we are filtering our array list.
        // on below line we are creating a new filtered array list.
         filteredlist = arrayListOf<ContactModel>()
        // on below line we are running a loop for checking if the item is present in array list.
        for (item in contactModelArrayList!!) {
            if (item.name!!.toLowerCase().contains(text.toLowerCase())|| item.number!!.contains(text)) {
                // on below line we are adding item to our filtered array list.
                filteredlist!!.add(item)
            }
        }
        // on below line we are checking if the filtered list is empty or not.
        if (filteredlist!!.isEmpty()) {
            Toast.makeText(this, "No Contact Found", Toast.LENGTH_SHORT).show()
        } else {
            // passing this filtered list to our adapter with filter list method.
            customAdapter!!.filterList(filteredlist!!)
        }
    }

    private fun friendSync()
    {
            customDialog?.showProgressBar()
            Ion.with(this@Invite)
                .load("POST", ApiURL.FriendSync_URL)
                .setHeader("Authorization",strNewtoken)
                .setBodyParameter("mobile[]", mob)
                .setBodyParameter("showUserType", "all")
                .asString()
                .setCallback(FutureCallback<String?> { _, result ->
                    try {
                        val jsonObject = JSONObject(result)
                        val success = jsonObject.getString("success")
                        if (success == "true") {
                            customDialog?.hideProgressBar()
                            val jsonObject1: JSONObject = jsonObject.getJSONObject("data")
                            val arr: JSONArray
                            try
                            {
                             customDialog?.hideProgressBar()
                                arr = jsonObject1.getJSONArray("successResult")
                                for (i in 0 until arr.length()) {
                                    val jasonobject3 = arr.getJSONObject(i)
                                    arrFriendsSyncModel.add(
                                        FriendsSyncModel
                                            (
                                            jasonobject3.getString("userId"),
                                            jasonobject3.getString("mobile"),
                                            jasonobject3.getString("userName"),
                                            jasonobject3.getString("fullName"),
                                            jasonobject3.getString("profileImage"),
//                                            jasonobject3.getString("profileImageThumb"),
                                            jasonobject3.getString("isMessagmee")
                                        )
                                    )

                                    friendsSyncAdapter = FriendsSyncList(arrFriendsSyncModel,
                                        this@Invite,
                                        R.layout.friends_sync_layout,
                                        this,
                                        1
                                    )

                                    val layoutManager1 = LinearLayoutManager(
                                        this@Invite,
                                        LinearLayoutManager.VERTICAL,
                                        false
                                    )
                                    recyclerView.layoutManager = layoutManager1
                                    recyclerView.setHasFixedSize(true)
                                    recyclerView.adapter = friendsSyncAdapter

                                }

                            } catch (e: Exception) {
                                customDialog?.hideProgressBar()
                                e.printStackTrace()
//                            Toast.makeText(this@StartActivity, e.toString(), Toast.LENGTH_SHORT)
//                                .show()
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

                    }
                })
        }


    private fun getContacts()
    {
        listView = findViewById<ListView>(R.id.listView)
//       image =  findViewById<ImageView>(R.id.invi)

        contactModelArrayList = ArrayList()
        filteredlist = arrayListOf<ContactModel>()

        val phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        var lastPhoneName: String = ""
        var lastPhoneNumber : String = ""

        while (phones!!.moveToNext()) {
            val name =
                phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
          val phoneNumber =
                phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val image =
                phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))


            if (!name.equals(lastPhoneName, ignoreCase = true) && !phoneNumber.equals(lastPhoneNumber, ignoreCase = true))
            {
                lastPhoneName = name
                lastPhoneNumber = phoneNumber

                val contactModel = ContactModel()
            contactModel.setNames(name.replace("\\s+", ""))
            contactModel.setNumbers(phoneNumber.replace("\\D+", ""))
                val id = image.substring(0,1)
                contactModel.setIds(id)
                contactModelArrayList!!.add(contactModel)
                Log.d("name>>", "$name  $phoneNumber $id")

            }
        }
        filteredlist!!.addAll(contactModelArrayList!!)
        phones.close()
        customAdapter = CustomAdapter(this, filteredlist!!)
        listView.adapter = customAdapter

        val invite_all : TextView = findViewById(R.id.invite_all)
        invite_all.setOnClickListener(View.OnClickListener {
            val image: ImageView = listView.findViewById<ImageView>(R.id.invi)
            val imgin: ImageView = listView.findViewById<ImageView>(R.id.invite)
            if (imgin.visibility == View.VISIBLE)
            {
                imgin.visibility  = View.GONE
                image.visibility = View.VISIBLE
            }
            else
            {
                imgin.visibility  = View.VISIBLE
                image.visibility = View.GONE
            }
        })

        listView.onItemClickListener = OnItemClickListener { parent, view, position, id ->

            val selectedItem = parent.getItemAtPosition(position)
            val sms = "Join us on MsgMee with fast text and calling facility.Join us on MsgMee with fast text and calling facility. You can now manage your communication at your fingertips. Click to install." //The message you want to text to the phone
            val smsIntent = Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", filteredlist!![position].number, null))
            smsIntent.putExtra("sms_body", sms)
            startActivity(smsIntent)


            val image: ImageView = view.findViewById<ImageView>(R.id.invi)
            val imgin: ImageView = view.findViewById<ImageView>(R.id.invite)

            if (imgin.visibility == View.VISIBLE)
            {
                imgin.visibility  = View.GONE
                image.visibility = View.VISIBLE
            }
            else
            {
                imgin.visibility  = View.VISIBLE
                image.visibility = View.GONE
            }
        }

    }
    private fun requestContactPermission()
    {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_CONTACTS
                )
            )
            {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Read contacts access needed")
                builder.setPositiveButton(android.R.string.ok, null)
                builder.setMessage("Please enable access to contacts.")
                builder.setOnDismissListener(DialogInterface.OnDismissListener {
                    requestPermissions(
                        arrayOf(Manifest.permission.READ_CONTACTS), PERMISSIONS_REQUEST_READ_CONTACTS)
                })
                builder.show()
            }
            else
            {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_CONTACTS),
                    PERMISSIONS_REQUEST_READ_CONTACTS
                )
            }
        } else {
            getContacts()
        }
    }


    override fun getAdapterView(view: View?, objects: MutableList<Any?>?, position: Int, from: Int) {
        val liner = view!!.findViewById<View>(R.id.lineer) as LinearLayout
        val image = view.findViewById<View>(R.id.userImage) as ImageView
        val userName = view.findViewById<View>(R.id.userName) as TextView
        val msgmee = view.findViewById<View>(R.id.isMsgmee) as TextView
        val sociomee = view.findViewById<View>(R.id.isSociomee) as TextView
//        val CountryCode = view!!.findViewById<View>(R.id.CountryCode) as TextView

        liner.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Invite, Chat::class.java)
            startActivity(intent)
        })

        if (arrFriendsSyncModel[position].isMessagmee.toInt() == 1)
        {
            msgmee.visibility = View.VISIBLE
            sociomee.visibility = View.INVISIBLE
        }
        else
        {
            sociomee.visibility = View.VISIBLE
            msgmee.visibility = View.INVISIBLE
        }
        Log.e("temp" , arrFriendsSyncModel.toString())
        Picasso.get().load(arrFriendsSyncModel[position].profileImage).into(image)

        // sets the text to the textview from our itemHolder class
        userName.text = arrFriendsSyncModel[position].userName
//        CountryCode.text = "+"+ arrFriendsSyncModel[position].teleCoded
//        strcountry_id = arrFriendsSyncModel[position].id
//        SelectCountry = arrFriendsSyncModel[position].code
    }

    fun back(view: View)
    {
        val intent = Intent(this@Invite, Dashboard::class.java)
        startActivity(intent)
    }

}
