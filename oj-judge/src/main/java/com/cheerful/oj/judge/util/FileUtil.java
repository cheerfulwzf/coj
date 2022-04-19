package com.cheerful.oj.judge.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/11 13:32
 * @DESCRIPTION:
 */
public class FileUtil {
    public static void write(String str, File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(str.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }

    /**
     * 返回文件的字符串
     * @param src 文件
     * @return
     */
    public static String read(File src){
        byte[] bytes = new byte[1024];
        int len;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(src);
            StringBuilder builder = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                byte[] effective = new byte[len];
                System.arraycopy(bytes, 0, effective, 0, len);
                builder.append(new String(effective, StandardCharsets.UTF_8));
            }
            return builder.toString();
        } catch (IOException e) {
            return "";
        } finally {
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
