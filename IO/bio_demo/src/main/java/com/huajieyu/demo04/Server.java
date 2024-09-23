package com.huajieyu.demo04;

import com.huajieyu.demo03.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接收多客户端
 * 伪异步
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        MyThreadPool pool = new MyThreadPool(5, 10);
        while(true){
            Socket tempSocket = serverSocket.accept();
            ServerRunnable runnable = new ServerRunnable(tempSocket);
            pool.exec(runnable);
        }
    }
}
