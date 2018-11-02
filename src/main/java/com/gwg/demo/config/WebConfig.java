package com.gwg.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * SpringMVC子容器
 *
 * 1.想要使用Java配置的方式搭建Springmvc,只需要将@EnableWebMvc添加到你的一个@Configuration class即可,使用这种方式后就
 * 不需要再配置web.xml文件了
 * 2.在这里为什么实现WebMvcConfigurer？
 * 想要以Java形式定制默认的配置，你可以简单的实现WebMvcConfigurer接口，根据需要配置，或者继承WebMvcConfigurerAdapter并重写需要的方法：
 */
@Configuration
@EnableWebMvc // 启用SpringMVC
@ComponentScan("com.gwg.demo.controller")//Controller添加到SpringMVC的容器中，在这里不能添加到Spring的容器中，否者服务找不到。SpringMVC的容器可以访问Spring的容器中的Bean
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * SpringMVC支持JSP
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new
                InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * Java形式注册拦截器：
     * @param interceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {

    }

    /**
     * 跨域处理
     * @param corsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        //添加映射路径
        corsRegistry.addMapping("/**")
                //放行哪些原始域
                .allowedOrigins("http://localhost:3000")
                //是否发送Cookie信息
                .allowCredentials(true)
                 //放行哪些原始域(请求方式)
                .allowedMethods("GET","POST", "PUT", "DELETE", "OPTIONS")
                //放行哪些原始域(头部信息)
                .allowedHeaders("Content-Type", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With");

    }


}