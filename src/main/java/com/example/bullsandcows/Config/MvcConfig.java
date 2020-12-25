package com.example.bullsandcows.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("start");
        registry.addViewController("/game").setViewName("game");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("/registration");
        registry.addViewController("/rating").setViewName("/rating");
    }
}