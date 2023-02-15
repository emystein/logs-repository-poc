package ar.com.flow.platform.log.adapters.out.search

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "elasticsearch")
class ElasticSearchProperties {
    lateinit var host: String
    lateinit var port: String
    lateinit var username: String
    lateinit var password: String

    val hostAndPort: String
        get() = "${host}:${port}"
}