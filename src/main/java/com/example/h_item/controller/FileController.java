package com.example.h_item.controller;

import cn.hutool.core.util.IdUtil;
import com.example.h_item.common.Result;
import com.example.h_item.model.dto.UploadFileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    private static final String FILE_PATH = "static/img/";

    @PostMapping("/uploadFile")
    public Result<UploadFileDTO> uploadFile(@RequestBody MultipartFile file) {
        String path;
        String newFileName;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
            File upload = new File(path, FILE_PATH);
            if (!upload.exists()) {
                boolean mkdirs = upload.mkdirs();
                if (!mkdirs) {
                    log.error("创建目录失败！");
                    return Result.error("创建目录失败");
                }
            }
            newFileName = IdUtil.simpleUUID() + file.getOriginalFilename();
            file.transferTo(new File(path + FILE_PATH, newFileName));
        } catch (IOException e) {
            log.error("上传文件发生错误！", e);
            return Result.error("上传文件发生错误");
        }
        UploadFileDTO uploadFileDTO = new UploadFileDTO();
        uploadFileDTO.setOldName(file.getOriginalFilename());
        uploadFileDTO.setNewName(newFileName);
        uploadFileDTO.setFileUrl(path.substring(1) + FILE_PATH + newFileName);
        return Result.success(uploadFileDTO);
    }

    @GetMapping("/downLoadFile")
    public Result<String> downLoadFile(@RequestParam("fileName") String fileName, HttpServletResponse response,
                             HttpServletRequest request) {
        String path;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
            path += FILE_PATH + fileName;
            InputStream is = new FileInputStream(path);
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[is.available()];
            int readSize = is.read(buffer);
            response.reset();
            if (request.getHeader("User-Agent").contains("Firefox"))
                response.setHeader("Content-Disposition", "attachment;filename==?UTF-8?B?" + new BASE64Encoder().
                        encode(fileName.getBytes(StandardCharsets.UTF_8)) + "?=");
            else
                response.setHeader("Content-Disposition", "attachment;filename = " +
                        URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)));
            response.addHeader("Content-Length", "" + readSize);
            os.write(buffer);
        } catch (Exception e) {
            log.error("下载文件发生错误！", e);
            return Result.error("下载文件发生错误");
        }
        return Result.success("下载文件成功");
    }
}
