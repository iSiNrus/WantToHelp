package ru.barsik.wanttohelp.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.google.android.material.tabs.TabLayoutMediator
import ru.barsik.wanttohelp.R
import ru.barsik.wanttohelp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val TAG = "SearchFragment"
    private lateinit var binding: FragmentSearchBinding
    private lateinit var pagerAdapter: SearchPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pagerAdapter = SearchPagerAdapter(this)
        binding.viewPager2.adapter = pagerAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(TAG, "onQueryTextSubmit: GOT IT! $query")
                pagerAdapter.getFragmentByPos(binding.tabLayout.selectedTabPosition).setSearchQuery(query?:"")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "onQueryTextSubmit: chpok: $newText")
                if(newText!=null)
                    pagerAdapter.getFragmentByPos(binding.tabLayout.selectedTabPosition).setSearchQuery(newText)
                return true
            }

        })
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> "По мероприятиям"
                1 -> "По НКО"
                else -> "Unknown"
            }
        }.attach()
    }
}