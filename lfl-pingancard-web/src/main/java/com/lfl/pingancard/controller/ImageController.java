package com.lfl.pingancard.controller;


import com.lfl.common.pojo.Img;
import com.lfl.response.ResultBody;
import com.lfl.utils.AliYunOssUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//图片上传接口
@RestController
@RequestMapping("/image")
public class ImageController {

    /**
     * 上传图片,图片格式
     *
     * @param img
     * @return
     */
    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    public ResultBody upLoadImg(@RequestBody Img img) {
        if (img == null || StringUtils.isBlank(img.getFiledir())) {
            return ResultBody.error("参数错误 ");
        }
        String imageUrl = AliYunOssUtil.save(img.getFiledir(), img.getSuffix());
        if (StringUtils.isNotBlank(imageUrl)) {
            return ResultBody.success(imageUrl);
        }
        return ResultBody.error("上传失败");


    }




}
