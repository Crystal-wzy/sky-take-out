package com.sky.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Description 七牛OSS工具类
 * @Author: Zhiyong Wang
 * @Date: 2024/1/28 15:34
 */
@Data
@AllArgsConstructor
@Slf4j
public class QiniuOssUtil {

    private String accessKey;
    private String secretKey;
    private String bucketName;

    /**
     * 文件上传
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes, String objectName) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本

        UploadManager uploadManager = new UploadManager(cfg);
        String key = "img/"+objectName;
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            Auth auth = Auth.create(accessKey, secretKey);//创建凭证
            String upToken = auth.uploadToken(bucketName); //上传凭证
            try {
                //修改6 put方法 第一个参数 要放上面 自己定义的 inputStream对象
                Response response = uploadManager.put(inputStream, key, upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    ex2.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // domainOfBucket 中的域名为用户bucket绑定的下载域名
        String domainOfBucket = "http://s7ymb7eya.hd-bkt.clouddn.com/img";
        String encodedFileName = null;
        try {
            encodedFileName = URLEncoder.encode(objectName, "utf-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);

        log.info("文件上传到:{}", finalUrl);
        return finalUrl;
    }

}
