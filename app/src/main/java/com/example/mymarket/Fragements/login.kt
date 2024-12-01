package com.example.mymarket.Fragements

<<<<<<< HEAD
import android.content.Intent
=======
>>>>>>> 8fb32b5a486a16a9f4d272a477d3d4793f6c2735
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.mymarket.AcceuilActivity
=======
import androidx.fragment.app.Fragment
>>>>>>> 8fb32b5a486a16a9f4d272a477d3d4793f6c2735
import com.example.mymarket.R

class login : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_activity, container, false)
    }
<<<<<<< HEAD
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)
        val btnLogin = view.findViewById<Button>(R.id.buttonLogin)
        btnLogin.setOnClickListener{
            startActivity(Intent(requireContext(),AcceuilActivity::class.java))
        }
    }
}
=======
}
>>>>>>> 8fb32b5a486a16a9f4d272a477d3d4793f6c2735
