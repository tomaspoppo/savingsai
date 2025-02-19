package com.example.savingsai.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savingsai.ChatMessage
import com.example.savingsai.R
import com.example.savingsai.ui.ChatAdapter // 追加

class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerViewChat: RecyclerView
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSend: Button
    private val chatMessages = mutableListOf<ChatMessage>()
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        recyclerViewChat = findViewById(R.id.recyclerViewChat)
        editTextMessage = findViewById(R.id.editTextMessage)
        buttonSend = findViewById(R.id.buttonSend)

        chatAdapter = ChatAdapter(chatMessages)
        recyclerViewChat.layoutManager = LinearLayoutManager(this)
        recyclerViewChat.adapter = chatAdapter

        buttonSend.setOnClickListener {
            val userMessage = editTextMessage.text.toString()
            if (userMessage.isNotBlank()) {
                addMessage(userMessage, true)
                editTextMessage.text.clear()

                // AIの仮の応答
                val aiResponse = getAIResponse(userMessage)
                addMessage(aiResponse, false)
            }
        }
    }

    private fun addMessage(message: String, isUser: Boolean) {
        chatMessages.add(ChatMessage(message, isUser))
        chatAdapter.notifyItemInserted(chatMessages.size - 1) // 修正
        recyclerViewChat.scrollToPosition(chatMessages.size - 1)
    }

    private fun getAIResponse(userInput: String): String {
        return when {
            userInput.contains("スマホ") -> "格安SIMに乗り換えると節約できます。"
            userInput.contains("電気") -> "電気料金プランを見直すと良いでしょう。"
            else -> "その情報はまだ準備中です。"
        }
    }
}
