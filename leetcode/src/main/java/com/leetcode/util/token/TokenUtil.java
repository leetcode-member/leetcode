package com.leetcode.util.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.leetcode.config.BeanConfig;
import com.leetcode.model.constant.TokenConstant;
import jdk.nashorn.internal.parser.Token;
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
    public  String getToken(String userId, String userRole){
        //这个是放到负载payLoad 里面
        String token = JWT
                .create()
                .withClaim(TokenConstant.USER_ID_CLAIN, userId)
                .withClaim(TokenConstant.USER_ROLE_CLAIN, userRole)
                .withClaim(TokenConstant.TIME_STAMP_CLAIN,System.currentTimeMillis())
                .sign(Algorithm.HMAC256(beanConfig.getPrivateKey()));
        return token;
    }

    /**
     *  解析token.
     *  下面的字段使用 TokenConstant 进行封装
     * @param token token
     * @return HashMap的key
     *      {
     *     "userId": "3412435312",
     *     "userRole": "ROLE_USER",
     *     "timeStamp": "134143214"
     *      }
     */
    public Map<String,String> parseToken(String token){
        HashMap<String, String> map = new HashMap<>();
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(beanConfig.getPrivateKey()))
                .build().verify(token);
        Claim userId = decodedjwt.getClaim(TokenConstant.USER_ID_CLAIN);
        Claim userRole = decodedjwt.getClaim(TokenConstant.USER_ROLE_CLAIN);
        Claim timeStamp = decodedjwt.getClaim(TokenConstant.TIME_STAMP_CLAIN);
        map.put(TokenConstant.USER_ID_CLAIN,userId.asString());
        map.put(TokenConstant.USER_ROLE_CLAIN,userRole.asString());
        map.put(TokenConstant.TIME_STAMP_CLAIN,timeStamp.asLong().toString());
        return map;
    }

    /**
     * 获得 userId
     * @param token
     * @return
     */
    public String getUserId(String token) {
        return this.parseToken(token).get(TokenConstant.USER_ID_CLAIN);
    }

    /**
     * 获得 userRole
     * @param token
     * @return
     */
    public String getUserRole(String token) {
        return this.parseToken(token).get(TokenConstant.USER_ROLE_CLAIN);
    }

    /**
     * 获得 时间戳
     * @param token
     * @return
     */
    public String getTimeStamp(String token) {
        return this.parseToken(token).get(TokenConstant.TIME_STAMP_CLAIN);
    }


}
