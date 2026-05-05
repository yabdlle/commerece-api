package com.mod.commerce_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.persistence.autoconfigure.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["com.mod.commerce", "com.mod.commerce_api"])
@EnableJpaRepositories(basePackages = ["com.mod.commerce.repository"])
@EntityScan(basePackages = ["com.mod.commerce.domain.model"])
class CommerceApiApplication

fun main(args: Array<String>) {
	runApplication<CommerceApiApplication>(*args)
}