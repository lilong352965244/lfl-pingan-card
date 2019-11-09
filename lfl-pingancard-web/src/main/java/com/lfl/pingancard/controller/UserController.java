package com.lfl.pingancard.controller;

import com.lfl.pingancard.pojo.User;
import com.lfl.pingancard.service.UserService;
import com.lfl.response.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author: lifalong
 * @create: 2019-11-05 15:27
 * @description:
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param mobile
     * @param Password
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/login")
    public ResultBody login(@RequestParam(name = "mobile") String mobile,
                            @RequestParam(name = "password") String Password,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        Boolean boo = this.userService.login(mobile, Password, request, response);
        if (!boo) {
            return ResultBody.error("登录账号或密码错误");
        }
        return ResultBody.success("登录成功");
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResultBody addUser(@Valid @RequestBody User user) {
        Boolean boo = this.userService.saveUser(user);
        if (!boo) {
            return ResultBody.error("注册失败");
        }
        return ResultBody.success("注册成功");
    }

    /**
     * 根据用户名称获得用户信息
     *
     * @param request
     * @return
     */
    @GetMapping("query")
    public ResultBody queryUser(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        User user = this.userService.queryUserByUsername(username);
        if (user == null) {
            return ResultBody.error("查询用户信息失败");
        }
        user.setPassword(null);
        return ResultBody.success(user);
    }


}
