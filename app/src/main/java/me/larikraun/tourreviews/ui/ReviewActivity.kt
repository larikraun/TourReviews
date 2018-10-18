package me.larikraun.tourreviews.ui

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
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
import javax.inject.Inject


class ReviewActivity : AppCompatActivity(), LifecycleOwner {
    @Inject
    lateinit var repo: ReviewRepository
    @Inject
    lateinit var connectionUtil: ConnectionUtil
    lateinit var mComponent: AppMainComponent
    lateinit var viewModelFactory: ReviewViewModelFactory
    lateinit var adapter: ReviewAdapter
    lateinit var mBinding: ActivityReviewBinding
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

        viewModel.fetchReviewsList()

        adapter = ReviewAdapter(reviewsList)
        reviews_list.layoutManager = LinearLayoutManager(this)
        reviews_list.adapter = adapter

        viewModel.reviews.observe(this, Observer<ArrayList<Review>> {
            it?.let {
              //  it?.clear()
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

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_review, menu)
//        return false
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
