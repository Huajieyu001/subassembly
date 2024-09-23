package com.huajieyu.demo03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接收多客户端
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while(true){
            Socket tempSocket = serverSocket.accept();
            ServerThread thread = new ServerThread(tempSocket);
            thread.start();
        }
    }
}
