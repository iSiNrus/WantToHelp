package ru.barsik.wanttohelp.ui.news

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
import androidx.recyclerview.widget.RecyclerView
import ru.barsik.domain.model.Event
import ru.barsik.simbirpractic.util.NewsDiffUtil
import ru.barsik.wanttohelp.R
import ru.barsik.wanttohelp.databinding.FragmentNewsBinding
import ru.barsik.wanttohelp.ui.BaseFragment

class NewsFragment : BaseFragment<NewsViewModel>() {

    private val TAG = "NewsFragment"
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
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
//            val picFirebase = Firebase.storage
//            picFirebase.maxOperationRetryTimeMillis = 2000
//            val ref = picFirebase.getReference(itemList[position].title_img_path)
//            ref.downloadUrl.addOnSuccessListener {
//                Log.d(TAG, "onBindViewHolder: Success")
//                holder.image.load(it)
//            }.addOnFailureListener {
//                Log.d(TAG, "onBindViewHolder: Failure")
//                holder.image.setImageBitmap(
//                    BitmapFactory.decodeStream(
//                        requireContext().resources.assets.open(
//                            itemList[position].title_img_path
//                        )
//                    )
//                )
//            }

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