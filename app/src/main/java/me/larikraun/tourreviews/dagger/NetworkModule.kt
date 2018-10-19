package me.larikraun.tourreviews.dagger

import android.content.Context

import java.io.File
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

@Module(includes = [(ContextModule::class)])
class NetworkModule {

    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Provides
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, (10 * 1000 * 1000).toLong())
    }

    @Provides
    fun cacheFile(context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }


    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(false)
                .addInterceptor { this.onOnIntercept(it) }
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS).cache(cache).build()
    }

    @Throws(IOException::class)
    private fun onOnIntercept(chain: Interceptor.Chain): Response? {
        var response: Response? = null
        try {
            response = chain.proceed(chain.request())
        } catch (exception: SocketTimeoutException) {
            exception.printStackTrace()
        } catch (exception: UnknownHostException) {
            exception.printStackTrace()
        } catch (exception: ConnectException) {
            exception.printStackTrace()
        }

        return response
    }

}
