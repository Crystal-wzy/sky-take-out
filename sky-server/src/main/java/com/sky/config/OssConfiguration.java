package com.sky.config;

import com.sky.properties.QiniuOssProperties;
import com.sky.utils.QiniuOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 配置类，用于创建QiniuOssUtil对象
 * @Author: Zhiyong Wang
 * @Date: 2024/1/28 17:57
 */
@Configuration
@Slf4j
public class OssConfiguration {

    @Bean
    public QiniuOssUtil qiniuOssUtil(QiniuOssProperties qiniuOssProperties) {
        log.info("开始创建七牛云文件上传工具类对象：{}", qiniuOssProperties);
        return new QiniuOssUtil(qiniuOssProperties.getAccessKey(),
                qiniuOssProperties.getSecretKey(),
                qiniuOssProperties.getBucketName());
    }

}
