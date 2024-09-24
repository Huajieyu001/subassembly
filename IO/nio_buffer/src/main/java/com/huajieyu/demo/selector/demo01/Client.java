package com.huajieyu.demo.selector.demo01;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8848));

        sc.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            String msg = new SimpleDateFormat("yyyy-MM-dd HH/mm/ss.SSS").format(System.currentTimeMillis()) + ":\t" + str;

            // 把数据放入buffer
            buffer.put(msg.getBytes());
            // 切换到可读模式
            buffer.flip();
            // 把buffer写入的数据放入到通道
            sc.write(buffer);
            // 清除buffer
            buffer.clear();
        }

        sc.close();
    }
}
