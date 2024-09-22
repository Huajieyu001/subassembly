package com.huajieyu.demo05;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

/**
 * 伪异步
 */
public class ServerRunnable implements Runnable {

    private Socket socket;

    ServerRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        try {
            is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            DataInputStream dis = new DataInputStream(is);
            String string = dis.readUTF();

            File file = new File("C:\\Users\\xanadu\\Desktop\\test\\receive\\" + UUID.randomUUID() + string);
            FileOutputStream fos = new FileOutputStream(file);

            byte[] bytes = new byte[1024];
            int len;

            while ((len = dis.read(bytes)) > 0) {
                fos.write(bytes, 0, len);
            }
            fos.close();
            dis.close();
            br.close();
            is.close();
            System.out.println("文件保存成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
