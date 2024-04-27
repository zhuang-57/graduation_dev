package com.example.h_item.interceptor;

import cn.hutool.core.util.StrUtil;
import com.example.h_item.cache.UserLoginCache;
import com.example.h_item.common.StatusCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 登录校验
 *
 * @author fancy
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private UserLoginCache userLoginCache;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getParameter("token");
        if (StrUtil.isBlank(token)) {
            log.info("用户没有权限进行访问：找不到对应token");
            StatusCodeException.throwException("用户未登录");
            return false;
        }
        Long userId = userLoginCache.getUserIdByToken(token);
        if (Objects.isNull(userId)) {
            log.info("用户传了token，但是服务器中无对应的user信息，token:{}", token);
            StatusCodeException.throwException("用户未登录");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }

}