package com.lfl.pingancard.service.impl;

import com.lfl.exception.CustomException;
import com.lfl.pingancard.mapper.ImagesMapper;
import com.lfl.pingancard.pojo.Images;
import com.lfl.pingancard.service.ImageService;
import com.lfl.utils.OSSClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author: lifalong
 * @create: 2019-11-15 17:35
 * @description:
 **/
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImagesMapper imagesMapper;

    @Override
    public String updateHead(MultipartFile file) {
        OSSClientUtil ossClient = new OSSClientUtil();

        if (file == null || file.getSize() <= 0) {
            throw new CustomException("-1", "上传图片不能为空");
        }
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = "https://sanyi-images.oss-cn-hongkong.aliyuncs.com/" + ossClient.getImgUrl(name);

        ossClient.destory();
        return imgUrl;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveImages(Images images) {
        String imagesUrl = images.getImagesUrl();
        String[] strings = imagesUrl.split(",");
        if (strings.length > 9 || strings.length <= 0) {
            throw new CustomException("-1", "图片数数量不能超过9张");
        }

        // 从文件服务器中删除对应的文件
        Boolean boo = true;

        String deleteImagesUrl = images.getDelImagesUrl();
        if (StringUtils.isNotBlank(deleteImagesUrl)) {
            String[] delteImages = deleteImagesUrl.split(",");
            OSSClientUtil ossClient = new OSSClientUtil();
            boo = ossClient.deleteFile(delteImages);
            ossClient.destory();
        }
        if (boo) {
            images.setImageNumber(strings.length);
            Date date = new Date();
            images.setCreateTime(date);
            images.setLastUpdateTime(date);
            return this.imagesMapper.insertSelective(images) == 1;
        }
        return false;
    }

    @Override
    public Images queryImagesByPersonId(Long personId) {
        Images images = new Images();
        images.setPersonId(personId);
        return this.imagesMapper.selectOne(images);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateImages(Images images) {
        String imagesUrl = images.getImagesUrl();
        String[] strings = imagesUrl.split(",");
        if (strings.length > 9 || strings.length <= 0) {
            throw new CustomException("-1", "图片数数量不能超过9张");
        }

        // 从文件服务器中删除对应的文件
        Boolean boo = true;

        String deleteImagesUrl = images.getDelImagesUrl();
        if (StringUtils.isNotBlank(deleteImagesUrl)) {
            String[] deleteImages = deleteImagesUrl.split(",");
            OSSClientUtil ossClient = new OSSClientUtil();
            boo = ossClient.deleteFile(deleteImages);
            ossClient.destory();
        }
        if (boo) {
            images.setImageNumber(strings.length);
            Date date = new Date();
            images.setLastUpdateTime(date);
            return this.imagesMapper.updateByPrimaryKeySelective(images) == 1;
        }
        return false;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteServiceImg(String imgUrl) {
        //   imgUrl = "https://sanyi-images.oss-cn-hongkong.aliyuncs.com/test/1574667503449.jpg";
        Boolean boo = false;
        String[] deleteImages = imgUrl.split(",");
        OSSClientUtil ossClient = new OSSClientUtil();
        boo = ossClient.deleteFile(deleteImages);
        // ossClient.destory();
        return boo;
    }

    @Override

    public Boolean deleteImgById(Long id) {
        return this.imagesMapper.deleteByPrimaryKey(id) == 1;
    }
}
