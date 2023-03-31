package ru.barsik.wanttohelp.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

abstract class BaseFragment<T : AndroidViewModel>(
    private val viewModelClazz: Class<T>
) : Fragment() {
    protected val navController: NavController by lazy { requireNavController() }

    protected val viewModel: T by lazy { ViewModelProvider(this)[viewModelClazz] }
    private fun requireNavController(): NavController {
        return (requireActivity() as MainActivity).getNavController()
    }

}