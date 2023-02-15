package ar.com.flow.platform.log.adapters.out.search

import ar.com.flow.platform.log.domain.AppLogEntry
import ar.com.flow.platform.log.ports.out.AppLog
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ElasticsearchAppLog(
    @Autowired private val elasticsearchAppLogEntries: ElasticsearchAppLogEntries,
) : AppLog {
    private val logEntryMapper: AppLogEntryMapper = Mappers.getMapper(AppLogEntryMapper::class.java)

    override fun addEntry(entry: AppLogEntry) {
        val elasticSearchEntry = logEntryMapper.domainToMongo(entry)
        elasticsearchAppLogEntries.save(elasticSearchEntry)
    }

    override fun entriesByContext(context: String): List<AppLogEntry> {
       return elasticsearchAppLogEntries.findAllByContext(context).map { logEntryMapper.mongoToDomain(it) }
    }
}