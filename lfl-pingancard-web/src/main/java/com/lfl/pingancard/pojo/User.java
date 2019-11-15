package com.lfl.pingancard.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -6856882214532254997L;
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名称不能为空")
    @Length(min = 2, max = 12)
    private String username;

    /**
     * 登录密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 10)
    private String password;

    /**
     * 用户手机号
     */
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[3|4|5|6|7|8|9]\\d{9}$")
    private String mobile;

    /**
     * true:有效； false:无效
     */
    private Boolean valid;

    /**
     * 0：过期；1：审核中；2：审核通过
     */
    private Integer enableStatus;

    /**
     * 充值金额：单位分
     */
    private Integer money;

    /**
     * 会员级别:0：普通会员，1：超级管理员
     */
    private Integer level;

    /**
     * 有效截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date expireTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    @JsonIgnore
    private Date lastUpdateTime;

    /**
     * 备注
     */
    private String remarks;
}