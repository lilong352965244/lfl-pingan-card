package com.lfl.pingancard.service;

import com.lfl.common.pojo.PageResult;
import com.lfl.pingancard.pojo.Person;

/**
 * @author: lifalong
 * @create: 2019-11-09 14:03
 * @description:
 **/

public interface PersonService {
    /**
     * 添加客户信息
     * @param person
     * @return
     */
    Boolean savePerson(Person person);


    /**
     * 根据主键修改person
     * @param person
     */
    void updatePersonById(Person person);

    /**
     * 根据主键删除person
     * @param id
     */
    void deletePersonById(Long id);

    /**
     * 分页查询客户信息
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    PageResult<Person> queryPersonPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key);
}
