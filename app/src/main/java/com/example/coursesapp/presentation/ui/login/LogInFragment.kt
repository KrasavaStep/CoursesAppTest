package com.example.coursesapp.presentation.ui.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.coursesapp.R
import com.example.coursesapp.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue
import androidx.core.net.toUri

@AndroidEntryPoint
class LogInFragment : Fragment() {

    val loginViewModel: LoginViewModel by viewModels()

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

        val passwordTextLayout = binding.passwordEdittextLayout
        val emailTextLayout = binding.emailEdittextLayout

        emailTextLayout.editText?.filters = arrayOf(getInputFilter())

        setupTextWatchers()
        val enterBtn = binding.enterBtn
        loginViewModel.loginState.observe(viewLifecycleOwner) { state ->
            if (state.isEmailValid && state.isPasswordValid) {
                enterBtn.isEnabled = true
                passwordTextLayout.isErrorEnabled = false
                emailTextLayout.isErrorEnabled = false
            } else {
                enterBtn.isEnabled = false
                passwordTextLayout.isErrorEnabled = true
                emailTextLayout.isErrorEnabled = true
                if (!state.isPasswordValid) {
                    passwordTextLayout.error = "Пароль должен содержать минимум 6 символов"
                }
                if (!state.isEmailValid) {
                    emailTextLayout.error = "Введите корректный Email"
                }
            }
        }

        enterBtn.setOnClickListener {
            navigateToHomeScreen()
        }

        binding.okBtn.setOnClickListener {
            openUrl(requireContext(), "https://ok.ru/")
        }

        binding.vkBtn.setOnClickListener {
            openUrl(requireContext(), "https://vk.com/")
        }

    }

    private fun setupTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateForm()
            }

        }

        binding.passwordEdittext.addTextChangedListener(textWatcher)
        binding.emailEdittext.addTextChangedListener(textWatcher)
    }

    private fun validateForm() {
        val email = binding.emailEdittext.text?.toString() ?: ""
        val password = binding.passwordEdittext.text?.toString() ?: ""

        loginViewModel.sendEvent(LoginEvent.ValidateDataEvent(email, password))
    }


    private fun navigateToHomeScreen() {
        val navController = findNavController()
        navController.navigate(
            R.id.navigation_home,
            null,
            NavOptions.Builder().setPopUpTo(R.id.navigation_login, true).build()
        )
    }

    private fun openUrl(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        context.startActivity(intent)
    }


    private fun getInputFilter() = InputFilter { source, start, end, dest, dstart, dend ->
        for (i in start until end) {
            if (Character.UnicodeBlock.of(source[i]) == Character.UnicodeBlock.CYRILLIC) {
                return@InputFilter ""
            }
        }
        null
    }

}