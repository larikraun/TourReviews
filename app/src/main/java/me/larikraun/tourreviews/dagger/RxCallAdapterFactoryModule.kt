package me.larikraun.tourreviews.dagger

import dagger.Module
import dagger.Provides
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
class RxCallAdapterFactoryModule {

    @Provides
    fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
}
