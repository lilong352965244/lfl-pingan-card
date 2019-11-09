package com.lfl.pingancard.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Table(name = "tb_images")
@Data
public class Images {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * person表主键id
     */
    @NotBlank(message = "联系人id不能为空")
    private Long personId;

    /**
     * 图片地址，最多10个图片
     */
    @NotBlank(message = "图片地址不能为空")
    private String imagesUrl;

    /**
     * 图片描述
     */
    private String imgDescription;

    /**
     * 图片个数,默认10张
     */
    private Integer imageNumber;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastUpdate;

    /**
     * 创建人
     */
    private String createName;

}