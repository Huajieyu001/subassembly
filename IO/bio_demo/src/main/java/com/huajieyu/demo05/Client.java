package com.huajieyu.demo05;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 发送多行
 */
public class Client {
    public static void main(String[] args) {

        Socket socket = null;
        String file = "C:\\Users\\xanadu\\Desktop\\test\\send\\壁纸.jpg";
        try (InputStream is = new FileInputStream(new File(file));) {
            long start = System.currentTimeMillis();
            socket = new Socket("localhost", 8888);
            OutputStream os = socket.getOutputStream();

            DataOutputStream dos = new DataOutputStream(os);

            byte[] bytes = new byte[1024];
            int lastIndexOf = file.lastIndexOf(".");
            dos.writeUTF(file.substring(lastIndexOf));

            int len = -1;
            while ((len = is.read(bytes)) > 0) {
                dos.write(bytes, 0, len);
            }
            dos.flush();
            long end = System.currentTimeMillis();
            socket.shutdownOutput();
            System.out.println("count:" + (end - start));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
