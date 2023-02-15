package ar.com.flow.platform.log.adapters.out.search

import org.elasticsearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.RestClients
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import org.springframework.web.client.RestOperations


@Configuration
@ComponentScan
@EnableElasticsearchRepositories
@Profile("elasticsearch")
class ElasticsearchClientConfiguration : AbstractElasticsearchConfiguration() {
    @Autowired
    private lateinit var properties: ElasticSearchProperties

    @Bean
    override fun elasticsearchClient(): RestHighLevelClient {
        val clientConfiguration = ClientConfiguration.builder()
            .connectedTo(properties.hostAndPort)
            .withBasicAuth(properties.username, properties.password)
            .build()
        return RestClients.create(clientConfiguration).rest()
    }

    @Bean
    fun elasticsearchRestOperations(restTemplateBuilder: RestTemplateBuilder): RestOperations {
        return restTemplateBuilder
            .rootUri(properties.hostAndPort)
            .basicAuthentication(properties.username, properties.password)
            .build()
    }
}