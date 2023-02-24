package com.example.weatherapp

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.loading
import com.example.weatherapp.data.onError
import com.example.weatherapp.data.onSuccess
import com.example.weatherapp.models.WeatherResponse
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivityViewModel : ViewModel(), KoinComponent {
    private val weatherRepository: WeatherRepository by inject()
    val weather = ObservableField<WeatherResponse>()
    val state = ObservableField<String>()
    var disposable: Disposable? = null

    init {
        disposable = getWeatherFromRepository()
    }

    fun getWeatherFromRepository() = weatherRepository.getWeather().subscribe { status ->
        status.onSuccess { response ->
            weather.set(response)
            state.set("")
        }.loading {
            state.set("Loading...")
        }.onError { error ->
            state.set(error.message)
        }
    }


    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}