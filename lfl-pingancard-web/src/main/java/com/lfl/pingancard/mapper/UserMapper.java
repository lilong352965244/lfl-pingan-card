package com.lfl.pingancard.mapper;

import com.lfl.pingancard.pojo.User;
import com.lfl.tkmapper.TkMapper;

import java.util.List;


public interface UserMapper extends TkMapper<User> {

    List<User> selUserAll2();
}