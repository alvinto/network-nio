package com.ws.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * SocketChannel是一个连接到TCP网络套接字的通道
 * Created by wangshuai on 2016/5/30.
 */
public class SocketChannelTest {
    public static void main(String[] args) {
        //创建SocketChannel
        SocketChannel socketChannel1 = null;
        try {
            //1.通过打开一个SocketChannel并连接到互联网上的某台服务器 创建
            socketChannel1 = SocketChannel.open();
            socketChannel1.connect(new InetSocketAddress("http://jenkov.com", 80));
            //2.一个新连接到ServerSocketChannel时，会创建一个SocketChannel
            ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));
            //设置为非阻塞模式
            // serverSocketChannel.configureBlocking(false);
            //监听新进来的连接
            while (true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                //do something with socketChannel
                if(socketChannel != null){//非阻塞模式下需要判断

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socketChannel1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
