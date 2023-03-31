package ru.barsik.wanttohelp.ui.news

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.barsik.domain.model.Event
import ru.barsik.wanttohelp.R
import ru.barsik.wanttohelp.databinding.FragmentNewsBinding
import ru.barsik.wanttohelp.ui.BaseFragment
import ru.barsik.wanttohelp.util.NewsDiffUtil

class NewsFragment : BaseFragment<NewsViewModel>(NewsViewModel::class.java) {

    private val TAG = "NewsFragment"
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNews.layoutManager = LinearLayoutManager(context)
        binding.rvNews.adapter = NewsEventsAdapter(emptyList())
        viewModel.getEventListLD().observe(requireActivity()) {
            binding.pbNews.visibility = View.GONE
            binding.rvNews.visibility = View.VISIBLE
            (binding.rvNews.adapter as NewsEventsAdapter).setData(it)
        }
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_filter ->
                    navController.navigate(R.id.action_newsFragment_to_filterNewsFragment)
            }
            true
        }
        viewModel.getAllEvents()
    }

    private inner class NewsEventsAdapter(private var itemList: List<Event>) :
        RecyclerView.Adapter<NewsEventsAdapter.EventViewHolder>() {
        private inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.tv_news_title)
            val description: TextView = itemView.findViewById(R.id.tv_news_description)
            val image: ImageView = itemView.findViewById(R.id.news_image)
            val remainTime: TextView = itemView.findViewById(R.id.tv_news_remain_time)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.news_item_card, parent, false)
            return EventViewHolder(view)
        }

        override fun getItemCount() = itemList.size
        override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
            holder.title.text = itemList[position].title
            holder.description.text = itemList[position].description
            holder.remainTime.text = "Not supported yet"
            holder.image.setImageBitmap(
                BitmapFactory.decodeByteArray(
                    itemList[position].imageByteArray,
                    0,
                    itemList[position].imageByteArray?.size ?: 0
                )
            )

            holder.itemView.setOnClickListener {
//                with(requireActivity() as MainActivity) {
//                    newsUpdaterFlow.value = (itemList[position].id)
//                    switchFragment(
//                        EventInfoFragment(itemList[position]),
//                        addBackStack = true,
//                        showBottomNavigation = false
//                    )
//                }
            }
        }

        fun setData(newItemList: List<Event>) {
            val diffUtil = NewsDiffUtil(itemList, newItemList)
            val diffResult = DiffUtil.calculateDiff(diffUtil)
            itemList = newItemList
            diffResult.dispatchUpdatesTo(this)


        }

    }

}