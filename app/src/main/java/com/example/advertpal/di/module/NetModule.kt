package com.example.advertpal.di.module

import com.example.advertpal.utils.QUERY_PARAMETER_VERSION_NAME
import com.example.advertpal.utils.QUERY_PARAMETER_VERSION_VALUE
import com.example.advertpal.utils.VK_API_BASE_URL
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())

        okHttpClient.interceptors().add(Interceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder()
                .addQueryParameter(QUERY_PARAMETER_VERSION_NAME, QUERY_PARAMETER_VERSION_VALUE)
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        })

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideBaseUrl(): String = VK_API_BASE_URL

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory,
        callAdapterFactory: RxJava2CallAdapterFactory,
        baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
}
