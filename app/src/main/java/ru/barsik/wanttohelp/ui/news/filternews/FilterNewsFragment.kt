package ru.barsik.wanttohelp.ui.news.filternews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.barsik.wanttohelp.databinding.FragmentFilterNewsBinding
import ru.barsik.wanttohelp.ui.BaseFragment

class FilterNewsFragment : BaseFragment<FilterNewsViewModel>(FilterNewsViewModel::class.java) {

    private lateinit var binding: FragmentFilterNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterNewsBinding.inflate(layoutInflater)
        return binding.root
    }

}