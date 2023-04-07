package ru.barsik.wanttohelp.ui.categories

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import ru.barsik.domain.model.Category
import ru.barsik.wanttohelp.App
import ru.barsik.wanttohelp.R
import ru.barsik.wanttohelp.databinding.FragmentCategoriesBinding
import ru.barsik.wanttohelp.ui.BaseFragment
import ru.barsik.wanttohelp.ui.search.UsualBaseFragment
import ru.barsik.wanttohelp.util.CategoryDiffUtil
import javax.inject.Inject

class CategoriesFragment : UsualBaseFragment() {

    private lateinit var binding: FragmentCategoriesBinding

    @Inject
    lateinit var viewModel: CategoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.categoryRecyclerview.adapter = CategoryItemAdapter(emptyList())
        binding.categoryRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.getCategories()
        viewModel.getCategoriesLD().observe(requireActivity()){
            binding.progressBarCategories.visibility = View.GONE
            binding.categoryRecyclerview.visibility = View.VISIBLE
            (binding.categoryRecyclerview.adapter as CategoryItemAdapter).setData(it)
        }
    }

    private inner class CategoryItemAdapter(private var categoryList: List<Category>) :
        RecyclerView.Adapter<CategoryItemAdapter.CategoryViewHolder>() {

        inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val image: ImageView = itemView.findViewById(R.id.iv_cat_item)
            val title: TextView = itemView.findViewById(R.id.tv_cat_item_title)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
            return CategoryViewHolder(itemView)
        }

        override fun getItemCount(): Int = categoryList.size

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            holder.title.text = categoryList[position].title
            holder.image.setImageBitmap(
                BitmapFactory.decodeByteArray(
                    categoryList[position].imageByteArray,
                    0,
                    categoryList[position].imageByteArray?.size ?: 0
                )
            )
        }
        fun setData(newItemList: List<Category>) {
            val diffUtil = CategoryDiffUtil(categoryList, newItemList)
            val diffResult = DiffUtil.calculateDiff(diffUtil)
            categoryList = newItemList
            diffResult.dispatchUpdatesTo(this)
        }

    }

}