package com.inmortal.messenger.BizPageAndMarketPlace

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.inmortal.messenger.R
import com.inmortal.messenger.SocialChat.Chat

class BizpageMessage : AppCompatActivity() {

    lateinit var social: TextView
    lateinit var bizpage: TextView
    lateinit var market: TextView
    lateinit var calls: TextView
    lateinit var social_bar: View
    lateinit var bizpage_bar: View
    lateinit var marketbar: View
    lateinit var calls_bar: View
    lateinit var hide_popup_after_hit:LinearLayout
    lateinit var hide_popup_after_hit_m:LinearLayout
    lateinit var popup_after_hit:LinearLayout
    lateinit var popup_after_hit_m:LinearLayout
    lateinit var blank_screen:LinearLayout
    lateinit var bizpage_view:LinearLayout
    lateinit var market_view:LinearLayout
    lateinit var to_marketchat:RelativeLayout
    lateinit var to_marketplace:RelativeLayout
    lateinit var to_unknown:RelativeLayout
    lateinit var to_bizpage:RelativeLayout
    lateinit var to_sellerchat:RelativeLayout
    lateinit var title:TextView
    lateinit var title_pic:ImageView
    lateinit var back_arow:ImageView

    // Initialise the DrawerLayout, NavigationView and ToggleBar
    private lateinit var drawerLayout: DrawerLayout
//    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var headerview : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bizpage_message)

        // Call findViewById on the DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)

        // Pass the ActionBarToggle action into the drawerListener
//        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
//        drawerLayout.addDrawerListener(actionBarToggle)

//        // Display the hamburger icon to launch the drawer
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
//        actionBarToggle.syncState()


        // Call findViewById on the NavigationView
        navView = findViewById(R.id.navView)
        headerview =  navView.getHeaderView(0)
        to_marketplace = headerview.findViewById(R.id.to_marketplace)
        to_bizpage = headerview.findViewById(R.id.to_bizpage)
        to_unknown = headerview.findViewById(R.id.to_unknown)

        // Call setNavigationItemSelectedListener on the NavigationView to detect when items are clicked
//        navView.setNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.myProfile -> {
//                    Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show()
//                    true
//                }
//                R.id.people -> {
//                    Toast.makeText(this, "People", Toast.LENGTH_SHORT).show()
//                    true
//                }
//                R.id.settings -> {
//                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
//                    true
//                }
//                else -> {
//                    false
//                }
//            }
//        }

        title = findViewById(R.id.title)
        title_pic = findViewById(R.id.title_pic)
        back_arow = findViewById(R.id.back_arow)

        blank_screen = findViewById(R.id.blank_screen)
        bizpage_view = findViewById(R.id.bizpage_view)
        market_view = findViewById(R.id.market_view)
        to_marketchat = findViewById(R.id.to_marketchat)
        to_sellerchat = findViewById(R.id.unknown)

        social = findViewById(R.id.social)
        social_bar = findViewById(R.id.social_bar)

        bizpage = findViewById(R.id.bizpage)
        bizpage_bar = findViewById(R.id.bizpage_bar)

        market = findViewById(R.id.market)
        marketbar = findViewById(R.id.market_bar)

        calls = findViewById(R.id.calls)
        calls_bar = findViewById(R.id.calls_bar)

        back_arow.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        social.setOnClickListener(View.OnClickListener {
            social_bar.visibility = View.VISIBLE
            bizpage_bar.visibility = View.INVISIBLE
            marketbar.visibility = View.INVISIBLE
            calls_bar.visibility = View.INVISIBLE

            social.setTextColor(ContextCompat.getColor(this,R.color.light_green))
            bizpage.setTextColor(Color.BLACK)
            market.setTextColor(Color.BLACK)
            calls.setTextColor(Color.BLACK)

            blank_screen.visibility=View.VISIBLE
            bizpage_view.visibility = View.GONE
            market_view.visibility = View.GONE
            showEmptyPopUp()
        })
        bizpage.setOnClickListener(View.OnClickListener {
            social_bar.visibility = View.INVISIBLE
            bizpage_bar.visibility = View.VISIBLE
            marketbar.visibility = View.INVISIBLE
            calls_bar.visibility = View.INVISIBLE

            social.setTextColor(Color.BLACK)
            bizpage.setTextColor(ContextCompat.getColor(this,R.color.light_green))
            market.setTextColor(Color.BLACK)
            calls.setTextColor(Color.BLACK)

            blank_screen.visibility=View.GONE
            bizpage_view.visibility = View.VISIBLE
            market_view.visibility = View.GONE

            to_bizpage.visibility =View.VISIBLE
            to_marketplace.visibility = View.GONE
            to_unknown.visibility = View.VISIBLE
            drawerLayout.openDrawer(Gravity.LEFT)

            title.text = "BizPage Messages"
            title_pic.visibility = View.VISIBLE
            back_arow.visibility = View.GONE
        })
        market.setOnClickListener(View.OnClickListener {
            social_bar.visibility = View.INVISIBLE
            bizpage_bar.visibility = View.INVISIBLE
            marketbar.visibility = View.VISIBLE
            calls_bar.visibility = View.INVISIBLE

            social.setTextColor(Color.BLACK)
            bizpage.setTextColor(Color.BLACK)
            market.setTextColor(ContextCompat.getColor(this,R.color.light_green))
            calls.setTextColor(Color.BLACK)

            blank_screen.visibility=View.GONE
            bizpage_view.visibility = View.GONE
            market_view.visibility = View.VISIBLE

            to_bizpage.visibility = View.GONE
            to_marketplace.visibility = View.VISIBLE
            to_unknown.visibility = View.GONE
            drawerLayout.openDrawer(navView)

            title.text = "Market Messages"
            title_pic.visibility = View.GONE
            back_arow.visibility = View.VISIBLE

        })

