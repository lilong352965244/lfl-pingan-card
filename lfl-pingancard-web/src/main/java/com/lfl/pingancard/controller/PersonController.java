package com.lfl.pingancard.controller;

import com.lfl.pingancard.pojo.Person;
import com.lfl.pingancard.pojo.User;
import com.lfl.pingancard.service.PersonService;
import com.lfl.pingancard.service.UserService;
import com.lfl.response.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author: lifalong
 * @create: 2019-11-09 14:18
 * @description:
 **/
@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;


    /**
     * 添加用户信息
     *
     * @param person
     * @return
     */
    @PostMapping("add")
    public ResultBody addPerson(@Valid @RequestBody Person person,
                                HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        User user = userService.queryUserByUsername(username);  //获得该客户的信息
        if (user != null) {
            person.setUserId(user.getId());
            Boolean boo = this.personService.savePerson(person);
            if (boo) {
                return ResultBody.success("添加客户成功");
            }
        }
        return ResultBody.error("添加客户失败");
    }

    /**
     * 修改客户信息
     *
     * @param person
     * @return
     */
    @PostMapping("update")
    public ResultBody updatePerson(@RequestBody Person person) {
        if (person.getId() == null) {
            return ResultBody.error("客户主键不能为空");
        }
        this.personService.updatePersonById(person);
        return ResultBody.success("修改成功");
    }

    /**
     * 删除客户信息
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResultBody deletePerson(@PathVariable Long id) {
        this.personService.deletePersonById(id);
        return ResultBody.success("删除成功");
    }




}
