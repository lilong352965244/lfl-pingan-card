package com.lfl.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import jodd.util.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 阿里云图片文件上传
 */
public class AliYunOssUtil {
    private static OSSClient ossClient;

    /**
     * 阿里云图片文件上传
     * @param filedir 图片的base64字符串
     * @param suffix 图片存放的文件夹，这是一个虚的文件夹，只是用来管理文件用，会体现在生成的图片地址上
     * @return
     */
    public static String save(String filedir, String suffix){
        OSSClient oss = new OSSClient(OSSClientConstants.ENDPOINT, OSSClientConstants.ACCESS_KEY_ID,OSSClientConstants.ACCESS_KEY_SECRET);
        InputStream inputStream1;
        String[] shartimg = new String[2];
        //base64所在字段
        if(!filedir.isEmpty()){
            shartimg = filedir.split(",");
            int index = shartimg[0].indexOf("/");
            int endindex = shartimg[0].indexOf(";");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = sdf.format(new Date());
            String endName  = shartimg[0].substring(index+1,endindex);//获取文件后缀
            String newFileName = date +"."+ endName;// 新文件名
            if(StringUtils.isNotBlank(suffix))
            {
                newFileName=suffix+"/"+newFileName;
            }
            try {
                Base64 base64 = new Base64();
                byte[] imageByte =  base64.decode(shartimg[1]);
                //String b = shartimg[1];
                //byte[] byt = b.getBytes();
                //byte[] bytes = new BASE64Decoder().decodeBuffer(shartimg[1]);  //将字符串转换为byte数组
                inputStream1 = new ByteArrayInputStream(imageByte);
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(inputStream1.available());
                oss.putObject(OSSClientConstants.BACKET_NAME,newFileName, inputStream1, metadata);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String shareImg ="https://"+OSSClientConstants.BACKET_NAME+".oss-cn-hongkong.aliyuncs.com/"+newFileName;
            return shareImg;
        }else{
            return "数据有误";
        }
    }
}

