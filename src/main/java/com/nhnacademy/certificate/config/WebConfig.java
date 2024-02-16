package com.nhnacademy.certificate.config;

import com.nhnacademy.certificate.controller.ControllerBase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan(
        basePackageClasses = ControllerBase.class,
        includeFilters = @ComponentScan.Filter(Controller.class)
)
public class WebConfig implements WebMvcConfigurer {
}
