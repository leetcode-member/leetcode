package com.leetcode.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2020/11/28 12:46
 */
@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "bean")
public class BeanConfig {
    private String baseFilePath;
    private String privateKey;
    private Long redisSaveTime;
    private Long yangToken;
    private Long oldToken;

    private String uploadFolder;
    private String fileCacheDomain;

}
