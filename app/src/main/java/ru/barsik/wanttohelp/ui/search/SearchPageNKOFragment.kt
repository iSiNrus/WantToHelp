package ru.barsik.wanttohelp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ru.barsik.wanttohelp.R
import ru.barsik.wanttohelp.databinding.FragmentSearchPageNkoBinding
import ru.barsik.wanttohelp.ui.BaseFragment

class SearchPageNKOFragment : BaseFragment<SearchViewModel>(SearchViewModel::class.java),
    SearchableFragment {

    private lateinit var binding: FragmentSearchPageNkoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPageNkoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(requireContext(), 1))
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SearchRecyclerAdapter(emptyList(), SearchRecyclerAdapter.TypeOfList.ORGS)
        }
        viewModel.getNkoSearchLD().observe(requireActivity()) { resList ->

            binding.searchContent.isVisible = true
            binding.clPlaceholder.isVisible = false
            if (resList.isEmpty())
                binding.tvResultsOfSearch.text = "Результаты поиска: Ничего не найдено"
            else
                binding.tvResultsOfSearch.text =
                    "Результаты поиска: ${resList.size} организации"

            (binding.recyclerView.adapter as SearchRecyclerAdapter).setData(resList)
        }
    }

    override fun setSearchQuery(query: String) {
        if (query.isEmpty()) {
            binding.searchContent.isVisible = false
            binding.clPlaceholder.isVisible = true
        } else
            viewModel.setNKOQueryFlow(query)
    }

}