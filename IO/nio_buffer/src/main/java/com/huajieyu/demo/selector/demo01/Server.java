package com.huajieyu.demo.selector.demo01;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 设置维欧非阻塞模式
        ssc.configureBlocking(false);
        //绑定端口
        ssc.bind(new InetSocketAddress(8848));
        //定义选择器
        Selector selector = Selector.open();
        //注册选择器
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            System.out.println("轮询中");
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                } else if (next.isReadable()) {
                    SocketChannel sc = (SocketChannel) next.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = sc.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                }

                // 一定要remove，不然还是会进入next.isAcceptable()的分歧里，然后sc拿不到，报空指针异常
                iterator.remove();
            }
        }
        ssc.close();
    }
}
