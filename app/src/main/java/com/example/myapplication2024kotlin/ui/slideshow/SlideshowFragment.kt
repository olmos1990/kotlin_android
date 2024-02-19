package com.example.myapplication2024kotlin.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2024kotlin.databinding.FragmentSlideshowBinding


import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.android.volley.Request
import org.json.JSONException


class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val url = "https://jsonplaceholder.typicode.com/todos/1"


        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    val userId = response.getInt("userId")
                    val id = response.getInt("id")
                    val title = response.getString("title")
                    val completed = response.getBoolean("completed")
                    textView.text = "$userId\n$id\n$title\n$completed"
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                textView.text = "error"
            }
        )

        val requestQueue: RequestQueue = Volley.newRequestQueue(activity?.applicationContext)
        requestQueue.add(jsonObjectRequest)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}