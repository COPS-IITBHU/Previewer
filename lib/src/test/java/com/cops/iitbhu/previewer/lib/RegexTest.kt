package com.cops.iitbhu.previewer.lib

import org.junit.Test

import org.junit.Assert.*

class RegexTest {

    @Test
    fun checkRegexIsValid() {
        val links = listOf(
            "https://www.youtube.com/watch?v=DFYRQ_zQ-gk",
            "https://www.youtube.com/watch?v=DFYRQ_zQ-gk&feature=featured",
            "https://www.youtube.com/watch?v=DFYRQ_zQ-gk",
            "http://www.youtube.com/watch?v=DFYRQ_zQ-gk",
            "//www.youtube.com/watch?v=DFYRQ_zQ-gk",
            "www.youtube.com/watch?v=DFYRQ_zQ-gk",
            "https://youtube.com/watch?v=DFYRQ_zQ-gk",
            "http://youtube.com/watch?v=DFYRQ_zQ-gk",
            "//youtube.com/watch?v=DFYRQ_zQ-gk",
            "youtube.com/watch?v=DFYRQ_zQ-gk",
            "https://m.youtube.com/watch?v=DFYRQ_zQ-gk",
            "http://m.youtube.com/watch?v=DFYRQ_zQ-gk",
            "//m.youtube.com/watch?v=DFYRQ_zQ-gk",
            "m.youtube.com/watch?v=DFYRQ_zQ-gk",
            "https://www.youtube.com/v/DFYRQ_zQ-gk?fs=1&hl=en_US",
            "http://www.youtube.com/v/DFYRQ_zQ-gk?fs=1&hl=en_US",
            "//www.youtube.com/v/DFYRQ_zQ-gk?fs=1&hl=en_US",
            "www.youtube.com/v/DFYRQ_zQ-gk?fs=1&hl=en_US",
            "youtube.com/v/DFYRQ_zQ-gk?fs=1&hl=en_US",
            "https://www.youtube.com/embed/DFYRQ_zQ-gk?autoplay=1",
            "https://www.youtube.com/embed/DFYRQ_zQ-gk",
            "http://www.youtube.com/embed/DFYRQ_zQ-gk",
            "//www.youtube.com/embed/DFYRQ_zQ-gk",
            "www.youtube.com/embed/DFYRQ_zQ-gk",
            "https://youtube.com/embed/DFYRQ_zQ-gk",
            "http://youtube.com/embed/DFYRQ_zQ-gk",
            "//youtube.com/embed/DFYRQ_zQ-gk",
            "youtube.com/embed/DFYRQ_zQ-gk",
            "https://youtu.be/DFYRQ_zQ-gk?t=120",
            "https://youtu.be/DFYRQ_zQ-gk",
            "http://youtu.be/DFYRQ_zQ-gk",
            "//youtu.be/DFYRQ_zQ-gk",
            "youtu.be/DFYRQ_zQ-gk",
            "https://www.youtube.com/HamdiKickProduction?v=DFYRQ_zQ-gk",
        )
        val expected = "DFYRQ_zQ-gk"
        links.forEach { link ->
            val actual = Previewer.youtubeLinkToImageUrl(link)
            assertEquals(expected, actual)
        }
    }
}