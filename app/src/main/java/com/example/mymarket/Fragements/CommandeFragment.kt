package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Commandes
import com.example.mymarket.R
import com.example.mymarket.Service.CommandesService
import com.example.mymarket.adapters.adapterCommandes
import java.sql.Date

class CommandeFragment : Fragment() {
    private var commandes: MutableList<Commandes> = mutableListOf()
    private lateinit var selectedTextView: TextView
    private lateinit var selectedView: View
    private lateinit var adapter: adapterCommandes

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.commande_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_commandes)
        val underAllView = view.findViewById<View>(R.id.under_all)
        val underEncoursView = view.findViewById<View>(R.id.under_encours)
        val underEnattentsView = view.findViewById<View>(R.id.under_enattents)
        val underLivreView = view.findViewById<View>(R.id.under_livre)
        val allTextView = view.findViewById<TextView>(R.id.all)
        val encoursTextView = view.findViewById<TextView>(R.id.encours)
        val enattentsTextView = view.findViewById<TextView>(R.id.enattents)
        val livreTextView = view.findViewById<TextView>(R.id.livre)

        // Configuration initiale
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        commandes = CommandesService.findAll()
        selectedTextView = allTextView
        selectedView = underAllView
        selectedView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))

        adapter = adapterCommandes(commandes, parentFragmentManager)
        recyclerView.adapter = adapter


        allTextView.setOnClickListener {
            commandes.clear()
            commandes.addAll(CommandesService.findAll())
            updateSelection(allTextView, underAllView, listOf(underEncoursView, underEnattentsView, underLivreView))
        }

        encoursTextView.setOnClickListener {
            commandes.clear()
            commandes.addAll(CommandesService.findAll().filter { it.status == "EN Cours" }.toMutableList())
            updateSelection(encoursTextView, underEncoursView, listOf(underAllView, underEnattentsView, underLivreView))
        }

        enattentsTextView.setOnClickListener {
            commandes.clear()
            commandes.addAll(CommandesService.findAll().filter { it.status == "En attente" }.toMutableList())
            updateSelection(enattentsTextView, underEnattentsView, listOf(underAllView, underEncoursView, underLivreView))
        }

        livreTextView.setOnClickListener {
            commandes.clear()
            commandes.addAll(CommandesService.findAll().filter { it.status == "Livré" }.toMutableList())
            updateSelection(livreTextView, underLivreView, listOf(underAllView, underEncoursView, underEnattentsView))
        }
    }

    private fun updateSelection(
        newSelectedTextView: TextView,
        newSelectedView: View,
        otherViews: List<View>
    ) {
        // Réinitialiser la vue et couleur précédente
        selectedView.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))

        // Mettre à jour la nouvelle sélection
        newSelectedView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
        selectedTextView = newSelectedTextView
        selectedView = newSelectedView

        // Réinitialiser les autres vues
        otherViews.forEach { it.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent)) }

        // Mise à jour de l'adaptateur
        adapter.notifyDataSetChanged()
    }
}
