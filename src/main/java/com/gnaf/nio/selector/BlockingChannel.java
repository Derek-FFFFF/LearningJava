package com.gnaf.nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Derek
 * @date 2019/6/22 19:54
 */
public class BlockingChannel {

    public void client() throws Exception {

        // 1 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        // 2 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (inChannel.read(buf) != -1) {
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }

        inChannel.close();
        sChannel.close();
    }

    public void server() throws Exception {
        // 1 获取通道 绑定端口
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.bind(new InetSocketAddress(9999));

        // 2 目标地址
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

        // 3 创建缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 4 获取客户端连接通道
        SocketChannel sChannel = ssChannel.accept();

        // 5 保存到本地
        while (sChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        ssChannel.close();
        sChannel.close();
        outChannel.close();
    }

}
