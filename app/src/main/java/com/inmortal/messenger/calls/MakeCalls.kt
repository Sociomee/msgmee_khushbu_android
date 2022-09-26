package com.inmortal.messenger.calls

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import com.inmortal.messenger.R
import com.inmortal.messenger.SocialChat.Search
import com.inmortal.messenger.activity.Dashboard
import java.util.*

class MakeCalls : AppCompatActivity() {

    var clicked = false

    var status = 1

    lateinit var done:ImageView
    lateinit var close_search:TextView
    lateinit var indi_call:ImageView
    lateinit var indi_video_call:ImageView
    lateinit var search: SearchView
    lateinit var header: RelativeLayout
    lateinit var a: RelativeLayout
    lateinit var u: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_calls)

        done = findViewById(R.id.done)
        close_search = findViewById(R.id.close_search)
        search = findViewById(R.id.search)
        header = findViewById(R.id.header)
        indi_call = findViewById(R.id.indi_call)
        indi_video_call = findViewById(R.id.indi_video_call)
        a = findViewById(R.id.a)
        u = findViewById(R.id.u)

        // on below line we are adding on query
        // listener for our search view.
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchtext = newText!!.toLowerCase(Locale.getDefault())
                if (searchtext.isNotEmpty())
                {
                    if (searchtext.startsWith("a"))
                    {
                        a.visibility = View.VISIBLE
                        u.visibility = View.GONE
                    }
                    else if (searchtext.startsWith("u"))
                    {
                        a.visibility = View.GONE
                        u.visibility = View.VISIBLE
                    }
                    else
                    {
                        Toast.makeText(this@MakeCalls , "No foundings" , Toast.LENGTH_SHORT).show()
                        a.visibility = View.GONE
                        u.visibility = View.GONE
                    }
                }
                else
                {
                    Toast.makeText(this@MakeCalls , "No founds" , Toast.LENGTH_SHORT).show()
                    a.visibility = View.VISIBLE
                    u.visibility = View.VISIBLE
                }
                return false
            }
        })

        done.setOnClickListener(View.OnClickListener {
            search.visibility = View.VISIBLE
            header.visibility = View.GONE
        })
        close_search.setOnClickListener(View.OnClickListener {
            search.visibility = View.GONE
            header.visibility = View.VISIBLE
        })
        indi_call.setOnClickListener(View.OnClickListener {
            //change boolean value
            clicked=true

            val sharedPref: SharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
            val editor1: SharedPreferences.Editor = sharedPref.edit()
            editor1.putBoolean("clicked", true)
            editor1.apply()

            val intent = Intent(this@MakeCalls , SingleAudioCall::class.java)
            startActivity(intent)
        })
        indi_video_call.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MakeCalls , SingleVideoCall::class.java)
            startActivity(intent)
        })


    }

    fun back(view: View) {
        val intent = Intent(this@MakeCalls , Dashboard::class.java)
        startActivity(intent)
    }
}