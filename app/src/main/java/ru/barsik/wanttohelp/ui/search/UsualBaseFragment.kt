package ru.barsik.wanttohelp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import ru.barsik.wanttohelp.ui.MainActivity

abstract class UsualBaseFragment : Fragment() {
    protected val navController: NavController by lazy { requireNavController() }

    private fun requireNavController(): NavController {
        return (requireActivity() as MainActivity).getNavController()
    }

    protected fun showBottomNavigation() {
        (requireActivity() as MainActivity).showBottomNavigation(true)
    }

    protected fun hideBottomNavigation() {
        (requireActivity() as MainActivity).showBottomNavigation(false)
    }
}