package com.ws.selector;

import java.io.IOException;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。
 * 这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。
 * Created by wangshuai on 2016/5/30.
 */
public class SelectorTest {
    public static void main(String[] args) {
        try {
            //创建Selector
            Selector selector = Selector.open();
            //为了将Channel和Selector配合使用，必须将Channel注册到Selector
            //与Selector一起使用时，Channel必须处于非阻塞模式下。
            // 这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以。
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(false);//设置为非阻塞模式
            //第二个参数是interest集合，表示通过Selector监听Channel时对什么事件感兴趣
            //Connect/Accept/Read/ Write
            SelectionKey key = channel.register(selector,SelectionKey.OP_READ);

            while (true){
                //selector.select()阻塞到至少有一个通道在你注册的事件上就绪了
                int readyChannels = selector.select();
                if(readyChannels == 0){
                    continue;
                }
                //一旦调用了select()方法，并且返回值表明有一个或更多个通道就绪了，
                // 然后可以通过调用selector的selectedKeys()方法，访问“已选择键集（selected key set）”中的就绪通道。
                Set selectedKey = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKey.iterator();
                while (keyIterator.hasNext()){
                    SelectionKey key1 = keyIterator.next();
                    if(key1.isAcceptable()){
                        // a connection was accepted by a ServerSocketChannel.
                    }else if (key1.isConnectable()){
                        // a connection was established with a remote server.
                    }else if (key1.isReadable()){
                        // a channel is ready for reading
                    }else if(key1.isWritable()){
                        // a channel is ready for writing
                    }
                    keyIterator.remove();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
