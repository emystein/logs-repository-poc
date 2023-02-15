package ar.com.flow.platform.log.adapters.out.search

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.util.*

@Document(indexName = "logs-app-default", createIndex = false)
class AppLogEntry {
    @Id
    var id: String? = null
    // @timestamp is elasticsearch data stream timestamp field
    @Field(name = "@timestamp", type = FieldType.Date)
    var timestamp: Date = Date()
    var level: String? = null
    var status: String? = null
    var processId: String? = null
    var threadId: String? = null
    var context: String? = null
    var logicalName: String? = null
    var `object`: String? = null
    var affectedObject: String? = null
    var type: String? = null
    var eventCode: String? = null
    var source: String? = null
    var data: String? = null
}