package com.example.savingsai.model

data class ChatMessage(
    val message: String,
    val isUser: Boolean // ユーザー: true, AI: false
)
