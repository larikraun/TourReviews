package me.larikraun.tourreviews.network;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.larikraun.tourreviews.model.ReviewResponse;
import retrofit2.Retrofit;

/**
 * Author: Omolara Adejuwon
 * Date: 17/10/2018.
 */
public class ReviewRepository {
	
	Retrofit mRetrofit;
	
	@Inject
	public ReviewRepository (Retrofit retrofit) {
		mRetrofit = retrofit;
	}
	
	public Observable<ReviewResponse> fetchReviews () {
		return mRetrofit.create (FetchReviewService.class).fetchReviews (4, 0,0,"date_of_review","DESC").subscribeOn (Schedulers.io ()).observeOn (AndroidSchedulers.mainThread ());
	}
	
}
