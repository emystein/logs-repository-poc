package ar.com.flow.platform.log.domain

import java.util.*

object AppLogEntryFactory {
    val logEntry1 = AppLogEntry(
        status = "ST",
        timestamp = Date(),
        level = "DEBUG",
        `object` = "TestApp",
        affectedObject = "TestApp",
        context = "1",
        data = "log message"
    )

    fun logEntry(context: String): AppLogEntry {
        return AppLogEntry(
            status = "ST",
            timestamp = Date(),
            level = "DEBUG",
            `object` = "TestApp",
            affectedObject = "TestApp",
            context = context,
            data = "log message"
        )
    }
}