package com.lfl.pingancard.controller;

import com.lfl.pingancard.pojo.Images;
import com.lfl.pingancard.service.ImageService;
import com.lfl.response.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lifalong
 * @create: 2019-11-15 17:32
 * @description:
 **/
@RestController
@RequestMapping("image")
@Slf4j
public class ImageController {
    @Autowired
    private ImageService imageService;

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/uploadimg")
    public ResultBody ImgUpload(MultipartFile file) {
        String head = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            head = imageService.updateHead(file);//此处是调用上传服务接口
            //head = "test/123312.jpg";
            if (StringUtils.isNotBlank(head)) {
                map.put("imgurl", head);
                return ResultBody.success(map);
            }
        } catch (Exception e) {
            log.error("【上传图片】" + e.getMessage());
            return ResultBody.error("上传失败");
        }
        return ResultBody.error("上传失败");
    }


    @DeleteMapping(value = "/deleteServerImg")
    public ResultBody ImgDelete(@NotNull @RequestBody Map<String, Object> map) {


        Boolean boo = this.imageService.deleteServiceImg((String) map.get("delImagesUrl"));
        if (boo) {
            return ResultBody.success("删除图片成功");
        }
        return ResultBody.error("删除图片失败");
    }


    /**
     * 添加图片
     *
     * @param request
     * @param images
     * @return
     */
    @PostMapping("/add")
    public ResultBody addImages(HttpServletRequest request, @Valid @RequestBody Images images) {
        String username = (String) request.getAttribute("username");
        images.setCreateName(username);
        Boolean boo = this.imageService.saveImages(images);
        if (!boo) {
            return ResultBody.error("添加图片失败");
        }
        return ResultBody.success("添加图片成功");
    }

    /**
     * 根据主键id查询images
     *
     * @param personId
     * @return
     */
    @GetMapping("/query/{personId}")
    public ResultBody queryImages(@PathVariable(value = "personId") Long personId) {
        Images images = this.imageService.queryImagesByPersonId(personId);
        return ResultBody.success(images);
    }

    /**
     * 根据主键id修改images
     *
     * @param images
     * @return
     */
    @PostMapping("/update")
    public ResultBody updateImages(@Valid @RequestBody Images images) {
        Boolean boo = this.imageService.updateImages(images);
        if (!boo) {
            return ResultBody.error("修改图片失败");
        }
        return ResultBody.success("修改图片成功");
    }


}
