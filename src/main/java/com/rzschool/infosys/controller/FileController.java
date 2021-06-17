package com.rzschool.infosys.controller;

import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file/**")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("put")
    public RtnResult<String> putFile(@RequestParam MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        return RtnResult.success(fileService.putFile(filename, file.getInputStream()));
    }
}
