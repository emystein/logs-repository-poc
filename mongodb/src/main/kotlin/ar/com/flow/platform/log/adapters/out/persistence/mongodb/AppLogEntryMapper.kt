package ar.com.flow.platform.log.adapters.out.persistence.mongodb

import ar.com.flow.platform.log.domain.AppLogEntry
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface AppLogEntryMapper {
    @Mapping(target = "id", ignore = true)
    fun domainToMongo(entry: AppLogEntry): ar.com.flow.platform.log.adapters.out.persistence.mongodb.AppLogEntry
    fun mongoToDomain(entry: ar.com.flow.platform.log.adapters.out.persistence.mongodb.AppLogEntry): AppLogEntry
}