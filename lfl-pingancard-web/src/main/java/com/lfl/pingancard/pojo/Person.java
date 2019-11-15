package com.lfl.pingancard.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_person")
@Data
public class Person implements Serializable {
    private static final long serialVersionUID = -3484884984126505918L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 客户名字
     */
    @NotBlank(message = "客户姓名不能为空")
    @Length(min = 2, max = 12)
    private String name;

    /**
     * 客户联系方式
     */
    private String phone;

    /**
     * 身份号
     */
    @NotBlank(message = "客户身份证号不能为空")
    @Pattern(regexp =
            "^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$",
            message = "身份证号格式有误")
    private String personCard;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;

}