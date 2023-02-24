package com.example.weatherapp


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapp.data.Status
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.models.WeatherResponse
import com.jakewharton.rxrelay3.BehaviorRelay
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class MainActivityViewModelTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val weatherRepository: WeatherRepository = mockk()
    private val mainActivityViewModel: MainActivityViewModel by inject()

    @Before
    fun setUp() {
        startKoin {
            modules(module {
                single { weatherRepository }
                viewModel { MainActivityViewModel() }
            })
        }
    }

    @Test
    fun `when getWeatherFromRepository is called, should update weather and loading`() {
        // given
        val weatherResponse = mockk<WeatherResponse>()
        val behaviorRelay =
            BehaviorRelay.createDefault<Status<WeatherResponse>>(Status.OnSuccess(weatherResponse))
        every { weatherRepository.getWeather() } returns behaviorRelay

        // when
        mainActivityViewModel.getWeatherFromRepository()

        // then
        verify { weatherRepository.getWeather() }
        assert(mainActivityViewModel.weather.get() == weatherResponse)
        assert(mainActivityViewModel.state.get() == "")
    }

    @Test
    fun `when getWeatherFromRepository returns error, should update loading`() {
        // given
        val behaviorRelay = BehaviorRelay.create<Status<WeatherResponse>>()
        every { weatherRepository.getWeather() } returns behaviorRelay

        // when
        mainActivityViewModel.getWeatherFromRepository()

        // then
        verify { weatherRepository.getWeather() }
        assert(mainActivityViewModel.state.get()?.isEmpty() != true)
    }
}
