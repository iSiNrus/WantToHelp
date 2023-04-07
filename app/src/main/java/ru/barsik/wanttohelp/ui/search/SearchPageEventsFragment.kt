package ru.barsik.wanttohelp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ru.barsik.wanttohelp.App
import ru.barsik.wanttohelp.ui.UsualBaseFragment
import ru.barsik.wanttohelp.databinding.FragmentSearchPageEventsBinding
import javax.inject.Inject

class SearchPageEventsFragment : UsualBaseFragment(), SearchableFragment {

    private lateinit var binding: FragmentSearchPageEventsBinding
    private var viewIsReady = false

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPageEventsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewIsReady = true
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(requireContext(), 1))
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SearchRecyclerAdapter(emptyList(), SearchRecyclerAdapter.TypeOfList.EVENTS)
        }
        viewModel.getEventSearchLD().observe(requireActivity()) { resList ->

            binding.searchContent.isVisible = true
            binding.clPlaceholder.isVisible = false
            if (resList.isEmpty())
                binding.tvResultsOfSearch.text = "Результаты поиска: Ничего не найдено"
            else
                binding.tvResultsOfSearch.text =
                    "Результаты поиска: ${resList.size} мероприятия"

            (binding.recyclerView.adapter as SearchRecyclerAdapter).setData(resList)
        }
    }

    override fun setSearchQuery(query: String) {
        if (viewIsReady) {
            if (query.isEmpty()) {
                binding.searchContent.isVisible = false
                binding.clPlaceholder.isVisible = true
            } else
                viewModel.setEventQueryFlow(query)
        }
    }

}