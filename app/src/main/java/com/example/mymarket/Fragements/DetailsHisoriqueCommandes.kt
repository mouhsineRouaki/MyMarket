package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.R
import com.example.mymarket.Service.CommandesService
import com.example.mymarket.adapters.adapterCommandes
import com.example.mymarket.adapters.adapterPanier
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailsHisoriqueCommandes: BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_historique_commandes, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycleView = view.findViewById<RecyclerView>(R.id.RecyclePanier)
        recycleView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val total = view.findViewById<TextView>(R.id.Total)
        val date = view.findViewById<TextView>(R.id.Time_commande)
        val totalCat = view.findViewById<TextView>(R.id.Total_category)
        val status = view.findViewById<TextView>(R.id.status)
        val data = arguments?.getInt("num")
        if (data != null) {
            val commandeFinded = CommandesService.findById(data)
            if (commandeFinded != null) {
                total.text = String.format("%.2f DH", commandeFinded.prixTotal)
                date.text = "${commandeFinded.dateCmd} "
                totalCat.text = "${commandeFinded.TotalCategory} "
                status.text = "${commandeFinded.status} "
                recycleView.adapter= adapterPanier(commandeFinded.ListProduits.toMutableList(),PanierFragment(),false){produit ->
                    Toast.makeText(requireContext(),"${produit.nomP}",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "commande null ", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onStart() {
        super.onStart()

        val dialog = dialog
        if (dialog != null) {
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }
}