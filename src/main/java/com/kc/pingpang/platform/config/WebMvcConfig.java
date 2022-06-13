package com.kc.pingpang.platform.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final List<String> EXCLUDE_PATH_PATTERNS = new ArrayList<>();

    static {
        EXCLUDE_PATH_PATTERNS.add("/*");
        EXCLUDE_PATH_PATTERNS.add("/index.html");
        EXCLUDE_PATH_PATTERNS.add("/services/rs/admin/account/login");
        EXCLUDE_PATH_PATTERNS.add("/services/rs/admin/account/logout");
        EXCLUDE_PATH_PATTERNS.add("/services/rs/console/competition/join");
        EXCLUDE_PATH_PATTERNS.add("/services/rs/console/competition/detail");
        EXCLUDE_PATH_PATTERNS.add("/services/rs/console/order/create");
        EXCLUDE_PATH_PATTERNS.add("/services/rs/console/order/notify");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(EXCLUDE_PATH_PATTERNS)
                .order(1);
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 序列换成json时,将所有的long变成string
         * 因为js中得数字类型不能包含所有的java long值
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        objectMapper.registerModule(simpleModule);

        jackson2HttpMessageConverter.setObjectMapper(objectMapper);

        converters.add(0, jackson2HttpMessageConverter);
    }
}
