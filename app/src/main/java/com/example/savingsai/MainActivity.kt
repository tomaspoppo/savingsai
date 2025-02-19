package com.example.savingsai

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.savingsai.ui.ChatActivity // 追加

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStartChat = findViewById<Button>(R.id.buttonStartChat)
        buttonStartChat.setOnClickListener {
            val intent = Intent(this@MainActivity, ChatActivity::class.java) // 修正
            startActivity(intent)
        }
    }
}
