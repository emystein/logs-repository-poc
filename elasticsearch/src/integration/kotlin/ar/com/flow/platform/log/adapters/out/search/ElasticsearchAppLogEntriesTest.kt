package ar.com.flow.platform.log.adapters.out.search

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestOperations

@SpringBootTest(classes = [TestApplication::class])
@ActiveProfiles("elasticsearch")
class ElasticsearchAppLogEntriesTest {
    @Autowired
    private lateinit var appLogEntries: ElasticsearchAppLogEntries

    @Autowired
    private lateinit var elasticsearchRestOperations: RestOperations

    @BeforeEach
    fun setUp() {
        try {
            elasticsearchRestOperations.put("/_data_stream/logs-app-default", null)
        } catch (e: Exception) {
            // assume exception is thrown due to data stream already exist
        }
    }

    @Test
    fun findAll() {
        val entry = AppLogEntry()
        entry.level = "DBG"
        entry.status = "PEND"
        entry.data = "test message"

        appLogEntries.save(entry)

        val all = appLogEntries.findAll(Pageable.ofSize(10))

        Assertions.assertThat(all.totalElements).isGreaterThan(0L)
    }
}