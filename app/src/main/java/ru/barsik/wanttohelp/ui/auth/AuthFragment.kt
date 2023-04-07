package ru.barsik.wanttohelp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import ru.barsik.wanttohelp.App
import ru.barsik.wanttohelp.R
import ru.barsik.wanttohelp.databinding.FragmentAuthBinding
import ru.barsik.wanttohelp.ui.BaseFragment
import ru.barsik.wanttohelp.ui.search.UsualBaseFragment
import javax.inject.Inject

class AuthFragment : UsualBaseFragment() {
    private lateinit var binding: FragmentAuthBinding

    @Inject
    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
    }

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