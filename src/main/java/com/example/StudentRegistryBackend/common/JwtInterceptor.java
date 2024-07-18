package com.example.StudentRegistryBackend.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.StudentRegistryBackend.model.Student;
import com.example.StudentRegistryBackend.exception.CustomException;
import com.example.StudentRegistryBackend.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * jwt拦截器 相当于安检员，对token进行安全检查
 */

@Component
public class JwtInterceptor implements HandlerInterceptor {


  private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);


  @Resource
  private StudentService studentService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    log.info("JwtInterceptor preHandle method invoked for URI: {}", request.getRequestURI());
    // 1. 从http请求的header中获取token
    String token = request.getHeader("token");
    log.info("token is: " + token);
    if (StrUtil.isBlank(token)) {
      // 如果没拿到，我再去参数里面拿一波试试  /api/admin?token=xxxxx
     // token = request.getParameter("token");
      log.warn("No token provided, throwing CustomException");
      throw new CustomException("无token，请重新登录");
    }
    log.info("Token retrieved: {}", token);
    // 2. 开始执行认证
    /*if (StrUtil.isBlank(token)) {
      throw new CustomException("无token，请重新登录");
    }*/
    // 获取 token 中的userId
    String userId;
    Student student;
    try {
      userId = JWT.decode(token).getAudience().get(0);
      // 根据token中的userid查询数据库
      student = studentService.findByById(Long.parseLong(userId));
      log.info("正在进行登陆鉴权中");
    } catch (Exception e) {
      String errMsg = "token验证失败，请重新登录";
      log.error("报错啦啦啦啦啦啦" + errMsg + ", token=" + token, e);
      throw new CustomException(errMsg);
    }
    if (student == null) {
      throw new CustomException("用户不存在，请重新登录");
    }
    try {
      // 用户密码加签验证 token
      JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(student.getPassword())).build();
      jwtVerifier.verify(token); // 验证token
    } catch (JWTVerificationException e) {
      throw new CustomException("token验证失败，请重新登录");
    }
    log.info("token验证成功，允许放行");
    return true;
  }
}