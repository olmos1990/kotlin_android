package com.example.myapplication2024kotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2024kotlin.R
import com.example.myapplication2024kotlin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val username = root.findViewById<View>(R.id.username) as TextView
        val password = root.findViewById<View>(R.id.password) as TextView
        val login = root.findViewById<View>(R.id.loginbtn) as Button
        login.setOnClickListener {
            if (username.text.toString() == "admin" && password.text.toString() == "admin") {
                Toast.makeText(activity!!.applicationContext, "correct", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity!!.applicationContext, "incorrect", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}