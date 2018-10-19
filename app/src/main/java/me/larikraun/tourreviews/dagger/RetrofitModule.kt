package me.larikraun.tourreviews.dagger

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [(NetworkModule::class), (GsonConverterModule::class), (RxCallAdapterFactoryModule::class)])
class RetrofitModule {

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, callAdapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://www.getyourguide.com")
                .build()
    }
}