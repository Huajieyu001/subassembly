package com.huajieyu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MyServerThread extends Thread{

    private Socket socket;

    public MyServerThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str;
            while((str = br.readLine()) != null){
                System.out.println(str);
                sendToAll(str);
            }
        }
        catch (Exception e){
            System.out.println("有人下线了");
            Server.socketList.remove(socket);
        }
    }

    private void sendToAll(String str) throws Exception{
        for(Socket s : Server.socketList){
            PrintStream ps = new PrintStream(s.getOutputStream());
            ps.println(str);
            ps.flush();
        }
    }
}
