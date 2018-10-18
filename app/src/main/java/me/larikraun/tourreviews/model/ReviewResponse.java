
package me.larikraun.tourreviews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
	
	@SerializedName("status")
	@Expose
	private Boolean status;
	@SerializedName("total_reviews_comments")
	@Expose
	private Integer totalReviewsComments;
	@SerializedName("data")
	@Expose
	private List<Review> reviews = null;
	
	public Boolean getStatus () {
		return status;
	}
	
	public void setStatus (Boolean status) {
		this.status = status;
	}
	
	public Integer getTotalReviewsComments () {
		return totalReviewsComments;
	}
	
	public void setTotalReviewsComments (Integer totalReviewsComments) {
		this.totalReviewsComments = totalReviewsComments;
	}
	
	public List<Review> getReviews () {
		return reviews;
	}
	
	public void setReviews (List<Review> reviews) {
		this.reviews = reviews;
	}
	
}
