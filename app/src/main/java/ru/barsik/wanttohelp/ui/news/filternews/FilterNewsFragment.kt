package ru.barsik.wanttohelp.ui.news.filternews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.barsik.domain.model.Category
import ru.barsik.wanttohelp.R
import ru.barsik.wanttohelp.databinding.FragmentFilterNewsBinding
import ru.barsik.wanttohelp.ui.BaseFragment
import ru.barsik.wanttohelp.util.CategoryDiffUtil

class FilterNewsFragment : BaseFragment<FilterNewsViewModel>(FilterNewsViewModel::class.java) {

    private lateinit var binding: FragmentFilterNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCategories.adapter = FilterAdapter(emptyList())

        viewModel.getCategories()
        viewModel.getCategoriesListLD().observe(requireActivity()){
            (binding.rvCategories.adapter as FilterAdapter).setData(it)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private inner class FilterAdapter(private var categoryItemsList: List<Category>) :
        RecyclerView.Adapter<FilterAdapter.CategoryFilterViewHolder>() {

        private inner class CategoryFilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.tv_category_title)
            val switch: SwitchCompat = itemView.findViewById(R.id.switch_category)
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): CategoryFilterViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.category_filter_item, parent, false)
            return CategoryFilterViewHolder(itemView)
        }

        override fun getItemCount() = categoryItemsList.size

        override fun onBindViewHolder(holder: CategoryFilterViewHolder, position: Int) {
            holder.title.text = categoryItemsList[position].title
            holder.switch.setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("meeeee", "onBindViewHolder: $isChecked")
                if(isChecked) viewModel.addCategoryToResult(categoryItemsList[position])
                else viewModel.removeCategoryFromResult(categoryItemsList[position])
            }
        }

        fun setData(newItemList: List<Category>) {
            val diffUtil = CategoryDiffUtil(categoryItemsList, newItemList)
            val diffResult = DiffUtil.calculateDiff(diffUtil)
            categoryItemsList = newItemList
            diffResult.dispatchUpdatesTo(this)


        }

    }

}