package com.example.weatherapp.data

import com.example.weatherapp.models.WeatherResponse
import com.jakewharton.rxrelay3.BehaviorRelay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface WeatherRepository {
    fun getWeather(): BehaviorRelay<Status<WeatherResponse>>
}

class WeatherImpl : WeatherRepository, KoinComponent {
    private val weatherService: WeatherService by inject()

    override fun getWeather(): BehaviorRelay<Status<WeatherResponse>> =
        BehaviorRelay.create<Status<WeatherResponse>?>().also { relay ->
            relay.accept(Status.Loading())
            weatherService.getWeather().subscribeWithRelay(relay) { response ->
                response
            }
        }
}