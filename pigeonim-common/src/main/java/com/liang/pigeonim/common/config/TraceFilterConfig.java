package com.liang.pigeonim.common.config;

import com.liang.pigeonim.common.filter.TraceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/6/16 9:30 AM
 */
@Configuration
public class TraceFilterConfig {

    @Bean
    public FilterRegistrationBean traceFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TraceFilter());
        List<String> urlList = new ArrayList<>();
        urlList.add("/*");
        registration.setUrlPatterns(urlList);
        registration.setName("traceFilter");
        registration.setOrder(1);
        return registration;
    }
}
