package com.cpucode.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : cpucode
 * @date : 2021/7/5
 * @time : 23:33
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Component
public class TokenManager {
    /**
     * token有效时长
     */
    private long tokenEcpiration = 24 * 60*60*1000;

    /**
     * 编码秘钥
     */
    private String tokenSignKey = "cpucode";

    /**
     * 使用jwt根据用户名生成token
     * @param username
     * @return
     */
    public String createToken(String username) {
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+tokenEcpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();

        return token;
    }

    /**
     * 根据token字符串得到用户信息
     * @param token
     * @return
     */
    public String getUserInfoFromToken(String token) {
        String userinfo = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();

        return userinfo;
    }

    /**
     * 删除token
     * @param token
     */
    public void removeToken(String token) { }
}
