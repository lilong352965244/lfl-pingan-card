package com.lfl.pingancard.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "联系人id不能为空")
    private Long personId;

    /**
     * 图片地址，最多9个图片
     */
    @NotBlank(message = "图片地址不能为空")
    private String imagesUrl;

    /**
     * 要删除的图片地址
     */
    @Transient
    private String delImagesUrl;

    /**
     * 图片描述
     */
    private String imgDescription;

    /**
     * 图片个数,默认9张
     */
    private Integer imageNumber;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;


    /**
     * 创建人
     */
    private String createName;

}