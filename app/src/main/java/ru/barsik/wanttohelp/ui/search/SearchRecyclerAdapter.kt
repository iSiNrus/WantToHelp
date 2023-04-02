package ru.barsik.wanttohelp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.barsik.domain.model.Event
import ru.barsik.wanttohelp.R
import ru.barsik.wanttohelp.util.NewsDiffUtil

class SearchRecyclerAdapter(
    private var listItems: List<Event>,
    private val type: TypeOfList
) :
    RecyclerView.Adapter<SearchRecyclerAdapter.SearchItemViewHolder>() {

    inner class SearchItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_search_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchItemViewHolder(view)
    }

    override fun getItemCount() = listItems.size

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        when(type){
            TypeOfList.EVENTS -> holder.title.text = listItems[position].title
            TypeOfList.ORGS -> holder.title.text = listItems[position].organization
        }

    }

    fun setData(newItemList: List<Event>) {
        val diffUtil = NewsDiffUtil(listItems, newItemList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listItems = newItemList
        diffResult.dispatchUpdatesTo(this)
    }

    enum class TypeOfList {
        EVENTS, ORGS
    }
}