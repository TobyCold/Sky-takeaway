package com.sky.controller.admin;

import cn.hutool.core.io.FileUtil;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@RestController
@Api("文件相关接口")
@Slf4j
@RequestMapping("admin/common")
public class FileUpload {
    //TODO 待优化
    String PATH = "D:\\weblog\\";

    /**
     * 文件上传
     * TODO 解决文件上传问题
     * @param file
     * @throws IOException
     */
    @PostMapping("upload")
    @ApiOperation("文件上传")
    public Result<?> upload(@RequestParam("file") MultipartFile file) throws IOException {

        String data = "";
        LocalDateTime now = LocalDateTime.now();
        data += now.getYear();
        data += now.getMonthValue();
        data += now.getDayOfMonth();

        File folder = new File(PATH + data);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdir();
        }


        String fileName = file.getOriginalFilename();
        @Cleanup
        InputStream inputStream = file.getInputStream();
        @Cleanup
        OutputStream outputStream = new FileOutputStream(PATH + data + "\\" + fileName);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        return Result.success("http://192.168.31.55:8080/admin/common/"+ data + "/" + fileName);
    }

    /**
     * 文件回显、下载
     *
     * @param data
     * @param name
     * @param httpServletResponse
     * @throws IOException
     */
    @GetMapping("{data}/{file:.+}")
    public void getFile(@PathVariable("data") String data, @PathVariable("file") String name, HttpServletResponse httpServletResponse) throws IOException {
        @Cleanup
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        byte[] bytes = Files.readAllBytes(Paths.get(PATH + data + "\\" + name));
        outputStream.write(bytes);
    }
}
