package com.pelmeni.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
@EnableCaching
@SpringBootApplication
class PelmeniBackendApplication

fun main(args: Array<String>) {
	runApplication<PelmeniBackendApplication>(*args)
}
