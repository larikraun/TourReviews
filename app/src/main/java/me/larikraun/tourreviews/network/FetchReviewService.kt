package me.larikraun.tourreviews.network

import io.reactivex.Observable
import me.larikraun.tourreviews.model.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Author: Omolara Adejuwon
 * Date: 17/10/2018.
 */
interface FetchReviewService {
    @GET("/berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/reviews.json")
    @Headers("User-Agent: GetYourGuide")
    fun fetchReviews(@Query("count") count: Int, @Query("page") page: Int, @Query("rating") rating: Int, @Query("sortBy") sort: String, @Query("direction") dir: String): Observable<ReviewResponse>
}
