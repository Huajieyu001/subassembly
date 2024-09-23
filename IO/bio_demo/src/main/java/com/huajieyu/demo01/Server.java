package com.huajieyu.demo01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接收单行
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        System.out.println("start->");
        if ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("<-end");
    }
}
