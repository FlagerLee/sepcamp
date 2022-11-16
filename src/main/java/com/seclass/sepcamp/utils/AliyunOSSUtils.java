package com.seclass.sepcamp.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.ibatis.io.Resources;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class AliyunOSSUtils {
    /**
     * @methodName upload
     * @Description: 根据input参数和文件名，将其保存在阿里云OSS中
     * @return: java.lang.String
     **/
    public  static String upload(String filename, InputStream input){

        SAXReader reader = new SAXReader();
        Element root = null;
        try {
            String resource = "aliyunOSS.xml";
            Document doc = reader.read(Resources.getResourceAsStream(resource));
            root = doc.getRootElement();
        } catch (Exception  e) {
            e.printStackTrace();
        }


        String endpoint =  "oss-cn-shenzhen.aliyuncs.com";
        String accessKeyId = root.element("OSSKey").getText();
        String accessKeySecret = root.element("OSSSecret").getText();


        //2 创建OssClient对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //3 获取文件信息，为了上传

        // meta设置请求头
        ObjectMetadata meta = new ObjectMetadata();
     //   meta.setContentType("image/jpg");
        ossClient.putObject("image-djx", filename, input, meta);
        //关闭ossClient
        ossClient.shutdown();

        //返回上传之后地址，拼接地址
        String uploadUrl = "https://"+"image-djx"+"."+endpoint+"/"+filename;
        return uploadUrl;
    }

}
