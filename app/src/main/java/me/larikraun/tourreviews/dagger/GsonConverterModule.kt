package me.larikraun.tourreviews.dagger

import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory

@Module
class GsonConverterModule {

    @Provides
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}
