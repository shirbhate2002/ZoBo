package dev.vaidilya.zobo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import dev.vaidilya.zobo.api.BlogsAPI
import dev.vaidilya.zobo.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//@InstallIn(ActivityComponent::class)
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader("x-api-key", Constants.X_API_KEY)
                        .build()
                )
            }
            .build()
    }

    @Singleton
    @Provides
    fun getBlogsAPI(retrofit: Retrofit.Builder, client: OkHttpClient): BlogsAPI {
        return retrofit.client(client)
            .build()
            .create(BlogsAPI::class.java)
    }
}