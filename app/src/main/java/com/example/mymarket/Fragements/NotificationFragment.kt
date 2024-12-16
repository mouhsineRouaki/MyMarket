package com.example.mymarket.Fragements

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Notification
import com.example.mymarket.R
import com.example.mymarket.Service.NotificationService
import com.example.mymarket.adapters.NotificationsAdapter

class NotificationFragment:Fragment() {
    lateinit var adapter: NotificationsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewNotifications)

        val notifications = NotificationService.findAll()

        adapter = NotificationsAdapter(notifications)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.startAutoRefresh()
    }

    override fun onPause() {
        super.onPause()
        adapter.stopAutoRefresh()
    }
}