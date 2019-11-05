package com.lfl.pingancard.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_images")
public class Images {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * person表主键id
     */
    @Column(name = "person_id")
    private Integer personId;

    /**
     * 图片地址，最多10个图片
     */
    @Column(name = "images_url")
    private String imagesUrl;

    /**
     * 图片描述
     */
    @Column(name = "img_description")
    private String imgDescription;

    /**
     * 图片个数,默认10张
     */
    @Column(name = "image_number")
    private Integer imageNumber;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "last_update")
    private Date lastUpdate;

    /**
     * 创建人
     */
    @Column(name = "create_name")
    private String createName;

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
     * 获取person表主键id
     *
     * @return person_id - person表主键id
     */
    public Integer getPersonId() {
        return personId;
    }

    /**
     * 设置person表主键id
     *
     * @param personId person表主键id
     */
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    /**
     * 获取图片地址，最多10个图片
     *
     * @return images_url - 图片地址，最多10个图片
     */
    public String getImagesUrl() {
        return imagesUrl;
    }

    /**
     * 设置图片地址，最多10个图片
     *
     * @param imagesUrl 图片地址，最多10个图片
     */
    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    /**
     * 获取图片描述
     *
     * @return img_description - 图片描述
     */
    public String getImgDescription() {
        return imgDescription;
    }

    /**
     * 设置图片描述
     *
     * @param imgDescription 图片描述
     */
    public void setImgDescription(String imgDescription) {
        this.imgDescription = imgDescription;
    }

    /**
     * 获取图片个数,默认10张
     *
     * @return image_number - 图片个数,默认10张
     */
    public Integer getImageNumber() {
        return imageNumber;
    }

    /**
     * 设置图片个数,默认10张
     *
     * @param imageNumber 图片个数,默认10张
     */
    public void setImageNumber(Integer imageNumber) {
        this.imageNumber = imageNumber;
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
     * @return last_update - 最后修改时间
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * 设置最后修改时间
     *
     * @param lastUpdate 最后修改时间
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * 获取创建人
     *
     * @return create_name - 创建人
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 设置创建人
     *
     * @param createName 创建人
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }
}