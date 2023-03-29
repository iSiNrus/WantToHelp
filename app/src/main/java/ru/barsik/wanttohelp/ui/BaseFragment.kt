package ru.barsik.wanttohelp.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<T: AndroidViewModel>(private val viewModelClazz: Class<T>) : Fragment()  {

    protected val viewModel : T by lazy { ViewModelProvider(this)[viewModelClazz] }

}