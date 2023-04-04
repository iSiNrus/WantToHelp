package ru.barsik.wanttohelp.ui.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "AuthViewModel"

    private val password = MutableStateFlow("")
    private val login = MutableStateFlow("")
    private val isButtonActive = MutableLiveData<Boolean>(false)

    fun setupChecker() {
        login.combine(password) { l, p ->
         isButtonActive.postValue(l.length > 5 && p.length > 5)
            Log.d(TAG, "getB: ${l.length > 5 && p.length > 5}")
        }.launchIn(viewModelScope)
    }

    fun getIsButtonActiveLD() = isButtonActive

    fun updatePassword(pass: String) {
        password.value = pass
    }

    fun updateLogin(log: String) {
        login.value = log
    }

}