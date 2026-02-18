package com.example.coursesapp.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.ValidateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateUseCase: ValidateUseCase
): ViewModel() {

    private var _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    init {
        _loginState.value = LoginState(
            isEmailValid = false,
            isPasswordValid = false
        )
    }

    fun sendEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.ValidateDataEvent -> {
                validateData(event)
            }
        }
    }

    private fun validateData(event: LoginEvent.ValidateDataEvent) {
        val result = validateUseCase.execute(event.email, event.password)
        _loginState.value = _loginState.value?.copy(
            isEmailValid = result.isEmailValid,
            isPasswordValid = result.isPasswordValid
        )
    }

}