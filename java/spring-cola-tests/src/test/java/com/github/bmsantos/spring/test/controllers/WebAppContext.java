package com.github.bmsantos.spring.test.controllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.github.bmsantos.spring.test.controllers" })
public class WebAppContext extends WebMvcConfigurerAdapter {
}