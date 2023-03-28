package ru.barsik.simbirpractic.util

import androidx.recyclerview.widget.DiffUtil
import ru.barsik.simbirpractic.entity.Event

class NewsDiffUtil(
    private val oldList: List<Event>,
    private val newList: List<Event>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
}