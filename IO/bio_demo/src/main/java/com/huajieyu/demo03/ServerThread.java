package com.huajieyu.demo03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket socket;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        try {
            is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            System.out.print("start->");
            while ((line = br.readLine()) != null) {
                if(line.equals("end")){break;}
                System.out.println(line);
            }
            System.out.print("<-end");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
