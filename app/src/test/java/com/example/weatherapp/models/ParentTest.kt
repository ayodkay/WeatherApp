package com.example.weatherapp.models

import org.junit.Assert.assertEquals
import org.junit.Test

class ParentTest {

    @Test
    fun testParent() {
        val parent = Parent(
            latt_long = "51.506321,-0.12714",
            location_type = "City",
            title = "London",
            woeid = 44418
        )

        // Check the values of the parent object
        assertEquals("51.506321,-0.12714", parent.latt_long)
        assertEquals("City", parent.location_type)
        assertEquals("London", parent.title)
        assertEquals(44418, parent.woeid)
    }
}
