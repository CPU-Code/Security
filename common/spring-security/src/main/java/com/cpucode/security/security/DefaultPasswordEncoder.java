package com.cpucode.security.security;

import com.cpucode.utils.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author : cpucode
 * @date : 2021/7/5
 * @time : 23:30
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }
    public DefaultPasswordEncoder(int strength) {
    }

    /**
     * 进行MD5加密
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        return MD5.encrypt(charSequence.toString());
    }

    /**
     * 进行密码比对
     * @param charSequence
     * @param s
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(MD5.encrypt(charSequence.toString()));
    }
}
