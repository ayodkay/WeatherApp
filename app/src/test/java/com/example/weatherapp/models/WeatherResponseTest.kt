package com.example.weatherapp.models

import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherResponseTest {

    @Test
    fun testWeatherResponse() {
        val parent = Parent(
            latt_long = "51.506321,-0.12714",
            location_type = "City",
            title = "London",
            woeid = 44418
        )

        val source = Source(
            crawl_rate = 360,
            slug = "bbc",
            title = "BBC",
            url = "http://www.bbc.co.uk/weather/"
        )

        val weatherResponse = WeatherResponse(
            consolidated_weather = listOf(),
            latt_long = "51.506321,-0.12714",
            location_type = "City",
            parent = parent,
            sources = listOf(source),
            sun_rise = "2023-02-24T06:56:38.290864+00:00",
            sun_set = "2023-02-24T17:31:24.804327+00:00",
            time = "2023-02-24T16:15:18.235947+00:00",
            timezone = "Europe/London",
            timezone_name = "Greenwich Mean Time",
            title = "London",
            woeid = 44418
        )

        // Check the values of the weatherResponse object
        assertEquals(listOf<ConsolidatedWeather>(), weatherResponse.consolidated_weather)
        assertEquals("51.506321,-0.12714", weatherResponse.latt_long)
        assertEquals("City", weatherResponse.location_type)
        assertEquals(parent, weatherResponse.parent)
        assertEquals(listOf(source), weatherResponse.sources)
        assertEquals("2023-02-24T06:56:38.290864+00:00", weatherResponse.sun_rise)
        assertEquals("2023-02-24T17:31:24.804327+00:00", weatherResponse.sun_set)
        assertEquals("2023-02-24T16:15:18.235947+00:00", weatherResponse.time)
        assertEquals("Europe/London", weatherResponse.timezone)
        assertEquals("Greenwich Mean Time", weatherResponse.timezone_name)
        assertEquals("London", weatherResponse.title)
        assertEquals(44418, weatherResponse.woeid)
    }
}
