package com.liang.pigeonim.common.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.liang.pigeonim.common.util.FastJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/6/16 11:30 AM
 */
@Configuration
public class FastjsonConfig implements WebMvcConfigurer {

    @Autowired
    Environment environment;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ParserConfig.getGlobalInstance().setSafeMode(true);
        FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        if (environment.acceptsProfiles(Profiles.of("dev", "test"))) {
            fastJsonConfig.setSerializerFeatures(
                    // 保留map空的字段
                    SerializerFeature.WriteMapNullValue,
                    // 将String类型的null转成""
                    SerializerFeature.WriteNullStringAsEmpty,
                    // 将Number类型的null转成0
                    // SerializerFeature.WriteNullNumberAsZero,
                    // 将List类型的null转成[]
                    SerializerFeature.WriteNullListAsEmpty,
                    // 将Boolean类型的null转成false
                    // SerializerFeature.WriteNullBooleanAsFalse,
                    // SerializerFeature.WriteDateUseDateFormat,
                    SerializerFeature.PrettyFormat,
                    // 避免循环引用
                    SerializerFeature.DisableCircularReferenceDetect);
        } else {
            fastJsonConfig.setSerializerFeatures(
                    // 保留map空的字段
                    SerializerFeature.WriteMapNullValue,
                    // 将String类型的null转成""
                    SerializerFeature.WriteNullStringAsEmpty,
                    // 将Number类型的null转成0
                    // SerializerFeature.WriteNullNumberAsZero,
                    // 将List类型的null转成[]
                    SerializerFeature.WriteNullListAsEmpty,
                    // 将Boolean类型的null转成false
                    // SerializerFeature.WriteNullBooleanAsFalse,
                    // SerializerFeature.WriteDateUseDateFormat,
                    // SerializerFeature.PrettyFormat,
                    // 避免循环引用
                    SerializerFeature.DisableCircularReferenceDetect);
        }

        // 先不全局配置
        //fastJsonConfig.setSerializeFilters(new BigDecimalValueFilter());
        // 设置统一序列化规则
        FastJsonUtil.setDefaultSerializerFeatures(fastJsonConfig.getSerializerFeatures());
        fastConvert.setFastJsonConfig(fastJsonConfig);

        // 解决中文乱码
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastConvert.setSupportedMediaTypes(fastMediaTypes);

        converters.add(0, fastConvert);
    }
}
