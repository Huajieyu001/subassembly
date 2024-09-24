package com.huajieyu.demo.channel;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChannelTest {

    @Test
    void testWrite() throws Exception {
        FileOutputStream os = new FileOutputStream("testWrite.txt");
        FileChannel channel = os.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(300);

        buffer.put("hello world".getBytes());
        buffer.flip();

        int write = channel.write(buffer);
        System.out.println(write);
        channel.close();
    }

    @Test
    void testRead() throws Exception {
        FileInputStream os = new FileInputStream("testWrite.txt");
        FileChannel channel = os.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(300);

        int read = channel.read(buffer);
        System.out.println(new String(buffer.array()));

        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes, 0, buffer.limit());
        System.out.println("out:" + new String(bytes));
        channel.close();
    }

    @Test
    void testCopy() throws Exception {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("C:\\Users\\xanadu\\Desktop\\test\\send\\2021-08-17_2195893600_watermark.mp4");
        FileChannel inChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("C:\\Users\\xanadu\\Desktop\\test\\send\\" + UUID.randomUUID() + ".mp4");
        FileChannel outChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 200);

        // 使用buffer获取从inChannel传输来的内容
        inChannel.read(buffer);
        // 把buffer设为读模式，方便读取buffer输出到新文件
        buffer.flip();
        // 使用buffer获取需要给outChannel传输的内容
        outChannel.write(buffer);
        long end = System.currentTimeMillis();

        inChannel.close();
        outChannel.close();

        System.out.println("speed:" + (end - start) + "ms");
    }

    @Test
    void testCopy1() throws Exception {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("C:\\Users\\xanadu\\Desktop\\test\\send\\2021-08-17_2195893600_watermark.mp4");
        FileChannel inChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("C:\\Users\\xanadu\\Desktop\\test\\send\\" + UUID.randomUUID() + ".mp4");
        FileChannel outChannel = fos.getChannel();

        /*
        当allocate分配的capacity大小为以下数值时，copy相应花费的时间
        1024*1024*200   459 ms
        1024*1024:      216 ms
        1024:           1933 ms
        100:            16115 ms
        1:              nnnn ms
        结论：分配的空间不是越大越好也不是越小越好，而是需要找到一个较好的大小来配置
         */
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            // 使用buffer获取从inChannel传输来的内容
            // 返回结果为-1说明没有获取到字节数据了，可以结束循环
            if (inChannel.read(buffer) == -1) {
                break;
            }
            // 把buffer设为读模式，方便读取buffer输出到新文件
            buffer.flip();
            // 使用buffer获取需要给outChannel传输的内容
            outChannel.write(buffer);
            // 重复使用buffer需要clear
            buffer.clear();
        }

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("speed:" + (end - start) + "ms");
        fis.close();
        fos.close();
    }

    @Test
    void testMoreChannelRead() throws Exception {
        FileInputStream fis = new FileInputStream("read01.txt");
        FileChannel fisChannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream(UUID.randomUUID().toString().substring(0, 8) + ".txt");
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer buffer1 = ByteBuffer.allocate(5);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);

        ByteBuffer[] buffers = { buffer1, buffer2 };
        fisChannel.read(buffers);
        for (ByteBuffer b : buffers) {
            b.flip();
            // 第一次会输出第一个数组能容纳的空间的数据，如果容纳不了的才会在第二个buffer里面输出
            System.out.println(new String(b.array(), 0, b.remaining()));
        }
        fosChannel.write(buffers);

        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();
    }

    @Test
    // .
    void testMoreChannelWrite() throws Exception {
        FileInputStream fis = new FileInputStream("read01.txt");
        FileOutputStream fos1 = new FileOutputStream(UUID.randomUUID().toString().substring(0, 8) + ".txt");
        FileOutputStream fos2 = new FileOutputStream(UUID.randomUUID().toString().substring(0, 8) + ".txt");
        FileOutputStream fos3 = new FileOutputStream(UUID.randomUUID().toString().substring(0, 8) + ".txt");
        FileOutputStream fos4 = new FileOutputStream(UUID.randomUUID().toString().substring(0, 8) + ".txt");

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileChannel inChannel = fis.getChannel();
        buffer.flip();
        List<FileChannel> list = new ArrayList<>();
        list.add(fos1.getChannel());
        list.add(fos2.getChannel());
        list.add(fos3.getChannel());
        list.add(fos4.getChannel());

        if (inChannel.read(buffer) != -1) {
            list.forEach(e -> {
                System.out.println("++++++++");
                try {
                    buffer.flip();
                    e.write(buffer);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }

    @Test
    void testTransferFrom() throws Exception{
        FileInputStream fis = new FileInputStream("TransferFrom.txt");
        FileChannel fisChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("TransferFrom-Out.txt");
        FileChannel fosChannel = fos.getChannel();

        fosChannel.transferFrom(fisChannel, fisChannel.position(), fisChannel.size());
        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();
    }

    @Test
    void testTransferTo() throws Exception{
        FileInputStream fis = new FileInputStream("TransferTo.txt");
        FileChannel fisChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("TransferTo-Out.txt");
        FileChannel fosChannel = fos.getChannel();

        fisChannel.transferTo(fisChannel.position(), fisChannel.size(), fosChannel);
        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();
    }

    @Test
    void testTransferFrom1() throws Exception{
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("C:\\Users\\xanadu\\Desktop\\test\\send\\test.mp4");
        FileChannel fisChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("C:\\Users\\xanadu\\Desktop\\test\\send\\" + UUID.randomUUID() + ".mp4");
        FileChannel fosChannel = fos.getChannel();

        fosChannel.transferFrom(fisChannel, fisChannel.position(), fisChannel.size());
        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();
        long end = System.currentTimeMillis();
        System.out.println("speed:" + (end - start) + " ms");
    }
}
