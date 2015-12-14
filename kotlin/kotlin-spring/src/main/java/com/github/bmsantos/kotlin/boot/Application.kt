package com.github.bmsantos.kotlin.boot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
public open class Application {
    companion object {
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}