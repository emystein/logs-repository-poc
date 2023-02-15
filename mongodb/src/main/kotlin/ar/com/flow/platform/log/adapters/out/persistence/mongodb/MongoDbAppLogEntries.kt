package ar.com.flow.platform.log.adapters.out.persistence.mongodb

import org.springframework.data.mongodb.repository.MongoRepository

interface MongoDbAppLogEntries : MongoRepository<AppLogEntry, String> {
    fun findAllByContext(context: String): List<AppLogEntry>
    fun deleteAllByContext(context: String)
}