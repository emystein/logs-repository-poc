package ar.com.flow.platform.log.adapters.out.search

import ar.com.flow.platform.log.domain.AppLogEntryFactory.logEntry
import ar.com.flow.platform.log.ports.out.AppLog
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestOperations


@SpringBootTest(classes = [TestApplication::class])
@ActiveProfiles("elasticsearch")
class ElasticsearchAppLogTest {
    @Autowired
    private lateinit var log: AppLog

    @Autowired
    private lateinit var logEntries: ElasticsearchAppLogEntries

    @Autowired
    private lateinit var elasticsearchRestOperations: RestOperations

    private val context: String = "1"

    @BeforeEach
    internal fun setUp() {
        try {
            elasticsearchRestOperations.put("/_data_stream/logs-app-default", null)
        } catch (e: Exception) {
            // assume exception is thrown due to data stream already exist
        }

        logEntries.deleteAllByContext(context)
    }

    @Test
    internal fun getEntriesByContext() {
        log.addEntry(logEntry(context))
        log.addEntry(logEntry(context))
        log.addEntry(logEntry(context = "${context}2"))

        val entriesByContext = log.entriesByContext(context)

        entriesByContext.forEach { assertThat(it.context).isEqualTo(context) }
    }
}
