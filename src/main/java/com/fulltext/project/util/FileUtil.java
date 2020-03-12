package com.fulltext.project.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/9.
 *
 * @author anlu.
 */
public class FileUtil {

    public static void download(String filename, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(new FileInputStream(new File(filename)));
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }

}