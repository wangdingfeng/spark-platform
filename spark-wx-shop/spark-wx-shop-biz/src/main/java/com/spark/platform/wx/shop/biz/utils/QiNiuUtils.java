package com.spark.platform.wx.shop.biz.utils;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.biz.utils
 * @ClassName: HBYAppUtils
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/12/19 15:14
 * @Version: 1.0
 */
@Slf4j
public class QiNiuUtils {

    /**
     * 上传图案
     *
     * @param multipartFile
     * @param key 文件名
     * @return
     * @throws IOException
     */
    @SneakyThrows
    public static String upload(MultipartFile multipartFile, String key, String ACCESS_KEY, String SECRET_KEY, String bucketName) {
        // 密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        //创建上传对象
        Configuration configuration = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(configuration);
        //调用put方法上传
        Response res = uploadManager.put(multipartFile.getInputStream(), key, auth.uploadToken(bucketName), null, null);
        if (res.isOK()) {
            return key;
        }
        return null;

    }

    /**
     * 查询
     *
     * @param url
     * @param keyWord
     * @return
     * @throws Exception
     */
    public static ArrayList<String> isContainContent(String url, String keyWord) throws Exception {
        File pathname = new File(url);
        ArrayList<String> jsonObjects = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        boolean result = false;
        //行读取
        LineNumberReader lineReader = null;
        InputStreamReader read = new InputStreamReader(new FileInputStream(pathname), "gbk");
        lineReader = new LineNumberReader(read);
        String readLine = null;
        while ((readLine = lineReader.readLine()) != null) {

            //判断是否包含
            if (readLine.contains(keyWord)) {
                jsonObject.put("pathname", pathname);
                jsonObjects.add(readLine);
            }
        }
        //关闭流
        if (lineReader != null) {
            try {
                lineReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObjects;
    }
}
