package com.gwg.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring容器，为了简化配置将所有Bean放到SpringMVC子容器中
 */
@Configuration
@Order(Integer.MIN_VALUE)
@ComponentScan(basePackages={"com.gwg"}, excludeFilters={
        @ComponentScan.Filter(type= FilterType.ANNOTATION, value=EnableWebMvc.class),
        @ComponentScan.Filter(type= FilterType.ANNOTATION, value=RestController.class)//排除SpringMVC的
})
public class RootConfig {

}