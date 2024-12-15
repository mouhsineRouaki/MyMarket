package com.example.mymarket.adapters

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Notification
import com.example.mymarket.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class NotificationsAdapter(private val notifications: MutableList<Notification>) :
    RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder>() {

    private val handler = Handler(Looper.getMainLooper())
    private val refreshInterval = 60 * 1000L

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userIcon: ImageView = itemView.findViewById(R.id.userIcon)
        val notificationText: TextView = itemView.findViewById(R.id.notificationText)
        val notificationTime: TextView = itemView.findViewById(R.id.notificationTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.userIcon.setImageResource(notification.userIcon)
        holder.notificationText.text = notification.text
        holder.notificationTime.text = formatTimeAgo(notification.timestamp)
    }

    override fun getItemCount(): Int = notifications.size

    private fun formatTimeAgo(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp

        return when {
            TimeUnit.MILLISECONDS.toMinutes(diff) < 1 -> "Just now"
            TimeUnit.MILLISECONDS.toMinutes(diff) < 60 -> "${TimeUnit.MILLISECONDS.toMinutes(diff)}m ago"
            TimeUnit.MILLISECONDS.toHours(diff) < 24 -> "${TimeUnit.MILLISECONDS.toHours(diff)}h ago"
            else -> SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date(timestamp))
        }
    }

    fun startAutoRefresh() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                notifyDataSetChanged()
                handler.postDelayed(this, refreshInterval)
            }
        }, refreshInterval)
    }

    // Méthode pour arrêter le rafraîchissement automatique
    fun stopAutoRefresh() {
        handler.removeCallbacksAndMessages(null)
    }
}
