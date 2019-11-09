package com.lfl.pingancard.service.impl;

import com.lfl.pingancard.mapper.ImagesMapper;
import com.lfl.pingancard.mapper.PersonMapper;
import com.lfl.pingancard.pojo.Images;
import com.lfl.pingancard.pojo.Person;
import com.lfl.pingancard.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
