package com.example.weatherapp.models

import org.junit.Assert.assertEquals
import org.junit.Test

class SourceTest {

    @Test
    fun testSource() {
        val source = Source(
            crawl_rate = 360,
            slug = "bbc",
            title = "BBC",
            url = "http://www.bbc.co.uk/weather/"
        )

        // Check the values of the source object
        assertEquals(360, source.crawl_rate)
        assertEquals("bbc", source.slug)
        assertEquals("BBC", source.title)
        assertEquals("http://www.bbc.co.uk/weather/", source.url)
    }
}
