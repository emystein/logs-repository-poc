package ar.com.flow.platform.log.api

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NanoDateTimeFormatTest {
    @Test
    fun fromString() {
        val instant = LocalDateTime.parse("20210908235800:365771584",
            DateTimeFormatter.ofPattern("yyyyMMddHHmmss:SSSSSSSSS"))

        assertThat(instant.year).isEqualTo(2021)
        assertThat(instant.monthValue).isEqualTo(9)
        assertThat(instant.dayOfMonth).isEqualTo(8)
        assertThat(instant.hour).isEqualTo(23)
        assertThat(instant.minute).isEqualTo(58)
        assertThat(instant.second).isEqualTo(0)
        assertThat(instant.nano).isEqualTo(365771584)
    }
}