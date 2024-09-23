package com.huajieyu.demo01;

import java.io.*;
import java.net.Socket;

/**
 * 发送单行
 */
public class Client {
    public static void main(String[] args) {

        Socket socket = null;
        try {
            socket = new Socket("localhost", 8888);
            OutputStream os = socket.getOutputStream();

            PrintStream ps = new PrintStream(os);
            ps.println("给你发条消息!");
            ps.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
