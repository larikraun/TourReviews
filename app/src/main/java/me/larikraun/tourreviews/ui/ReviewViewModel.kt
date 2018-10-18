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
import me.larikraun.tourreviews.utils.ConnectionUtil

/**
 * Author: Omolara Adejuwon
 * Date: 18/10/2018.
 */
class ReviewViewModel(internal var mRepository: ReviewRepository, var connectivity: ConnectionUtil) : ViewModel() {
    var reviews = MutableLiveData<ArrayList<Review>>()
    var totalReviewsComments = MutableLiveData<Int>()
    val errorMessage = MutableLiveData<Throwable>()

    val isLoading = ObservableField(true)
    val hasError = ObservableField(false)
    fun fetchReviewsList(count: Int, page: Int) {
        if (connectivity.isConnectedToInternet!!)
            mRepository.fetchReviews(count, page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<ReviewResponse>() {

                        override fun onError(e: Throwable) {
                            errorMessage.value = e
                            hasError.set(true)
                        }

                        override fun onNext(data: ReviewResponse) {
                            totalReviewsComments.value = data.totalReviewsComments
                            reviews.value = data.reviews
                        }

                        override fun onComplete() {
                            isLoading.set(false)
                        }

                    })
        else {
            errorMessage.value = Throwable("No internet connection")
            hasError.set(true)
            isLoading.set(false)
        }
    }

    fun getTotalComments(): Int? {
        return totalReviewsComments.value
    }
}
