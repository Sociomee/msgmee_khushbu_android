package com.inmortal.messenger.SocialChat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.inmortal.messenger.R

class GroupDocs : AppCompatActivity() {

    lateinit var Returnex:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_docs)

        Returnex = findViewById(R.id.Return)
        Returnex.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@GroupDocs , ManageStorage::class.java)
            startActivity(intent)
        })
    }
}