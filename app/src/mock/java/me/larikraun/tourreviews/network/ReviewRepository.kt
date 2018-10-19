package me.larikraun.tourreviews.network

import io.reactivex.Observable
import me.larikraun.tourreviews.model.Review
import me.larikraun.tourreviews.model.ReviewResponse
import retrofit2.Retrofit
import javax.inject.Inject

open class ReviewRepository @Inject
constructor(internal var mRetrofit: Retrofit) {

    open fun fetchReviews(count: Int, page: Int): Observable<ReviewResponse> {
        val reviewResponse = ReviewResponse()
        reviewResponse.status = true
        reviewResponse.totalReviewsComments = 10
        val reviews = ArrayList<Review>()
        reviews.add(Review(type = "REVIEW", rating = "3.5f", title = "A random title", message = "A very long and random message from the author", author = "Omolara - Nigeria", date = "October, 19, 2018"))
        reviewResponse.reviews = reviews
        return Observable.just(reviewResponse)
    }


}