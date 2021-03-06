package me.larikraun.tourreviews.dagger

import android.content.Context

import dagger.Module
import dagger.Provides
import me.larikraun.tourreviews.network.ReviewRepository
import me.larikraun.tourreviews.utils.ConnectionUtil
import retrofit2.Retrofit

/**
 * Author: Omolara Adejuwon
 * Date: 17/10/2018.
 */
@Module(includes = [(RetrofitModule::class), (ContextModule::class)])
class RepositoryModule {
    @Provides
    fun providesRepo(retrofit: Retrofit): ReviewRepository {
        return ReviewRepository(retrofit)
    }

    @Provides
    fun providesConnectionUtils(context: Context): ConnectionUtil {
        return ConnectionUtil(context)
    }
}
