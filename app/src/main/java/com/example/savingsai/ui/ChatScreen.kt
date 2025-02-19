package com.example.savingsai.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savingsai.adapter.ChatAdapter
import com.example.savingsai.model.ChatMessage
import com.example.savingsai.databinding.ActivityChatBinding

class ChatScreen : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val messages = mutableListOf<ChatMessage>()
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ChatAdapter(messages)
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.chatRecyclerView.adapter = adapter

        binding.sendButton.setOnClickListener {
            val text = binding.messageInput.text.toString()
            if (text.isNotEmpty()) {
                messages.add(ChatMessage(text, true))
                adapter.notifyItemInserted(messages.size - 1)
                binding.chatRecyclerView.scrollToPosition(messages.size - 1)
                binding.messageInput.text.clear()
            }
        }
    }
}
