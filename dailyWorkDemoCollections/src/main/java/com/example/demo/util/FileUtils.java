package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author 晨泽
 * @created 2017/12/12
 * @version people-archive 1.0
 */
@Slf4j
public final class FileUtils {

    private FileUtils() {
    }

    
    public static void deleteFile(File... files) {  
        for (File file : files) {  
            if (file.exists()) {
                file.delete();
            }  
        }  
    }

    /**
     * 文件上传
     * @param from 上传的文件
     * @param to 服务器目标文件夹
     * @return 文件名
     */
    public static File upload(MultipartFile from, String to) throws IOException {
        String fileName = from.getOriginalFilename();
        String filePrefix = getPrefix(fileName);
        String fileExtension = getExtension(fileName);
        File file;
        //设置上传地址
       /* Properties propertyLoader = ConfigUtils.propertyLoader("application-hjdev.properties");
        String path = propertyLoader.getProperty("server.tomcat.basedir");*/
        // 文件存在处理策略
        while ((file = new File(to + File.separator + fileName)).exists()) {
            fileName = filePrefix + "_" + System.currentTimeMillis() + fileExtension;
        }

        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(from.getInputStream());
            out = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bytes = new byte[2048];
            int n = -1;
            while ((n = in.read(bytes,0, bytes.length)) != -1) {
                out.write(bytes, 0, n);
            }
            return file;
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw e;
        } finally {
            closeIutputStream(in);
            closeOutputStream(out);
        }
    }
    /**
     * 获得文件扩展名
     * @param fileName 文件全名 a.b.c
     * @return
     */
    public static String getExtension(String fileName) {
        String splitor = ".";
        int i = fileName.lastIndexOf(splitor);
        if (i == -1) {
            return "";
        }
        return splitor + fileName.substring(i + 1);
    }

    /**
     * 获得文件名前缀
     * @param fileName 文件全名 a.b.c
     * @return
     */
    public static String getPrefix(String fileName) {
        String splitor = ".";
        int i = fileName.lastIndexOf(splitor);
        if (i == -1) {
            return fileName;
        }
        return fileName.substring(0, i);
    }
    /**
     * 关闭输出流
     * @param outputStream
     */
    public static void closeOutputStream(OutputStream outputStream){
        if (outputStream != null) {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
               // logger.warn("[output stream close fail]"  + ExceptionUtils.getStackTrace(e));
            }
        }
    }

    /**
     * 关闭输入流
     * @author gpf
     * @param inputStream
     */
    public static void closeIutputStream(InputStream inputStream){
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                //logger.warn("[input stream close fail]"  + ExceptionUtils.getStackTrace(e));
            }
        }
    }


}
