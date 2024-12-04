package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Commandes
import com.example.mymarket.R
import com.example.mymarket.adapters.adapterCommandes
import java.sql.Date

class CommandeFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.commande_layout, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_commandes)
         recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val commandes = listOf(
            Commandes(dateCmd = Date(2024, 0, 1), status = "En cours", prixTotal = 250.0, TotalCategory = 3),
            Commandes(dateCmd = Date(2024, 1, 15), status = "Livré", prixTotal = 500.0, TotalCategory = 5),
            Commandes(dateCmd = Date(2024, 2, 20), status = "Annulé", prixTotal = 120.0, TotalCategory = 2),
            Commandes(dateCmd = Date(2024, 3, 10), status = "En attente", prixTotal = 300.0, TotalCategory = 4),
            Commandes(dateCmd = Date(2024, 4, 25), status = "En cours", prixTotal = 400.0, TotalCategory = 3),
            Commandes(dateCmd = Date(2024, 5, 5), status = "Livré", prixTotal = 150.0, TotalCategory = 1),
            Commandes(dateCmd = Date(2024, 6, 30), status = "Annulé", prixTotal = 320.0, TotalCategory = 2),
            Commandes(dateCmd = Date(2024, 7, 12), status = "En cours", prixTotal = 270.0, TotalCategory = 3),
            Commandes(dateCmd = Date(2024, 8, 18), status = "Livré", prixTotal = 500.0, TotalCategory = 4),
            Commandes(dateCmd = Date(2024, 9, 1), status = "Annulé", prixTotal = 210.0, TotalCategory = 2)
        )
        val adapter = adapterCommandes(commandes)
        recyclerView.adapter = adapter

    }
}