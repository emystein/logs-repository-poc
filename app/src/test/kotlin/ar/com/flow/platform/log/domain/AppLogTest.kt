package ar.com.flow.platform.log.domain

import ar.com.flow.platform.log.adapters.out.persistence.inmemory.InMemoryAppLog
import ar.com.flow.platform.log.domain.AppLogEntryFactory.logEntry1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AppLogTest {
    @Test
    internal fun getEntriesByContext() {
        val log = InMemoryAppLog()

        log.addEntry(logEntry1)

        assertThat(log.entriesByContext(logEntry1.context!!)).isEqualTo(listOf(logEntry1))
    }
}