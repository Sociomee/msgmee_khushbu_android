package com.inmortal.messenger.SocialChat

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnLongClickListener
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.inmortal.messenger.Adapter.MessageChatAdapter
import com.inmortal.messenger.R
import com.inmortal.messenger.StartActivity
import com.inmortal.messenger.URL.ApiURL
import com.inmortal.messenger.URL.CustomProgressDialog
import com.inmortal.messenger.activity.Dashboard
import com.inmortal.messenger.activity.Invite
import com.inmortal.messenger.model.MessageChatModel
import com.inmortal.messenger.profile.ProfileInfo
import com.inmortal.messenger.testing.ChattingActivity
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import org.json.JSONArray
import org.json.JSONObject


class Chat : AppCompatActivity() {


//Samir Strarts msg
private val customDialog: CustomProgressDialog? = null
    var messageChatModelList: ArrayList<MessageChatModel> = ArrayList<MessageChatModel>()
    var recyclerView: RecyclerView? = null
    var adapter: MessageChatAdapter? = null
    var messageET: EditText? = null
    var sendBtn: ImageView? = null
//Samir Strarts ends

    lateinit var dots: ImageView
    lateinit var clear: LinearLayout
    lateinit var delete: LinearLayout
    lateinit var delete_for_me: LinearLayout
    lateinit var cancel: LinearLayout
    lateinit var cancel_deleting: LinearLayout
    lateinit var search: LinearLayout
    lateinit var media: LinearLayout
    lateinit var block: LinearLayout

    lateinit var chat: LinearLayout
    lateinit var New: LinearLayout
    lateinit var send_contact: LinearLayout
    lateinit var deleted: LinearLayout
    lateinit var unread: RelativeLayout
    lateinit var user_unblocked: RelativeLayout
    lateinit var audio_footer: RelativeLayout
    lateinit var reply: LinearLayout
    lateinit var profile_info: LinearLayout

    lateinit var bottomSheetDialog_clear: BottomSheetDialog
    lateinit var bottomSheetDialog_delete: BottomSheetDialog
    lateinit var bottomSheetDialog_delete_message: BottomSheetDialog

    lateinit var header : RelativeLayout
    lateinit var header_after_hold : RelativeLayout
    lateinit var hide_popup: LinearLayout
    lateinit var delete_message: ImageView
    lateinit var info_after_hold: ImageView
    lateinit var copy: ImageView
    lateinit var forward_message: ImageView
    lateinit var normal_keyboard : RelativeLayout
    lateinit var reply_keyboard : LinearLayout
    lateinit var before_copied : RelativeLayout
    lateinit var message_copied : LinearLayout

    var strNewtoken = ""
    var token = ""


    //  Fab Comps +=??>>
// Use the FloatingActionButton for all the add person
// and add alarm
    lateinit var mAddAlarmFab: FloatingActionButton
    lateinit var mAddPersonFab: FloatingActionButton
    lateinit var media_fab: FloatingActionButton
    lateinit var doc_fab: FloatingActionButton
    lateinit  var Parent_Fab: ExtendedFloatingActionButton
// Samir
    lateinit  var Parent_Fab_samir: ExtendedFloatingActionButton
    lateinit var addAlarmActionText: RelativeLayout
    lateinit var addPersonActionText:  RelativeLayout
    lateinit var media_action_txt:  RelativeLayout
    lateinit var doc_action_txt:  RelativeLayout
    lateinit var mic:  RelativeLayout
    lateinit var org_footer:  RelativeLayout

    var isAllFabsVisible: Boolean? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


//         Samir Starts
        messageET = findViewById<View>(R.id.messageET) as EditText
        sendBtn = findViewById<View>(R.id.sendBtn) as ImageView

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        val manager = LinearLayoutManager(this@Chat, RecyclerView.VERTICAL, false)
        recyclerView!!.layoutManager = manager


        val model1 = MessageChatModel(
            "Hello. How are you today?",
            "10:00 PM",
            0
        )
        val model2 = MessageChatModel(
            "Hey! I'm fine. Thanks for asking!",
            "10:00 PM",
            1
        )
        val model3 = MessageChatModel(
            "Sweet! So, what do you wanna do today?",
            "10:00 PM",
            0
        )
        val model4 = MessageChatModel(
            "Nah, I dunno. Play soccer.. or learn more coding perhaps?",
            "10:00 PM",
            1
        )


        messageChatModelList.add(model1)
        messageChatModelList.add(model2)
        messageChatModelList.add(model3)
        messageChatModelList.add(model4)
        messageChatModelList.add(model1)
        messageChatModelList.add(model2)
        messageChatModelList.add(model3)
        messageChatModelList.add(model4)
        messageChatModelList.add(model1)
        messageChatModelList.add(model2)
        messageChatModelList.add(model3)
        messageChatModelList.add(model4)

