package com.zm.security.securitys.handler;

import com.zm.security.common.config.JWTConfig;
import com.zm.security.common.util.JWTTokenUtil;
import com.zm.security.common.util.ResultUtil;
import com.zm.security.securitys.entity.SelfUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 登录成功处理类
 * @Author mbql
 * @CreateTime 2019/10/3 9:13
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * 登录成功返回结果
     * @Author mbql
     * @CreateTime 2019/10/3 9:27
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        // 组装JWT
        SelfUserEntity selfUserEntity =  (SelfUserEntity) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(selfUserEntity);
        token = JWTConfig.tokenPrefix + token;
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code","200");
        resultData.put("msg", "登录成功");
        resultData.put("token",token);
        ResultUtil.responseJson(response,resultData);
    }
}
