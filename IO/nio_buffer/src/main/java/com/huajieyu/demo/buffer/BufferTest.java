package com.huajieyu.demo.buffer;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

public class BufferTest {

    @Test
    void test001() {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------------------------------");
        buffer.put("huajieyu".getBytes());

        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------------------------------");

        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------------------------------");

        byte [] bytes = new byte[buffer.limit()];
        buffer.get(bytes, 0, buffer.limit());
        String str = new String(bytes);
        System.out.println(str);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

    }

    @Test
    void test002(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------------------------------");
        buffer.put("huajieyu".getBytes());

        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------------------------------");

        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------------------------------");

        buffer.clear();

        byte [] bytes = new byte[buffer.limit()];
        buffer.get(bytes, 0, buffer.limit());
        String str = new String(bytes);
        System.out.println(str);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

    }

    @Test
    void test003(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------------------------------");
        buffer.put("huajieyu".getBytes());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------------------------------");
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------------------------------");

        byte [] bytes = new byte[4];
        // 传入一个bytes数组但是没有指定长度的时候，如果数组长度大于limit，则会报错，小于等于则没错
        // 如byte [] bytes = new byte[9];传入会报错
        // 如果传入一个长度大于limit的数组，可以指定读取长度小于limit，解决报错问题
        buffer.get(bytes);
        String str = new String(bytes);
        System.out.println(str);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        byte [] bytes2 = new byte[3];

        // 使用mark标记位置，下次get的时候从此处开始读取，长度不能超多limit-mark
        buffer.mark();
        buffer.get(bytes2);
        System.out.println("---------------------------------------");
        System.out.println(new String(bytes2));
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        byte [] bytes3 = new byte[4];
        // 使用reset返回到mark的位置
        buffer.reset();
        // 使用hasRemaining判断是否剩余字符
        if(buffer.hasRemaining()){
            System.out.println("----------------reset-----------------------");
            System.out.println(buffer.position());
            System.out.println(buffer.limit());
            System.out.println(buffer.capacity());
            // 使用array获取capacity内所有字符
            byte[] array = buffer.array();
            System.out.println("----------------array-----------------------");
            System.out.println(new String(array));
            System.out.println(buffer.position());
            System.out.println(buffer.limit());
            System.out.println(buffer.capacity());
        }
    }

    @Test
    void test004(){
        // ByteBuffer.allocateDirect申请直接内存
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        // ByteBuffer.allocate申请非直接内存：堆内存
        ByteBuffer buffer2 = ByteBuffer.allocate(10);

        // isDirect判断是否是直接内存
        System.out.println(buffer.isDirect());
        System.out.println(buffer2.isDirect());
        System.out.println("---------------------------------------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------------------------------");
        buffer.put("huajieyu".getBytes());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------------------------------");
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("---------------------------------------");

        byte [] bytes = new byte[4];
        // 传入一个bytes数组但是没有指定长度的时候，如果数组长度大于limit，则会报错，小于等于则没错
        // 如byte [] bytes = new byte[9];传入会报错
        // 如果传入一个长度大于limit的数组，可以指定读取长度小于limit，解决报错问题
        buffer.get(bytes);
        String str = new String(bytes);
        System.out.println(str);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        byte [] bytes2 = new byte[3];

        // 使用mark标记位置，下次get的时候从此处开始读取，长度不能超多limit-mark
        buffer.mark();
        buffer.get(bytes2);
        System.out.println("---------------------------------------");
        System.out.println(new String(bytes2));
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        byte [] bytes3 = new byte[4];
        // 使用reset返回到mark的位置
        buffer.reset();
        // 使用hasRemaining判断是否剩余字符
        if(buffer.hasRemaining()){
            System.out.println("----------------reset-----------------------");
            System.out.println(buffer.position());
            System.out.println(buffer.limit());
            System.out.println(buffer.capacity());
            // 使用array获取capacity内所有字符
            byte[] array = buffer.array();
            System.out.println("----------------array-----------------------");
            System.out.println(new String(array));
            System.out.println(buffer.position());
            System.out.println(buffer.limit());
            System.out.println(buffer.capacity());
        }
    }
}
