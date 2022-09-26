package com.inmortal.messenger.Settings

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.inmortal.messenger.Adapter.CustomAdapter
import com.inmortal.messenger.R
import com.inmortal.messenger.model.ContactModel

class Contact_Invite_Activity : AppCompatActivity() {

    lateinit var listView: ListView
    private var contactModelArrayList: ArrayList<ContactModel>? = null
    private var customAdapter: CustomAdapter? = null
    private var filteredlist: ArrayList<ContactModel>?=null
    val PERMISSIONS_REQUEST_READ_CONTACTS = 1
    lateinit var searchView: SearchView
    lateinit var img_back_ar: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_invite)


        img_back_ar = findViewById(R.id.img_back_ar)
        img_back_ar.setOnClickListener(View.OnClickListener {

            onBackPressed()
        })

//        enableContactPermission()
        requestContactPermission()

        searchView = findViewById(R.id.search)
        searchView.clearFocus()
        searchView.maxWidth = Int.MAX_VALUE
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
    }



    private fun getContacts()
    {
        listView = findViewById<ListView>(R.id.listView)
//       image =  findViewById<ImageView>(R.id.invi)

        contactModelArrayList = ArrayList()
        filteredlist = arrayListOf<ContactModel>()

        val phones = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
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



        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val selectedItem = parent.getItemAtPosition(position)
                val sms =
                    "Join us on MsgMee with fast text and calling facility.Join us on MsgMee with fast text and calling facility. You can now manage your communication at your fingertips. Click to install." //The message you want to text to the phone
                val smsIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.fromParts("sms", filteredlist!![position].number, null)
                )
                smsIntent.putExtra("sms_body", sms)
                startActivity(smsIntent)


                val image: ImageView = view.findViewById<ImageView>(R.id.invi)
                val imgin: ImageView = view.findViewById<ImageView>(R.id.invite)

                if (imgin.visibility == View.VISIBLE) {
                    imgin.visibility = View.GONE
                    image.visibility = View.VISIBLE
                } else {
                    imgin.visibility = View.VISIBLE
                    image.visibility = View.GONE
                }
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

    fun requestContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        android.Manifest.permission.READ_CONTACTS
                    )
                ) {
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
                getContacts()
            }
        } else {
            getContacts()
        }
    }
}