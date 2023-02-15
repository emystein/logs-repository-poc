package ar.com.flow.platform.log.adapters.out.persistence.mongodb

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "mongodb")
class MongoDbProperties {
    lateinit var host: String
    lateinit var port: String
    var username: String? = null
    var password: String? = null
}