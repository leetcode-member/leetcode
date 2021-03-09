package com.leetcode.web.controller;

import com.leetcode.util.cos.TencentCOSUploadFileUtil;
import com.leetcode.util.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/9 17:50
 */
@Slf4j
@RestController
public class UploadFileController {

    /**
     * jarvan： 限制上传文件类型为图片
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("uploadFile") MultipartFile file){
        if (null == file) {
            return Result.badRequest("文件为空");
        }
        log.info("file.getContentType():" + file.getContentType());
        if (!file.getContentType().contains("image")) {
            return Result.badRequest("请上传图片类型");
        }
        String uploadfile = TencentCOSUploadFileUtil.uploadfile(file);
        return Result.ok(uploadfile);
    }


}
