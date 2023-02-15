package ar.com.flow.platform.log.adapters.`in`.web.rest

import ar.com.flow.platform.log.domain.AppLogEntry
import ar.com.flow.platform.log.ports.out.AppLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

@Component
@RestController
@RequestMapping("/log/app")
class AppLogController(@Autowired private val appLog: AppLog) {
    @PostMapping("/entries")
    @ResponseStatus(HttpStatus.CREATED)
    fun addEntry(@RequestBody entry: AppLogEntry) {
        appLog.addEntry(entry)
    }

    @GetMapping("/entries/by-context/{context}")
    fun entriesByContext(@PathVariable("context") context: String): List<AppLogEntry> {
        return appLog.entriesByContext(context)
    }
}