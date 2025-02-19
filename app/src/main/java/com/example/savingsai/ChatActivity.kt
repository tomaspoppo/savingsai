package com.example.savingsai.ui

import android.os.Bundle
import android.util.Log // 追加
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savingsai.ChatMessage
import com.example.savingsai.R
import com.example.savingsai.ui.ChatAdapter
import com.example.savingsai.BuildConfig // 追加

class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerViewChat: RecyclerView
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSend: Button
    private val chatMessages = mutableListOf<ChatMessage>()
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ソフトキーボードの動作を調整（キーボード表示時にUIを適切に調整）
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContentView(R.layout.activity_chat)

        // 🚀 追加: APIキーのログを確認
        Log.d("API_KEY_CHECK", "取得した APIキー: ${BuildConfig.OPENAI_API_KEY}")
        Log.d("API_KEY_CHECK", "BuildConfig.OPENAI_API_KEY の値が空か: ${BuildConfig.OPENAI_API_KEY.isEmpty()}")

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
        chatAdapter.notifyItemInserted(chatMessages.size - 1)
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
