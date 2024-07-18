package com.example.StudentRegistryBackend.config;


import com.example.StudentRegistryBackend.common.JwtInterceptor;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

    @Resource
    private JwtInterceptor jwtInterceptor;
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        //log.info("Good evening，configurePathMatch，我要开始工作啦");
        configurer.addPathPrefix("/api",clazz->clazz.isAnnotationPresent(RestController.class));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //log.info("Good evening，addCorsMappings，我要开始工作啦");
        registry.addMapping("/**").allowedOrigins("http://localhost:5173");
    }


     //加自定义拦截器JwtInterceptor，设置拦截规则
     @Override
     public void addInterceptors(InterceptorRegistry registry) {
     // log.info("Good evening,addInterceptors，我要开始工作啦");
         /*registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**")
                 .excludePathPatterns("/api/student/login")
                 .excludePathPatterns("/api/student/register");*/
       //log.info("Good evening,addInterceptors，我结束工作啦");
     }
}
