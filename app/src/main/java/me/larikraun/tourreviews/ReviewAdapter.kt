package me.larikraun.tourreviews

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.larikraun.tourreviews.databinding.ReviewListItemBinding
import me.larikraun.tourreviews.model.Review

/**
 * Author: Omolara Adejuwon
 * Date: 18/10/2018.
 */
class ReviewAdapter(private val items: List<Review>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = R.layout.review_list_item
        val view = LayoutInflater.from(parent.context)
        val reviewListItemBinding = DataBindingUtil.inflate<ReviewListItemBinding>(view, layoutId, parent, false)
        reviewListItemBinding.root.isFocusable = true
        return ViewHolder(reviewListItemBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(items[position])
    }

    inner class ViewHolder(val itemBinding: ReviewListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Review) {
            with(itemBinding) {
                title.text = item.title
                message.text = item.message
                details.text = item.author
            }
        }
    }

}
