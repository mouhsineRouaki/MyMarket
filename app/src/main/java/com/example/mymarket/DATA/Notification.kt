package com.example.mymarket.DATA


data class Notification(
    val userIcon: Int,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)