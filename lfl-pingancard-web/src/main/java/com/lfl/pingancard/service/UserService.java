package com.lfl.pingancard.service;

import com.lfl.pingancard.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: lifalong
 * @create: 2019-11-06 09:43
 * @description:
 **/
public interface UserService {

    /**
     * 校验用户数据
     *
     * @param username
     * @param mobile
     * @return
     */
    Boolean checkData(String username, String mobile);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    Boolean saveUser(User user);

    /**
     * 用户登录
     *
     * @param mobile
     * @param password
     * @return
     */
    Boolean login(String mobile, String password, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据username 获得use信息
     * @param username
     * @return
     */
    User queryUserByUsername(String username);
}
