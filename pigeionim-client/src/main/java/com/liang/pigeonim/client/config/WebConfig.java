package com.liang.pigeonim.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/9 3:40 PM
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private Environment environment;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (environment.acceptsProfiles(Profiles.of("dev", "test"))) {
            registry.addResourceHandler("/favicon.ico")
                    .addResourceLocations("classpath:/static/");

        }
    }
}
