package ar.com.flow.platform.log.adapters.out.search

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(ElasticsearchClientConfiguration::class)
class LogApiConfiguration {
}