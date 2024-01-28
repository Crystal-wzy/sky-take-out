package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description 七牛OSS(对象存储服务)属性类
 * @Author: Zhiyong Wang
 * @Date: 2024/1/28 15:20
 */
@Component
@ConfigurationProperties(prefix = "sky.qiniuoss")
@Data
public class QiniuOssProperties {

    private String accessKey;
    private String secretKey;
    private String bucketName;

}
