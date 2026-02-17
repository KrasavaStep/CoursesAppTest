package com.example.coursesapp.presentation.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.coursesapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    val homeViewModel: HomeViewModel by viewModels()

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

        homeViewModel.state.observe(viewLifecycleOwner) { state ->
            if (state.isLoading) {
                Log.d("TEST_LOAD_DATA", "Идёт загрузка")
            }
            else {
                Log.d("TEST_LOAD_DATA", "Загрузка окончена")
            }
            if (!state.errorBody.isEmpty()) {
                Log.d("TEST_LOAD_DATA", "error = ${state.errorBody} ${state.errorCode}")
            }
            if (!state.data.isEmpty()) {
                Log.d("TEST_LOAD_DATA", "data = ${state.data}")
            }
            if (!state.exception.isEmpty()) {
                Log.d("TEST_LOAD_DATA", "exc = ${state.exception}")
            }
        }

    }
}