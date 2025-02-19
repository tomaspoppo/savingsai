package com.example.savingsai

data class ChatMessage(
    val message: String, // メッセージ本文
    val isUser: Boolean  // true: ユーザー, false: AI
)
