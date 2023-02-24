package com.example.weatherapp.data

import com.example.weatherapp.models.WeatherResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import retrofit2.http.GET

interface WeatherService {
    @GET("mobile-take-home/4418.json")
    fun getWeather(): Flowable<Response<WeatherResponse>>
}


