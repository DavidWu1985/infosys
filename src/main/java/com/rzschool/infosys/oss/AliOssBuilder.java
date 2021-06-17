package com.rzschool.infosys.oss;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.PutObjectResult;
import com.rzschool.infosys.util.FileUtil;
import com.rzschool.infosys.util.StringUtil;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 阿里云存储构建类
 */
public class AliOssBuilder {

    private static OssProperties ossProperties = new OssProperties();

    private static Map<String, OSSClient> ossClientPool = new ConcurrentHashMap<>();


    private static OSSClient ossClient() {
        OSSClient ossClient = ossClientPool.get("ossClient");
        if(ossClient != null){
            return ossClient;
        }
        // 创建ClientConfiguration。ClientConfiguration是OSSClient的配置类，可配置代理、连接超时、最大连接数等参数。
        ClientConfiguration conf = new ClientConfiguration();
        // 设置OSSClient允许打开的最大HTTP连接数，默认为1024个。
        conf.setMaxConnections(1024);
        // 设置Socket层传输数据的超时时间，默认为50000毫秒。
        conf.setSocketTimeout(50000);
        // 设置建立连接的超时时间，默认为50000毫秒。
        conf.setConnectionTimeout(50000);
        // 设置从连接池中获取连接的超时时间（单位：毫秒），默认不超时。
        conf.setConnectionRequestTimeout(1000);
        // 设置连接空闲超时时间。超时则关闭连接，默认为60000毫秒。
        conf.setIdleConnectionTime(60000);
        // 设置失败请求重试次数，默认为3次。
        conf.setMaxErrorRetry(5);
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(ossProperties.getAccessKey(), ossProperties.getSecretKey());
        ossClient = new OSSClient(ossProperties.getEndPoint(), credentialsProvider, conf);
        ossClientPool.put("ossClient", ossClient);
        return ossClient;
    }

    public static OssFile putFile(String fileName, InputStream stream) {
        return put(stream, fileName);
    }

    private static OssFile put(InputStream stream, String key) {
        String originalName = key;
        key = fileName(key);
        PutObjectResult response = ossClient().putObject(ossProperties.getBucketName(), key, stream);
        int retry = 0;
        int retryCount = 5;
        while (StringUtils.isEmpty(response.getETag()) && retry < retryCount) {
            response = ossClient().putObject(ossProperties.getBucketName(), key, stream);
            retry++;
        }
        OssFile file = new OssFile();
        file.setOriginalName(originalName);
        file.setName(key);
        file.setLink(fileLink(key));
        return file;
    }

    private static String fileName(String originalFilename) {
        return "upload" + "/" + StringUtil.randomUUID() + "." + FileUtil.getFileExtension(originalFilename);
    }

    private static String fileLink(String key) {
        String prefix = ossProperties.getEndPoint().contains("https://") ? "https://" : "http://";
        return prefix + ossProperties.getBucketName() + "." + ossProperties.getEndPoint().replaceFirst(prefix, "") + "/" + key;
    }


}
