package ru.barsik.wanttohelp.ui.profile

import android.app.Service
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.content.res.AppCompatResources
import ru.barsik.wanttohelp.R

class AlarmDialogAdapter(
    private val ctx: Context,
    @LayoutRes private val layoutResource: Int,
    private val items: List<Pair<String, Int>>
) : ArrayAdapter<Pair<String, Int>>(ctx, layoutResource, items) {

    private val inflater = ctx.getSystemService(Service.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(layoutResource, null)
        val textViewItem = view.findViewById<TextView>(R.id.tv_item)
        textViewItem.text = items[position].first
        view.findViewById<ImageView>(R.id.iv_item_icon)
            .setImageDrawable(AppCompatResources.getDrawable(ctx, items[position].second))
        return view
    }
}