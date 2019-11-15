package com.lfl.pingancard.controller;

import com.lfl.pingancard.service.ImageService;
import com.lfl.response.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: lifalong
 * @create: 2019-11-15 17:32
 * @description:
 **/
@RestController
@RequestMapping("image2")
public class ImageController2 {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    public ResultBody headImgUpload(HttpServletRequest request, MultipartFile file) {
        String head = null;
        try {
            head =  imageService.updateHead(file, 4);//此处是调用上传服务接口，4是需要更新的userId 测试数据。

        } catch (Exception e) {
            e.printStackTrace();
          return   ResultBody.error("上传失败");
        }

       return   ResultBody.success(head);
    }

}
