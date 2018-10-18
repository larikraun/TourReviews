package me.larikraun.tourreviews.dagger;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {NetworkModule.class, GsonConverterModule.class, RxCallAdapterFactoryModule.class})
public class RetrofitModule {
	
	@Provides
	public Retrofit provideRetrofit (OkHttpClient okHttpClient, RxJava2CallAdapterFactory callAdapterFactory) {
		return new Retrofit.Builder ()
				.addCallAdapterFactory (callAdapterFactory)
				.addConverterFactory (GsonConverterFactory.create ())
				.client (okHttpClient)
				.baseUrl ("https://www.getyourguide.com")
				.build ();
	}
}