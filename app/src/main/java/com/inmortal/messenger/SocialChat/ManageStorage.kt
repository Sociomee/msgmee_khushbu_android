package com.inmortal.messenger.SocialChat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.inmortal.messenger.R
import com.inmortal.messenger.Settings.StorageSettings

class ManageStorage : AppCompatActivity()
{
    lateinit var forward:RelativeLayout
    lateinit var nex:RelativeLayout
    lateinit var larger:RelativeLayout
    lateinit var larger_view:RelativeLayout
    lateinit var razadarDocs:LinearLayout
    lateinit var gpDocs:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_storage)
        forward = findViewById(R.id.ne)
        nex = findViewById(R.id.nex)
        larger = findViewById(R.id.larger)
        larger_view = findViewById(R.id.larger_view)
        razadarDocs = findViewById(R.id.ChatDocs)
        gpDocs = findViewById(R.id.GpDocs)
        forward.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ManageStorage , Forwarded::class.java)
            startActivity(intent)
        })
        nex.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ManageStorage , Forwarded::class.java)
            startActivity(intent)
        })
        larger.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ManageStorage , Larger::class.java)
            startActivity(intent)
        })
        larger_view.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ManageStorage , Larger::class.java)
            startActivity(intent)
        })
        razadarDocs.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ManageStorage , SendChatDocs::class.java)
            startActivity(intent)
        })
        gpDocs.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ManageStorage , GroupDocs::class.java)
            startActivity(intent)
        })
    }
    fun back(view: View)
    {
        val intent = Intent(this@ManageStorage , StorageSettings::class.java)
        startActivity(intent)
    }
}