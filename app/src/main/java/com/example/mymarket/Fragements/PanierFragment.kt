package com.example.mymarket.Fragements

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Commandes
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.Service.CommandesService
import com.example.mymarket.Service.PanierService
import com.example.mymarket.Service.ProduitService
import com.example.mymarket.Service.VilleService
import com.example.mymarket.adapters.adapterPanier

class PanierFragment : Fragment() {

    lateinit var totalPanier: TextView
    lateinit var adapter: adapterPanier
    lateinit var produit: Produit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.panier_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.RecyclePanier)
        val id = arguments?.getInt("id")
        totalPanier = view.findViewById(R.id.Total)
        val btnCommande: Button = view.findViewById(R.id.btnCommander)

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val list = PanierService.findAll()
        adapter = adapterPanier(list, this, true) { produit ->
            Toast.makeText(requireContext(), "${produit.nomP} ajouté au panier !", Toast.LENGTH_SHORT).show()
        }

        // List to hold products for command
        val listtt = mutableListOf<Produit>()
        listtt.addAll(PanierService.findAll())

        // Calculate the total amount (without applying any discount yet)
        val total = list.sumOf { it.prix * it.quantitePanier } - 0.29

        // Command button click listener
        btnCommande.setOnClickListener {
            if (listtt.isNotEmpty()) {
                val bottomSheetFragment = bottomLayoutFragement()
                bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
            } else {
                Toast.makeText(requireContext(), "Panier vide, commande refusée.", Toast.LENGTH_SHORT).show()
            }
        }

        // Process order when Panier has products and id is not null
        if (PanierService.findAll().isNotEmpty() && id != null) {
            val copie = PanierService.findAll().toList()
            for (p in copie) {
                val c = if (p.quantite == p.quantitePanier) {
                    p.quantite = 0
                    p.quantite
                } else {
                    p.quantite - p.quantitePanier
                }

                // Remove products with zero quantity from the panier
                if (p.quantite == 0) {
                    PanierService.delete(p)
                    adapter.notifyDataSetChanged()  // Refresh the adapter
                    updateTotal() // Refresh the total after deletion
                }

                // Update product stock and panier
                ProduitService.update(p, c)
                PanierService.update(p, c)
            }

            // Create the command
            val ville = VilleService.findById(id)
            if (ville != null) {
                CommandesService.create(Commandes(total, listtt, ville))
                Toast.makeText(requireContext(), "Commande ajoutée.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Commande annulée, ville vide.", Toast.LENGTH_SHORT).show()
            }
        }

        // Set the recycler view adapter and update the total amount
        recyclerView.adapter = adapter
        updateTotal() // Update the total price on view creation
    }

    // Function to notify the adapter that the data has changed
    fun NotifyAdapter() {
        adapter.notifyDataSetChanged()
    }

    // Update the total price of the panier
    @SuppressLint("DefaultLocale")
    fun updateTotal() {
        var total = 0.0
        val service = PanierService.findAll()
        for (e in service) {
            // Calculate the total price for each product considering any promo and fixed discount
            total += if (e.Promo <= 0) {
                e.prix * e.quantitePanier - 0.29
            } else {
                (e.prix * (1 - e.Promo / 100.0)) * e.quantitePanier - 0.29
            }
        }

        // Update the total in the UI
        totalPanier.text = String.format("%.2f", total)
    }
}
