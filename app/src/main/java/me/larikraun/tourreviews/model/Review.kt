package me.larikraun.tourreviews.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Review(
        var type: String? = "REVIEW",
        @SerializedName("review_id")
    @Expose
        var reviewId: Int? = null,
        @SerializedName("rating")
    @Expose
        var rating: String? = null,
        @SerializedName("title")
    @Expose
        var title: String? = null,
        @SerializedName("message")
    @Expose
        var message: String? = null,
        @SerializedName("author")
    @Expose
        var author: String? = null,
        @SerializedName("foreignLanguage")
    @Expose
        var foreignLanguage: Boolean? = null,
        @SerializedName("date")
    @Expose
        var date: String? = null,
        @SerializedName("languageCode")
    @Expose
        var languageCode: String? = null,
        @SerializedName("traveler_type")
    @Expose
        var travelerType: String? = null,
        @SerializedName("reviewerName")
    @Expose
        var reviewerName: String? = null,
        @SerializedName("reviewerCountry")
    @Expose
    var reviewerCountry: String? = null

)
