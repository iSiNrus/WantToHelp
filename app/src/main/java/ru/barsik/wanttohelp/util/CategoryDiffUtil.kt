package ru.barsik.wanttohelp.util

import androidx.recyclerview.widget.DiffUtil
import ru.barsik.domain.model.Category
import ru.barsik.domain.model.Event

class CategoryDiffUtil(
    private val oldList: List<Category>,
    private val newList: List<Category>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
}