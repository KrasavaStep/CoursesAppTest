package com.example.coursesapp.presentation.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coursesapp.R
import com.example.coursesapp.databinding.FragmentHomeBinding
import com.example.coursesapp.presentation.ui.home.CoursesListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        val adapter = CoursesListAdapter()
        binding.coursesRecyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.coursesRecyclerView.layoutManager = layoutManager

        homeViewModel.sendEvent(HomeEvent.GetCoursesEvent())

        homeViewModel.state.observe(viewLifecycleOwner) { state ->
            if (state.isLoading) {
                binding.progressCircular.visibility = View.VISIBLE
            } else {
                binding.progressCircular.visibility = View.GONE
            }
            if (!state.errorBody.isEmpty()) {
                binding.serverErrorView.visibility = View.VISIBLE
                binding.errorCodeTxt.text = state.errorCode.toString()
                binding.errorMsgTxt.text = state.errorBody
            }
            else {
                binding.serverErrorView.visibility = View.GONE
            }
            if (!state.data.isEmpty()) {
                binding.emptyListMsg.visibility = View.GONE
                adapter.setData(state.data)
            }
            else {
                binding.emptyListMsg.visibility = View.VISIBLE
            }
            if (!state.exception.isEmpty()) {
                binding.exceptionMsg.visibility = View.VISIBLE
                Log.d("TEST_LOAD_DATA", "exc = ${state.exception}")
            }
            else {
                binding.exceptionMsg.visibility = View.GONE
            }
        }

        binding.filterByDateBtn.setOnClickListener {
            adapter.updateData()
        }

    }
}