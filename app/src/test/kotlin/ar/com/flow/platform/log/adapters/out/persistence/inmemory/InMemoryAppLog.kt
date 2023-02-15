package ar.com.flow.platform.log.adapters.out.persistence.inmemory

import ar.com.flow.platform.log.domain.AppLogEntry
import ar.com.flow.platform.log.ports.out.AppLog

class InMemoryAppLog : AppLog {
    private val entries: MutableList<AppLogEntry> = mutableListOf()

    override fun addEntry(entry: AppLogEntry) {
        entries.add(entry)
    }

    override fun entriesByContext(context: String): List<AppLogEntry> {
        return entries.filter { entry -> entry.context == context }
    }
}