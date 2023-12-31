package com.rzschool.infosys.controller;

import com.rzschool.infosys.db.entity.OssResource;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file/**")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("put")
    public RtnResult<OssResource> putFile(@RequestParam MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        return RtnResult.success(fileService.putFile(filename, file.getInputStream()));
    }

    @DeleteMapping("/remove/{id}")
    public RtnResult<Boolean> removeFile(@PathVariable("id") int id) throws IOException {
        return RtnResult.success(fileService.removeFile(id));
    }

    @GetMapping("get")
    public void downloadFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/msword");
        fileService.downLoadFile(response.getOutputStream());
    }
}
