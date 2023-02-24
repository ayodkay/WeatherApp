package com.example.weatherapp.di

import com.example.weatherapp.data.WeatherService
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule {
    fun weatherServiceModule(): Module {
        return module {
            single<WeatherService> {
                Retrofit.Builder()
                    .baseUrl("https://cdn.faire.com/static/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(client)
                    .build()
                    .create(WeatherService::class.java)
            }
        }
    }

    private val client by lazy {
        OkHttpClient.Builder()
            .connectTimeout(300L, TimeUnit.SECONDS)
            .readTimeout(300L, TimeUnit.SECONDS)
            .writeTimeout(300L, TimeUnit.SECONDS)
            .build()
    }
}