        calls.setOnClickListener(View.OnClickListener
        {
            calls_bar.visibility = View.VISIBLE
            bizpage_bar.visibility = View.INVISIBLE
            marketbar.visibility = View.INVISIBLE
            social_bar.visibility =View.INVISIBLE

            social.setTextColor(Color.BLACK)
            bizpage.setTextColor(Color.BLACK)
            market.setTextColor(Color.BLACK)
            calls.setTextColor(ContextCompat.getColor(this,R.color.light_green))

            blank_screen.visibility=View.VISIBLE
            bizpage_view.visibility = View.GONE
            market_view.visibility = View.GONE

            showEmptyPopUp()
        })

        to_marketchat.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@BizpageMessage , MarketPlaceChat::class.java)
            startActivity(intent)
        })
        to_sellerchat.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@BizpageMessage , MarketPlaceSeller::class.java)
            startActivity(intent)
        })
        hide_popup_after_hit = findViewById(R.id.hide_popup_after_hit)
        hide_popup_after_hit_m = findViewById(R.id.hide_popup_after_hit_m)
        popup_after_hit = findViewById(R.id.popup_after_hit)
        popup_after_hit_m = findViewById(R.id.popup_after_hit_m)

        hide_popup_after_hit.setOnClickListener(View.OnClickListener {
            popup_after_hit.visibility  = View.GONE
        })
        hide_popup_after_hit_m.setOnClickListener(View.OnClickListener {
            popup_after_hit_m.visibility  = View.GONE
        })

        to_marketplace.setOnClickListener(View.OnClickListener {
            popup_after_hit_m.visibility = View.VISIBLE
            market.setTextColor(ContextCompat.getColor(this,R.color.light_green))
            marketbar.visibility = View.VISIBLE
            market_view.visibility = View.VISIBLE
            bizpage.setTextColor(Color.BLACK)
            bizpage_bar.visibility = View.INVISIBLE
            bizpage_view.visibility = View.GONE
            drawerLayout.close()
        })
        to_bizpage.setOnClickListener(View.OnClickListener {
            popup_after_hit.visibility = View.VISIBLE
            bizpage.setTextColor(ContextCompat.getColor(this,R.color.light_green))
            bizpage_bar.visibility = View.VISIBLE
            bizpage_view.visibility = View.VISIBLE
            market.setTextColor(Color.BLACK)
            marketbar.visibility = View.INVISIBLE
            market_view.visibility = View.GONE
            drawerLayout.close()
        })
    }

    private fun showEmptyPopUp()
    {
        //       Dialog Box popup appears when come to otp screen
        val view =  View.inflate(this@BizpageMessage, R.layout.no_data_yet ,null)
        val builder = AlertDialog.Builder(this@BizpageMessage)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        val back_to_bizpage:RelativeLayout = view.findViewById(R.id.back_to_bizpage)
        back_to_bizpage.setOnClickListener(View.OnClickListener {
            bizpage_view.visibility=View.VISIBLE
            bizpage.visibility= View.VISIBLE
            bizpage_bar.visibility= View.VISIBLE
            blank_screen.visibility=View.GONE
            social.setTextColor(Color.BLACK)
            social_bar.visibility= View.INVISIBLE
            market.setTextColor(Color.BLACK)
            marketbar.visibility= View.INVISIBLE
            calls.setTextColor(Color.BLACK)
            calls_bar.visibility= View.INVISIBLE
            dialog.dismiss()
        })
        /*ends*/
    }

    // override the onSupportNavigateUp() function to launch the Drawer when the hamburger icon is clicked
    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }

    // override the onBackPressed() function to close the Drawer when the back button is clicked
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}