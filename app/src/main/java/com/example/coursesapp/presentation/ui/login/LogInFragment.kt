package com.example.coursesapp.presentation.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coursesapp.R
import com.example.coursesapp.databinding.FragmentHomeBinding
import com.example.coursesapp.databinding.FragmentLogInBinding
import com.example.coursesapp.presentation.MainActivity
import com.google.android.material.textfield.TextInputEditText

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLogInBinding.bind(view)

        val editTextLayout = binding.passwordEdittextLayout
        editTextLayout.editText?.setOnFocusChangeListener { _, hasFocus ->
            editTextLayout.isHintEnabled = !hasFocus
        }

        setupTextWatchers()

    }

    private fun setupTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //TODO
            }

        }

        binding.passwordEdittext.addTextChangedListener(textWatcher)
        binding.emailEdittext.addTextChangedListener(textWatcher)
    }


}