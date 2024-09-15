package com.example.todoapimvp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapimvp.R
import com.example.todoapimvp.databinding.FragmentSvgBinding
import com.example.todoapimvp.model.repository.repo_implement.RepositoryImpl

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "svg_number"
private const val ARG_PARAM2 = "title_string"

/**
 * A simple [Fragment] subclass.
 * Use the [SvgFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SvgFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding:FragmentSvgBinding? = null
    private val binding get() =_binding
    private lateinit var comunicator: Comunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.i("todoMVP", "SvgFragment param1 = $param1 ")
        Log.i("todoMVP", "SvgFragment param2 = $param2 ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSvgBinding.inflate(inflater,container,false)
        val imageId = param1!!.toInt()-1
        val imageFragment = RepositoryImpl.svgArray[imageId]
        binding!!.ivSvgTodo.setImageResource(imageFragment)
        comunicator = activity as Comunicator

        binding!!.ivSvgTodo.setOnClickListener {
            comunicator.backFragmentTitle(param2.toString())
        }
        return binding!!.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SvgFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SvgFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}