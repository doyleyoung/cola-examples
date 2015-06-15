package com.github.bmsantos.kotlin.boot.controller

import com.github.bmsantos.kotlin.boot.data.Greeting
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
public class GreetingController {

    val counter = AtomicLong()

    @RequestMapping("/greeting")
    public fun greeting(@RequestParam(value="name", defaultValue = "World") name:String) : Greeting {
        return Greeting(counter.incrementAndGet(), "Hello, $name")
    }
}