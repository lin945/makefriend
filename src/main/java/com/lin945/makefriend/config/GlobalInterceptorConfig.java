package com.lin945.makefriend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lwm
 * @date 2020-09-12 16:43
 * @description 全局拦截器
 */
@Configuration
public class GlobalInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    LoginCheckInterceptor loginCheckInterceptor;

    //    @Bean
//    public WebMvcConfigurer webMvcConfigurer() {
//        return new WebMvcConfigurer() {
//
//            //添加此方法解决上述问题
//
//
//        };
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**") // 要拦截的地址
                .excludePathPatterns(
                        "/register",//注册
                        "/login", // 登陆接口
                        "/test", // 测试地址
                        "/static/**", // 图片
                        "/swagger-ui.html/**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/**"
                ); // 不要拦截的地址
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 放行swagger
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}