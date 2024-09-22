package com.huajieyu.demo03;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 多客户端
 */
public class Client {
    public static void main(String[] args){

        Socket socket = null;
        try {
            socket = new Socket("localhost", 8888);
            OutputStream os = socket.getOutputStream();

            PrintStream ps = new PrintStream(os);
            Scanner sc = new Scanner(System.in);
            while(true){
                String string = sc.nextLine();
                ps.println(string);
                ps.flush();
                if(string.equals("end")){
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
