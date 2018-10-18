package me.larikraun.tourreviews.ui

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.content_review.*
import me.larikraun.tourreviews.R
import me.larikraun.tourreviews.ReviewAdapter
import me.larikraun.tourreviews.dagger.AppMainComponent
import me.larikraun.tourreviews.dagger.ContextModule
import me.larikraun.tourreviews.dagger.DaggerAppMainComponent
import me.larikraun.tourreviews.databinding.ActivityReviewBinding
import me.larikraun.tourreviews.model.Review
import me.larikraun.tourreviews.network.ReviewRepository
import me.larikraun.tourreviews.utils.ConnectionUtil
import me.larikraun.tourreviews.utils.InsetDividerDecoration
import javax.inject.Inject


class ReviewActivity : AppCompatActivity(), LifecycleOwner {
    @Inject
    lateinit var repo: ReviewRepository
    @Inject
    lateinit var connectionUtil: ConnectionUtil
    lateinit var mComponent: AppMainComponent
    lateinit var viewModelFactory: ReviewViewModelFactory
    lateinit var adapter: ReviewAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var mBinding: ActivityReviewBinding
    var isLoading = false
    var hasMore = false
    var page = 0
    var count = 10
    var reviewsList = ArrayList<Review>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_review)
        setSupportActionBar(toolbar)

        mComponent = DaggerAppMainComponent.builder()
                .contextModule(ContextModule(this))
                .build()
        mComponent.inject(this)
        viewModelFactory = ReviewViewModelFactory(repo, connectionUtil)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ReviewViewModel::class.java)

        mBinding.viewModel = viewModel
        mBinding.executePendingBindings()
        viewModel.fetchReviewsList(count, page)

        adapter = ReviewAdapter(reviewsList)
        layoutManager = LinearLayoutManager(this)
        reviews_list.layoutManager = layoutManager
        reviews_list.adapter = adapter
        reviews_list.addItemDecoration(InsetDividerDecoration(resources.getDimensionPixelSize(R.dimen.divider_height),
                resources.getDimensionPixelSize(R.dimen.divider_height),
                ContextCompat.getColor(this, R.color.app_darker_grey)))
        reviews_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                hasMore = viewModel.getTotalComments()!! > ((page + 1) * count)

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && !isLoading && hasMore) {
                    isLoading = true
                    adapter.addItem(Review(type = "LOADING"))
                    page++
                    viewModel.fetchReviewsList(count, page)
                }
            }
        })
        viewModel.reviews.observe(this, Observer<ArrayList<Review>> {
            isLoading = false
            it?.let {
                //  it?.clear()
                if (adapter.itemCount > 0) {
                    adapter.removeItem()
                }
                if (it.isEmpty() ) {
                    viewModel.errorMessage.value = Throwable("No reviews for this place")
                    viewModel.hasError.set(true)
                } else {
                    reviewsList.addAll(it)
                    adapter.notifyDataSetChanged()
                }
            }
        })
        viewModel.errorMessage.observe(this, Observer<Throwable> { error.text = it?.message })
    }

}
