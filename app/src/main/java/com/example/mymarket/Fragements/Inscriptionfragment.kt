package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mymarket.DATA.utilisateur
import com.example.mymarket.DATA.villeType
import com.example.mymarket.R
import com.example.mymarket.Service.VilleService
import com.example.mymarket.Service.utilisateurService

class Inscriptionfragment : Fragment() {

    private lateinit var villesList: List<villeType>
    private lateinit var selectedVille: villeType
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.inscription_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nomUser = view.findViewById<EditText>(R.id.nomUser)
        val prenomUser = view.findViewById<EditText>(R.id.PrenomUser)
        val dateNaissance = view.findViewById<EditText>(R.id.dateNaissance)
        val retour = view.findViewById<ImageButton>(R.id.retour)
        val radioGroup = view.findViewById<RadioGroup>(R.id.RadioGroup)
        val checkBoxConditions = view.findViewById<CheckBox>(R.id.condition)
        val buttonInscription = view.findViewById<Button>(R.id.buttonInscription)
        val villeSpinner = view.findViewById<Spinner>(R.id.ville)
        val linear = view.findViewById<LinearLayout>(R.id.fragment_container2)

        villesList = listOf(
            villeType.Safi, villeType.CasaBlanca,
            villeType.Tanger, villeType.Agadir
        )

        val adapter = object : ArrayAdapter<villeType>(
            requireContext(), android.R.layout.simple_spinner_item, villesList
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.gravity = Gravity.CENTER
                textView.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.inputTextColor)
                )
                return view
            }

            override fun getDropDownView(
                position: Int, convertView: View?, parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.gravity = Gravity.CENTER
                textView.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.inputTextColor)
                )
                return view
            }
        }
        villeSpinner.adapter = adapter
        villeSpinner.setSelection(0)
        selectedVille = villesList[0]

        villeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedVille = villesList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        retour.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.sortie_animations,
                    R.anim.sortie_animations,
                    R.anim.pop_enter_animations,
                    R.anim.pop_sortie_animations
                )
                .replace(R.id.fragmentContainer, WelcomeFragment())
                .commit()
        }

        buttonInscription.setOnClickListener {
            if (validateForm(
                    nomUser.text.toString(),
                    prenomUser.text.toString(),
                    dateNaissance.text.toString(),
                    radioGroup,
                    checkBoxConditions
                )
            ) {
                linear.visibility = View.GONE

                val fragmentB = emailAndPasswordFragement()
                val fragmentC = ImageInscriptionFragement()
                val bundle = Bundle()
                bundle.putString("nom", nomUser.text.toString())
                fragmentB.arguments = bundle
                fragmentC.arguments = bundle

                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameInscription, fragmentB)
                    .commit()

                val ville = VilleService.findByNom(selectedVille)
                if (ville != null) {
                    utilisateurService.create(
                        utilisateur(
                            nomUser.text.toString(),
                            prenomUser.text.toString(),
                            dateNaissance.text.toString(),
                            getSelectedGender(radioGroup),
                            ville
                        )
                    )
                }else{
                    showToast("ville null ")
                    return@setOnClickListener
                }
            }
        }
    }

    private fun validateForm(
        nom: String,
        prenom: String,
        date: String,
        radioGroup: RadioGroup,
        checkBoxConditions: CheckBox
    ): Boolean {
        if (nom.isEmpty() || prenom.isEmpty() || date.isEmpty()) {
            showToast("Tous les champs doivent être remplis")
            return false
        }

        if (radioGroup.checkedRadioButtonId == -1) {
            showToast("Sélectionnez votre sexe")
            return false
        }

        if (!checkBoxConditions.isChecked) {
            showToast("Vous devez accepter les conditions")
            return false
        }

        return true
    }

    private fun getSelectedGender(radioGroup: RadioGroup): String {
        val selectedId = radioGroup.checkedRadioButtonId
        val selectedRadioButton = view?.findViewById<RadioButton>(selectedId)
        return selectedRadioButton?.text.toString()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
