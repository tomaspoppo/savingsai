package com.example.savingsai.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.savingsai.R
import com.example.savingsai.model.ChatMessage

class ChatAdapter(private var messages: MutableList<ChatMessage>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userMessage: TextView = view.findViewById(R.id.userMessage)
        val botMessage: TextView = view.findViewById(R.id.botMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = messages[position]
        holder.apply {
            if (message.isUser) {
                userMessage.text = message.message
                userMessage.visibility = View.VISIBLE
                botMessage.visibility = View.GONE
            } else {
                botMessage.text = message.message
                botMessage.visibility = View.VISIBLE
                userMessage.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = messages.size

    // ✅ 新しいメッセージを追加するメソッド
    fun addMessage(newMessage: ChatMessage) {
        messages.add(newMessage)
        notifyItemInserted(messages.size - 1)
    }
}
