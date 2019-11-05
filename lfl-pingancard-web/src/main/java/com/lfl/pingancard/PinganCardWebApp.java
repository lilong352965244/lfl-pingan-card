package com.lfl.pingancard;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @author: lifalong
 * @create: 2019-10-28 17:26
 * @description:
 **/
@SpringBootApplication
@MapperScan("com.lfl.pingancard.mapper")
public class PinganCardWebApp {
    public static void main(String[] args) {
        SpringApplication.run(PinganCardWebApp.class, args);
    }
}
