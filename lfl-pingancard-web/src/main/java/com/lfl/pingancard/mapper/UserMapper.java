package com.lfl.pingancard.mapper;

import com.lfl.pingancard.pojo.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserMapper extends Mapper<User> {

    List<User> selUserAll2();
}