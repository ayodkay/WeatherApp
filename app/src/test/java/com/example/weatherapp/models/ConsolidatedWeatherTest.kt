package com.example.weatherapp.models

import org.junit.Assert.assertEquals
import org.junit.Test

class ConsolidatedWeatherTest {

    @Test
    fun testConsolidatedWeather() {
        val weather = ConsolidatedWeather(
            air_pressure = 1017.0,
            applicable_date = "2022-02-23",
            created = "2022-02-23T03:44:21.729917Z",
            humidity = 55,
            id = 6781832265834496,
            max_temp = 17.595,
            min_temp = 12.125,
            predictability = 73,
            the_temp = 16.62,
            visibility = 9.999726596675416,
            weather_state_abbr = "hr",
            weather_state_name = "Heavy Rain",
            wind_direction = 231.16318762429648,
            wind_direction_compass = "SW",
            wind_speed = 6.646553473768334
        )

        // Check the values of the weather object
        assertEquals(1017.0, weather.air_pressure, 0.0)
        assertEquals("2022-02-23", weather.applicable_date)
        assertEquals("2022-02-23T03:44:21.729917Z", weather.created)
        assertEquals(55, weather.humidity)
        assertEquals(6781832265834496, weather.id)
        assertEquals(17.595, weather.max_temp, 0.0)
        assertEquals(12.125, weather.min_temp, 0.0)
        assertEquals(73, weather.predictability)
        assertEquals(16.62, weather.the_temp, 0.0)
        assertEquals(9.999726596675416, weather.visibility, 0.0)
        assertEquals("hr", weather.weather_state_abbr)
        assertEquals("Heavy Rain", weather.weather_state_name)
        assertEquals(231.16318762429647, weather.wind_direction, 0.0)
        assertEquals("SW", weather.wind_direction_compass)
        assertEquals(6.646553473768334, weather.wind_speed, 0.0)
    }
}
