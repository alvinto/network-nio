package com.ws.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * DatagramChannel是一个能收发UDP包的通道。
 * 因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。
 * 它发送和接收的是数据包。
 * Created by wangshuai on 2016/5/30.
 */
public class DatagramChannelTest {
    public static void main(String[] args) {
        try {
            //打开DatagramChannel
            DatagramChannel channel = DatagramChannel.open();
            channel.socket().bind(new InetSocketAddress(9999));

            //接收数据
            ByteBuffer buffer = ByteBuffer.allocate(48);
            buffer.clear();
            //receive()方法会将接收到的数据包内容复制到指定的Buffer. 如果Buffer容不下收到的数据，多出的数据将被丢弃。
            channel.receive(buffer);

            //发送数据
            String newData = "New String to write to file..." + System.currentTimeMillis();
            buffer.clear();
            buffer.put(newData.getBytes());
            buffer.flip();
            int byteSend = channel.send(buffer,new InetSocketAddress("jenkov.com",80));

            //连接到指定的地址
            //可以将DatagramChannel“连接”到网络中的特定地址的。由于UDP是无连接的，
            // 连接到特定地址并不会像TCP通道那样创建一个真正的连接。而是锁住DatagramChannel ，
            // 让其只能从特定地址收发数据。
            channel.connect(new InetSocketAddress("jenkov.com", 80));
            //当连接后，就可以使用read()/write()方法，就像用传统的方法一样
            int byteRead = channel.read(buffer);
            int byteWrite = channel.write(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
