package com.inmortal.messenger.calls

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.os.postDelayed
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.Broadcast.NewBroadcast
import com.inmortal.messenger.R
import com.inmortal.messenger.Settings.SettingsMain
import com.inmortal.messenger.SocialChat.Blocked
import com.inmortal.messenger.StartActivity
import com.inmortal.messenger.activity.Dashboard
import java.util.*

class VoiceCallPanel : AppCompatActivity() {

    lateinit var back_call:ImageView
    lateinit var cardView:LinearLayout
    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var bottomSheetSearch: BottomSheetDialog
    lateinit var add: RelativeLayout
    lateinit var end_call: RelativeLayout
    lateinit var grp_call: RelativeLayout
    lateinit var narendra_view: RelativeLayout
    lateinit var whole_body: RelativeLayout

    var clicked = false

    lateinit var add_grp_members: RelativeLayout
    lateinit var incoming: LinearLayout
    lateinit var main_view_after_add: LinearLayout
    lateinit var search: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_call_panel)

        back_call = findViewById(R.id.back_call)
        end_call = findViewById(R.id.end_call)
        cardView = findViewById(R.id.cardview)
        incoming = findViewById(R.id.navigate)
        add_grp_members = findViewById(R.id.add_grp_members)
        main_view_after_add = findViewById(R.id.main_view_after_add)
        whole_body = findViewById(R.id.whole_body)
        back_call.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@VoiceCallPanel , MakeCalls::class.java)
            startActivity(intent)
        })
        cardView.setOnClickListener(View.OnClickListener {
            bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View =
                LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_voice_call, null)

            add = view.findViewById(R.id.add)
            add.setOnClickListener(View.OnClickListener {
                bottomSheetDialog.dismiss()
                bottomSheetSearch = BottomSheetDialog(this, R.style.BottomSheetTheme)
                val view1: View =
                    LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_voice_search, null)
                grp_call = view1.findViewById(R.id.grp_call)
                grp_call.setOnClickListener(View.OnClickListener {
                    //       Dialog Box popup appears when come to otp screen
                    val view_dialog =  View.inflate(this@VoiceCallPanel, R.layout.add_in_voice_call ,null)
                    val builder = AlertDialog.Builder(this@VoiceCallPanel)
                    builder.setView(view_dialog)
                    val dialog = builder.create()
                    dialog.show()
                    val cancel : TextView = view_dialog.findViewById(R.id.cancel_add)
                    cancel.setOnClickListener(View.OnClickListener {
                        dialog.dismiss()
                    })
                    val proceed_add : TextView = view_dialog.findViewById(R.id.proceed_add)
                    proceed_add.setOnClickListener(View.OnClickListener {
                        add_grp_members.visibility = View.VISIBLE
                        cardView.visibility = View.GONE
                        incoming.visibility = View.GONE
                        main_view_after_add.visibility = View.VISIBLE
                        dialog.dismiss()
                    })
                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    dialog.setCancelable(true)
                    dialog.setCanceledOnTouchOutside(true)
                    /*ends*/
                    bottomSheetSearch.dismiss()
                })
                bottomSheetSearch.requestWindowFeature(Window.FEATURE_NO_TITLE)
                bottomSheetSearch.setContentView(view1)
                bottomSheetSearch.show()
            })
            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()
        })
        add_grp_members.setOnClickListener(View.OnClickListener {
            bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View =
                LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_voice_call, null)

            add = view.findViewById(R.id.add)
            add.setOnClickListener(View.OnClickListener {
                bottomSheetDialog.dismiss()
                bottomSheetSearch = BottomSheetDialog(this, R.style.BottomSheetTheme)
                val view1: View =
                    LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_voice_search, null)
                grp_call = view1.findViewById(R.id.grp_call)
                narendra_view = view1.findViewById(R.id.narendra_view)
                search = view1.findViewById(R.id.search_to_add)
                search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {

                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchtext = newText!!.toLowerCase(Locale.getDefault())
                        if (searchtext.isNotEmpty())
                        {
                            if (searchtext.startsWith("n"))
                            {
                                narendra_view.visibility = View.VISIBLE
                                grp_call.visibility = View.GONE
                            }
                            else if (searchtext.startsWith("u"))
                            {
                                narendra_view.visibility = View.GONE
                                grp_call.visibility = View.VISIBLE
                            }
                            else
                            {
                                Toast.makeText(this@VoiceCallPanel , "No foundings" , Toast.LENGTH_SHORT).show()
                                narendra_view.visibility = View.GONE
                                grp_call.visibility = View.GONE
                            }
                        }
                        else
                        {
                            Toast.makeText(this@VoiceCallPanel , "No founds" , Toast.LENGTH_SHORT).show()
                            narendra_view.visibility = View.VISIBLE
                            grp_call.visibility = View.VISIBLE
                        }
                        return false
                    }
                })

                grp_call.setOnClickListener(View.OnClickListener {
                    //       Dialog Box popup appears when come to otp screen
                    val view_dialog =  View.inflate(this@VoiceCallPanel, R.layout.add_in_voice_call ,null)
                    val builder = AlertDialog.Builder(this@VoiceCallPanel)
                    builder.setView(view_dialog)
                    val dialog = builder.create()
                    dialog.show()
                    val cancel : TextView = view_dialog.findViewById(R.id.cancel_add)
                    cancel.setOnClickListener(View.OnClickListener {
                        dialog.dismiss()
                    })
                    val proceed_add : TextView = view_dialog.findViewById(R.id.proceed_add)
                    proceed_add.setOnClickListener(View.OnClickListener {
                        add_grp_members.visibility = View.VISIBLE
                        cardView.visibility = View.GONE
                        incoming.visibility = View.GONE
                        main_view_after_add.visibility = View.VISIBLE
                        dialog.dismiss()
                    })
                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    dialog.setCancelable(true)
                    dialog.setCanceledOnTouchOutside(true)
                    /*ends*/
                    bottomSheetSearch.dismiss()
                })
                bottomSheetSearch.requestWindowFeature(Window.FEATURE_NO_TITLE)
                bottomSheetSearch.setContentView(view1)
                bottomSheetSearch.show()
            })
            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()
        })

        Handler().postDelayed({
            val intent = Intent(this@VoiceCallPanel , IncomingVoiceCall::class.java)
            startActivity(intent)
        },5000)

        whole_body.setOnClickListener(View.OnClickListener {
            clicked = true
        })

        if (!clicked)
        {

        }
        else
        {
            Toast.makeText(this@VoiceCallPanel , "Stop" , Toast.LENGTH_SHORT).show()
        }

        end_call.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@VoiceCallPanel , MakeCalls::class.java)
            startActivity(intent)
        })
    }
}