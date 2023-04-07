package ru.barsik.wanttohelp.ui.news

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.barsik.domain.model.Event
import ru.barsik.wanttohelp.App
import ru.barsik.wanttohelp.R
import ru.barsik.wanttohelp.databinding.FragmentNewsBinding
import ru.barsik.wanttohelp.ui.MainActivity
import ru.barsik.wanttohelp.ui.event_info.EventInfoFragment
import ru.barsik.wanttohelp.ui.news.filternews.FilterNewsFragment
import ru.barsik.wanttohelp.ui.search.UsualBaseFragment
import ru.barsik.wanttohelp.util.NewsDiffUtil
import javax.inject.Inject

class NewsFragment : UsualBaseFragment() {

    private val TAG = "NewsFragment"
    private lateinit var binding: FragmentNewsBinding

    @Inject
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as App).appComponent.inject(this)
        setFragmentResultListener("filter") { _, bundle ->
            if (!bundle.isEmpty) {
                val categoriesIdList =
                    bundle.getIntegerArrayList(FilterNewsFragment.BUNDLE_CATEGORIES_ID_LIST)
                if (categoriesIdList != null) {
                    viewModel.setFilterCategories(categoriesIdList)
                }
            }
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvNews.layoutManager = LinearLayoutManager(context)
        binding.rvNews.adapter = NewsEventsAdapter(emptyList())
        viewModel.getEventListLD().observe(requireActivity()) {
            binding.pbNews.visibility = View.GONE
            binding.rvNews.visibility = View.VISIBLE
            (binding.rvNews.adapter as NewsEventsAdapter).setData(it)
            updateNewsBadge()
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

    private fun updateNewsBadge() {
        (requireActivity() as MainActivity).updateNewsBadge(
            viewModel.getEventListLD().value?.count { x ->
                !viewModel.getReadIds().contains(x.id)
            } ?: -1
        )
    }

    override fun onResume() {
        super.onResume()
        showBottomNavigation()
    }

    private inner class NewsEventsAdapter(private var itemList: List<Event>) :
        RecyclerView.Adapter<NewsEventsAdapter.EventViewHolder>() {
        private inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.tv_news_title)
            val description: TextView = itemView.findViewById(R.id.tv_news_description)
            val image: ImageView = itemView.findViewById(R.id.news_image)
            val remainTime: TextView = itemView.findViewById(R.id.tv_news_remain_time)
            var eventId: Int = -1
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
            holder.eventId = itemList[position].id

            holder.itemView.setOnClickListener {
                viewModel.readEvent(holder.eventId)
                updateNewsBadge()
                val bundle = Bundle()
                bundle.putInt(EventInfoFragment.EVENT_ID_ARG, holder.eventId)
                navController.navigate(
                    R.id.action_newsFragment_to_eventInfoFragment,
                    bundle
                )
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