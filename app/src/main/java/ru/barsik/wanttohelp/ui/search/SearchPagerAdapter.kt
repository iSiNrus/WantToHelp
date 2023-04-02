package ru.barsik.wanttohelp.ui.search

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SearchPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment)  {
    private val pageEvents = SearchPageEventsFragment()
    private val pageNKO = SearchPageNKOFragment()
    override fun getItemCount(): Int {
        return 2
    }

    fun getFragmentByPos(pos : Int) : SearchableFragment {
        return if(pos==0) pageEvents
        else pageNKO
    }
    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> pageEvents
            1 -> pageNKO
            else -> throw Exception("Unsupported page position")
        }
    }
}