        recyclerView!!.smoothScrollToPosition(messageChatModelList.size)
        adapter = MessageChatAdapter(messageChatModelList, this@Chat)
        recyclerView!!.adapter = adapter


        sendBtn!!.setOnClickListener {
            val msg = messageET!!.text.toString()
            val model = MessageChatModel(
                msg,
                "10:00 PM",
                0
            )
            messageChatModelList.add(model)
            recyclerView!!.smoothScrollToPosition(messageChatModelList.size)
            adapter!!.notifyDataSetChanged()
            messageET!!.setText("")

//            Api Functn for recieving user message
            recieveMssg()
        }

// Samir Ends

        val sharedPreferences: SharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
        token = sharedPreferences.getString("token", "").toString()
        Log.e("token", token)
        strNewtoken = "Bearer " + token


        chat = findViewById(R.id.chat)
        New = findViewById(R.id.New)
        send_contact = findViewById(R.id.send_contact)
        deleted = findViewById(R.id.deleted)
        unread = findViewById(R.id.unread)
        mic = findViewById(R.id.mic)
        user_unblocked = findViewById(R.id.user_unblocked)

        org_footer = findViewById(R.id.footer)

        header = findViewById(R.id.header)
        header_after_hold = findViewById(R.id.header_after_hold)
        hide_popup = findViewById(R.id.hide_popup)
        reply = findViewById(R.id.longg)
        profile_info = findViewById(R.id.profile_info)
        delete_message = findViewById(R.id.delete_message)
        normal_keyboard = findViewById(R.id.normal_keyboard)
        info_after_hold = findViewById(R.id.info_after_hold)
        copy = findViewById(R.id.copy)
        forward_message = findViewById(R.id.popup_options)
        before_copied = findViewById(R.id.before_copy)
        message_copied = findViewById(R.id.message_copied)
        reply_keyboard = findViewById(R.id.reply_keyboard)
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
delete_message.setOnClickListener(View.OnClickListener {
    bottomSheetDialog_delete_message = BottomSheetDialog(this, R.style.BottomSheetTheme)
    val view_delete: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_delete_chat, null)
    delete_for_me = view_delete.findViewById(R.id.delete_for_me)
    delete_for_me.setOnClickListener(View.OnClickListener {
        bottomSheetDialog_delete_message.dismiss()
        Toast.makeText(this@Chat , "Message deleted" , Toast.LENGTH_SHORT).show()
        header_after_hold.visibility=View.GONE
        header.visibility=View.VISIBLE
    })
    cancel_deleting = view_delete.findViewById(R.id.cancel_deleting)
    cancel_deleting.setOnClickListener(View.OnClickListener {
        bottomSheetDialog_delete_message.dismiss()
    })
    bottomSheetDialog_delete_message.dismiss()
    bottomSheetDialog_delete_message.requestWindowFeature(Window.FEATURE_NO_TITLE)
    bottomSheetDialog_delete_message.setContentView(view_delete)
    bottomSheetDialog_delete_message.show()

})
        hide_popup.setOnClickListener(View.OnClickListener {
            header_after_hold.visibility = View.GONE
            header.visibility = View.VISIBLE
            before_copied.visibility= View.VISIBLE
            message_copied.visibility= View.GONE
        })
        info_after_hold.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Chat , MessageInfoSingle::class.java)
            startActivity(intent)
        })
        copy.setOnClickListener(View.OnClickListener {
        before_copied.visibility= View.INVISIBLE
        message_copied.visibility= View.VISIBLE
        })
        forward_message.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Chat , ForwardMessage::class.java)
            startActivity(intent)
        })
        dots = findViewById(R.id.dots)
        dots.setOnClickListener(View.OnClickListener {
            bottomSheetDialog_clear = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_chat, null)

            clear = view.findViewById(R.id.clea)
            media = view.findViewById(R.id.media)
            block = view.findViewById(R.id.block_one)

            block.setOnClickListener(View.OnClickListener {
                user_unblocked.visibility=View.VISIBLE
                bottomSheetDialog_clear.dismiss()
            })
            media.setOnClickListener(View.OnClickListener {
                val intent = Intent(this@Chat, Media::class.java)
                startActivity(intent)
                bottomSheetDialog_clear.dismiss()
            })
            search = view.findViewById(R.id.search)
            search.setOnClickListener(View.OnClickListener {
                val intent = Intent(this@Chat, Search::class.java)
                startActivity(intent)
            })
            clear.setOnClickListener(View.OnClickListener {
                bottomSheetDialog_clear.dismiss()
                bottomSheetDialog_delete = BottomSheetDialog(this, R.style.BottomSheetTheme)
                val view_delete: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_delete, null)
                delete = view_delete.findViewById(R.id.delete)
                delete.setOnClickListener(View.OnClickListener {
                    bottomSheetDialog_delete.dismiss()
                    Toast.makeText(this@Chat , "Chat deleted" , Toast.LENGTH_SHORT).show()
                    chat.visibility = View.GONE
                    user_unblocked.visibility = View.GONE
                    New.visibility = View.GONE
                    send_contact.visibility = View.GONE
                    unread.visibility = View.GONE
                    deleted.visibility = View.VISIBLE
                })
                cancel = view_delete.findViewById(R.id.cancel)
                cancel.setOnClickListener(View.OnClickListener {
                    bottomSheetDialog_delete.dismiss()
                })
                bottomSheetDialog_delete.dismiss()
                bottomSheetDialog_delete.requestWindowFeature(Window.FEATURE_NO_TITLE)
                bottomSheetDialog_delete.setContentView(view_delete)
                bottomSheetDialog_delete.show()


            })
            bottomSheetDialog_clear.dismiss()
            bottomSheetDialog_clear.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog_clear.setContentView(view)
            bottomSheetDialog_clear.show()
        })


        // _________________--------------------_____Fab_____________________________________

        Parent_Fab = findViewById(R.id.add_fab)
        Parent_Fab_samir = findViewById(R.id.add_fab_samir)
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
   Parent_Fab_samir.shrink()

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

        Parent_Fab_samir.setOnClickListener(
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
              val intent = Intent(this@Chat , AttachContact::class.java)
                startActivity(intent)
            })

        media_fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Chat , Gallery::class.java)
            startActivity(intent)
        })

        mAddAlarmFab.setOnClickListener(
            View.OnClickListener {
                val intent = Intent(this@Chat , SendChatDocs::class.java)
                startActivity(intent)
            })
        profile_info.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Chat , ProfileInfo::class.java)
            startActivity(intent)
        })
