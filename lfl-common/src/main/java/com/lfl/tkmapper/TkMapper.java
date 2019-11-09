package com.lfl.tkmapper;


import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: lifalong
 * @create: 2019-10-24 14:12
 * @description: 统一的TkMapper接口
 **/
public interface TkMapper<T> extends Mapper<T>, SelectByIdListMapper<T, Long> {
}
