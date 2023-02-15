package ar.com.flow.platform.log.adapters.out.persistence.mongodb

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [TestApplication::class])
@ActiveProfiles("mongodb")
class MongoDbAppLogEntriesTest {
    @Autowired
    private lateinit var appLogEntries: MongoDbAppLogEntries

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