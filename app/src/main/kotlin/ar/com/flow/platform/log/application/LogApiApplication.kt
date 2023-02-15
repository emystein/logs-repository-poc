package ar.com.flow.platform.log.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LogApiApplication

fun main(args: Array<String>) {
	runApplication<LogApiApplication>(*args)
}
