package com.mod.commerce_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.mod.commerce", "com.mod.commerce_api"])
class CommerceApiApplication

fun main(args: Array<String>) {
	runApplication<CommerceApiApplication>(*args)
}
