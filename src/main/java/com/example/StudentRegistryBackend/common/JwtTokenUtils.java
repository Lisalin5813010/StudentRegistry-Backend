package com.example.StudentRegistryBackend.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.StudentRegistryBackend.model.Student;
import com.example.StudentRegistryBackend.service.StudentService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.annotation.Resource;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenUtils {

  private static StudentService staticStudentService;
  private static final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);

  @Resource
  private StudentService studentService;

  @PostConstruct
  public void setStudentService() {
    staticStudentService = studentService;
  }

  /**
   * 生成token
   */


  public static String genToken(String studentId, String password) {
    return JWT.create().withAudience(studentId) // 将 user id 保存到 token 里面,作为载荷
            .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期
            .sign(Algorithm.HMAC256(password)); // 以 password 作为 token 的密钥
  }

  /**
   * 获取当前登录的用户信息
   */


  public static Student getCurrentUser() {
    String token = null;
    try {

      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      token = request.getHeader("token");
      if (StrUtil.isBlank(token)) {
        token = request.getParameter("token");
      }
      if (StrUtil.isBlank(token)) {
        log.error("获取当前登录的token失败， token: {}", token);
        return null;
      }
      // 解析token，获取用户的id
      String studentId = JWT.decode(token).getAudience().get(0);
      return staticStudentService.findByById(Long.valueOf(Integer.valueOf(studentId)));
    } catch (Exception e) {
      log.error("获取当前登录的管理员信息失败, token={}", token,  e);
      return null;
    }
  }
}