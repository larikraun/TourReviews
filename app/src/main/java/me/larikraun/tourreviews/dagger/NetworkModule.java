package me.larikraun.tourreviews.dagger;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = {ContextModule.class})
public class NetworkModule {
	
	@Provides
	public HttpLoggingInterceptor loggingInterceptor () {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor ();
		interceptor.setLevel (HttpLoggingInterceptor.Level.BODY);
		
		return interceptor;
	}
	
	@Provides
	public Cache cache (File cacheFile) {
		return new Cache (cacheFile, 10 * 1000 * 1000);
	}
	
	@Provides
	public File cacheFile (Context context) {
		return new File (context.getCacheDir (), "okhttp_cache");
	}
	
	
	@Provides
	public OkHttpClient okHttpClient (HttpLoggingInterceptor loggingInterceptor, Cache cache) {
		
		return new OkHttpClient.Builder ()
				.addInterceptor (loggingInterceptor)
				.retryOnConnectionFailure (false)
				.addInterceptor (this::onOnIntercept)
				.connectTimeout (60, TimeUnit.SECONDS)
				.readTimeout (60, TimeUnit.SECONDS)
				.writeTimeout (60, TimeUnit.SECONDS).cache (cache).build ();
	}
	
	private Response onOnIntercept (Interceptor.Chain chain) throws IOException {
		Response response = null;
		try {
			response = chain.proceed (chain.request ());
		} catch ( SocketTimeoutException | UnknownHostException exception ) {
			exception.printStackTrace ();
		} catch ( ConnectException exception ) {
			exception.printStackTrace ();
		}
		return response;
	}
	
}
