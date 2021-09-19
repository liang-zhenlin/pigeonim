package com.liang.pigeon.route.service;

import com.liang.pigeon.route.entity.req.LoginReqBO;
import com.liang.pigeon.route.entity.req.RegisterInfoReqBO;
import com.liang.pigeon.route.entity.res.RegisterInfoResVO;
import com.liang.pigeonim.common.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/11 6:49 PM
 */
@Service
@Slf4j
public class RouteService {

    @Resource
    private CacheService cacheService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private DiscoveryClient discoveryClient;


    /**
     * 注册
     * @param registerInfoReqBO
     * @return
     */
    public RegisterInfoResVO register(RegisterInfoReqBO registerInfoReqBO) {

        String key = Constant.ACCOUNT_PREFIX + registerInfoReqBO.getUserId();
        String name = redisTemplate.opsForValue().get(registerInfoReqBO.getUserName());
        RegisterInfoResVO registerInfoResVO = null;
        if (null == name) {
            redisTemplate.opsForValue().set(key, registerInfoReqBO.getUserName());
            redisTemplate.opsForValue().set(registerInfoReqBO.getUserName(), key);
        }else {
            long userId = Long.parseLong(name.split(":")[1]);
            registerInfoResVO = RegisterInfoResVO.builder().userId(userId).userName(registerInfoReqBO.getUserName()).build();
        }

        return registerInfoResVO;
    }


    /**
     * 登录
     * @param loginReqBO
     * @return
     */
    public boolean login(LoginReqBO loginReqBO) {

        String key = Constant.ACCOUNT_PREFIX + loginReqBO.getUserId();
        String userName = redisTemplate.opsForValue().get(key);
        if (null == userName) {
            return false;
        }
        if (!userName.equals(loginReqBO.getUserName())) {
            return false;
        }
        boolean status = cacheService.saveAndCheckUserLoginStatus(loginReqBO.getUserId());
        if (status == false) {
            return false;
        }
        return true;
    }


    public List<String> getServerList() {

        List<String> services = discoveryClient.getServices();
        List<ServiceInstance> instances = null;
        List<String> serviceList = new ArrayList<>();
        List<String> serviceNames = services.stream().filter(v -> "server".equals(v.split("-")[1])).collect(Collectors.toList());
        for (String service : serviceNames) {
            instances = discoveryClient.getInstances(service);
            String host = instances.get(0).getHost();
            int port = instances.get(0).getPort();
            serviceList.add(host + ":" + port);
        }
        return serviceList;
    }

}
