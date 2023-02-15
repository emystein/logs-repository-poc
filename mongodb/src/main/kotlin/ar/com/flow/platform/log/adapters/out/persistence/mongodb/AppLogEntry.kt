package ar.com.flow.platform.log.adapters.out.persistence.mongodb

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document(collection = "app_log")
class AppLogEntry {
    @Id
    var id: String? = null
    @Field(name = "@timestamp")
    var timestamp: Date? = null
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