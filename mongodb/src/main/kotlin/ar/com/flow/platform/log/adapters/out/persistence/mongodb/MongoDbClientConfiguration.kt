package ar.com.flow.platform.log.adapters.out.persistence.mongodb

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@Configuration
@ComponentScan
@EnableMongoRepositories
@Profile("mongodb")
class MongoDbClientConfiguration {
    @Autowired
    private lateinit var properties: MongoDbProperties

}