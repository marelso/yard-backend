package io.marelso.yard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class YardApplication

fun main(args: Array<String>) {
	runApplication<YardApplication>(*args)
}
