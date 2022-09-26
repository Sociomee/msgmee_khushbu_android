package com.inmortal.messenger.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.BizPageAndMarketPlace.BizpageChat
import com.inmortal.messenger.Broadcast.NewBroadcast
import com.inmortal.messenger.R
import com.inmortal.messenger.Settings.SettingsMain
import com.inmortal.messenger.SocialChat.*
import com.inmortal.messenger.StartActivity
import com.inmortal.messenger.calls.CallsInfo
import com.inmortal.messenger.calls.MakeCalls

class Dashboard : AppCompatActivity()
{
    var more: ImageView? = null
    var clicked = false
    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var greenBar: RelativeLayout
    lateinit var message_view: LinearLayout
    lateinit var no_view: RelativeLayout
    lateinit var calling_after: LinearLayout
    lateinit var no_view_calls: RelativeLayout
    lateinit var raza: RelativeLayout
    lateinit var bizpage_chat: RelativeLayout
    lateinit var header: RelativeLayout
    lateinit var header_after: RelativeLayout
    lateinit var top_view: LinearLayout
    lateinit var hide_popup: LinearLayout
    lateinit var unknown: RelativeLayout
    lateinit var group: LinearLayout
    lateinit var close: ImageView
    lateinit var pop_options: ImageView
    lateinit var log_out: LinearLayout
    lateinit var new: LinearLayout
    lateinit var call: LinearLayout
    lateinit var settings: LinearLayout
    lateinit var block: LinearLayout
    lateinit var gp: LinearLayout
    lateinit var broadcast: LinearLayout
    lateinit var syncing: LinearLayout
    lateinit var social: TextView
    lateinit var calls: TextView
    lateinit var social_bar: View
    lateinit var calls_bar: View
    lateinit var message_people: View
    lateinit var makecalls: TextView
    lateinit var pinned_chat: ImageView
    lateinit var pinn: ImageView
    lateinit var unpinn: ImageView
    lateinit var archive: ImageView
    lateinit var to_calls_info: RelativeLayout
    private var sharedPreferences: SharedPreferences? = null
    private val sharedPrefFile = "kotlinsharedpreference"

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        more = findViewById(R.id.dots)
        greenBar = findViewById(R.id.greenBar)
        close = findViewById(R.id.close)
        social = findViewById(R.id.social)
        social_bar = findViewById(R.id.social_bar)
        calls = findViewById(R.id.calls)
        calls_bar = findViewById(R.id.calls_bar)
        message_view = findViewById(R.id.message)
        no_view = findViewById(R.id.no_view)
        no_view_calls = findViewById(R.id.no_view_calls)
        calling_after = findViewById(R.id.calling_after)
        raza = findViewById(R.id.raza)
        bizpage_chat = findViewById(R.id.bizpage_chat)
        header = findViewById(R.id.header)
        pop_options = findViewById(R.id.popup_options)
        header_after = findViewById(R.id.header_after)
        top_view = findViewById(R.id.top_view)
        hide_popup = findViewById(R.id.hide_popup)
        unknown = findViewById(R.id.unknown)
        group = findViewById(R.id.group)
        pinned_chat = findViewById(R.id.pinned_chat)
        pinn = findViewById(R.id.pin)
        unpinn = findViewById(R.id.unpin)
        archive = findViewById(R.id.archive)
        message_people = findViewById(R.id.message_people)
        makecalls = findViewById(R.id.makecalls)
        to_calls_info = findViewById(R.id.to_calls_info)
        social.setOnClickListener(View.OnClickListener {
            social_bar.visibility = View.VISIBLE
            calls_bar.visibility = View.INVISIBLE
//            calls.setTextColor("background: #4F4F4F")
            if (message_view.visibility == View.VISIBLE)
            {
                no_view.visibility = View.GONE
            }
            else
            {
                no_view.visibility = View.VISIBLE
            }
            no_view_calls.visibility = View.GONE
            calls.setTextColor(Color.BLACK)
            social.setTextColor(ContextCompat.getColor(this,R.color.light_green))
        })
        calls.setOnClickListener(View.OnClickListener
        {
            calls_bar.visibility = View.VISIBLE
            social_bar.visibility =View.INVISIBLE
            message_view.visibility =View.GONE
            no_view.visibility = View.GONE
            no_view_calls.visibility = View.VISIBLE
            social.setTextColor(Color.BLACK)
            calls.setTextColor(ContextCompat.getColor(this,R.color.light_green))
        })

        sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
       sharedPreferences!!.getString("profileSetupNameStatus","")

        more?.setOnClickListener(View.OnClickListener
        {
           bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View =
                LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_more, null)
            new = view.findViewById(R.id.neww)
            block = view.findViewById(R.id.block)
            settings = view.findViewById(R.id.settings)
            call = view.findViewById(R.id.call)
            new.setOnClickListener(View.OnClickListener {
                toContacts()
            })

            settings.setOnClickListener(View.OnClickListener {
                val intent = Intent(this@Dashboard, SettingsMain::class.java)
                startActivity(intent)
            })
            block.setOnClickListener(View.OnClickListener {
                val intent = Intent(this@Dashboard, Blocked::class.java)
                startActivity(intent)
            })
            call.setOnClickListener(View.OnClickListener {
                val intent = Intent(this@Dashboard, MakeCalls::class.java)
                startActivity(intent)
            })
            gp = view.findViewById(R.id.gp)
            gp.setOnClickListener(View.OnClickListener {
               toContacts()
            })
            syncing = view.findViewById(R.id.syncing)
            syncing.setOnClickListener(View.OnClickListener {
              bottomSheetDialog.dismiss()
                if(message_view.visibility == View.VISIBLE)
                {
                    greenBar.visibility = View.GONE
                    Toast.makeText(this@Dashboard , "Sociomee connections already synced" , Toast.LENGTH_SHORT).show()
                }
                else
                {
//                    greenBar.visibility = View.VISIBLE
                    val view:View = greenBar
                    view.visibility = View.VISIBLE
                    Handler().postDelayed({ view.visibility = View.GONE
                                          if (no_view.visibility == View.VISIBLE)
                                          {
                                              message_view.visibility= View.VISIBLE
                                              no_view.visibility= View.GONE
                                          }
                                        else if (no_view_calls.visibility == View.VISIBLE)
                                          {
                                            no_view.visibility=View.VISIBLE
                                              no_view.visibility= View.GONE
                                          }
//                                            if (clicked)
//                                            {
//                                                message_view.visibility= View.GONE
//                                                no_view.visibility= View.VISIBLE
//                                            }
//                                             else
//                                            {
//                                                message_view.visibility= View.VISIBLE
//                                                no_view.visibility= View.GONE
//                                            }
                                          }, 3000)
                }
            })
            broadcast = view.findViewById(R.id.broadcast)
            broadcast.setOnClickListener(View.OnClickListener {
                val intent = Intent(this@Dashboard, NewBroadcast::class.java)
                startActivity(intent)
            })
            log_out = view.findViewById(R.id.log_out)
            log_out.setOnClickListener(View.OnClickListener {
                val editor:SharedPreferences.Editor =  sharedPreferences!!.edit()
                editor.clear()
                editor.commit()
                val intent = Intent(this@Dashboard, StartActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this@Dashboard , " Logged Out" , Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss()
            })
           bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
           bottomSheetDialog.setContentView(view)
           bottomSheetDialog.show()

        })

        close.setOnClickListener(View.OnClickListener {

            clicked=true
            greenBar.visibility= View.GONE
//            no_view.visibility =View.GONE
            no_view_calls.visibility = View.GONE
//            message_view.visibility =View.VISIBLE
//            calls_bar.visibility = View.INVISIBLE
            social_bar.visibility = View.VISIBLE

            social.setTextColor(ContextCompat.getColor(this,R.color.light_green))
            calls.setTextColor(ContextCompat.getColor(this,R.color.black))
            if (calls_bar.visibility == View.VISIBLE)
            {
                message_view.visibility = View.GONE
                no_view.visibility = View.GONE
                no_view_calls.visibility = View.VISIBLE

                social.setTextColor(ContextCompat.getColor(this,R.color.black))
                calls.setTextColor(ContextCompat.getColor(this,R.color.light_green))
                social_bar.visibility = View.INVISIBLE
            }
        })

