package com.example.mymarket.Service

import com.example.mymarket.DATA.Notification
import com.example.mymarket.interfaces.IDAO

object NotificationService : IDAO<Notification> {
    private val listPanier= mutableListOf<Notification>()
    override fun create(p: Notification): Boolean {
        return listPanier.add(p)
    }

    override fun update(p: Notification, q:Int): Boolean {
        TODO()
    }

    override fun delete(o: Notification): Boolean {
        return listPanier.remove(o)
    }

    override fun findById(id: Int): Notification? {
        TODO()
    }

    override fun findAll(): MutableList<Notification> {
        return listPanier.toMutableList()
    }
}