package com.sunbing.background.config;

import com.sunbing.background.interceptor.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 *
 * @author sunbing
 * @version 1.0
 * @since 2020/12/10 16:04
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/login").excludePathPatterns("/toLoginPage").excludePathPatterns("/static/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * ResourceHandler:相当于以static开头才会视为资源文件。（等同于yml文件配置spring.mvc.static-path-pattern）
         * 配置前：http://localhost:8089/layui/layui.js
         * 配置后：http://localhost:8089/static/layui/layui.js
         * 这里配置是为了统一访问路径。方便拦截器不拦截静态资源（只需排除/static/**）
         */
        //ResourceLocations:去static目录下面去找，不配置默认/static
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


}
