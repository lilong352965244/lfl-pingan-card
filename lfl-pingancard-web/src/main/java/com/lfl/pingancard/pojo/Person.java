package com.lfl.pingancard.pojo;

import javax.persistence.*;

@Table(name = "tb_person")
public class Person {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 客户名字
     */
    private String name;

    /**
     * 客户联系方式
     */
    private String phone;

    /**
     * 身份号
     */
    @Column(name = "person_card")
    private String personCard;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取客户名字
     *
     * @return name - 客户名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置客户名字
     *
     * @param name 客户名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取客户联系方式
     *
     * @return phone - 客户联系方式
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置客户联系方式
     *
     * @param phone 客户联系方式
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取身份号
     *
     * @return person_card - 身份号
     */
    public String getPersonCard() {
        return personCard;
    }

    /**
     * 设置身份号
     *
     * @param personCard 身份号
     */
    public void setPersonCard(String personCard) {
        this.personCard = personCard;
    }
}