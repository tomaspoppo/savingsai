package com.example.savingsai.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savingsai.adapter.ChatAdapter
import com.example.savingsai.databinding.ActivityChatBinding
import com.example.savingsai.model.ChatMessage

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val chatMessages = mutableListOf<ChatMessage>()
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ View Binding を適用
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ RecyclerView の設定
        chatAdapter = ChatAdapter(chatMessages)
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.chatRecyclerView.adapter = chatAdapter

        // ✅ 送信ボタンのクリック処理
        binding.sendButton.setOnClickListener {
            val userMessage = binding.messageInput.text.toString()
            if (userMessage.isNotBlank()) {
                addMessage(userMessage, isUser = true)
                binding.messageInput.text.clear()
            }
        }
    }

    private fun addMessage(message: String, isUser: Boolean) {
        chatMessages.add(ChatMessage(message, isUser))
        chatAdapter.notifyItemInserted(chatMessages.size - 1)
        binding.chatRecyclerView.scrollToPosition(chatMessages.size - 1)
    }
}
