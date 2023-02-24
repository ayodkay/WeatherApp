package com.example.weatherapp.di

import com.example.weatherapp.MainActivityViewModel
import com.example.weatherapp.data.WeatherImpl
import com.example.weatherapp.data.WeatherRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {
    single<WeatherRepository> { WeatherImpl() }
    viewModel { MainActivityViewModel() }
}