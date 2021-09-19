package com.liang.pigeon.route.service;

import com.liang.pigeonim.common.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/12 1:21 PM
 */
@Service
@Slf4j
public class CacheService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 保存和检查用户登录状态
     * @param userId
     * @return
     */
    public boolean saveAndCheckUserLoginStatus(Long userId) {

        Long add = redisTemplate.opsForSet().add(Constant.LOGIN_STATUS_PREFIX, userId.toString());
        if (add == 0) {
            return false;
        }else {
            return true;
        }
    }
}
