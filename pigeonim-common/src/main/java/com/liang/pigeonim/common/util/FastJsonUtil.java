package com.liang.pigeonim.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/6/16 11:31 AM
 */
@Slf4j
public class FastJsonUtil {

    private static SerializeConfig config;

    static {
        config = new SerializeConfig();
    }

    private static SerializerFeature[] features = {};

    public static void setDefaultSerializerFeatures(SerializerFeature[] features) {
        FastJsonUtil.features = features;
    }

    public static String toJSONString(Object object) {
        try {
            return JSON.toJSONString(object, config, features);
        } catch (Exception e) {
            log.error("fastjson转换错误", e);
        }
        return null;
    }

    public static Object[] toArray(String text) {
        try {
            return toArray(text, null);
        } catch (Exception e) {
            log.error("fastjson转换错误", e);
        }
        return null;
    }

    public static <T> Object[] toArray(String text, Class<T> clazz) {
        try {
            return JSON.parseArray(text, clazz).toArray();
        } catch (Exception e) {
            log.error("fastjson转换错误", e);
        }
        return null;
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        try {
            return JSON.parseObject(text, clazz);
        } catch (Exception e) {
            log.error("fastjson转换错误", e);
        }
        return null;
    }

    public static Map<?, ?> toMap(String json) {
        try {
            return JSON.parseObject(json);
        } catch (Exception e) {
            log.error("fastjson转换错误", e);
        }
        return null;
    }

    public static <T> List<T> toList(String text, Class<T> clazz) {
        try {
            return JSON.parseArray(text, clazz);
        } catch (Exception e) {
            log.error("fastjson转换错误", e);
        }
        return null;
    }

    public static Object getValueFromJson(final String json, final String key) {
        try {
            if (StringUtils.isBlank(json) || StringUtils.isBlank(key)) {
                return null;
            }
            return JSON.parseObject(json).getString(key);
        } catch (Exception e) {
            log.error("fastjson转换错误", e);
        }
        return null;
    }
}
