package me.larikraun.tourreviews.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReviewResponse {

    @SerializedName("status")
    @Expose
    var status: Boolean? = null
    @SerializedName("total_reviews_comments")
    @Expose
    var totalReviewsComments: Int? = null
    @SerializedName("data")
    @Expose
    var reviews: List<Review>? = null

}
