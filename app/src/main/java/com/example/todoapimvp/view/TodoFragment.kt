package com.example.todoapimvp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapimvp.R
import com.example.todoapimvp.databinding.FragmentTodoBinding
import com.example.todoapimvp.model.Todo

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "svg_number"
private const val ARG_PARAM2 = "title_string"

/**
 * A simple [Fragment] subclass.
 * Use the [TodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding:FragmentTodoBinding? = null
    private val binding get() = _binding!!
    private var svgId:Int = 1
    private var title:String? = "null"
    private lateinit var comunicator: Comunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.i("todoMVP", "todoFragment OnCreate() param1 : $param1")
        Log.i("todoMVP", "todoFragment OnCreate() param2 : $param2")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTodoBinding.inflate(inflater,container,false)
        comunicator = activity as Comunicator
        binding.tvFragment.setOnClickListener {
            comunicator.passDataCom(svgId.toString(),title!!)
        }
        if (param2 != null)  binding.tvFragment.text = param2

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TodoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun setData(todo: Todo) {
        svgId = todo.userId
        title = todo.title
        binding.tvFragment.text  = todo.title
        Log.i("todoMVP", " data class todo = $todo")
    }
}