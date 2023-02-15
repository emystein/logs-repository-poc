package ar.com.flow.platform.log.application

import ar.com.flow.platform.log.adapters.out.persistence.mongodb.MongoDbClientConfiguration
import ar.com.flow.platform.log.adapters.out.search.ElasticsearchClientConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


@Configuration
@ComponentScan(basePackages = ["ar.com.flow.platform.log.adapters.in.web.rest"])
@Import(
    ElasticsearchClientConfiguration::class,
    MongoDbClientConfiguration::class
)
class LogApiConfiguration
