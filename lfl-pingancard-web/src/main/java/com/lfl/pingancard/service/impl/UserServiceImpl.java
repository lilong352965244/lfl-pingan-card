package com.lfl.pingancard.service.impl;

import com.lfl.constant.CommonConstant;
import com.lfl.exception.CustomException;
import com.lfl.pingancard.mapper.UserMapper;
import com.lfl.pingancard.pojo.User;
import com.lfl.pingancard.service.UserService;
import com.lfl.utils.CookieUtils;
import com.lfl.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author: lifalong
 * @create: 2019-11-06 09:44
 * @description:
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public Boolean checkData(String username, String mobile) {
        User user = new User();
        user.setUsername(username);
        user.setMobile(mobile);

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orEqualTo("username", username).orEqualTo("mobile", mobile);
        return this.userMapper.selectCountByExample(example) == 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveUser(User user) {
        // 先校验用户是否已存在
        if (!checkData(user.getUsername(), user.getMobile())) {
            throw new CustomException("-1", "名字或手机号已存在");
        }
        user.setEnableStatus(1);
        user.setLevel(0);

        Date date = new Date();
        user.setExpireTime(date);
        user.setCreateTime(date);
        user.setLastUpdateTime(date);
        return this.userMapper.insertSelective(user) == 1;
    }

    @Override
    public Boolean login(String mobile, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setMobile(mobile);
        user.setPassword(password);

        User userSelect = this.userMapper.selectOne(user);

        // 必须为有效状态，才能登陆
        if (userSelect != null && userSelect.getValid()) {
            // 设置1天的有效时间
            String token = TokenUtil.createJwtToken(userSelect.getUsername(),Long.toString(userSelect.getId()));
            // 产生Cookie
            CookieUtils.setCookie(request, response, "TOKEN", token, CommonConstant.REDIS_EXPIRE_SECONDS);
            return true;
        }
        return false;
    }

    @Override
    public User queryUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return this.userMapper.selectOne(user);
    }
}
