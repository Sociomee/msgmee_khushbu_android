package com.inmortal.messenger.SocialChat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.inmortal.messenger.R

class GalleryBrightness : AppCompatActivity() {

    lateinit var cancel:LinearLayout
    lateinit var mseekBar: SeekBar
    lateinit var seekbar_value: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_brightness)

        cancel = findViewById(R.id.footer)

        mseekBar =  findViewById(R.id.customSeekBar)
        seekbar_value =  findViewById(R.id.seekbar_value)
        mseekBar.progress = 0


        mseekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean)
            {
//                if(progress!=0)
//                {
//                    val cur = seekBar.width/seekBar.max
//
//                    seekbar_value.x = (cur * progress).toFloat() + -10
//                }
//                seekbar_value.y = seekBar.pivotY + 10
//                Log.e("Pos", seekbar_value.x.toString() + ": " + seekBar.width+":" +seekBar.x)
                seekbar_value.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                print("onStartTrackingTouch ${seekBar.x}")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                print("onStopTrackingTouch ${seekBar.x}")
            }
        })
        cancel.setOnClickListener {
            val intent = Intent(this@GalleryBrightness, GalleryFilter::class.java)
            startActivity(intent)
        }
    }
    fun print(msg: String) {
        Log.e("SEEK", msg)
    }
}