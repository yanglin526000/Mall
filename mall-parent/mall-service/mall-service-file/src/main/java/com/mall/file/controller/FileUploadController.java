package com.mall.file.controller;

import com.mall.file.file.FastDFSFile;
import com.mall.file.utils.FastDFSUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/28 1:49
 * @Description:
 */
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {

    /**
     * <p>
     * Upload
     * </p>
     *
     * @param file
     * @return org.springframework.http.ResponseEntity<java.lang.Object>
     * @author yanglin
     * @date 2020-06-30 09:37:02
     */
    @PostMapping
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException {
        FastDFSFile fastDFSFile = new FastDFSFile();
        fastDFSFile.setName(file.getOriginalFilename());
        fastDFSFile.setContent(file.getBytes());
        fastDFSFile.setExt(StringUtils.getFilenameExtension(file.getOriginalFilename()));

        String[] upload = FastDFSUtils.upload(fastDFSFile);

        String url = FastDFSUtils.getTrackerUrl() + "/" + upload[0] + "/" + upload[1];
        return ResponseEntity.ok(url);
    }

}
