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
import org.springframework.util.DigestUtils;
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
        user.setValid(true);

        Date date = new Date();
        user.setExpireTime(date);
        user.setCreateTime(date);
        user.setLastUpdateTime(date);
        return this.userMapper.insertSelective(user) == 1;
    }

    @Override
    public Boolean login(String mobile, String password, String loginTime, String version,
                         HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setMobile(mobile);
        User userSelect = this.userMapper.selectOne(user);
        // 必须为有效状态，才能登陆
        if (userSelect != null) {

            if (!userSelect.getValid()) {
                throw new CustomException("-1", "账号已被禁用！请联系15670616655");
            }

            String newPassword = userSelect.getPassword() + version + loginTime;
            String passwordMd5 = DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(newPassword.getBytes()).getBytes());

            System.out.println(passwordMd5);
            if (password.equals(passwordMd5)) {
                // 设置3天的有效时间
                String token = TokenUtil.createJwtToken(userSelect.getUsername(), Long.toString(userSelect.getId()),
                        userSelect.getExpireTime());
                // 产生Cookie
                CookieUtils.setCookie(request, response, "TOKEN", token, CommonConstant.REDIS_EXPIRE_SECONDS);
                return true;
            }
        }
        return false;
    }

    @Override
    public User queryUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        User userSelect = this.userMapper.selectOne(user);

        Date date = new Date(); // 现在时间
        if (userSelect.getExpireTime().before(date) && (userSelect.getEnableStatus() == 2)) {
            user.setId(userSelect.getId());
            user.setUsername(username);
            user.setEnableStatus(0);  // 代表有效时间已过期
            if (updateUser(user)) {
                userSelect.setEnableStatus(0);
                return userSelect;
            } else {
                return null;
            }
        }
        return userSelect;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUser(User user) {
        Date date = new Date();
        user.setLastUpdateTime(date);
        return this.userMapper.updateByPrimaryKeySelective(user) == 1;
    }
}
