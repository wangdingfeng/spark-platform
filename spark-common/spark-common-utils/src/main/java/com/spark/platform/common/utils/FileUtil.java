package com.spark.platform.common.utils;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/11 22:43
 * @Description: 文件工具类
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {
    /**
     * 下载文件
     * @param filename
     * @param path
     * @param response
     * @throws IOException
     */
    public static void download(String filename, String path, HttpServletResponse response) throws IOException {
        download(filename, new FileInputStream(path), response);
    }

    /**
     * 下载文件
     * @param filename
     * @param inputStream
     * @param response
     * @throws IOException
     */
    public static void download(String filename, InputStream inputStream, HttpServletResponse response) throws IOException {
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(filename);
        response.setHeader("Content-type", type);
        String byteName = new String(filename.getBytes("utf-8"), "iso-8859-1");
        response.setHeader("Content-Disposition", "attachment;filename=" + byteName);
        //返回格式为流
        response.setContentType("APPLICATION/OCTET-STREAM");
        OutputStream outputStream = response.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(inputStream);
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }
}
