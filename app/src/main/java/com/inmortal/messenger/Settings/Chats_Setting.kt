package com.inmortal.messenger.Settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.*
import com.inmortal.messenger.Adapter.*
import com.inmortal.messenger.URL.ApiURL
import com.inmortal.messenger.URL.CustomProgressDialog
import com.inmortal.messenger.model.GoogleAccountModel
import com.inmortal.messenger.model.LanguageModel
import com.inmortal.messenger.model.MyChatModel
import com.inmortal.messenger.model.MyChatss_ClearModel
import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList



class Chats_Setting : AppCompatActivity(), NewLanguageList.ReturnView {

    lateinit var back_arrow: ImageView
    lateinit var constraint_schedule: ConstraintLayout
    lateinit var btnok_chatbackup: AppCompatButton
    lateinit var constraint_exportchat: ConstraintLayout
    lateinit var btnok_exportchat: AppCompatButton
    lateinit var constraint_archive_chat: ConstraintLayout
    lateinit var constraint_clear_chat: ConstraintLayout
    lateinit var constraint_delete_chat: ConstraintLayout
    lateinit var constraint_googleAC: ConstraintLayout
    lateinit var constraint_choose_language: ConstraintLayout
    lateinit var recycler_my_chats: RecyclerView
    lateinit var adapter: GoogleAccount_Adapter
    val list = ArrayList<GoogleAccountModel>()
    val list1 = ArrayList<MyChatModel>()
    lateinit var adapter1: MyChats_Adapter
    lateinit var text_archive_select: TextView
    lateinit var btnok_archivechat: AppCompatButton
    lateinit var text_select_all: TextView
    lateinit var text_clear_select: TextView
    val list2 = ArrayList<MyChatss_ClearModel>()
    lateinit var adapter2: MyChats_ClearAdapter
    lateinit var btnok_clearchat: AppCompatButton
    lateinit var text_select_alll:TextView


    lateinit var bottomsheet_schedule_backup: BottomSheetDialog
    lateinit var bottomsheet_exportchat: BottomSheetDialog
    lateinit var bottomsheet_archive_chat: BottomSheetDialog
    lateinit var bottomsheet_clear_allchats: BottomSheetDialog
    lateinit var bottomsheet_clear_chats: BottomSheetDialog
    lateinit var bottomsheet_deletechat: BottomSheetDialog
    lateinit var bottomsheet_googleaccount: BottomSheetDialog
    lateinit var bottomsheet_my_chats: BottomSheetDialog