// _________________--------------------_____Fab_____________________________________

        val profileName=intent.getStringExtra("key")
        Log.e(profileName , "key")
            if (profileName == "0")
            {
                send_contact.visibility = View.VISIBLE
                New.visibility = View.GONE
                user_unblocked.visibility = View.GONE
            }
            else
            {
                send_contact.visibility = View.GONE
                New.visibility = View.VISIBLE
            }

        user_unblocked.setOnClickListener(View.OnClickListener
        {
        val intent = Intent(this@Chat , Blocked::class.java)
            startActivity(intent)
        })

//        mic.setOnLongClickListener {
//        org_footer.visibility = View.GONE
//            audio_footer.visibility = View.VISIBLE
//            true
//        }
//        mic.setOnClickListener(View.OnClickListener {
//            org_footer.visibility = View.VISIBLE
//            audio_footer.visibility = View.GONE
//        })

        mic.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Chat , ChattingActivity::class.java)
            startActivity(intent)
        })

    }

    private fun recieveMssg() {

        customDialog?.showProgressBar()
        Ion.with(this@Chat)
            .load("POST", ApiURL.GetChat_URL)
            .setHeader("Authorization",strNewtoken)
            .setBodyParameter("pageIndex", 0.toString())
            .setBodyParameter("pageSize", 10.toString())
            .asString()
            .setCallback(FutureCallback<String?> { _, result ->
                try {
                    val jsonObject = JSONObject(result)
                    val success = jsonObject.getString("success")
                    if (success == "true") {
                        customDialog?.hideProgressBar()
                        val jsonObject1: JSONObject = jsonObject.getJSONObject("data")
                        var jsonObject2: JSONObject = jsonObject1.getJSONObject("successResult")
                        val arr: JSONArray
                        try
                        {
                            customDialog?.hideProgressBar()
                            arr = jsonObject2.getJSONArray("rows")
                            for (i in 0 until arr.length())
                            {
                                val jasonobject3 = arr.getJSONObject(i)
//                                messageChatModelList.add(MessageChatModel
//                                    (
//                                        jasonobject3.getString("fullName"),)
//                                )
//
//                                adapter = MessageChatAdapter(messageChatModelList, this@Chat, R.layout.receive_layout, this, 1)

                                val layoutManager1 = LinearLayoutManager(
                                    this@Chat,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                                recyclerView?.layoutManager = layoutManager1
                                recyclerView?.setHasFixedSize(true)
                                recyclerView?.adapter = adapter
                            }
                        }
                        catch (e: Exception) {
                            customDialog?.hideProgressBar()
                            e.printStackTrace()
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


    fun back(view: View)
    {
        val intent = Intent(this@Chat, Dashboard::class.java)
        startActivity(intent)
    }
}