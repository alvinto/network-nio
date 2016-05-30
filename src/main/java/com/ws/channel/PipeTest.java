package com.ws.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道。
 * 数据会被写到sink通道，从source通道读取。
 * Created by wangshuai on 2016/5/30.
 */
public class PipeTest {
    public static void main(String[] args) {
        try {
            //打开pipe
            Pipe pipe = Pipe.open();

            //向管道写数据
            //要向管道写数据，需要访问sink通道
            Pipe.SinkChannel sinkChannel = pipe.sink();

            ByteBuffer buffer = ByteBuffer.allocate(48);
            String newData = "New String to write to file..." + System.currentTimeMillis();
            buffer.clear();
            buffer.put(newData.getBytes());
            buffer.flip();
            while(buffer.hasRemaining()){
                sinkChannel.write(buffer);
            }

            //从管道读数据
            //需要访问source通道
            Pipe.SourceChannel sourceChannel = pipe.source();
            buffer.clear();
            int byteRead = sourceChannel.read(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
