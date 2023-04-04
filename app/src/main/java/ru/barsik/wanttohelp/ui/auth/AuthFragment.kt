package ru.barsik.wanttohelp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import ru.barsik.wanttohelp.R
import ru.barsik.wanttohelp.databinding.FragmentAuthBinding
import ru.barsik.wanttohelp.ui.BaseFragment

class AuthFragment : BaseFragment<AuthViewModel>(AuthViewModel::class.java) {
    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        hideBottomNavigation()

        viewModel.setupChecker()
        viewModel.getIsButtonActiveLD().observe(requireActivity()){
            binding.btnSubmit.isEnabled = it
        }
        binding.etEmail.addTextChangedListener {
            viewModel.updateLogin(it.toString())
        }
        binding.etPassword.addTextChangedListener {
            viewModel.updatePassword(it.toString())
        }

        binding.btnSubmit.setOnClickListener{
            navController.navigate(R.id.action_authFragment_to_navig_news)
        }
    }
}