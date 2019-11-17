package com.gnaf.nio.channel;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 如何打开channel
 * @author Derek
 * @date 2019/6/20 21:57
 */
public class OpenChannel {

    /**
     * 方式一：通过IO类的getChannel方式获取Channel
     */
    public static void test1() throws Exception {
        FileInputStream input = new FileInputStream("d:\\1.jpg");
        FileOutputStream output = new FileOutputStream("d:\\2.jpg");

        // 1 获取通道
        FileChannel inChannel = input.getChannel();
        FileChannel outChannel = output.getChannel();

        // 2 分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 3 循环读取并写入
        while (inChannel.read(buffer) != -1) {
            // 切换数据读取模式
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        outChannel.close();
        inChannel.close();
        output.close();
        input.close();
    }

    /**
     * 方式二：通过open方法
     */
    public static void test2() throws Exception {
        FileChannel inChannel = FileChannel.open(Paths.get("d:\\1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("d:\\2.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

        MappedByteBuffer inBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        byte[] buf = new byte[inBuf.limit()];
        inBuf.get(buf);
        outBuf.put(buf);

        inChannel.close();
        outChannel.close();
    }

    /**
     * 方式三：通过FileChannel的open方法
     */
    public static void test3() throws Exception {
        FileChannel inChannel = FileChannel.open(Paths.get("d:\\1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("d:\\2.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

        inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }
}