        pinn.setOnClickListener(View.OnClickListener {
           pinned_chat.visibility = View.VISIBLE
            if (pinned_chat.visibility == View.VISIBLE)
            {
                pinn.visibility = View.GONE
                unpinn.visibility = View.VISIBLE
            }
            else
            {
                pinn.visibility = View.VISIBLE
                unpinn.visibility = View.GONE
            }

        })
        archive.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Dashboard , ArchiveDashboard::class.java)
            startActivity(intent)
        })
        hide_popup.setOnClickListener(View.OnClickListener {
            header_after.visibility = View.GONE
            top_view.visibility = View.VISIBLE
            raza.setBackgroundColor(Color.TRANSPARENT)
        })
        pop_options.setOnClickListener(View.OnClickListener {
            bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View =
                LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_hold_dashboard, null)
            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()
        })
        raza.setOnClickListener(View.OnClickListener {
            raza.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                      top_view.visibility = View.GONE
                        header_after.visibility= View.VISIBLE
                        raza.setBackgroundColor(Color.parseColor("#E3F4EA"))
                    }
                    MotionEvent.ACTION_UP -> {
                        top_view.visibility = View.GONE
                        header_after.visibility= View.VISIBLE
                        raza.setBackgroundColor(Color.parseColor("#E3F4EA"))
                    }
                }
                true
            }
            val intent = Intent(this@Dashboard, Chat::class.java)
            startActivity(intent)
        })
        raza.setOnLongClickListener {
            raza.isSelected = true
            true
        }

        if (raza.isSelected)
        {
            header.visibility = View.GONE
        }
        else
        {
            header.visibility = View.VISIBLE
        }
        unknown.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Dashboard, UnknownChat::class.java)
            startActivity(intent)
        })
        group.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Dashboard, Group::class.java)
            startActivity(intent)
        })
        message_people.setOnClickListener(View.OnClickListener {
            toContacts()
        })
        makecalls.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Dashboard, MakeCalls::class.java)
            startActivity(intent)
        })


        val sharedPreferences:SharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
       var clicked : Boolean = sharedPreferences.getBoolean("clicked", false)
        Log.e("token", clicked.toString())
//
//        val profileName=intent.getStringExtra("key")
//        Log.e(profileName , "key")
        if (clicked)
        {
            no_view.visibility = View.GONE
            no_view_calls.visibility = View.GONE
            calling_after.visibility = View.VISIBLE
            calls.setTextColor(ContextCompat.getColor(this,R.color.light_green))
            calls_bar.visibility = View.VISIBLE
            social_bar.visibility = View.INVISIBLE
            social.setTextColor(ContextCompat.getColor(this,R.color.black))
            Toast.makeText(this@Dashboard  , "All Calls" , Toast.LENGTH_SHORT).show()
        }
//        else
//        {
//            no_view_calls.visibility = View.VISIBLE
//            calling_after.visibility = View.GONE
//            Toast.makeText(this@Dashboard  , "Hide Calls" , Toast.LENGTH_SHORT).show()
//        }

        to_calls_info.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Dashboard  ,  CallsInfo::class.java)
            startActivity(intent)
        })
        bizpage_chat.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Dashboard , BizpageChat::class.java)
            startActivity(intent)
        })
    }

    private fun toContacts()
    {
        ActivityCompat.requestPermissions(this@Dashboard, arrayOf(Manifest.permission.READ_CONTACTS),1)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
        {
            val intent = Intent(this@Dashboard, Invite::class.java)
            startActivity(intent)
            Toast.makeText(this@Dashboard , "Successully Synced Contacts" , Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(this@Dashboard , "Failed To Synced" , Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(this@Dashboard, arrayOf(Manifest.permission.READ_CONTACTS),1)
        }
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed()
    {
        if (doubleBackToExitPressedOnce)
        {
     finish()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }


}