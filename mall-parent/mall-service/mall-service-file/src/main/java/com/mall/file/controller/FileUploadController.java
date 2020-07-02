package com.mall.file.controller;

import com.mall.file.file.FastDFSFile;
import com.mall.file.utils.FastDFSUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/28 1:49
 * @Description:
 */
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {

    private static final String filePath = "D:/file/";

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

    /**
     * <p>
     * Upload Temp
     * </p>
     *
     * @param file
     * @return org.springframework.http.ResponseEntity<java.lang.Object>
     * @author yanglin
     * @date 2020-07-02 19:57:38
     */
    @PostMapping("temp")
    public ResponseEntity<Object> uploadTemp(@RequestParam("file") MultipartFile file) throws IOException {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            FileOutputStream out = new FileOutputStream(filePath + file.getOriginalFilename());
            out.write(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("uploading failure");
        }
        return ResponseEntity.ok("uploading success");
    }

    /**
     * <p>
     * DownLoad
     * </p>
     *
     * @param response
     * @author yanglin
     * @date 2020-07-02 20:24:09
     */
    @GetMapping("/download/{fileName}")
    public void downLoad(HttpServletResponse response, @PathVariable String fileName) throws UnsupportedEncodingException {
        fileName += ".jpg";
        File file = new File(filePath + "/" + fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "utf8"));
            byte[] buffer = new byte[1024];
            //输出流
            OutputStream os = null;
            try (FileInputStream fis = new FileInputStream(file);
                 BufferedInputStream bis = new BufferedInputStream(fis);) {
                os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
