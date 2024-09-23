package com.huajieyu.demo05;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * 接收单客户端多行
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        MyThreadPool pool = new MyThreadPool(3, 5);
        while(true){
            Socket socket = serverSocket.accept();
            pool.exec(new ServerRunnable(socket));
        }
    }
}
