package com.rzschool.infosys.service;

import com.aliyun.oss.model.OSSObject;
import com.rzschool.infosys.db.entity.OssResource;
import com.rzschool.infosys.db.repository.OssResourceRepository;
import com.rzschool.infosys.oss.AliOssUtil;
import com.rzschool.infosys.oss.OssFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileService {

    @Autowired
    private OssResourceRepository ossResourceRepository;

    public OssResource putFile(String fileName, InputStream input){
        OssFile file = AliOssUtil.putFile(fileName, input);
        OssResource resource = new OssResource();
        resource.setFileLink(file.getLink());
        resource.setFileName(file.getName());
        resource.setOriginalName(file.getOriginalName());
        OssResource save = ossResourceRepository.save(resource);
        return save;
    }

    public void downLoadFile(OutputStream outputStream) throws IOException {
        OSSObject ossObject = AliOssUtil.downLoadFile("upload/af015d551f4b09b8aa2d42481d35308a.doc");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(ossObject.getObjectContent());
        //缓冲文件输出流
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        //缓冲区的大小
        byte[] buffer = new byte[1024];
        int length = 0 ; //代表实际读取的字节数
        while( (length = bufferedInputStream.read( buffer ) )!= -1 ){
            //length 代表实际读取的字节数
            bufferedOutputStream.write(buffer, 0, length );
        }
        bufferedOutputStream.flush();
        ossObject.close();
    }

    public boolean removeFile(Integer ossId){
        OssResource resource = ossResourceRepository.getOne(ossId);
        AliOssUtil.removeFile(resource.getFileName());
        ossResourceRepository.deleteById(ossId);
        return true;
    }
}
