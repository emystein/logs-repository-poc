package ar.com.flow.platform.log.domain

import java.util.*

data class AppLogEntry(
    val timestamp: Date,
    val status: String? = null,
    var processId: String? = null,
    var threadId: String? = null,
    var context: String? = null,
    var logicalName: String? = null,
    var `object`: String? = null,
    var affectedObject: String? = null,
    var level: String? = null,
    var type: String? = null,
    var eventCode: String? = null,
    var source: String? = null,
    var data: String? = null
)
