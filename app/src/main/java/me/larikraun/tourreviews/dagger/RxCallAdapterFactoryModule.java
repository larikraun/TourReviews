package me.larikraun.tourreviews.dagger;

import dagger.Module;
import dagger.Provides;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@Module
public class RxCallAdapterFactoryModule {
	
	@Provides
	public RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory () {
		return RxJava2CallAdapterFactory.create ();
	}
}
