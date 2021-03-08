package com.lin945.makefriend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author lin945
 * @date 2021/3/7 9:21
 * @description
 */

public class JwtUtils {
    /**
     * 获取jwt token 并存储redis
     * @param userId
     * @return
     */
    public static String getToken(String userId) {
        String token="";
        String randomGenerationId = randomGenerationId();
        token= JWT.create().withAudience(String.valueOf(userId))
                .sign(Algorithm.HMAC256(randomGenerationId));
        RedisUtil.set(String.valueOf(userId),randomGenerationId,10, TimeUnit.MINUTES);
        return token;
    }

    /**
     * 随机生成字符串
     * @return
     */
    public static String randomGenerationId() {
        Random random = new Random();
        String randomGenerationId = "";
        for (int i=1;i<33;i++) {
            String letterORnumber = random.nextInt(2)%2==0?"letter":"number";
            if(letterORnumber.equalsIgnoreCase("letter")) {
                int Random_num = random.nextInt(2)%2==0?65:97;
                randomGenerationId+=(char)(Random_num+random.nextInt(26));
            }else if(letterORnumber.equalsIgnoreCase("number")){
                randomGenerationId += String.valueOf(random.nextInt(10));
            }
        }
        return randomGenerationId;
    }

    public static String jwtVerifyGetUserId(String token) {
        String userId = JWT.decode(token).getAudience().get(0);
        // 验证 token
        String rs = RedisUtil.get(userId);
        if (rs == null) {
            throw new JWTDecodeException("token过期");
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(rs)).build();
        jwtVerifier.verify(token);
        return userId;
    }

}
