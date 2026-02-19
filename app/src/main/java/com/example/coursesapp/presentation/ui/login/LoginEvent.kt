package com.example.coursesapp.presentation.ui.login


sealed class LoginEvent {

    class ValidateDataEvent(val email: String, val password: String): LoginEvent()

}