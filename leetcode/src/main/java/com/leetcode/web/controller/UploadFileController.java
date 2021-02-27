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
    public Result<String> upload(@RequestParam("uploadFile") MultipartFile file){
        if (null == file) {
            return Result.badRequest("文件为空");
        }
        String uploadfile = TencentCOSUploadFileUtil.uploadfile(file);
        return Result.ok(uploadfile);
    }


}