    lateinit var ChqTxt: TextView
    var strlanguage_id = ""
    lateinit var searchL: SearchView
    private val arrModelList: java.util.ArrayList<LanguageModel> = java.util.ArrayList<LanguageModel>()
    private val LangArrayList: java.util.ArrayList<LanguageModel> = java.util.ArrayList<LanguageModel>()
    lateinit var bottomSheet_Language: BottomSheetDialog
    private val customDialog: CustomProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats_setting)

        ChqTxt = findViewById(R.id.chqTxt)
        back_arrow = findViewById(R.id.back_arrow)
        constraint_schedule = findViewById(R.id.constraint_schedule)
        constraint_exportchat = findViewById(R.id.constraint_export_chat)
        constraint_archive_chat = findViewById(R.id.constraint_archive_chat)
        constraint_clear_chat = findViewById(R.id.constraint_clear_chat)
        constraint_delete_chat = findViewById(R.id.constraint_delete_chat)
        constraint_googleAC = findViewById(R.id.constraint_googleAC)
        constraint_choose_language = findViewById(R.id.constraint_choose_language)



        for (i in 0..1) {
            if (i % 2 == 0) {
                val model =
                    GoogleAccountModel(R.drawable.categories2, "Saqib Saifi", "saqib@gmail.com")
                list.add(model)
            } else {
                val model =
                    GoogleAccountModel(R.drawable.fashion5, "Inmortal Tech", "abcd@gmail.com")
                list.add(model)
            }


        }





        back_arrow.setOnClickListener(View.OnClickListener {

            onBackPressed()

        })

        constraint_schedule.setOnClickListener(View.OnClickListener {

            bottomsheet_schedule_backup = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext)
                .inflate(R.layout.bottomsheet_schedule_backup, null)

            btnok_chatbackup = view.findViewById(R.id.btnok_chatbackup)

            btnok_chatbackup.setOnClickListener(View.OnClickListener {

                bottomsheet_schedule_backup.dismiss()

            })

            bottomsheet_schedule_backup.dismiss()
            bottomsheet_schedule_backup.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_schedule_backup.setContentView(view)
            bottomsheet_schedule_backup.show()

        })

        constraint_exportchat.setOnClickListener(View.OnClickListener {


            bottomsheet_exportchat = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext)
                .inflate(R.layout.bottomsheet_exportchat, null)



            btnok_exportchat = view.findViewById(R.id.btnok_exportchat)

            btnok_exportchat.setOnClickListener(View.OnClickListener {

                bottomsheet_exportchat.dismiss()

            })

            bottomsheet_exportchat.dismiss()
            bottomsheet_exportchat.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_exportchat.setContentView(view)
            bottomsheet_exportchat.show()


        })

        constraint_archive_chat.setOnClickListener(View.OnClickListener {

            bottomsheet_my_chats = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View =
                LayoutInflater.from(applicationContext).inflate(R.layout.bottomsheet_my_chats, null)

            recycler_my_chats = view.findViewById(R.id.recycler_my_chats)
            text_archive_select = view.findViewById(R.id.text_archive_select)
            text_select_all = view.findViewById(R.id.text_select_all)

            list1.clear()
            for (j in 0..1) {
                if (j % 2 == 0) {
                    val model1 = MyChatModel("A", "Piyush Sharma", "2.4 Mb", false)
                    list1.add(model1)
                } else {
                    val model1 = MyChatModel("F", "Ankit Goswami", "12.4 Mb", false)
                    list1.add(model1)
                }


            }


            adapter1 = MyChats_Adapter(this, list1)
            recycler_my_chats.adapter = adapter1

            text_select_all.setOnClickListener(View.OnClickListener {
                list1.clear()
                for (j in 0..1) {
                    if (j % 2 == 0) {
                        val model1 = MyChatModel("A", "Piyush Sharma", "2.4 Mb", true)
                        list1.add(model1)
                    } else {
                        val model1 = MyChatModel("F", "Ankit Goswami", "12.4 Mb", true)
                        list1.add(model1)
                    }

                }

                adapter1.notifyDataSetChanged()


            })

            text_archive_select.setOnClickListener(View.OnClickListener {


                bottomsheet_archive_chat = BottomSheetDialog(this, R.style.BottomSheetTheme)
                val view: View = LayoutInflater.from(applicationContext)
                    .inflate(R.layout.bottomsheet_archive_chat, null)

                btnok_archivechat = view.findViewById(R.id.btnok_archivechat)

                btnok_archivechat.setOnClickListener(View.OnClickListener {

                    bottomsheet_archive_chat.dismiss()
                    recycler_my_chats.visibility = View.GONE
                    bottomsheet_my_chats.dismiss()

                })

                bottomsheet_archive_chat.dismiss()
                bottomsheet_archive_chat.requestWindowFeature(Window.FEATURE_NO_TITLE)
                bottomsheet_archive_chat.setContentView(view)
                bottomsheet_archive_chat.show()

            })




            bottomsheet_my_chats.dismiss()
            bottomsheet_my_chats.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_my_chats.setContentView(view)
            bottomsheet_my_chats.show()


        })

        constraint_clear_chat.setOnClickListener(View.OnClickListener {

            bottomsheet_clear_chats = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext)
                .inflate(R.layout.bottomsheet_clear_chats, null)

            var recycler_clear_chats: RecyclerView = view.findViewById(R.id.recycler_clear_chats)
            text_clear_select = view.findViewById(R.id.text_clear_select)
            text_select_alll = view.findViewById(R.id.text_select_alll)

            list2.clear()

            for (k in 0..1) {
                if (k % 2 == 0) {
                    val model2 = MyChatss_ClearModel("L", "Rajat Sharma", "2.4 Mb",false)
                    list2.add(model2)
                } else {
                    val model2 = MyChatss_ClearModel("F", "Rahul Kumar", "12.4 Mb",false)
                    list2.add(model2)
                }


            }

            adapter2 = MyChats_ClearAdapter(this, list2)
            recycler_clear_chats.adapter = adapter2

            text_select_alll.setOnClickListener(View.OnClickListener {

                list2.clear()

                for (k in 0..1) {
                    if (k % 2 == 0) {
                        val model2 = MyChatss_ClearModel("L", "Rajat Sharma", "2.4 Mb",true)
                        list2.add(model2)
                    } else {
                        val model2 = MyChatss_ClearModel("F", "Rahul Kumar", "12.4 Mb",true)
                        list2.add(model2)
                    }


                }

                adapter2.notifyDataSetChanged()

            })


            text_clear_select.setOnClickListener(View.OnClickListener {

                bottomsheet_clear_allchats = BottomSheetDialog(this, R.style.BottomSheetTheme)
                val view: View = LayoutInflater.from(applicationContext)
                    .inflate(R.layout.bottomsheet_clear_allchats, null)

                btnok_clearchat = view.findViewById(R.id.btnok_clearchat)

                btnok_clearchat.setOnClickListener(View.OnClickListener {

                    bottomsheet_clear_allchats.dismiss()
                    recycler_clear_chats.visibility = View.GONE
                    bottomsheet_clear_chats.dismiss()

                })

                bottomsheet_clear_allchats.dismiss()
                bottomsheet_clear_allchats.requestWindowFeature(Window.FEATURE_NO_TITLE)
                bottomsheet_clear_allchats.setContentView(view)
                bottomsheet_clear_allchats.show()

            })


            bottomsheet_clear_chats.dismiss()
            bottomsheet_clear_chats.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_clear_chats.setContentView(view)
            bottomsheet_clear_chats.show()

        })

        constraint_delete_chat.setOnClickListener(View.OnClickListener {

            bottomsheet_deletechat = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext)
                .inflate(R.layout.bottomsheet_deletechat, null)


            bottomsheet_deletechat.dismiss()
            bottomsheet_deletechat.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_deletechat.setContentView(view)
            bottomsheet_deletechat.show()


        })

        constraint_googleAC.setOnClickListener(View.OnClickListener {

            bottomsheet_googleaccount = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext)
                .inflate(R.layout.bottomsheet_googleaccount, null)

            var recycler_googleaccount: RecyclerView = view.findViewById(R.id.recycler_googleaccount)
            adapter = GoogleAccount_Adapter(this, list)
            recycler_googleaccount.adapter = adapter

            bottomsheet_googleaccount.dismiss()
            bottomsheet_googleaccount.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomsheet_googleaccount.setContentView(view)
            bottomsheet_googleaccount.show()


        })

        constraint_choose_language.setOnClickListener(View.OnClickListener {

            LangArrayList.clear()
            LanguageLatestApi()
            bottomSheet_Language = BottomSheetDialog(this, R.style.BottomSheetTheme)
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
            bottomSheet_Language.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheet_Language.setContentView(view)
            bottomSheet_Language.show()

        })
    }
    private fun LanguageLatestApi()
    {
        customDialog?.showProgressBar()
        Ion.with(this@Chats_Setting)
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
                                arrModelList.add(
                                    LanguageModel(
                                        jasonobject3.getString("id"),
                                        jasonobject3.getString("name"),
                                        jasonobject3.getString("iconURL")
                                    )
                                )
//                                Have Created another Array List -> temp Array List for filteration for search and store Country Array list to it delete it when no filteration use

//                                LangArrayList.addAll(arrModelList)
                                languageAdapter = NewLanguageList(LangArrayList, this@Chats_Setting, R.layout.spinner_lang_item, this, 1)
                                val layoutManager1 = LinearLayoutManager(
                                    this@Chats_Setting,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
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




    override fun getLangAdapterView(view: View?, objects: MutableList<Any?>?, position: Int, from: Int)
    {
        val linerr = view!!.findViewById<View>(R.id.linerr) as LinearLayout
        val lang = view.findViewById<View>(R.id.lang) as TextView

        lang.text = LangArrayList[position].name
        strlanguage_id =LangArrayList[position].id

        linerr.setOnClickListener(View.OnClickListener {
            ChqTxt.text =LangArrayList[position].name
            strlanguage_id=LangArrayList[position].id
            bottomSheet_Language.dismiss()
        })
    }

}