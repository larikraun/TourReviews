
package me.larikraun.tourreviews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewJ {

    @SerializedName("review_id")
    @Expose
    private Integer reviewId;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("foreignLanguage")
    @Expose
    private Boolean foreignLanguage;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("languageCode")
    @Expose
    private String languageCode;
    @SerializedName("traveler_type")
    @Expose
    private String travelerType;
    @SerializedName("reviewerName")
    @Expose
    private String reviewerName;
    @SerializedName("reviewerCountry")
    @Expose
    private String reviewerCountry;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(Boolean foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getTravelerType() {
        return travelerType;
    }

    public void setTravelerType(String travelerType) {
        this.travelerType = travelerType;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewerCountry() {
        return reviewerCountry;
    }

    public void setReviewerCountry(String reviewerCountry) {
        this.reviewerCountry = reviewerCountry;
    }

}
