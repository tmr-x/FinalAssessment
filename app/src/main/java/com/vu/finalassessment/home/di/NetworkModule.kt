package com.vu.finalassessment.home.di

//Import necessary libraries.
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vu.finalassessment.home.network.RestfulApiDevService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module //Marks this class as a Dagger module.
@InstallIn(SingletonComponent::class) //Specifies that this module will be installed in the SingletonComponent.
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    //Provides a singleton instance of Retrofit.
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://nit3213-api-h2b3-latest.onrender.com")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(ScalarsConverterFactory.create()) //For plain text responses.
            .addConverterFactory(GsonConverterFactory.create(gson)) //For JSON responses.
            .build()
    }

    //Provides a singleton instance of the RestfulApiDevService interface.
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RestfulApiDevService {
        return retrofit.create(RestfulApiDevService::class.java)
    }
}