package me.larikraun.tourreviews.dagger;

import dagger.Module;
import dagger.Provides;
import me.larikraun.tourreviews.network.ReviewRepository;
import retrofit2.Retrofit;

/**
 * Author: Omolara Adejuwon
 * Date: 17/10/2018.
 */
@Module(includes = {RetrofitModule.class})
public class RepositoryModule {
	@Provides
	public ReviewRepository providesRepo (Retrofit retrofit) {
		return new ReviewRepository (retrofit);
	}
}
