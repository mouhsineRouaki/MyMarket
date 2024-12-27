package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.example.mymarket.DATA.villeType
import com.example.mymarket.R
import com.example.mymarket.Service.VilleService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.mymarket.Fragements.PanierFragment
import com.example.mymarket.Service.utilisateurService
import com.example.mymarket.Service.villeTypeService

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
        val heures = view.findViewById<TextView>(R.id.timeHours)

        val villesList = VilleService.findAll().toMutableList()
        val ListVilleType = mutableListOf<villeType>()
        for(e in villesList){
            ListVilleType.add(e.ville)
        }

        val user = utilisateurService.getUser()
        val position = villeTypeService.findByPosition(user.ville.ville)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, ListVilleType)
        spinner.adapter = adapter
        spinner.setSelection(position)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val villeTypeSelect = spinner.selectedItem as villeType
                val villeSelected = VilleService.findByNom(villeTypeSelect)
                if (villeSelected != null) {
                    heures.text = convertMillisToTime(villeSelected.timeLaivrison)
                } else {
                    heures.text = "Aucune donnée disponible"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val villeTypeSelect = spinner.selectedItem as villeType
        val villeSelected = VilleService.findByNom(villeTypeSelect)
        if (villeSelected != null) {
            heures.text =convertMillisToTime(villeSelected.timeLaivrison)
        }

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
    fun convertMillisToTime(millis: Long): String {
        val totalSeconds = millis / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return String.format("%02dH :%02dM :%02dS", hours, minutes, seconds)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }
}
