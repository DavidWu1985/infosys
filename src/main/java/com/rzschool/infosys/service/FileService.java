package com.rzschool.infosys.service;

import com.rzschool.infosys.oss.AliOssBuilder;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FileService {

    public String putFile(String fileName, InputStream input){
        return AliOssBuilder.putFile(fileName, input).getLink();
    }

}
