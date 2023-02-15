package ar.com.flow.platform.log.adapters.out.persistence.mongodb

import ar.com.flow.platform.log.domain.AppLogEntry
import ar.com.flow.platform.log.ports.out.AppLog
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MongoDbAppLog(
    @Autowired private val appLogEntries: MongoDbAppLogEntries,
) : AppLog {
    private val logEntryMapper: AppLogEntryMapper = Mappers.getMapper(AppLogEntryMapper::class.java)

    override fun addEntry(entry: AppLogEntry) {
        val elasticSearchEntry = logEntryMapper.domainToMongo(entry)
        appLogEntries.save(elasticSearchEntry)
    }

    override fun entriesByContext(context: String): List<AppLogEntry> {
       return appLogEntries.findAllByContext(context).map { logEntryMapper.mongoToDomain(it) }
    }
}