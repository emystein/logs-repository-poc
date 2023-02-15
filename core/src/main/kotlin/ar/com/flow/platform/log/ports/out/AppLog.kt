package ar.com.flow.platform.log.ports.out

import ar.com.flow.platform.log.domain.AppLogEntry

interface AppLog {
    fun addEntry(entry: AppLogEntry)
    fun entriesByContext(context: String): List<AppLogEntry>
}