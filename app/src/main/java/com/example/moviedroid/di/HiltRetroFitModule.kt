package com.example.moviedroid.di

import com.example.moviedroid.constants.Constants
import com.example.moviedroid.services.repository.remote.MovieEndPointInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltRetroFitModule {
        private val baseURL = Constants.BASE_URL
        private val token = "Bearer ${Constants.TOKEN}"

        @Singleton
        @Provides
        fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
                .addInterceptor{ chain ->
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", token)
                        .build()

                    chain.proceed(request)
                }.build()

        @Singleton
        @Provides
        fun providesRetrofitInstance (okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL)
                .client(okHttpClient)
                .build()

        @Singleton
        @Provides
        fun providesApiService(retrofit: Retrofit):MovieEndPointInterface = retrofit.create(MovieEndPointInterface::class.java)
}