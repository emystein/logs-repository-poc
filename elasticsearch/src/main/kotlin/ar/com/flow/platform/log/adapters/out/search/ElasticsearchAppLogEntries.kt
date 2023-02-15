package ar.com.flow.platform.log.adapters.out.search

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface ElasticsearchAppLogEntries : ElasticsearchRepository<AppLogEntry, String> {
    fun findAllByContext(context: String): List<AppLogEntry>
    fun deleteAllByContext(context: String)
}