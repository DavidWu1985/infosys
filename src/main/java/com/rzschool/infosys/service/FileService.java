package com.rzschool.infosys.service;

import com.aliyun.oss.model.OSSObject;
import com.rzschool.infosys.oss.AliOssUtil;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileService {

    public String putFile(String fileName, InputStream input){
        return AliOssUtil.putFile(fileName, input).getLink();
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

//        while (true) {
//            if(bufferedInputStream.available() < 512) {
//                while(i != -1) {
//                    i = bufferedInputStream.read(buffer);
//                    bufferedOutputStream.write(buffer, 0, i);
//                }
//                break;
//            } else {
//                //当文件的大小还大于512字节时
//                bufferedInputStream.read(buffer);
//                bufferedOutputStream.write(buffer);
//            }
//        }
        ossObject.close();
    }
}
