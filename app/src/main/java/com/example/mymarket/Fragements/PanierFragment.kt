package com.example.mymarket.Fragements

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Commandes
import com.example.mymarket.DATA.Notification
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.Service.CommandesService
import com.example.mymarket.Service.NotificationService
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
        val clear = view.findViewById<ImageButton>(R.id.clear)
        clear.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Confirmation de vider panier")
            builder.setMessage("do you want to vider panier")
            builder.setPositiveButton("OK") { dialog, which ->
                val list = PanierService.findAll()
                if(list.isEmpty()){
                    Toast.makeText(requireContext(), "Panier Deja Vide", Toast.LENGTH_SHORT).show()
                }else {
                    PanierService.Clear()
                    adapter.notifyDataSetChanged()
                    NotificationService.create(Notification(R.drawable.clear, "le Panier et vider"))
                }
            }
            builder.setNegativeButton("Annuler",null)
            builder.show()
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.RecyclePanier)
        val id = arguments?.getInt("id")
        totalPanier = view.findViewById(R.id.Total)
        val btnCommande: Button = view.findViewById(R.id.btnCommander)

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val list = PanierService.findAll()
        adapter = adapterPanier(list, this, true) { produit ->
            Toast.makeText(requireContext(), "${produit.nomP} ajouté au panier !", Toast.LENGTH_SHORT).show()
        }

        val listtt = mutableListOf<Produit>()
        listtt.addAll(PanierService.findAll())

        val total = list.sumOf { it.prix * it.quantitePanier } - 0.29

        btnCommande.setOnClickListener {
            if (listtt.isNotEmpty()) {
                val bottomSheetFragment = bottomLayoutFragement()
                bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
            } else {
                Toast.makeText(requireContext(), "Panier vide, commande refusée.", Toast.LENGTH_SHORT).show()
            }
        }

        if (PanierService.findAll().isNotEmpty() && id != null) {
            val copie = PanierService.findAll().toList()
            for (p in copie) {
                val c = if (p.quantite == p.quantitePanier) {
                    p.quantite = 0
                    p.quantite
                } else {
                    p.quantite - p.quantitePanier
                }

                if (p.quantite == 0) {
                    PanierService.delete(p)
                    adapter.notifyDataSetChanged()
                    updateTotal()
                }

                ProduitService.update(p, c)
                PanierService.update(p, c)
            }

            val ville = VilleService.findById(id)
            if (ville != null) {
                CommandesService.create(Commandes(total, listtt, ville))
                val c = CommandesService.findlast()
                NotificationService.create(Notification(R.drawable.commande,"la commande numero ${c.Num} bien ajouter\n En atente de livre"))
                Toast.makeText(requireContext(), "Commande ajoutée.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Commande annulée, ville vide.", Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView.adapter = adapter
        updateTotal()
    }

    fun NotifyAdapter() {
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("DefaultLocale")
    fun updateTotal() {
        var total = 0.0
        val service = PanierService.findAll()
        for (e in service) {
            total += if (e.Promo <= 0) {
                e.prix * e.quantitePanier + 0.29
            } else {
                (e.prix * (1 - e.Promo / 100.0)) * e.quantitePanier + 0.29
            }
        }

        totalPanier.text = String.format("%.2f", total)
    }
}
