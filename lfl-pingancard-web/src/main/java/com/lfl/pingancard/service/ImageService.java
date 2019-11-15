package com.lfl.pingancard.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: lifalong
 * @create: 2019-11-15 17:34
 * @description:
 **/
public interface ImageService {

     String updateHead(MultipartFile file, long userId);
}
