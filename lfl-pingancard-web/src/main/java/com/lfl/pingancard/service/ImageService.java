package com.lfl.pingancard.service;

import com.lfl.pingancard.pojo.Images;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: lifalong
 * @create: 2019-11-15 17:34
 * @description:
 **/
public interface ImageService {
    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    String updateHead(MultipartFile file);

    /**
     * 保存图片
     *
     * @param images
     * @return
     */
    Boolean saveImages(Images images);

    /**
     * 根据personId查询images
     *
     * @param personId
     * @return
     */
    Images queryImagesByPersonId(Long personId);

    /**
     * 更新图片信息
     * @param images
     */
    Boolean updateImages(Images images);

    /**
     * 批量删除图片
     * @param imgUrl
     * @return
     */
    Boolean deleteServiceImg(String imgUrl);

    /**
     * 根据主键删除images
     * @param id
     * @return
     */
    Boolean deleteImgById(Long id);
}
