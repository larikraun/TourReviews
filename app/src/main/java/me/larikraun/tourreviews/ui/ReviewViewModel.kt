package me.larikraun.tourreviews.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import me.larikraun.tourreviews.model.Review
import me.larikraun.tourreviews.model.ReviewResponse
import me.larikraun.tourreviews.network.ReviewRepository

/**
 * Author: Omolara Adejuwon
 * Date: 18/10/2018.
 */
class ReviewViewModel(internal var mRepository: ReviewRepository) : ViewModel() {
    var reviews = MutableLiveData<List<Review>>()
    val errorMessage = MutableLiveData<Throwable>()

    val isLoading = ObservableField(false)
    fun fetchReviewsList() {
        mRepository.fetchReviews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ReviewResponse>() {

                    override fun onError(e: Throwable) {
                        //if some error happens in our data layer our app will not crash, we will
                        // get error here
                        errorMessage.value = e
                    }

                    override fun onNext(data: ReviewResponse) {
                        reviews.value = data.reviews
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                })
    }
}
