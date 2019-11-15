package com.lfl.pingancard.service.impl;

import com.lfl.exception.CustomException;
import com.lfl.pingancard.mapper.ImagesMapper;
import com.lfl.pingancard.service.ImageService;
import com.lfl.utils.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: lifalong
 * @create: 2019-11-15 17:35
 * @description:
 **/
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private OSSClientUtil ossClient; //注进来

    @Override
    public String updateHead(MultipartFile file, long userId) {
        if (file == null || file.getSize() <= 0) {
            throw new CustomException("头像不能为空");
        }
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
     //   userDao.updateHead(userId, imgUrl);//只是本地上传使用的
        return imgUrl;
    }
}
