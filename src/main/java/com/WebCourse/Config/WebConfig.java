package com.WebCourse.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Maps http://localhost:8080/ to src/main/resources/static/index.html
        registry.addViewController("/").setViewName("forward:/index.html");
    }
}