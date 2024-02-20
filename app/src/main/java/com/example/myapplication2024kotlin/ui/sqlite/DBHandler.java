package com.example.myapplication2024kotlin.ui.sqlite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2024kotlin.databinding.FragmentDBHandlerBinding


import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.android.volley.Request
import org.json.JSONException


class DBHandler : Fragment() {

private var _binding: FragmentDBHandlerBinding? = null

// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {
        val dbhandlerViewModel =
        ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        dbhandlerViewModel.text.observe(viewLifecycleOwner) {
        textView.text = it
        }

//SQLITE
// creating variables for our edittext, button and dbhandler
private var courseNameEdt: EditText? =
        null,  // creating variables for our edittext, button and dbhandler
private var courseTracksEdt: EditText? =
        null,  // creating variables for our edittext, button and dbhandler
private var courseDurationEdt: EditText? =
        null,  // creating variables for our edittext, button and dbhandler
private var courseDescriptionEdt: EditText? = null
private var addCourseBtn: Button? = null
private var dbHandler: DBHandler? = null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing all our variables.
        courseNameEdt = findViewById<EditText>(R.id.idEdtCourseName)
        courseTracksEdt = findViewById<EditText>(R.id.idEdtCourseTracks)
        courseDurationEdt = findViewById<EditText>(R.id.idEdtCourseDuration)
        courseDescriptionEdt = findViewById<EditText>(R.id.idEdtCourseDescription)
        addCourseBtn = findViewById<Button>(R.id.idBtnAddCourse)

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler =
        DBHandler(this@MainActivity)

        // below line is to add on click listener for our add course button.
        addCourseBtn.setOnClickListener(View.OnClickListener { // below line is to get data from all edit text fields.
        val courseName = courseNameEdt.getText().toString()
        val courseTracks = courseTracksEdt.getText().toString()
        val courseDuration = courseDurationEdt.getText().toString()
        val courseDescription = courseDescriptionEdt.getText().toString()

        // validating if the text fields are empty or not.
        if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
        Toast.makeText(this@MainActivity, "Please enter all the data..", Toast.LENGTH_SHORT)
        .show()
        return@OnClickListener
            }

                    // on below line we are calling a method to add new
                    // course to sqlite data and pass all our values to it.
                    dbHandler!!.addNewCourse(courseName, courseDuration, courseDescription, courseTracks)

                    // after adding the data we are displaying a toast message.
                    Toast.makeText(this@MainActivity, "Course has been added.", Toast.LENGTH_SHORT).show()
        courseNameEdt.setText("")
        courseDurationEdt.setText("")
        courseTracksEdt.setText("")
        courseDescriptionEdt.setText("")
        })
        }

        return root
        }

        override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        }
        }