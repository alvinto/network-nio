package com.ws.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel是一个连接到文件的通道，总是运行在阻塞模式下
 * Created by wangshuai on 2016/5/30.
 */
public class FileChannelTest {
    public static void main(String[] args) {
        try {
            /**读数据开始*/
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"\\readme.txt");
            //RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
            FileChannel channel = inputStream.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = channel.read(buf);
            /**读数据结束*/

            /**向FileChannel写数据开始*/
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            buf.clear();
            byteBuffer.put(newData.getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                channel.write(byteBuffer);
            }
            /**向FileChannel写数据结束*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
