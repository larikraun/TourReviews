package me.larikraun.tourreviews.dagger;

import dagger.Module;
import dagger.Provides;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class GsonConverterModule {

    @Provides
    public GsonConverterFactory provideGson() {
        return GsonConverterFactory.create();
    }
}
