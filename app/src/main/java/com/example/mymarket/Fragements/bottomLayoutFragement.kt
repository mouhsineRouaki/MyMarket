package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.FragmentTransaction
import com.example.mymarket.DATA.villeType
import com.example.mymarket.R
import com.example.mymarket.Service.VilleService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.mymarket.Fragements.PanierFragment

class bottomLayoutFragement : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bootom_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner = view.findViewById<Spinner>(R.id.addressInput)
        val button = view.findViewById<Button>(R.id.saveButton)

        val villesList = listOf(
            villeType.Safi, villeType.CasaBlanca,
            villeType.Tanger, villeType.Agadir
        )
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, villesList)
        spinner.adapter = adapter

        button.setOnClickListener {
            val selectedCity = spinner.selectedItem as villeType
            val V = VilleService.findAll().find { it.ville == selectedCity }

            if (V != null) {

                val fragment = PanierFragment()
                val bundle = Bundle()
                bundle.putInt("id", V.id)
                fragment.arguments = bundle

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit()
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }
}
