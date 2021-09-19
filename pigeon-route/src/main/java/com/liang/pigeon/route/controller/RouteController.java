package com.liang.pigeon.route.controller;

import com.liang.pigeon.route.entity.RouteInfo;
import com.liang.pigeon.route.entity.req.LoginReqBO;
import com.liang.pigeon.route.entity.req.RegisterInfoReqBO;
import com.liang.pigeon.route.entity.res.PigeonIMServerResVO;
import com.liang.pigeon.route.entity.res.RegisterInfoResVO;
import com.liang.pigeon.route.handle.LoopHandle;
import com.liang.pigeon.route.service.RouteService;
import com.liang.pigeonim.common.entity.res.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/11 4:22 PM
 */
@RestController
@Slf4j
@RefreshScope
@RequestMapping(value = "/route")
public class RouteController {

    @Resource
    private RouteService routeService;

    @Resource
    private LoopHandle loopHandle;

    /**
     * 注册账号
     * @param registerInfoReqBO
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public BaseResponse<RegisterInfoResVO> register(@RequestBody RegisterInfoReqBO registerInfoReqBO) {
        BaseResponse<RegisterInfoResVO> res = new BaseResponse();
        long userId = System.currentTimeMillis();
        RegisterInfoReqBO info = RegisterInfoReqBO.builder()
                .userId(userId)
                .userName(registerInfoReqBO.getUserName())
                .build();
        RegisterInfoResVO registerInfoResVO = routeService.register(info);

        return BaseResponse.success(registerInfoResVO, null);
    }


    public BaseResponse<PigeonIMServerResVO> login(@RequestBody LoginReqBO loginReqBO) {
        String routeServer = loopHandle.routeServer(routeService.getServerList(), String.valueOf(loginReqBO.getUserId()));
        RouteInfo routeInfo = RouteInfo.builder().ip(routeServer.split(":")[0]).httpPort(Integer.parseInt(routeServer.split(":")[1])).build();

        return null;
    }


    @RequestMapping("/testGetServiceList")
    public List<String> testGetServiceList() {
        return routeService.getServerList();
    }

}
