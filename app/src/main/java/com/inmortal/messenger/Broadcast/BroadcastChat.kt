package com.inmortal.messenger.Broadcast

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.inmortal.messenger.R
import com.inmortal.messenger.SocialChat.AttachContact

class BroadcastChat : AppCompatActivity() {

    lateinit var dots: ImageView
    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var search_b: LinearLayout
    lateinit var clear_b: LinearLayout
    lateinit var main_view: LinearLayout
    //  Fab Comps +=??>>
// Use the FloatingActionButton for all the add person
// and add alarm
    lateinit var mAddAlarmFab: FloatingActionButton
    lateinit var mAddPersonFab: FloatingActionButton
    lateinit var media_fab: FloatingActionButton
    lateinit var doc_fab: FloatingActionButton
    lateinit  var Parent_Fab: ExtendedFloatingActionButton
    lateinit var addAlarmActionText: RelativeLayout
    lateinit var addPersonActionText: RelativeLayout
    lateinit var media_action_txt: RelativeLayout
    lateinit var doc_action_txt: RelativeLayout
    var isAllFabsVisible: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_chat)

        dots = findViewById(R.id.dots)
        main_view = findViewById(R.id.main_view)
        dots.setOnClickListener(View.OnClickListener {
            bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_broadcast, null)
            search_b = view.findViewById(R.id.search_b)
            clear_b = view.findViewById(R.id.clear_b)
            clear_b.setOnClickListener(View.OnClickListener {
                main_view.visibility = View.GONE
                bottomSheetDialog.dismiss()
            })
            search_b.setOnClickListener(View.OnClickListener {
                val intent = Intent(this@BroadcastChat ,BroadcastList::class.java)
                startActivity(intent)
            })
            bottomSheetDialog.dismiss()
            bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()
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

        Parent_Fab.setOnClickListener(
            View.OnClickListener {
                isAllFabsVisible = if (!isAllFabsVisible!!) {


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
                } else {

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
                val intent = Intent(this@BroadcastChat , AttachContact::class.java)
                startActivity(intent)
            })

        mAddAlarmFab.setOnClickListener(
            View.OnClickListener {
                Toast.makeText(this@BroadcastChat, "Alarm Added", Toast.LENGTH_SHORT).show() })
// _________________--------------------_____Fab_____________________________________

    }

    fun back(view: View) {
        val intent = Intent(this@BroadcastChat , BroadcastInfo::class.java)
        startActivity(intent)
    }
}