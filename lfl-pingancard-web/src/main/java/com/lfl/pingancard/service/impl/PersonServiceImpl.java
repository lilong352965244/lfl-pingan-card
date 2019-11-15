package com.lfl.pingancard.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Page;
import com.lfl.common.pojo.PageResult;
import com.lfl.pingancard.mapper.ImagesMapper;
import com.lfl.pingancard.mapper.PersonMapper;
import com.lfl.pingancard.pojo.Images;
import com.lfl.pingancard.pojo.Person;
import com.lfl.pingancard.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author: lifalong
 * @create: 2019-11-09 14:09
 * @description:
 **/
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private ImagesMapper imagesMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean savePerson(Person person) {
        Date date = new Date();
        person.setCreateTime(date);
        person.setLastUpdateTime(date);
        return this.personMapper.insertSelective(person) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePersonById(Person person) {
        Date date = new Date();
        person.setLastUpdateTime(date);
        this.personMapper.updateByPrimaryKeySelective(person);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePersonById(Long id) {
        // tb_person表中删除
        this.personMapper.deleteByPrimaryKey(id);

        // tb-images表删除
        Images images = new Images();
        images.setPersonId(id);
        Images imagesSel = imagesMapper.selectOne(images);

        // 删除服务器的图片，查询出的URL,根据URL去删除

    }

    @Override
    public PageResult<Person> queryPersonPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        // 设置查询 最多100条
        PageHelper.startPage(page, Math.min(rows, 100));

        // 创建查询条件
        Example example = new Example(Person.class);
        Example.Criteria criteria = example.createCriteria();

        // 条件过滤
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%");
        }

        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + (desc ? " DESC" : " ASC"));
        }

        Page<Person> pageInfo = (Page<Person>) this.personMapper.selectByExample(example);

        return new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getResult());
    }
}
