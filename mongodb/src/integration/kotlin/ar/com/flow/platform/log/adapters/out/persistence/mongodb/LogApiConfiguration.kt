package ar.com.flow.platform.log.adapters.out.persistence.mongodb

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(MongoDbClientConfiguration::class)
class LogApiConfiguration {
}