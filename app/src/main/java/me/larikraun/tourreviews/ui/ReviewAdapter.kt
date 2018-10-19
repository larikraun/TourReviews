package me.larikraun.tourreviews.ui

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.larikraun.tourreviews.R
import me.larikraun.tourreviews.databinding.ReviewListItemBinding
import me.larikraun.tourreviews.model.Review

/**
 * Author: Omolara Adejuwon
 * Date: 18/10/2018.
 */
class ReviewAdapter(private val items: ArrayList<Review>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
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

    fun addItem(review: Review) {
        items.add(review)
        notifyItemInserted(items!!.size - 1)
    }

    fun removeItem() {
        items?.removeAt(items.size - 1)
        notifyItemRemoved(items!!.size)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(items[position])
    }

    inner class ViewHolder(val itemBinding: ReviewListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Review) {
            with(itemBinding) {
                if (item.type == "LOADING") {
                    progress.visibility = View.VISIBLE
                    container.visibility = View.GONE
                } else {
                    progress.visibility = View.GONE
                    container.visibility = View.VISIBLE
                    title.text = item.title
                    message.text = item.message
                    details.text = item.author +" , " + item.date
                    rating.rating = item.rating!!.toFloat()
                    val stars = rating.progressDrawable as LayerDrawable
                    stars.getDrawable(2).setColorFilter(Color.parseColor("#ffc601"), PorterDuff.Mode.SRC_ATOP)
                    stars.getDrawable(1).setColorFilter(Color.parseColor("#75777f"), PorterDuff.Mode.SRC_ATOP)
                    stars.getDrawable(0).setColorFilter(Color.parseColor("#75777f"), PorterDuff.Mode.SRC_ATOP)
                }
            }

        }
    }

}
