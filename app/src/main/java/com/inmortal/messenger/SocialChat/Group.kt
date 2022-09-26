package com.inmortal.messenger.SocialChat

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.inmortal.messenger.R
import com.inmortal.messenger.activity.Dashboard
import com.inmortal.messenger.activity.Invite

class Group : AppCompatActivity() {

    lateinit var header : RelativeLayout
    lateinit var header_after_hold : RelativeLayout
    lateinit var hide_popup: LinearLayout
    lateinit var info_after_hold: ImageView
    lateinit var normal_keyboard : RelativeLayout
    lateinit var mic : RelativeLayout
    lateinit var voice_message : LinearLayout
    lateinit var reply_keyboard : LinearLayout
    lateinit var reply : LinearLayout

    //  Fab Comps +=??>>
// Use the FloatingActionButton for all the add person
// and add alarm
    lateinit var mAddAlarmFab: FloatingActionButton
    lateinit var mAddPersonFab: FloatingActionButton
    lateinit var media_fab: FloatingActionButton
    lateinit var doc_fab: FloatingActionButton
    lateinit  var Parent_Fab: ExtendedFloatingActionButton
    lateinit var addAlarmActionText: RelativeLayout
    lateinit var addPersonActionText:  RelativeLayout
    lateinit var media_action_txt:  RelativeLayout
    lateinit var doc_action_txt:  RelativeLayout


    var isAllFabsVisible: Boolean? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        header = findViewById(R.id.header)
        header_after_hold = findViewById(R.id.header_after_hold)
        info_after_hold = findViewById(R.id.info_after_hold)
        hide_popup = findViewById(R.id.hide_popup)
        mic = findViewById(R.id.mic)
        voice_message = findViewById(R.id.voice_message)
        header.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Group, GroupInfo::class.java)
            startActivity(intent)
        })

reply = findViewById(R.id.reply)
        normal_keyboard = findViewById(R.id.normal_message)
        reply_keyboard = findViewById(R.id.reply_keeyboard)

        hide_popup.setOnClickListener(View.OnClickListener {
            header_after_hold.visibility = View.GONE
            header.visibility = View.VISIBLE
        })
        info_after_hold.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Group , MessageInfo::class.java)
            startActivity(intent)
        })
        reply.setOnClickListener(View.OnClickListener {
            reply.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        normal_keyboard.visibility = View.GONE
                        reply_keyboard.visibility = View.VISIBLE
                        header.visibility = View.GONE
                        header_after_hold.visibility = View.VISIBLE
                    }
                    MotionEvent.ACTION_UP -> {
                        normal_keyboard.visibility = View.VISIBLE
                        reply_keyboard.visibility = View.GONE
                        header.visibility = View.GONE
                        header_after_hold.visibility = View.VISIBLE
                    }
                }
                true
            }
        })
mic.setOnClickListener(View.OnClickListener {
    mic.setOnTouchListener { _, motionEvent ->
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                voice_message.visibility = View.VISIBLE
                mic.setBackgroundColor(Color.RED)
            }
            MotionEvent.ACTION_UP -> {
                voice_message.visibility = View.GONE
                mic.setBackgroundColor(Color.TRANSPARENT)
            }
        }
        true
    }
})
        // _________________--------------------_____Fab_____________________________________

        Parent_Fab = findViewById(R.id.add_fab)
        mAddAlarmFab = findViewById(R.id.add_alarm_fab)
        mAddPersonFab = findViewById(R.id.add_person_fab)
        media_fab = findViewById(R.id.media_fab)
        doc_fab = findViewById(R.id.doc_fab)
        addAlarmActionText = findViewById(R.id.add_alarm_action_text)
        addPersonActionText = findViewById(R.id.add_person_action_text)
        media_action_txt = findViewById(R.id.media_action_txt)
        doc_action_txt = findViewById(R.id.doc_action_txt)
        mAddAlarmFab.visibility = View.GONE
        mAddPersonFab.visibility = View.GONE
        addAlarmActionText.visibility = View.GONE
        addPersonActionText.visibility = View.GONE
        media_fab.visibility = View.GONE
        media_action_txt.visibility = View.GONE
        doc_fab.visibility = View.GONE
        doc_action_txt.visibility = View.GONE


        isAllFabsVisible = false

        Parent_Fab.shrink()

        Parent_Fab.setOnClickListener(View.OnClickListener {
                isAllFabsVisible = if (!isAllFabsVisible!!)
                {
                    mAddAlarmFab.show()
                    mAddPersonFab.show()
                    media_fab.show()
                    doc_fab.show()
                    addAlarmActionText.visibility = View.VISIBLE
                    addPersonActionText.visibility = View.VISIBLE
                    media_action_txt.visibility = View.VISIBLE
                    doc_action_txt.visibility = View.VISIBLE

                    Parent_Fab.extend()

                    Parent_Fab.backgroundTintList = (ColorStateList.valueOf(resources.getColor(R.color.light_green)))
                    Parent_Fab.iconTint = ColorStateList.valueOf(Color.rgb(50, 10, 200))

                    true
                }
                else
                {
                    mAddAlarmFab.hide()
                    mAddPersonFab.hide()
                    media_fab.hide()
                    doc_fab.hide()
                    addAlarmActionText.setVisibility(View.GONE)
                    addPersonActionText.setVisibility(View.GONE)
                    media_action_txt.setVisibility(View.GONE)
                    doc_action_txt.setVisibility(View.GONE)

                    Parent_Fab.shrink()
                    Parent_Fab.backgroundTintList = (ColorStateList.valueOf(resources.getColor(R.color.light_grey)))
                    Parent_Fab.iconTint = ColorStateList.valueOf(Color.rgb(255, 50, 50))

                    false
                }
            })

        mAddPersonFab.setOnClickListener(
            View.OnClickListener {
                val intent = Intent(this@Group , AttachContact::class.java)
                startActivity(intent)
            })

        media_fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Group , Gallery::class.java)
            startActivity(intent)
        })

        mAddAlarmFab.setOnClickListener(
            View.OnClickListener {
                val intent = Intent(this@Group , SendChatDocs::class.java)
                startActivity(intent)
            })
// _________________--------------------_____Fab_____________________________________
    }

    fun back(view: View)
    {
        val intent = Intent(this@Group, Dashboard::class.java)
        startActivity(intent)
    }
}