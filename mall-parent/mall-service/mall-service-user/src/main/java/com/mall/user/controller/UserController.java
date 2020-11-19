package com.mall.user.controller;

import com.mall.common.base.controller.BaseHibernateController;
import com.mall.common.base.service.BaseHibernateService;
import com.mall.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * UserController
 * </p>
 *
 * @author yanglin
 * @date 2020-11-18 00:07:36
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseHibernateController<User> {


    @Autowired
    private BaseHibernateService<User> baseUserService;

    @RequestMapping("/login")
    public ResponseEntity<Object> login(String username, String password, HttpServletResponse response, HttpServletRequest request) {
        //1.从数据库中查询用户名对应的用户的对象
        User cond = new User();
        cond.setUsername(username);
        List<User> userList = (List<User>) baseUserService.listAccurate(cond).get("list");

        if (userList == null || userList.size() == 0) {
            //2.判断用户是否为空 为空返回数据
            return ResponseEntity.notFound().build();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
        //3如果不为空格 判断 密码是否正确 正确则登录成功
        if (BCrypt.checkpw(password, userList.get(0).getPassword())) {
            //成功
            Map<String, Object> info = new HashMap<>();
            info.put("role", "USER");
            info.put("success", "SUCCESS");
            info.put("userInfo", userList.get(0));

//            //1.生成令牌
//            String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(info), null);
//            //2.设置cookie中
//            Cookie cookie = new Cookie("Authorization", jwt);
//            response.addCookie(cookie);
//            //3.设置头文件中
//            response.setHeader("Authorization", jwt);

            return ResponseEntity.ok(info);
        } else {
            //失败
            Map<String, Object> info = new HashMap<>();
            info.put("error", "密码错误");
            return ResponseEntity.badRequest().body(info);
        }


    }

}
