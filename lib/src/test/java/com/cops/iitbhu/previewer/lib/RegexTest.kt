package com.cops.iitbhu.previewer.lib

import org.junit.Test

import org.junit.Assert.*

class RegexTest {

    @Test
    fun checkRegexIsValid() {
        val youtubeLink = "https://www.youtube.com/watch?v=Pur_0KroFR8"
        val expected = "Pur_0KroFR8"
        val actual = Previewer.youtubeLinkToImageUrl(youtubeLink)
        assertEquals(expected, actual)
    }
}