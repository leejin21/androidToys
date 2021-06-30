package com.example.newsapp

import android.app.Activity
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScrapScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScrapScreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val resultview: View = inflater.inflate(R.layout.fragment_scrap_screen, container, false)
        val spinner: Spinner = resultview.findViewById(R.id.spinner)
        // 아래에서 첫 인자: this  대신 requireContext()
        ArrayAdapter.createFromResource(
                requireContext(),
                R.array.bg_color_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object: Activity(), AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    // An item was selected. U can retreive the selected item using
                    // parent.getItemAtPosition(pos) : 문자열(리스트 아이템)

                    val scrollview: View = resultview.findViewById(R.id.scrollview)
                    val scrollview_text: TextView = resultview.findViewById(R.id.scroll_text)

                    val black = ContextCompat.getColor(requireActivity(), R.color.black)
                    val white = ContextCompat.getColor(requireActivity(), R.color.white)

                    if (parent?.getItemAtPosition(position) == "black"){
//                        Log.i("position:0", parent?.getItemAtPosition(position).toString())
                        scrollview.setBackgroundColor(black)
                        scrollview_text.setTextColor(white)
                    } else {
//                        Log.i("position:1", parent?.getItemAtPosition(position).toString())
                        scrollview.setBackgroundColor(white)
                        scrollview_text.setTextColor(black)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Another interface callback
                    Log.i("nothing", "아무것도 ㅇ아님")
                }
            }
        }



        return resultview
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScrapScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScrapScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}