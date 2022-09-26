package com.inmortal.messenger.BizPageAndMarketPlace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inmortal.messenger.R

class MarketPlaceSeller : AppCompatActivity() {

    lateinit var dots: ImageView
    lateinit var bottomSheetDialog_clear: BottomSheetDialog
    lateinit var clear_screen: LinearLayout
    lateinit var chat: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_place_seller)

        dots = findViewById(R.id.dots)
        chat = findViewById(R.id.chat)
        dots.setOnClickListener(View.OnClickListener {
            bottomSheetDialog_clear = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val view: View = LayoutInflater.from(applicationContext).inflate(R.layout.bottom_slider_seller, null)
            clear_screen = view.findViewById(R.id.clea)
            clear_screen.setOnClickListener(View.OnClickListener {
                chat.visibility= View.GONE
            })
            bottomSheetDialog_clear.dismiss()
            bottomSheetDialog_clear.requestWindowFeature(Window.FEATURE_NO_TITLE)
            bottomSheetDialog_clear.setContentView(view)
            bottomSheetDialog_clear.show()
        })
    }

    fun back(view: View) {
        val intent = Intent(this@MarketPlaceSeller , BizpageMessage::class.java)
        startActivity(intent)
    }
}