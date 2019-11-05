package com.lfl.pingancard.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_user")
public class User {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户手机号
     */
    private Integer mobile;

    /**
     * 0：过期；1：审核中；2：审核通过
     */
    @Column(name = "enable_status")
    private Byte enableStatus;

    /**
     * 充值金额：单位分
     */
    private Integer money;

    /**
     * 有效截止时间
     */
    @Column(name = "expire_time")
    private Date expireTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名称
     *
     * @return username - 用户名称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名称
     *
     * @param username 用户名称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取登录密码
     *
     * @return password - 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     *
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户手机号
     *
     * @return mobile - 用户手机号
     */
    public Integer getMobile() {
        return mobile;
    }

    /**
     * 设置用户手机号
     *
     * @param mobile 用户手机号
     */
    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取0：过期；1：审核中；2：审核通过
     *
     * @return enable_status - 0：过期；1：审核中；2：审核通过
     */
    public Byte getEnableStatus() {
        return enableStatus;
    }

    /**
     * 设置0：过期；1：审核中；2：审核通过
     *
     * @param enableStatus 0：过期；1：审核中；2：审核通过
     */
    public void setEnableStatus(Byte enableStatus) {
        this.enableStatus = enableStatus;
    }

    /**
     * 获取充值金额：单位分
     *
     * @return money - 充值金额：单位分
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * 设置充值金额：单位分
     *
     * @param money 充值金额：单位分
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * 获取有效截止时间
     *
     * @return expire_time - 有效截止时间
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 设置有效截止时间
     *
     * @param expireTime 有效截止时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后修改时间
     *
     * @return last_update_time - 最后修改时间
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param lastUpdateTime 最后修改时间
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}