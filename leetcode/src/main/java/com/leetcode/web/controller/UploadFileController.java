package com.leetcode.web.controller;

import com.leetcode.util.cos.TencentCOSUploadFileUtil;
import com.leetcode.util.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/9 17:50
 */
@RestController
public class UploadFileController {
    @PostMapping("/upload")
    public String upload(@RequestParam("uploadFile") MultipartFile file){
        if (null == file) {
            return "文件为空";
        }
        String filePath = TencentCOSUploadFileUtil.uploadfile(file);
        return "上传成功，访问地址为:"+filePath;
    }
}
