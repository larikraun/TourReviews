package me.larikraun.tourreviews;

import android.app.Application;

import me.larikraun.tourreviews.dagger.AppMainComponent;
import me.larikraun.tourreviews.dagger.ContextModule;
import me.larikraun.tourreviews.dagger.DaggerAppMainComponent;
import me.larikraun.tourreviews.dagger.RepositoryModule;

/**
 * Author: Omolara Adejuwon
 * Date: 17/10/2018.
 */
public class TourReviewApp extends Application {
	//AppMainComponent mComponent;
	
	@Override
	public void onCreate () {
		super.onCreate ();
//		mComponent = DaggerAppMainComponent.builder ()
//				.contextModule (new ContextModule (this))
//				.repositoryModule (new RepositoryModule ())
//				.build ();
	}
}
