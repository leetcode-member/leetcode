package com.leetcode.util.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.leetcode.config.BeanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT
 * @author Jarvan
 * @version 1.0
 * @create 2020/11/29 16:31
 */
@Component
public class TokenUtil {
    @Autowired
    BeanConfig beanConfig;
    /**
     * 加密token.
     */
    public  String getToken(String username, String roleCode, String ...ipConfirm){
        //这个是放到负载payLoad 里面
        String token = JWT
                .create()
                .withClaim("username", username)
                .withClaim("roleCode", roleCode)
                .sign(Algorithm.HMAC256(beanConfig.getPrivateKey()+ipConfirm[0]));
        return token;
    }

    /**
     *  解析token.
     * @param token token
     * @param ipConfirm 地址的ip，每一次的ip是不一样的.每一次的token是不一样的.
     * @return HashMap的key
     *      * 1.username
     *      * 2.roleCode 例如 admin
     *      * 3.url
     */
    public Map<String,String> parseToken(String token, String ipConfirm){
        HashMap<String, String> map = new HashMap<>();
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(beanConfig.getPrivateKey()+ipConfirm))
                .build().verify(token);
        Claim username = decodedjwt.getClaim("username");
        Claim userRole = decodedjwt.getClaim("roleCode");
        map.put("username",username.asString());
        map.put("roleCode",userRole.asString());
        return map;
    }

}
