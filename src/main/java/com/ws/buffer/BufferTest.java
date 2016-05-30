package com.ws.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * Buffer基本用法
 * 1、写入数据到Buffer
 * 2、调用flip()方法
 * 3、从Buffer中读取数据
 * 4、调用clear()方法或者compact()方法
 * Created by alvin on 2016/5/24.
 */
public class BufferTest {
    public static void main(String[] args) {
        try {
            System.out.println(System.getProperty("user.dir")+"\\readme.txt");
            RandomAccessFile randomAccessFile = new RandomAccessFile(System.getProperty("user.dir")+"\\readme.txt","rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(48);//分配一个新的字节缓冲区
            int bytesReader = fileChannel.read(buffer);//将字节序列从通道读入给定的缓存区
            while(bytesReader != -1){
                System.out.print("Read:" + bytesReader);
                //反转此缓冲区。首先将限制设置为当前位置，然后将位置设置为 0。如果已定义了标记，则丢弃该标记。
                //在一系列通道读取或放置 操作之后，调用此方法为一系列通道写入或相对获取 操作做好准备。
                //将Buffer从写模式切换到读模式
                buffer.flip();
                //告知在当前位置和限制之间是否有元素。
                while(buffer.hasRemaining()){
                    System.out.print( buffer.get());//读取此缓冲区当前位置的字节，然后该位置递增
                }
                buffer.clear();//清除此缓冲区。将位置设置为 0，将限制设置为容量，并丢弃标记
                bytesReader = fileChannel.read(buffer);
            }
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
