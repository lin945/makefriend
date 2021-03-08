package com.lin945.makefriend.config;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.lin945.makefriend.pojo.ResultBody;
import com.lin945.makefriend.pojo.model.Users;
import com.lin945.makefriend.service.UsersService;
import com.lin945.makefriend.utils.JwtUtils;
import com.lin945.makefriend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lin945
 * @date 2021/3/5 19:58
 * @description
 */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Autowired
    UsersService usersService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            response.setContentType("application/json;charset=UTF-8");
            ResultBody resultBody = ResultBody.error().code(CodeConfig.SFM_JYM_NOTNULL.code)
                    .message(CodeConfig.SFM_JYM_NOTNULL.message);
            response.getWriter().write(JSONUtil.toJsonStr(resultBody));
            return false;
        }
        String userId;
        try {
            userId = JwtUtils.jwtVerifyGetUserId(token);
        } catch (JWTDecodeException j) {
            response.setContentType("application/json;charset=UTF-8");
            ResultBody resultBody = ResultBody.error().message(j.getMessage()).code(CodeConfig.TOKEN_ERROR.code);
            response.getWriter().write(JSONUtil.toJsonStr(resultBody));
            return false;
        }
        request.setAttribute("userid", userId);
        return true;
    }
}
