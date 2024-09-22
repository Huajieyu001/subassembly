package com.huajieyu;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static List<Socket> socketList = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8888);
        while(true){
            Socket socket = serverSocket.accept();
            socketList.add(socket);
            new MyServerThread(socket).start();
        }
    }
}
