package me.larikraun.tourreviews.network

import io.reactivex.Observable
import me.larikraun.tourreviews.model.ReviewResponse
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Author: Omolara Adejuwon
 * Date: 17/10/2018.
 */
class ReviewRepository @Inject
constructor(internal var mRetrofit: Retrofit) {

    fun fetchReviews(count: Int, page: Int): Observable<ReviewResponse> {
        return mRetrofit.create(FetchReviewService::class.java).fetchReviews(count, page, 0, "date_of_review", "DESC")
    }

}
