package com.board.shop.component;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler( "/files/**")
                .addResourceLocations("file:///D:/shop/file/");
        registry.addResourceHandler( "/resources/**")
                .addResourceLocations("classpath:/static/images/");
    }
}