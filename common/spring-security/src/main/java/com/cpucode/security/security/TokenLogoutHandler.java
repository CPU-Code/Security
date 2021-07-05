package com.cpucode.security.security;

import com.cpucode.utils.utils.R;
import com.cpucode.utils.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出处理器
 *
 * @author : cpucode
 * @date : 2021/7/5
 * @time : 23:32
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public class TokenLogoutHandler implements LogoutHandler {
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 退出
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     */
    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        // 从header里面获取token
        String token = httpServletRequest.getHeader("token");

        // token不为空，移除token，从redis删除token
        if(token != null) {
            //移除
            tokenManager.removeToken(token);

            //从token获取用户名
            String username = tokenManager.getUserInfoFromToken(token);

            redisTemplate.delete(username);
        }

        ResponseUtil.out(httpServletResponse, R.ok());
    }
}
