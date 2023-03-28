package ru.barsik.wanttohelp.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<T: ViewModel> : Fragment()  {

    protected val viewModel by lazy { ViewModelProvider(this)[getGenericsClass()] }

    private inline fun <reified T: ViewModel> getGenericsClass(): Class<T>{
        return T::class.java
    }

}